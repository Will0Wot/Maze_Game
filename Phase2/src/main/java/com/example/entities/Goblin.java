package com.example.entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.example.ai.MovementUtils;
import com.example.ai.Node;
import com.example.ai.Pathfinder;
import com.example.utils.EntityStates;
import com.example.utils.Position;

/**
 * Class representing a Goblin enemy in the game.
 *
 * @author Manya Sharma
 */
public class Goblin extends Enemies implements Mover, Interactable {
    // Constants for the size and offsets of the Goblin's image
    public static final int GOBLIN_SIZE = 192;
    // Constant for the punishment value applied to the Knight
    private final int punishValue = Integer.MIN_VALUE;
    private final Knight player;
    private ArrayList<Node> currentPath;
    private final Position lastKnightPosition;
    private boolean enraged;
    private Rectangle2D.Float enragedRange;
    private final float speed = 1f;
    private int direction = 1;
    private final Pathfinder pathfinder;

    /**
     * Constructs a Goblin object with specified parameters.
     *
     * @param position  The position of the goblin.
     * @param dimension The dimension (size) of the goblin.
     * @param state     The state of the goblin.
     * @param knight    The Knight object.
     */
    public Goblin(Position position, Dimension dimension, EntityStates state, GameImage gameImage, Knight knight, Pathfinder pathfinder) {
        super(position, dimension, state, gameImage);
        initializeCollisionBox();
        currentPath = new ArrayList<>();
        this.player = knight;
        this.lastKnightPosition = new Position(knight.getCollisionBox().x, knight.getCollisionBox().y);
        this.pathfinder = pathfinder;
        initializeEnrageRange();
    }


    private void initializeEnrageRange() {
        this.enraged = false;
        final int nodeSize = Node.getDEFAULT_SIZE();
        float x = collisionBox.getCurrentXPos();
        float y = collisionBox.getCurrentYPos();
        this.enragedRange = new Rectangle2D.Float(x - 5 * nodeSize / 2f, y - 5 * nodeSize / 2f, nodeSize * 5, nodeSize * 5);
    }

    @Override
    public void update() {
        // Update the Goblin's position and animations
        updateEntityPosition();
        updateState();
        updateAnimation(getState().ordinal());
    }

    @Override
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 45;
        final int IMAGE_Y_OFFSET = 50;
        final int IMAGE_WIDTH = 35;
        final int IMAGE_HEIGHT = 35;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        // Render the Goblin's current animation frame with an offset
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        x += (direction < 0 ? 128 : 0);
        BufferedImage image = animation.getCurrentImage();
        g.drawImage(image, x, y, direction * 128, 128, null);
    }
    @Override
    protected void updateState() {
        if (enraged) {
            setState(EntityStates.MOVING);
        } else {
            setState(EntityStates.IDLE);
        }
    }

    /**
     * Updates the position of the goblin entity.
     */
    @Override
    public void updateEntityPosition() {
        final int nodeSize = Node.getDEFAULT_SIZE();
        final int goblinX = (int) collisionBox.getCurrentXPos();
        final int goblinY = (int) collisionBox.getCurrentYPos();
        final int lastKnightX = (int) lastKnightPosition.getX();
        final int lastKnightY = (int) lastKnightPosition.getY();

        if (enraged) {
            // // same xcell and same ycell are calculating if player has moved to different cell of the board.
            boolean sameXCell = lastKnightX / nodeSize == goblinX / nodeSize;
            boolean sameYCell = lastKnightY / nodeSize == goblinY / nodeSize;
            if (currentPath.isEmpty() || !(sameXCell && sameYCell)) {
                // it checks if the current path is empty or the knight has moved from where goblin was last calculated
                Node enemyNode = new Node(goblinY / nodeSize, goblinX / nodeSize);
                final int newKnightX = (int) player.getCurrentPosition().getX();
                final int newKnightY = (int) player.getCurrentPosition().getY();
                Node playerNode = new Node(newKnightY / nodeSize, newKnightX / nodeSize);
                enemyNode.setCost(0, MovementUtils.getHeuristic(enemyNode, playerNode));
                currentPath = pathfinder.getPath(enemyNode, playerNode);
            }
            Node next = (currentPath.isEmpty() ? null : currentPath.get(0));
            if (next != null) {
                // payer are in the same node, move straight to the player. follow the most optimal path to the next cell otherwise.
                boolean shareXPos = goblinX / nodeSize == (int) lastKnightPosition.getX() / nodeSize;
                boolean shareYPos = goblinY / nodeSize == (int) lastKnightPosition.getY() / nodeSize;
                // if in the same cell, move directly to the player.
                if (shareXPos && shareYPos)
                    moveToPlayer();
                else {
                    followPath(next);
                }
            }
            lastKnightPosition.setX(player.getCollisionBox().x);
            lastKnightPosition.setY(player.getCollisionBox().y);
        }
    }

    /**
     * Moves the Goblin along a predefined path.
     *
     * @param next The next node in the path to follow.
     */
    private void followPath(Node next) {
        if (currentPath != null && !currentPath.isEmpty()) {
            final int nodeSize = Node.getDEFAULT_SIZE();
            // Convert Node's grid position to actual coordinates. Division by 2 for moving to center of tiles.
            float targetX = next.getX() * nodeSize + nodeSize / 2;
            float targetY = next.getY() * nodeSize + nodeSize / 2;
            float x = collisionBox.getCurrentXPos();
            float y = collisionBox.getCurrentYPos();
            // Move towards the target position
            if (x < targetY) {
                collisionBox.setCurrentXPos(x + speed);
                currentPosition.setX(x + speed);
                enragedRange.x += speed;
                direction = 1;
            } else if (x > targetY) {
                collisionBox.setCurrentXPos(x - speed);
                currentPosition.setX(x - speed);
                enragedRange.x -= speed;
                direction = -1;
            }

            if (y < targetX) {
                collisionBox.setCurrentYPos(y + speed);
                currentPosition.setY(y + speed);
                enragedRange.y += speed;
            } else if (y > targetX) {
                collisionBox.setCurrentYPos(y - speed);
                currentPosition.setY(y - speed);
                enragedRange.y -= speed;
            }
            if (y == (int) targetX && x == (int) targetY) {
                currentPath.remove(0);
            }
        }
    }

    /**
     * Moves the Goblin towards the player.
     */
    private void moveToPlayer() {
        float x = collisionBox.getCurrentXPos();
        float y = collisionBox.getCurrentYPos();
        float xOffset = player.getCollisionBox().x - x;
        float yOffset = player.getCollisionBox().y - y;
        // player below goblin
        if (xOffset > 0) {
            collisionBox.setCurrentXPos(x + speed);
            currentPosition.setX(x + speed);
            enragedRange.x += speed;
            direction = 1;
        }
        //goblin above player
        if (xOffset < 0) {
            collisionBox.setCurrentXPos(x - speed);
            currentPosition.setX(x - speed);
            enragedRange.x -= speed;
            direction = -1;
        }
        if (yOffset > 0) {
            collisionBox.setCurrentYPos(y + speed);
            currentPosition.setY(y + speed);
            enragedRange.y += speed;
        }
        if (yOffset < 0) {
            collisionBox.setCurrentYPos(y - speed);
            currentPosition.setY(y - speed);
            enragedRange.y -= speed;
        }
    }

    /**
     * Checks if the Goblin is currently enraged.
     *
     * @return True if the Goblin is enraged, false otherwise.
     */
    public boolean isEnraged() {
        return enraged;
    }

    /**
     * Sets the enraged state of the Goblin.
     *
     * @param enraged True to set the Goblin as enraged, false otherwise.
     */
    public void setEnraged(boolean enraged) {
        this.enraged = enraged;
    }

    /**
     * Retrieves the range within which the Goblin becomes enraged.
     *
     * @return The rectangular area representing the range of the Goblin's rage.
     */
    public Rectangle2D.Float getEnragedRange() {
        return enragedRange;
    }

    @Override
    // Interact with the knight by applying punishment
    public void interact(Knight knight) {
        knight.incrementScore(punishValue);
    }

    @Override
    // Return the punishment value
    public int getInteractableAmount() {
        return punishValue;
    }
}

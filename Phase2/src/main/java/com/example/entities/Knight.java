package com.example.entities;

import com.example.gamestate.Play;
import com.example.utils.Directions;
import com.example.utils.EntityStates;
import com.example.utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Knight extends AnimatedGameEntity implements Mover {
    public final static int KNIGHT_SIZE = 192;
    private final float knightSpeed = 1.25f;
    private int directionMultiplier = 1;
    private HashMap<Directions, Boolean> movementDirections;
    private boolean currentlyMoving;
    private Play play;
    private int score = 0;
    private int mandatoryRewardsCollected = 0;

    /**
     * Constructs a Knight object with the given parameters.
     *
     * @param pos       initial position of the knight
     * @param dim       dimensions of the knight
     * @param state     initial state of the knight
     * @param gameImage The Image the entity should use for drawing itself.
     * @param play      the play instance associated with the knight
     */
    public Knight(Position pos, Dimension dim, EntityStates state, GameImage gameImage, Play play) {
        super(pos, dim, state, gameImage);
        currentlyMoving = false;
        initializeMovementDirections();
        initializeCollisionBox();
        this.play = play;
    }

    @Override
    public void update() {
        updateEntityPosition();
        updateState();
        updateAnimation(getState().ordinal());
    }

    @Override
    protected void initializeCollisionBox() {
        final int IMAGE_X_OFFSET = 45;
        final int IMAGE_Y_OFFSET = 53;
        final int IMAGE_WIDTH = 40;
        final int IMAGE_HEIGHT = 31;
        Position pos = new Position(currentPosition.getX() + IMAGE_X_OFFSET, currentPosition.getY() + IMAGE_Y_OFFSET);
        Dimension dim = new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
        collisionBox = new CollisionBox(pos, dim, IMAGE_X_OFFSET, IMAGE_Y_OFFSET);
    }

    @Override
    public void render(Graphics g, int xOffset, int yOffset) {
        int x = (int) collisionBox.getCurrentXPos() - collisionBox.getxRenderDifference() - xOffset;
        int y = (int) collisionBox.getCurrentYPos() - collisionBox.getyRenderDifference() - yOffset;
        x += (directionMultiplier < 0 ? 128 : 0);
        BufferedImage image = animation.getCurrentImage();
        g.drawImage(image, x, y, directionMultiplier * 128, 128, null);
    }

    @Override
    public void updateEntityPosition() {
        currentlyMoving = false;
        if (!anyDirectionActive())
            return;
        float xPositionOffset = 0;
        float yPositionOffset = 0;
        float currentCollisionBoxX = collisionBox.getCurrentXPos();
        float currentCollisionBoxY = collisionBox.getCurrentYPos();
        if (isDirectionActive(Directions.LEFT)) {
            directionMultiplier = -1;
            xPositionOffset -= knightSpeed;
        }
        if (isDirectionActive(Directions.RIGHT)) {
            directionMultiplier = 1;
            xPositionOffset += knightSpeed;
        }
        if (isDirectionActive(Directions.UP)) {
            yPositionOffset -= knightSpeed;
        }
        if (isDirectionActive(Directions.DOWN)) {
            yPositionOffset += knightSpeed;
        }
        float newXPos = currentCollisionBoxX + xPositionOffset;
        float newYPos = currentCollisionBoxY + yPositionOffset;
        if (canMoveToPosition(newXPos, newYPos)) {
            updatePositionAndCollisionBox(newXPos, newYPos);
            currentlyMoving = true;
        }
    }

    private boolean anyDirectionActive() {
        return movementDirections.values().stream().anyMatch(Boolean::booleanValue);
    }

    private boolean isDirectionActive(Directions direction) {
        return movementDirections.get(direction);
    }

    private boolean canMoveToPosition(float newXPos, float newYPos) {
        float width = collisionBox.getWidth();
        float height = collisionBox.getHeight();
        return play.canPlayerMove(newXPos, newYPos, (int) width, (int) height);
    }

    private void updatePositionAndCollisionBox(float newXPos, float newYPos) {
        collisionBox.setCurrentXPos(newXPos);
        collisionBox.setCurrentYPos(newYPos);
        currentPosition.setX(newXPos);
        currentPosition.setY(newYPos);
    }

    private void initializeMovementDirections() {
        movementDirections = new HashMap<>();
        movementDirections.put(Directions.LEFT, false);
        movementDirections.put(Directions.RIGHT, false);
        movementDirections.put(Directions.UP, false);
        movementDirections.put(Directions.DOWN, false);
    }

    @Override
    protected void updateState() {
        if (currentlyMoving) {
            setState(EntityStates.MOVING);
        } else {
            setState(EntityStates.IDLE);
        }
    }

    public void setActiveDirection(Directions direction, boolean value) {
        movementDirections.put(direction, value);
    }

    public void incrementMandatoryRewardCount() {
        this.mandatoryRewardsCollected++;
    }

    public int getMandatoryRewardCount() {
        return this.mandatoryRewardsCollected;
    }

    public void incrementScore(int updatedScore) {
        this.score += updatedScore;
    }

    public int getScore() {
        return score;
    }

    public boolean isDead() {
        return score < 0;
    }

    public void reset() {
        score = 0;
        mandatoryRewardsCollected = 0;
        this.setState(EntityStates.IDLE);
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}

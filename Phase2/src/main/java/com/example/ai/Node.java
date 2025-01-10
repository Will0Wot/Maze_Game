package com.example.ai;

import java.util.Objects;

/**
 * Represents a node in a grid for pathfinding.
 *
 * @author Manya Sharma
 */
public class Node implements Comparable<Node> {
    private final int x;// X coordinate of the node
    private final int y; // Y coordinate of the node
    private float gCost; // Cost from the starting node to this node
    private float hCost; // Heuristic cost from this node to the target node
    private float fCost; // Total cost of the node (gCost + hCost)
    private Node parent; // Parent node in the path
    private static final int DEFAULT_SIZE = 125;

    /**
     * Constructs a node with the specified coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the cost of reaching this node and the heuristic cost from this node to the target node.
     *
     * @param gCost The cost from the starting node to this node.
     * @param hCost The heuristic cost from this node to the target node.
     */
    public void setCost(int gCost, int hCost) {
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }

    /**
     * Sets the parent node in the path.
     *
     * @param parent The parent node.
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Gets the X coordinate of the node.
     *
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y coordinate of the node.
     *
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the parent node in the path.
     *
     * @return The parent node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Gets the cost from the starting node to this node.
     *
     * @return The cost from the starting node to this node.
     */
    public float getGCost() {
        return gCost;
    }

    /**
     * Gets the heuristic cost from this node to the target node.
     *
     * @return The heuristic cost from this node to the target node.
     */
    public float getHCost() {
        return hCost;
    }

    /**
     * Gets the total cost of the node (gCost + hCost).
     *
     * @return The total cost of the node.
     */
    public float getFCost(){
        return  fCost;
    }

    /**
     * Compares this node with another node based on their F costs.
     * If F costs are equal, compares them based on their G costs.
     *
     * @param other The other node to compare with.
     * @return A negative integer, zero, or a positive integer if this node is less than, equal to, or greater than the other node.
     */
    @Override
    public int compareTo(Node other) {
        // Compare nodes by F cost, then by G cost if F costs are equal
        int fCost = (int)getFCost();
        int otherFCost = (int)other.getFCost();
        if (fCost == otherFCost)
            return Integer.compare((int)getGCost(), (int)other.getGCost());
        return Integer.compare(fCost, otherFCost);
    }

    /**
     * Checks if this node is equal to another object.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node node = (Node) obj;
        return this.x == node.getX() && this.y == node.getY();
    }

    public static int getDEFAULT_SIZE() {
        return DEFAULT_SIZE;
    }
}
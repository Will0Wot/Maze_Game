package com.example.ai;

import java.util.ArrayList;
/**
 * Utility class for movement-related operations.
 *
 * @author Manya Sharma
 */

public class MovementUtils {
    /**
     * Calculates the heuristic value between two nodes.
     *
     * @param node1 The starting node.
     * @param node2 The target node.
     * @return The heuristic value between the two nodes.
     */
    public static int getHeuristic(Node node1, Node node2) {
        int cellPosDiff = node1.getX() - node2.getX();
        int rowPosDiff = node1.getY() - node2.getY();
        return Math.abs(cellPosDiff) + Math.abs(rowPosDiff);
    }

    /**
     * Produces the path from the target node to the starting node.
     *
     * @param target The target node.
     * @return The path from the target node to the starting node.
     */
    public static ArrayList<Node> producePath(Node target) {
        ArrayList<Node> path = new ArrayList<>();
        Node currentNode = target;
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = currentNode.getParent();
        }
        // Remove the initial node to prevent moving backwards.
        if(path.size() > 1){
            path.remove(0);
        }
        return path;
    }
}
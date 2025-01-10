package com.example.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Pathfinder {

    private int [][] map;

    public Pathfinder(int [][] map) {
        this.map = map;
    }

    /**
     * Finds a path using A* algorithm from start to goal node on the map.
     *
     * @param start The starting node.
     * @param goal The goal node.
     * @return The path from start to goal node.
     */
    public ArrayList<Node> getPath(Node start, Node goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashSet<Node> explored = new HashSet<>();
        frontier.add(start);
        //optimal nodes are still there. examine the node by pulling it.
        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            explored.add(currentNode);
            // found path to goal node. Stop.
            if (currentNode.equals(goal)) {
                return MovementUtils.producePath(currentNode);
            }
            // examining cells possible to move to adjacent to the current node.
            for (Node adjacent : getNeighbors(currentNode, map)) {
                // already looked at cell, dont look at it again.
                if (explored.contains(adjacent))
                    continue;
                // cost from the start node to this adjacent node.
                float newGCost = currentNode.getGCost() + 1;
                // heuristic cost from current cell to goal
                float newHCost = MovementUtils.getHeuristic(adjacent, goal);
                adjacent.setCost((int) newGCost, (int) newHCost);
                adjacent.setParent(currentNode);
                if (!frontier.contains(adjacent)) {
                    frontier.add(adjacent);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Gets the neighboring nodes of a given node on the map.
     *
     * @param node The node whose neighbors are to be found.
     * @param map The game map.
     * @return List of neighboring nodes.
     */
    private ArrayList<Node> getNeighbors(Node node, int[][] map) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] direction : directions) {
            int newX = node.getX() + direction[0];
            int newY = node.getY() + direction[1];
            boolean test = map[newX][newY] != 1 && map[newX][newY] != 3;
            // Check boundaries and ensure the tile is not an obstacle
            if (newX >= 0 && newY >= 0 && newX < map.length && newY < map[0].length && test) {
                neighbors.add(new Node(newX, newY));
            }
        }
        return neighbors;
    }

}

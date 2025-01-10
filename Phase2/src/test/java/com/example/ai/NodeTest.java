package com.example.ai;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class NodeTest {
    private Node node;
    @BeforeEach
    void setup(){
        node = new Node(125, 125);
    }
    @Test
    public void Node() {
        Node expected = new Node(125, 125);
        assertEquals(expected, node);
    }


    @Test
    public void setCost() {
        float oldG = node.getGCost();
        float oldH = node.getHCost();
        node.setCost(120, 120);
        assertNotEquals(oldG, node.getGCost());
        assertNotEquals(oldH, node.getHCost());
    }


    @Test
    public void setParent() {
        Node n = new Node(123, 123);
        Node parent = new Node(123, 123);
        n.setParent(parent);
        assertEquals(n.getParent(), parent);
    }


    @Test
    public void getX() {
        int expected = 125;
        int actual = node.getX();
        assertEquals(expected, actual);
    }


    @Test
    public void getGCost() {


        float expected = 0;
        float actual = node.getGCost();
        assertEquals(expected, actual, 0.0000001F);
        node.setCost(10, 10);
        assertNotEquals(expected, node.getGCost());
    }


    @Test
    public void getHCost() {
        float expected = 0;
        float actual = node.getHCost();
        assertEquals(expected, actual, 0.0000001F);
        node.setCost(10, 10);
        assertNotEquals(expected, node.getHCost());
    }


    @Test
    public void getFCost() {
        float fCost = 20;
        node.setCost(10, 10);
        assertEquals(fCost, node.getFCost(), 0.0000001F);
    }


    @Test
    public void compareTo() {
        Node n1 = new Node(1, 1);
        n1.setCost(1, 1);
        Node n2 = new Node(200, 200);
        n2.setCost(10, 10);
        Node n3 = new Node(125, 125);
        n3.setCost(5, 5);
        node.setCost(5, 5);
        int expected = 1;
        int actual = node.compareTo(n1);
        assertEquals(expected, actual);
        expected = -1;
        actual = node.compareTo(n2);
        assertEquals(expected, actual);
        expected = 0;
        actual = node.compareTo(n3);
        assertEquals(expected, actual);
    }




}

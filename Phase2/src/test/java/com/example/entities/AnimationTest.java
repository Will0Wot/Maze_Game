package com.example.entities;

import com.example.utils.ResourceLoader;
import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {
    BufferedImage image;
    GameImage gameImage;
    Animation animation;
    @BeforeEach
    public void setup(){
        try {
            image = ResourceLoader.getImage(ResourceLoader.KNIGHT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameImage = new GameImage(image, Knight.KNIGHT_SIZE, Knight.KNIGHT_SIZE);
        animation = new Animation(gameImage);
    }

    @Test
    public void updateTest() {
        int index = 1;
        animation.update(index);
        assertEquals(animation.getCurrentAnimationType(), 1);
    }

    @Test
    public void fullUpdateTest(){
        int expectedFrame = animation.getCurrentFrame();
        for (int i = 0; i < 144; i++) {
            animation.update(0);
        }
        assertEquals(expectedFrame, animation.getCurrentFrame());
    }
    @Test
    public void updateAnimationTypeTest() {
        BufferedImage image = animation.getCurrentImage();
        animation.setCurrentFrame(1);
        animation.setCurrentAnimationType(1);
        BufferedImage newImage = animation.getCurrentImage();
        assertNotEquals(image, newImage);
    }

    @Test
    public void getCurrentAnimationTypeTest() {
        int expected = 0;
        int actual = animation.getCurrentAnimationType();
        assertEquals(expected, actual);
    }

    @Test
    public void setCurrentAnimationTypeTest() {
        int currentAnimationType = 123;
        animation.setCurrentAnimationType(currentAnimationType);
        assertEquals(animation.getCurrentAnimationType(), currentAnimationType);
    }

    @Test
    public void getCurrentFrameTest() {
        int expected = 0;
        int actual = animation.getCurrentFrame();
        assertEquals(expected, actual);
    }

    @Test
    public void setCurrentFrameTest() {
        int currentFrame = 123;
        animation.setCurrentFrame(currentFrame);
        assertEquals(123, animation.getCurrentFrame());
    }

    @Test
    public void getAnimationSpeedTest() {
        int expected = 12;
        int actual = animation.getAnimationSpeed();
        assertEquals(expected, actual);
    }

    @Test
    public void setAnimationSpeedTest() {
        int animationSpeed = 123;
        animation.setAnimationSpeed(animationSpeed);
        assertEquals(animationSpeed, animation.getAnimationSpeed());
    }

    @Test
    public void getANIMATION_DEFAULT_SPEED() {
        int expected = 144;
        int actual = animation.getANIMATION_DEFAULT_SPEED();
        assertEquals(expected, actual);
    }
}

package com.example.entities;

import java.awt.image.BufferedImage;

/**
 * Represents an animation handler that manages multiple animation types and frames from a sprite sheet.
 * This class requires a {@link GameImage} object to extract sub-images for each animation frame.
 * It supports updating the current frame based on a speed threshold and changing the type of animation
 * being displayed.</p>
 *
 * @author Pardeep Singh Manhas
 */
public class Animation {

    /**
     * Matrix of images representing the animations frames for each type.
     */
    private final BufferedImage[][] animations;
    /**
     * Number of animation types. The number of rows in the sprite sheet.
     */
    private final int animationTypes;
    /**
     * Currently active animation type index.
     */
    private int currentAnimationType;
    /**
     * Number of frames per animation type. The number of columns in the sprite sheet.
     */
    private final int frames;
    /**
     * Index of the currently displayed frame within the animation type.
     */
    private int currentFrame;
    /**
     * Counter to track updates and determine when to advance frames.
     */
    private int animationCounter;
    /**
     * Speed at which the animation updates (lower is faster).
     */
    private int animationSpeed;
    private final int ANIMATION_DEFAULT_SPEED = 144;

    /**
     * Initializes a new Animation object with a specified image source for sprite frames.
     * This constructor splits the provided image into sub-images according to the animation types and frames. It is
     * assumed that there are 2 animation types, and 6 frames, as that is the standard for the asset pack utilized.
     *
     * @param image The source {@link GameImage} containing sprite sheet.
     * @author Pardeep Singh Manhas
     */
    public Animation(GameImage image) {
        this.animationTypes = 2;
        this.frames = 6;
        this.animationCounter = 0;
        this.currentFrame = 0;
        this.currentAnimationType = 0;
        this.animationSpeed = ANIMATION_DEFAULT_SPEED / (frames * 2);
        this.animations = new BufferedImage[animationTypes][frames];
        initializeAnimations(image);
    }


    /**
     * Initializes the animations matrix using the provided {@link GameImage}.
     * Each animation type and frame is extracted from the main image as a sub-image.
     *
     * @param image The game image used to extract sub-images for animations.
     * @author Pardeep Singh Manhas
     */
    private void initializeAnimations(GameImage image) {
        BufferedImage trueImage = image.getImage();
        int width = image.getImageWidth();
        int height = image.getImageHeight();
        for (int i = 0; i < animationTypes; i++) {
            for (int j = 0; j < frames; j++) {
                animations[i][j] = trueImage.getSubimage(width * j, height * i, width, height);
            }
        }
    }


    /**
     * Updates the animation by advancing the current frame. Potentially changes the animation type based on the specified index
     * , depending on the requirements of some Entity.
     *
     * @param index The index of the animation type to potentially switch to.
     * @author Pardeep Singh Manhas
     */
    public void update(int index) {
        updateCurrentAnimation();
        updateAnimationType(index);
    }

    /**
     * Advances the animation based on the animation speed and current frame. After the current counter has been updated
     * enough times, the currentFrame will be incremented the next image in the provided {@link  GameImage} sprite sheet.
     * Loops through the sprite sheet if called enough times. Should be called repeatedly to animate images.
     *
     * @author Pardeep Singh Manhas
     */
    private void updateCurrentAnimation() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentFrame++;
            if (currentFrame == frames) {
                currentFrame = 0;
            }
        }
    }

    /**
     * Updates the current animation type to the given index if it is valid.
     *
     * @param index The new animation type index to be set, if within valid range.
     * @author Pardeep Singh Manhas
     */
    public void updateAnimationType(int index) {
        if (index < 0 || index >= animationTypes) {
            return;
        }
        this.currentAnimationType = index;
    }


    /**
     * Retrieves the currently displayed frame as an image. Can be used to draw the current image being animated.
     *
     * @return The current animation frame as a {@link BufferedImage}.
     * @author Pardeep Singh Manhas
     */
    public BufferedImage getCurrentImage() {
        return animations[currentAnimationType][currentFrame];
    }

    /**
     * Returns the current animation type index.
     *
     * @return the index of the currently active animation type.
     * @author Pardeep Singh Manhas
     */

    public int getCurrentAnimationType() {
        return currentAnimationType;
    }


    /**
     * Sets the current animation type to the specified index. This index should be within the range of available animation types.
     *
     * @param currentAnimationType The new animation type index to set, should be a valid index within the bounds.
     * @author Pardeep Singh Manhas
     */
    public void setCurrentAnimationType(int currentAnimationType) {
        this.currentAnimationType = currentAnimationType;
    }

    /**
     * Returns the current frame index within the active animation type.
     *
     * @return the index of the currently displayed frame within the animation sequence.
     * @author Pardeep Singh Manhas
     */

    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Sets the current frame to the specified index. This index should be within the range of frames for the current animation type.
     *
     * @param currentFrame The new frame index to set, should be a valid index within the bounds of the animation frames.
     * @throws IllegalArgumentException if the frame index is out of range (less than 0 or greater than the number of frames in the animation type).
     * @author Pardeep Singh Manhas
     */
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    /**
     * Returns the current speed of the animation updates. Lower values result in faster animation.
     *
     * @return the current speed of the animation updates.
     * @author Pardeep Singh Manhas
     */
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    /**
     * Sets the speed of the animation updates. Lower values will make the animation play faster.
     *
     * @param animationSpeed The new speed at which the animation should update.
     * @author Pardeep Singh Manhas
     */
    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    /**
     * Returns the default speed of the animation defined in the system. Default value is 144.
     *
     * @return the default animation speed as an integer value.
     * @author Pardeep Singh Manhas
     */
    public int getANIMATION_DEFAULT_SPEED() {
        return ANIMATION_DEFAULT_SPEED;
    }

}

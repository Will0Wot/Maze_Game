package com.example.entities;

import java.awt.*;

import com.example.utils.EntityStates;
import com.example.utils.Position;

public abstract class Reward extends GameEntity {
    public Reward(Position position, Dimension dimension, GameImage image, EntityStates state) {
        super(position, dimension, image, state);
    }
}

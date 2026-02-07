package com.amar.entity;

import com.amar.core.Camera;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends Entity {

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        // walls don't move
    }

    @Override
    public void render(GraphicsContext gc, Camera camera) {
        gc.fillRect(
                x - camera.getX(),
                y - camera.getY(),
                width,
                height
        );
    }
}
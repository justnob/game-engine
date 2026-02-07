package com.amar.entity;

import com.amar.core.Camera;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    public double x;
    public double y;
    public double width;
    public double height;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc, Camera camera);
}
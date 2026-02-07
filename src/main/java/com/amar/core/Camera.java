package com.amar.core;

public class Camera {

    private double x, y;
    private final double viewportWidth;
    private final double viewportHeight;

    public Camera(double viewportWidth, double viewportHeight) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
    }

    public void follow(double targetX, double targetY, double targetWidth, double targetHeight) {
        x = targetX + targetWidth / 2 - viewportWidth / 2;
        y = targetY + targetHeight / 2 - viewportHeight / 2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

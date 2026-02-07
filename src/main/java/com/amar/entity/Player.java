package com.amar.entity;

import com.amar.core.AssetManager;
import com.amar.core.Camera;
import com.amar.input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class Player extends Entity {

    private final double speed = 3;
    private final List<Wall> walls;

    public Player(double x, double y, List<Wall> walls) {
        super(x, y, 40, 40);
        this.walls = walls;
    }

    @Override
    public void update() {
        double dx = 0, dy = 0;

        if (Input.isKeyPressed(KeyCode.A)) dx -= speed;
        if (Input.isKeyPressed(KeyCode.D)) dx += speed;
        if (Input.isKeyPressed(KeyCode.W)) dy -= speed;
        if (Input.isKeyPressed(KeyCode.S)) dy += speed;

        move(dx, dy);
    }

    private void move(double dx, double dy) {
        x += dx;
        if (collides()) x -= dx;

        y += dy;
        if (collides()) y -= dy;
    }

    private boolean collides() {
        for (Wall wall : walls) {
            if (getBounds().intersects(wall.getBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext gc, Camera camera) {

        Image img = AssetManager.getImage("player");

        if (img != null) {
            gc.drawImage(
                    img,
                    x - camera.getX(),
                    y - camera.getY(),
                    width,
                    height
            );
        } else {
            gc.fillRect(
                    x - camera.getX(),
                    y - camera.getY(),
                    width,
                    height
            );
        }
    }
}

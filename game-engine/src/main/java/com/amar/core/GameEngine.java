package com.amar.core;

import com.amar.entity.Player;
import com.amar.entity.Wall;
import com.amar.input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameEngine extends Application {

    private Player player;
    List<Wall> walls = new ArrayList<>();
    private Camera camera;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        walls.add(new Wall(200, 150, 200, 30));
        walls.add(new Wall(100, 300, 30, 200));
        walls.add(new Wall(400, 400, 250, 30));

        player = new Player(50, 50, walls);
        camera = new Camera(800, 600);

        AssetManager.loadImageAsync(
                "player",
                "/assets/player.png"
        );

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(Input::onKeyPressed);
        scene.setOnKeyReleased(Input::onKeyReleased);

        stage.setTitle("Java Game Engine");
        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {

            private long lastTime = 0;
            private int frames = 0;
            private int fps = 0;
            private long lastFpsTime = 0;

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                    lastFpsTime = now;
                    return;
                }

                update();

                gc.setFill(javafx.scene.paint.Color.BLACK);
                gc.clearRect(0, 0, 800, 600);
                render(gc);

                frames++;
                if (now - lastFpsTime >= 1_000_000_000L) {
                    fps = frames;
                    frames = 0;
                    lastFpsTime = now;
                }

                lastTime = now;

                gc.fillText("FPS: " + fps, 10, 20);
            }
        }.start();
    }

    private void update() {
        player.update();
        camera.follow(player.x, player.y, player.width, player.height);
    }

    private void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);

        for (Wall wall : walls) {
            wall.render(gc, camera);
        }

        player.render(gc, camera);
    }
}

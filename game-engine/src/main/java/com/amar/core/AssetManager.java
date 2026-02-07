package com.amar.core;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.*;

public class AssetManager {

    private static final ExecutorService executor =
            Executors.newVirtualThreadPerTaskExecutor();

    private static final Map<String, Image> images = new ConcurrentHashMap<>();

    public static void loadImageAsync(String key, String path) {
        executor.submit(() -> {
            try (InputStream is = AssetManager.class.getResourceAsStream(path)) {
                if (is == null) {
                    throw new RuntimeException("Image not found: " + path);
                }
                Image image = new Image(is);
                images.put(key, image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static Image getImage(String key) {
        return images.get(key);
    }

    public static boolean isLoaded(String key) {
        return images.containsKey(key);
    }

}

package com.amar.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class Input {

    private static final Set<KeyCode> keys = new HashSet<>();

    public static void onKeyPressed(KeyEvent e) {
        keys.add(e.getCode());
    }

    public static void onKeyReleased(KeyEvent e) {
        keys.remove(e.getCode());
    }

    public static boolean isKeyPressed(KeyCode key) {
        return keys.contains(key);
    }
}

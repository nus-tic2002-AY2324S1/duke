package com.tina.service;

import com.tina.Tina;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Tina.class, args);
    }
}
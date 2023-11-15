package com.tina.app;

import com.tina.GUI.MainWindow;
import com.tina.service.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    private final Tina tina = new Tina();

    @Override
    public void start(Stage stage) throws com.tina.exception.IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            tina.initiate();
            fxmlLoader.<MainWindow>getController().setTina(tina);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

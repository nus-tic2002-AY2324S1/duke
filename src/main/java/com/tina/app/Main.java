package com.tina.app;

import com.tina.GUI.MainWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Starts the application with stage.
 */
public class Main extends Application {

    private final Tina tina = new Tina();

    private final Image icon = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    /**
     * Starts the application with stages.
     *
     * @param stage the primary stage for this application, onto which

     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(icon);
            stage.setTitle("Tina");

            fxmlLoader.<MainWindow>getController().setTina(tina);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

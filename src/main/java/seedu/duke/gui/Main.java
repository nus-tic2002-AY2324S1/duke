package seedu.duke.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.Duke;

/**
 * Manages the GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            MainWindow mw = fxmlLoader.getController();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            try {
                Duke.main(new String[0]);
                mw.startUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

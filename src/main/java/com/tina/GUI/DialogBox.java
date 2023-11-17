package com.tina.GUI;

import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Represents a Dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    @FXML
    public void initialize() {

    }

    /**
     * Creates a DialogBox.
     * Loads components from fxml file.
     *
     * @param text text to be showed.
     * @param img  images to be showed.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets user dialog.
     *
     * @param text the text.
     * @param img  the img.
     * @return the user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets tina dialog.
     *
     * @param text the text.
     * @param img  the img.
     * @return the tina dialog.
     */
    public static DialogBox getTinaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getChildren();
        db.setTextAlignment(TextAlignment.LEFT);
        return db;
    }

    /**
     * Sets text alignment.
     *
     * @param alignment the alignment.
     */
    public void setTextAlignment(TextAlignment alignment) {
        dialog.setTextAlignment(alignment);
    }
}
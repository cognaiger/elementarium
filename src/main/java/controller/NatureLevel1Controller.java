package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.SQLException;

public class NatureLevel1Controller extends DragAndDropWindow {
    @FXML
    private FontAwesomeIconView lookupIcon;

    private boolean isSearchBoxVisible = false;

    @FXML
    private VBox searchBox;

    @FXML
    private TextField searchField;


    public NatureLevel1Controller() throws SQLException, ClassNotFoundException {
        comRes = data.getCombinations();
        elements = data.getAllElements();
    }

    @FXML
    private void handleLookupClick(MouseEvent event) {
        if (isSearchBoxVisible) {
            hideSearchBox();
        } else {
            if (searchField != null) {
                String text = searchField.getText();
                System.out.println(text);
            }
            showSearchBox();
        }
    }

    private void showSearchBox() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), searchBox);
        transition.setToY(0);
        transition.play();
        isSearchBoxVisible = true;
    }

    private void hideSearchBox() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), searchBox);
        transition.setToY(-searchBox.getHeight());
        transition.play();
        isSearchBoxVisible = false;
    }

    public void handleEnterKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (searchField.getText() != null) {
                String searchText = searchField.getText();
                System.out.println("Search Text: " + searchText);
            }
        }
    }
}

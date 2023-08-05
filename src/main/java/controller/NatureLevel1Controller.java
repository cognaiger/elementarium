package controller;


import elementarium.utils.InitialNumberElement;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NatureLevel1Controller extends DragAndDropWindow {

    private final List<Integer> currentIdInBar = new ArrayList<>();

    @FXML
    private ImageView lookupIcon;

    private boolean isSearchBoxVisible = false;

    @FXML
    private VBox searchBox;

    @FXML
    private TextField searchField;

    public NatureLevel1Controller() throws SQLException, ClassNotFoundException {
        super();
        initialNumberElements = InitialNumberElement.NATURE_LEVEL_1;
        for (int i = 1; i <= 7; i++) {
            inBar[i] = true;
            bar.add(i);
            currentIdInBar.add(i);
        }
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
        searchField.setOnKeyTyped(event -> {
            String searchText = searchField.getText();
            if (searchText == "") {
                System.out.println(listViewText.getItems().size() + "SIZE ");
                for (int i : bar) {
                    if (!currentIdInBar.contains(Integer.valueOf(i))) {
                        currentIdInBar.add(i);
                    }
                }
                listView.getItems().clear();
                imageList.clear();
                for (int i : bar) {
                    ImageView imageView = new ImageView(elements.get(i - 1).getImageLink());
                    imageView.setUserData(i);
                    imageList.add(imageView);
                }
                listView.setItems(imageList);
                setCellFactory();
                return;
            }
            for (int i = 0; i < listViewText.getItems().size(); i++) {
                if (!listViewText.getItems().get(i).contains(searchText)) {
                    int needBeRemovedId = bar.get(i);
                    currentIdInBar.removeAll(Collections.singletonList(needBeRemovedId));
                    listView.getItems().clear();
                    imageList.clear();
                    for (Integer integer : currentIdInBar) {
                        ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                        imageView.setUserData(integer);
                        imageList.add(imageView);
                    }
                    listView.setItems(imageList);
                    setCellFactory();
                    System.out.println("need be removed " + needBeRemovedId + "name " + listViewText.getItems().get(i));
                } else {
                    System.out.println("GO HERE");
                    System.out.println(listViewText.getItems().get(i));
                    int needToBeAdded = bar.get(i);
                    if (!currentIdInBar.contains(Integer.valueOf(needToBeAdded))) {
                        currentIdInBar.add(Integer.valueOf(needToBeAdded));
                        listView.getItems().clear();
                        imageList.clear();
                        for (Integer integer : currentIdInBar) {
                            ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                            imageView.setUserData(integer);
                            imageList.add(imageView);
                        }
                        listView.setItems(imageList);
                        setCellFactory();
                    }
                }
            }
        });

        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            String searchText = searchField.getText();
            System.out.println("Backspace Pressed. Text: " + searchText);
            if (searchText == "") {
                for (int i : bar) {
                    if (!currentIdInBar.contains(Integer.valueOf(i))) {
                        currentIdInBar.add(i);
                    }
                }
                System.out.println(listViewText.getItems().size() + "SIZE ");
                listView.getItems().clear();
                imageList.clear();
                for (int i : bar) {
                    ImageView imageView = new ImageView(elements.get(i - 1).getImageLink());
                    imageView.setUserData(i);
                    imageList.add(imageView);
                }
                listView.setItems(imageList);
                setCellFactory();
                return;
            }

            for (int i = 0; i < listViewText.getItems().size(); i++) {
                if (listViewText.getItems().get(i).contains(searchText)) {
                    int needToBeAdded = bar.get(i);
                    if (!currentIdInBar.contains(Integer.valueOf(needToBeAdded))) {
                        currentIdInBar.add(Integer.valueOf(needToBeAdded));
                        listView.getItems().clear();
                        imageList.clear();
                        for (Integer integer : currentIdInBar) {
                            ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                            imageView.setUserData(integer);
                            imageList.add(imageView);
                        }
                        listView.setItems(imageList);
                        setCellFactory();
                    }
                } else {
                    int needBeRemovedId = bar.get(i);
                    currentIdInBar.removeAll(Collections.singletonList(needBeRemovedId));
                    listView.getItems().clear();
                    imageList.clear();
                    for (Integer integer : currentIdInBar) {
                        ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                        imageView.setUserData(integer);
                        imageList.add(imageView);
                    }
                    listView.setItems(imageList);
                    setCellFactory();
                    break;
                }
            }

        }
    }
}

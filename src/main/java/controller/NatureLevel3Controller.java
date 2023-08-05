package controller;


import elementarium.models.Element;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NatureLevel3Controller extends DragAndDropWindow {

    private final List<Integer> currentIdInBar = new ArrayList<>();

    @FXML
    private ImageView lookupIcon;

    private boolean isSearchBoxVisible = false;

    @FXML
    private VBox searchBox;

    @FXML
    private TextField searchField;

    @FXML
    private Pane congraBox;

    public NatureLevel3Controller() throws SQLException, ClassNotFoundException {
        super();
        resId = 83;
        initialId.addAll(Arrays.asList(17, 1, 2, 5, 76, 73, 78));
        setInitialId();
    }

    @Override
    public void checkRes(Element resElement) {
        if (resElement.getElementId() == resId) {
            System.out.println("ok");
            congraBox.setDisable(false);
            congraBox.setVisible(true);
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

                for (int i : bar) {
                    if (!currentIdInBar.contains(Integer.valueOf(i))) {
                        currentIdInBar.add(i);
                    }
                }
                listView.getItems().clear();
                imageList.clear();
                clearName();
                for (int i : bar) {
                    ImageView imageView = new ImageView(elements.get(i - 1).getImageLink());
                    imageView.setUserData(i);
                    imageList.add(imageView);
                    listViewText.getItems().add(elements.get(i - 1).getName());
                }
                listView.setItems(imageList);
                // setCellFactory();
//                refreshName();
                return;
            }
            for (int i = 0; i < listViewText.getItems().size(); i++) {
                if (!listViewText.getItems().get(i).contains(searchText)) {
                    int needBeRemovedId = bar.get(i);
                    currentIdInBar.removeAll(Collections.singletonList(needBeRemovedId));
                    listView.getItems().clear();
                    imageList.clear();
                    clearName();
                    for (Integer integer : currentIdInBar) {
                        ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                        imageView.setUserData(integer);
                        imageList.add(imageView);
                        listViewText.getItems().add(elements.get(integer.intValue() - 1).getName());
                    }
                    listView.setItems(imageList);
                    // setCellFactory();
                    // refreshName();
                } else {
                    System.out.println(listViewText.getItems().get(i));
                    int needToBeAdded = bar.get(i);
                    if (!currentIdInBar.contains(Integer.valueOf(needToBeAdded))) {
                        currentIdInBar.add(Integer.valueOf(needToBeAdded));
                        listView.getItems().clear();
                        imageList.clear();
                        clearName();
                        for (Integer integer : currentIdInBar) {
                            ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                            imageView.setUserData(integer);
                            imageList.add(imageView);
                            listViewText.getItems().add(elements.get(integer.intValue() - 1).getName());
                        }
                        listView.setItems(imageList);
                        // setCellFactory();
                        // refreshName();
                    }
                }
            }
        });

        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            String searchText = searchField.getText();

            if (searchText == "") {
                for (int i : bar) {
                    if (!currentIdInBar.contains(Integer.valueOf(i))) {
                        currentIdInBar.add(i);
                    }
                }
                listView.getItems().clear();
                imageList.clear();
                clearName();
                for (int i : bar) {
                    ImageView imageView = new ImageView(elements.get(i - 1).getImageLink());
                    imageView.setUserData(i);
                    imageList.add(imageView);
                    listViewText.getItems().add(elements.get(i - 1).getName());
                }
                listView.setItems(imageList);
                // setCellFactory();
                // refreshName();
                return;
            }

            for (int i = 0; i < listViewText.getItems().size(); i++) {
                if (listViewText.getItems().get(i).contains(searchText)) {
                    int needToBeAdded = bar.get(i);
                    if (!currentIdInBar.contains(Integer.valueOf(needToBeAdded))) {
                        currentIdInBar.add(Integer.valueOf(needToBeAdded));
                        listView.getItems().clear();
                        imageList.clear();
                        clearName();
                        for (Integer integer : currentIdInBar) {
                            ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                            imageView.setUserData(integer);
                            imageList.add(imageView);
                            listViewText.getItems().add(elements.get(integer.intValue() - 1).getName());
                        }
                        listView.setItems(imageList);
                        // setCellFactory();
                        // refreshName();
                    }
                } else {
                    int needBeRemovedId = bar.get(i);
                    currentIdInBar.removeAll(Collections.singletonList(needBeRemovedId));
                    listView.getItems().clear();
                    for (Integer ii : currentIdInBar) {
                        System.out.println("In bar: " + ii);
                    }
                    List<String> tmp = new ArrayList<>();
                    for (Integer integer : currentIdInBar) {
                        ImageView imageView = new ImageView(elements.get(integer.intValue() - 1).getImageLink());
                        imageView.setUserData(integer);
                        imageList.add(imageView);
                        tmp.add(elements.get(integer.intValue() - 1).getName());
                    }
                    listView.setItems(imageList);
                    clearName();
                    listViewText.setItems((ObservableList<String>) tmp);
                    //setCellFactory();
                    // refreshName();
                    break;
                }
            }

        }

    }


    public void addToListViewTextById(int elementId) {
//        Element x = elements.get(elementId-1);
//        listViewText.getItems().add(x.getName());
    }

    public  void clearName() {
        listViewText.getItems().clear();
    }


    public void nextLevel(ActionEvent actionEvent) {
    }
}

package controller;

import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

public class CustomListCell extends ListCell<String> {
    private final double fontSize;

    public CustomListCell(double fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            setPrefHeight(86);
            setFont(Font.font(fontSize)); // Đặt font và kích cỡ chữ cho mục
        }
    }
}

package elementarium;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        Class<?> clazz = this.getClass();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Image fireImage = new Image("fire.png");
        ImageView fireImageView = new ImageView(fireImage);
        fireImageView.setFitWidth(50);
        fireImageView.setFitHeight(50);
        fireImageView.setPreserveRatio(true);
        fireImageView.setSmooth(true);
        fireImageView.setCache(true);

        Image waterImg = new Image("water.png");
        ImageView waterImgView = new ImageView(waterImg);
        waterImgView.setFitWidth(50);
        waterImgView.setFitHeight(50);
        waterImgView.setPreserveRatio(true);
        waterImgView.setSmooth(true);
        waterImgView.setCache(true);

        gridPane.add(fireImageView, 0, 0);
        gridPane.add(waterImgView, 1, 0);

        fireImageView.setOnDragDetected(event -> {
            Dragboard db = fireImageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(fireImageView.getImage());
            db.setContent(content);

            ImageView dragView = new ImageView(fireImageView.getImage());
            dragView.setFitHeight(50);
            dragView.setFitWidth(50);
            db.setDragView(dragView.snapshot(null, null));

            event.consume();
        });

        waterImgView.setOnDragDetected(event -> {
            Dragboard db = waterImgView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(waterImgView.getImage());
            db.setContent(content);

            ImageView dragView = new ImageView(waterImgView.getImage());
            dragView.setFitHeight(50);
            dragView.setFitWidth(50);
            db.setDragView(dragView.snapshot(null, null));

            event.consume();
        });

        fireImageView.setOnMouseDragged(event -> {

        });

        fireImageView.setOnDragOver(event -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        Scene scene = new Scene(gridPane, 400, 300, Color.WHITE);

        stage.setScene(scene);
        stage.setTitle("Elementarium");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package play;

import elementarium.models.Element;
import elementarium.mouse_handler.MouseHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Game extends Application {
    private int imageViewsLength = 2;

    List<Element> elementList = new ArrayList<>();

    private ImageView[] imageViews = new ImageView[1001];
    private ImageView[] cloneImageViews = new ImageView[1001];
    private double[] offsetXs = new double[1001];
    private double[] offsetYs = new double[1001];

    public Game() {
        System.out.println("Constructor");
        elementList.add()
    }



    @Override
    public void start(Stage primaryStage) {

        // Load the original images
        Image originalImage1 = new Image("fire.png");
        Image originalImage2 = new Image("water.png");

        // Create the image views for the original images
        imageViews[0] = new ImageView(originalImage1);
        imageViews[1] = new ImageView(originalImage2);


        // Set the properties of the image views
        for (int i=0;i<=1;i++) {
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setFitWidth(50);
        }

        imageViews[0].setLayoutX(50);
        imageViews[0].setLayoutY(100);

        // Set the position of the second image view
        imageViews[1].setLayoutX(100);
        imageViews[1].setLayoutY(100);

        // Create a pane to hold the images
        Pane pane = new Pane(imageViews[0],imageViews[1]);

        // Create the scene
        Scene scene = new Scene(pane, 900, 600);

        // Register the mouse event handlers for the original images
        for (int i = 0; i < 2; i++) {
            int index = i;
            imageViews[i].setOnMousePressed(event -> onMousePressed(event, index));
            imageViews[i].setOnMouseDragged(event -> onMouseDragged(event, index));
            imageViews[i].setOnMouseReleased(event -> onMouseReleased(event, index));
        }

        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void onMousePressedAfterDrop(MouseEvent event, int index) {
        // Calculate the offset between the mouse position and the image position
        offsetXs[index] = event.getSceneX() - cloneImageViews[index].getLayoutX();
        offsetYs[index] = event.getSceneY() - cloneImageViews[index].getLayoutY();
    }

    private void onMousePressed(MouseEvent event, int index) {
        // Create a clone of the original image
        cloneImageViews[index] = new ImageView(imageViews[index].getImage());
        cloneImageViews[index].setPreserveRatio(true);
        cloneImageViews[index].setFitWidth(imageViews[index].getFitWidth());

        // Set the position of the clone image
        cloneImageViews[index].setLayoutX(imageViews[index].getLayoutX());
        cloneImageViews[index].setLayoutY(imageViews[index].getLayoutY());
        // Add the clone image to the pane
        Pane pane = (Pane) imageViews[index].getParent();
        pane.getChildren().add(cloneImageViews[index]);

        // Calculate the offset between the mouse position and the clone image position
        offsetXs[index] = event.getSceneX() - cloneImageViews[index].getLayoutX();
        offsetYs[index] = event.getSceneY() - cloneImageViews[index].getLayoutY();
    }

    private void onMouseDragged(MouseEvent event, int index) {
        // Move the clone image to the mouse position
        cloneImageViews[index].setLayoutX(event.getSceneX() - offsetXs[index]);
        cloneImageViews[index].setLayoutY(event.getSceneY() - offsetYs[index]);
    }

    private void onMouseReleased(MouseEvent event, int index) {
        // Remove the clone image from the pane
        Pane pane = (Pane) imageViews[index].getParent();
         // pane.getChildren().remove(cloneImageViews[index]);
        cloneImageViews[index].setOnMousePressed(e -> onMousePressedAfterDrop(e, index));
        cloneImageViews[index].setOnMouseDragged(e -> onMouseDragged(e, index));
        cloneImageViews[index].setOnMouseReleased(e -> onMouseReleased(e, index));


        // Check if the clone image overlaps with any other clone image
        boolean isOverlap = false;
        int overlapIndex = -1;
        System.out.println("GO HERE");
        for (int i = 0; i < imageViews.length; i++) {
            if (i != index && cloneImageViews[i]!=null && cloneImageViews[index].getBoundsInParent().intersects(cloneImageViews[i].getBoundsInParent()) ) {
                isOverlap = true;
                overlapIndex = i;
                break;
            }
        }
        ImageView newImageView = null;
        if (isOverlap) {
            System.out.println( "index: "+ index + " overlap: " + overlapIndex);

            pane.getChildren().removeAll(cloneImageViews[overlapIndex],cloneImageViews[index]);
            imageViews[imageViewsLength] = new ImageView(new Image("steam.png"));
            // Create a new image view for the combined image

            imageViews[imageViewsLength].setPreserveRatio(true);
            imageViews[imageViewsLength].setFitWidth(imageViews[index].getFitWidth());


            if (imageViews[imageViewsLength] == null) {
                System.out.println("imageView is null and length = " + imageViewsLength+1);
            }
            for (int i=2;i<=imageViewsLength;i++) {
                int indexx = i;
                imageViews[imageViewsLength].setOnMousePressed(e -> MouseHandler.onMousePressedNotClone(e, indexx,offsetYs,offsetXs,imageViews));
                imageViews[imageViewsLength].setOnMouseDragged(e -> MouseHandler.onMouseDraggedNotClone(e, indexx,offsetYs,offsetXs,imageViews));
            }

            // Set the position of the new image view
            imageViews[imageViewsLength].setLayoutX(event.getSceneX() - offsetXs[index]);
            imageViews[imageViewsLength].setLayoutY(event.getSceneY() - offsetYs[index]);

            System.out.println("Number of image on screen:" + imageViewsLength);
            // Add the new image view to the pane
            pane.getChildren().add(imageViews[imageViewsLength]);

             imageViewsLength++;

        }

    }



    public static void main(String[] args) {
        launch(args);
    }

}

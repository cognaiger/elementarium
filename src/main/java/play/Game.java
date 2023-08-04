package play;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class Game extends Application {

    private ImageView[] imageViews = new ImageView[2];
    private ImageView[] cloneImageViews = new ImageView[2];
    private double[] offsetXs = new double[2];
    private double[] offsetYs = new double[2];

    @Override
    public void start(Stage primaryStage) {

        // Load the original images
        Image originalImage1 = new Image("fire.png");
        Image originalImage2 = new Image("water.png");

        // Create the image views for the original images
        imageViews[0] = new ImageView(originalImage1);
        imageViews[1] = new ImageView(originalImage2);

        // Set the properties of the image views
        for (ImageView imageView : imageViews) {
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(100);
        }

        imageViews[0].setLayoutX(50);
        imageViews[0].setLayoutY(100);

        // Set the position of the second image view
        imageViews[1].setLayoutX(300);
        imageViews[1].setLayoutY(100);

        // Create a pane to hold the images
        Pane pane = new Pane(imageViews);

        // Create the scene
        Scene scene = new Scene(pane, 400, 400);

        // Register the mouse event handlers for the original images
        for (int i = 0; i < imageViews.length; i++) {
            int index = i;
            imageViews[i].setOnMousePressed(event -> onMousePressed(event, index));
            imageViews[i].setOnMouseDragged(event -> onMouseDragged(event, index));
            imageViews[i].setOnMouseReleased(event -> onMouseReleased(event, index));
        }

        // Show the scene
        primaryStage.setScene(scene);
        primaryStage.show();
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

            // Create a new image view for the combined image
            newImageView = new ImageView(new Image("steam.png"));
            newImageView.setPreserveRatio(true);
            newImageView.setFitWidth(imageViews[index].getFitWidth());

            // Set the position of the new image view
            newImageView.setLayoutX(event.getSceneX() - offsetXs[index]);
            newImageView.setLayoutY(event.getSceneY() - offsetYs[index]);

            // Add the new image view to the pane
            pane.getChildren().add(newImageView);

        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}

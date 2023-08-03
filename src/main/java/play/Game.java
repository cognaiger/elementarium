package play;

import elementarium.models.Element;
import elementarium.mouse_handler.MouseHandler;
import elementarium.utils.automatic_load_data.AutomaticLoadData;
import elementarium.utils.enums.ElementId;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Game extends Application {
    private int imageViewsLength = 2;


    public static final int MAX_ELEMENT = 1001;

    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 900;

    public static final int ELEMENT_WIDTH = 50;

    List<Element> elementList = new ArrayList<>();
    List<ImageView> elementImageViews = new ArrayList<>();

    private ImageView[] imageViews = new ImageView[MAX_ELEMENT];
    private ImageView[] cloneImageViews = new ImageView[MAX_ELEMENT];
    private double[] offsetXs = new double[MAX_ELEMENT];
    private double[] offsetYs = new double[MAX_ELEMENT];

    public Game() {
        System.out.println("Constructor");
        try {
            Element fire = AutomaticLoadData.getElementById(ElementId.FIRE_ID);
            System.out.println(fire.getName() + " " + fire.getImageLink());
            elementList.add(fire);
            elementList.add(AutomaticLoadData.getElementById(ElementId.WATER_ID));
            elementList.add(AutomaticLoadData.getElementById(ElementId.));
            System.out.println("List length " + elementList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLayoutElementImageViews() {
        int startY = 0;
        int startX = SCREEN_WIDTH - ELEMENT_WIDTH ;
        int step = 50;
        for (int i = 0; i < elementList.size(); i++) {
            elementList.get(i).getImageView().setLayoutX(startX);
            elementList.get(i).getImageView().setLayoutY(startY);
            startY += step;
        }
    }

    public void setImageViewsProperty() {
        for (int i = 0; i < elementList.size(); i++) {
            elementList.get(i).getImageView().setPreserveRatio(true);
            elementList.get(i).getImageView().setFitWidth(ELEMENT_WIDTH);
        }
    }

    public void addToImageViewList() {
        for (Element element : elementList) {
            elementImageViews.add(element.getImageView());
        }
    }

    public void addElementToPane(Pane pane, Element element) {
        pane.getChildren().add(element.getImageView());
    }

    public void addElementListToPane(Pane pane) {
        for (Element element : elementList) {
            pane.getChildren().add(element.getImageView());
        }
    }


    @Override
    public void start(Stage primaryStage) {

        setImageViewsProperty();
        setLayoutElementImageViews();
        addToImageViewList();


        // Create a pane to hold the images
        Pane pane = new Pane();
        addElementListToPane(pane);
        // Create the scene
        Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);


        for (int i = 0; i < elementList.size(); i++) {
            int index = i;
            elementList.get(i).getImageView().setOnMousePressed(event -> onMousePressed(event, index));
            elementList.get(i).getImageView().setOnMouseDragged(event -> onMouseDragged(event, index));
            elementList.get(i).getImageView().setOnMouseReleased(event -> onMouseReleased(event, index));
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
        cloneImageViews[index] = new ImageView(elementList.get(index).getImageView().getImage());
        cloneImageViews[index].setPreserveRatio(true);
        cloneImageViews[index].setFitWidth(ELEMENT_WIDTH);

        // Set the position of the clone image
        cloneImageViews[index].setLayoutX(elementList.get(index).getImageView().getLayoutX());
        cloneImageViews[index].setLayoutY(elementList.get(index).getImageView().getLayoutY());
        // Add the clone image to the pane
        Pane pane = (Pane) elementList.get(index).getImageView().getParent();
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
        Pane pane = (Pane) elementList.get(index).getImageView().getParent();
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
            imageViews[imageViewsLength].setFitWidth(ELEMENT_WIDTH);


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

package elementarium.mouse_handler;

import elementarium.models.Element;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import play.Game;

import java.util.List;

public class MouseHandler {
    public static void onMousePressedNotClone(MouseEvent event, int index, double[] offsetYs, double[] offsetXs, ImageView[] imageViews) {
        offsetXs[index] = event.getSceneX() - imageViews[index].getLayoutX();
        offsetYs[index] = event.getSceneY() - imageViews[index].getLayoutY();
    }

    public static void onMouseDraggedNotClone(MouseEvent event, int index, double[] offsetYs, double[] offsetXs, ImageView[] imageViews) {
        System.out.println("index in array is " + index);
        imageViews[index].setLayoutX(event.getSceneX() - offsetXs[index]);
        imageViews[index].setLayoutY(event.getSceneY() - offsetYs[index]);
    }

    public static void onMousePressedAfterDrop(MouseEvent event, int index, double[] offsetXs, double[] offsetYs, ImageView[] cloneImageViews) {
        // Calculate the offset between the mouse position and the image position
        offsetXs[index] = event.getSceneX() - cloneImageViews[index].getLayoutX();
        offsetYs[index] = event.getSceneY() - cloneImageViews[index].getLayoutY();
    }

    public static void onMouseDragged(MouseEvent event, int index, double[] offsetXs, double[] offsetYs, ImageView[] cloneImageViews) {
        // Move the clone image to the mouse position
        cloneImageViews[index].setLayoutX(event.getSceneX() - offsetXs[index]);
        cloneImageViews[index].setLayoutY(event.getSceneY() - offsetYs[index]);
    }


    public static void onMousePressed(MouseEvent event, int index, List<Element> elementList, ImageView[] cloneImageViews,double[] offsetXs,double[] offsetYs) {
        // Create a clone of the original image
        cloneImageViews[index] = new ImageView(elementList.get(index).getImageView().getImage());
        cloneImageViews[index].setPreserveRatio(true);
        cloneImageViews[index].setFitWidth(Game.ELEMENT_WIDTH);

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

}

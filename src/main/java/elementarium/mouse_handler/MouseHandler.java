package elementarium.mouse_handler;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

    public static void onMousePressedAfterDrop(MouseEvent event, int index,double[] offsetXs,double[] offsetYs, ImageView[] cloneImageViews) {
        // Calculate the offset between the mouse position and the image position
        offsetXs[index] = event.getSceneX() - cloneImageViews[index].getLayoutX();
        offsetYs[index] = event.getSceneY() - cloneImageViews[index].getLayoutY();
    }

}

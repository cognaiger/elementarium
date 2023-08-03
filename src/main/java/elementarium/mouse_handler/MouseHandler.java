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
}

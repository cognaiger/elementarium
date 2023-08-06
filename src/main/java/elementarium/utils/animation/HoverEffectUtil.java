package elementarium.utils.animation;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HoverEffectUtil {

    private static final double HOVER_SCALE_FACTOR = 1.2;
    private static final double DURATION_MS = 200;

    public static void addHoverEffect(ImageView imageView) {
        System.out.println("HOVER");
        imageView.setOnMouseEntered(event -> handleMouseEnter(imageView));
        imageView.setOnMouseExited(event -> handleMouseExit(imageView));
    }

    private static void handleMouseEnter(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(DURATION_MS), imageView);
        scaleTransition.setToX(HOVER_SCALE_FACTOR);
        scaleTransition.setToY(HOVER_SCALE_FACTOR);
        scaleTransition.play();
    }

    private static void handleMouseExit(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(DURATION_MS), imageView);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }
}

package elementarium.utils.animation;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {
    private static final double DURATION_MS = 1000;

    public static void shakeImageView(ImageView imageView) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), imageView);
        rotateTransition.setFromAngle(-5);
        rotateTransition.setToAngle(5);
        rotateTransition.setCycleCount(3);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }

    public static void animateAndRushImageViews(ImageView imageView1, ImageView imageView2) {
        // Scale down both ImageViews
        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(DURATION_MS), imageView1);
        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(DURATION_MS), imageView2);
        scaleTransition1.setToX(0.1);
        scaleTransition1.setToY(0.1);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);
        scaleTransition2.play();
        scaleTransition1.play();
    }
}

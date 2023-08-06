package elementarium.utils.animation;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {
    public static void shakeImageView(ImageView imageView) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), imageView);
        rotateTransition.setFromAngle(-5);
        rotateTransition.setToAngle(5);
        rotateTransition.setCycleCount(3);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }

    private static final double DURATION_MS = 100000000;

    public static void animateAndRushImageViews(ImageView imageView1, ImageView imageView2) {
        // Scale down both ImageViews
        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(DURATION_MS), imageView1);
        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(DURATION_MS), imageView2);
        scaleTransition1.setToX(0.1);
        scaleTransition1.setToY(0.1);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);

        // Translate both ImageViews towards each other
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(DURATION_MS), imageView1);
        translateTransition1.setToX(imageView2.getLayoutX() - imageView1.getLayoutX());
        translateTransition1.setToY(imageView2.getLayoutY() - imageView1.getLayoutY());

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(DURATION_MS), imageView2);
        translateTransition2.setToX(imageView1.getLayoutX() - imageView2.getLayoutX());
        translateTransition2.setToY(imageView1.getLayoutY() - imageView2.getLayoutY());

        // Play all animations
        scaleTransition1.play();
        scaleTransition2.play();
        translateTransition1.play();
        translateTransition2.play();
    }
}

package elementarium.utils.animation;

import javafx.animation.RotateTransition;
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
}

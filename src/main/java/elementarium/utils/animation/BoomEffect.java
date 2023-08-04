package elementarium.utils.animation;

import javafx.animation.ScaleTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BoomEffect {

    public static void applyBoomEffect(Pane parentPane) {
        double initialSize = 2;
        double finalSize = 200;
        Duration duration = Duration.seconds(1);

        parentPane.setPrefWidth(initialSize);
        parentPane.setPrefHeight(initialSize);

        // Create and start the "boom" effect animation
        ScaleTransition scaleTransition = new ScaleTransition(duration, parentPane);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(finalSize / initialSize);
        scaleTransition.setToY(finalSize / initialSize);

        scaleTransition.play();
    }
}




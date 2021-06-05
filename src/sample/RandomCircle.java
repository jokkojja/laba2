package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class RandomCircle extends Circle {

    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;

    private static final Random random = new Random();

    public RandomCircle(final Pane canvasPane) {
        // Возможное расстояние от левой границы до центра круга
        final double minCenterX = 0 + 25d;
        final double maxCenterX = canvasPane.getWidth() - minCenterX;
        super.setCenterX(
                minCenterX + (maxCenterX - minCenterX) * random.nextDouble()
        );

        // Возможное расстояние от верхней границы до центра круга
        final double minCenterY = 0 + 25d;
        final double maxCenterY = canvasPane.getHeight() - minCenterY;
        super.setCenterY(
                minCenterY + (maxCenterY - minCenterY) * random.nextDouble()
        );

        // Радиус подбирается таким образом, чтобы круг не заходил за границы canvasPane
        final double minRadius = Math.min(minCenterX, minCenterY);
        final double maxRadius = Math.min(
                Math.min(
                        super.getCenterX() - 0,
                        super.getCenterY() - 0
                ),
                Math.min(
                        canvasPane.getWidth() - super.getCenterX(),
                        canvasPane.getHeight() - super.getCenterY()
                )
        ) - minRadius;
        super.setRadius(minRadius + (maxRadius - minRadius) * random.nextDouble());

        this.setRandomFill();

        super.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                orgSceneX = mouseEvent.getSceneX();
                orgSceneY = mouseEvent.getSceneY();
                orgTranslateX = super.getTranslateX();
                orgTranslateY = super.getTranslateY();
                super.toFront();
            }
        });

        super.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                double offsetX = mouseEvent.getSceneX() - orgSceneX;
                double offsetY = mouseEvent.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                super.setTranslateX(newTranslateX);
                super.setTranslateY(newTranslateY);
            }
        });

        super.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                this.setRandomFill();
            }
        });
    }

    public void setRandomFill() {
        super.setFill(
                Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
        );
    }
}

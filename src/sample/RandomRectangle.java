package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class RandomRectangle extends Rectangle {

    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;

    private static final Random random = new Random();

    public RandomRectangle(final Pane canvasPane) {
        // Возможное расстояние от левой границы до верхней левой точки
        final double minX = 0 + 25d;
        final double maxX = canvasPane.getWidth() - (minX * 2);
        super.setX(
                minX + (maxX - minX) * random.nextDouble()
        );

        // Возможное расстояние от верхней границы до верхней левой точки
        final double minY = 0 + 25d;
        final double maxY = canvasPane.getHeight() - (minY * 2);
        super.setY(
                minY + (maxY - minY) * random.nextDouble()
        );

        // Ширина подбирается таким образом, чтобы не касаться правой границы canvasPane
        final double minWidth = Math.min(minX, canvasPane.getWidth() - super.getX() - minX);
        final double maxWidth = Math.max(minX, canvasPane.getWidth() - super.getX() - minX);
        super.setWidth(
                minWidth + (maxWidth - minWidth) * random.nextDouble()
        );

        // Высота подбирается таким образом, чтобы не касаться верхней границы canvasPane
        final double minHeight = Math.min(minY, canvasPane.getHeight() - super.getY() - minY);
        final double maxHeight = Math.max(minY, canvasPane.getHeight() - super.getY() - minY);
        super.setHeight(
                minHeight + (maxHeight - minHeight) * random.nextDouble()
        );

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

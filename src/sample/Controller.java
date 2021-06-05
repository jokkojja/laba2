package sample;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
public class Controller {
    @FXML
    private Pane canvasPane;

    @FXML
    public void drawCircle() {
        canvasPane.getChildren().add(new RandomCircle(canvasPane));
    }


    @FXML
    public void drawRectangle() {
        canvasPane.getChildren().add(new RandomRectangle(canvasPane));
    }

    @FXML
    public void clearScreen() {
        canvasPane.getChildren().clear();
    }
}

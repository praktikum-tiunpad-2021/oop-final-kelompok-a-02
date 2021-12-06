package Puzzle;

import javafx.scene.Scene;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameController {
    public static Board board;
    public static Scene scene;
    public static Stage stage;
    private static boolean pause = false;

    public static void set(Board _board, Scene _scene){
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        board = _board;
        scene = _scene;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                // System.out.println(arg0.getCode() + ", is alt : " + arg0.isAltDown());
                if(GameController.isPaused()) return;
                if(arg0.getCode() ==  KeyCode.R){
                    board.shuffle();
                }
            }
        });
    }
    public static void showWonDialog(){
        try {
            DialogController dc = new DialogController();
            Pane dialog = FXMLLoader.load(dc.getClass().getResource("/wonDialog.fxml"));
            Scene dialogScene = new Scene(dialog);
            stage.setScene(dialogScene);
            pause = true;
            stage.showAndWait();
            pause = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static boolean isPaused(){
        return pause;
    }
}

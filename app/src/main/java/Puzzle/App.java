package Puzzle;
	
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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



public class App extends Application {
    private static int size = 4;
	@Override
	public void start(Stage primaryStage) {
		try {
            Tile.textSize = 0.5;
            Board board = new Board(size,400,400);
            // Board board2 = new Board(4,400,400);
            // board2.setLayoutY(401);
            Group root = new Group();
            root.getChildren().addAll(board);
            Scene scene = new Scene(root);
            GameController.set(board, scene);

			primaryStage.setScene(scene);
            primaryStage.setTitle("Fifteen Puzzle");
			primaryStage.setResizable(false);
			primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent arg0) {
                    GameController.stage.close();
                }
            });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        try {
            size = Integer.valueOf(args[0]);
        } catch (Exception e) {
            System.out.println("Arguments not valid or not specified");
        }
		launch(args);
	}
}

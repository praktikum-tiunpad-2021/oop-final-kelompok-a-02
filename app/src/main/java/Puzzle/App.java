package Puzzle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;



public class App extends Application {
    private static int size = 4;
    private static int width = 400;
    private static int height = 400;
	@Override
	public void start(Stage primaryStage) {
		try {
            Board board = new Board(size,width,height);

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
            if(size < 2) throw new Exception("Size too small, minimal : 2");
        } catch (Exception e) {
            System.out.println("Arguments not valid or not specified, "+e.getMessage());
        }
		launch(args);
	}
}

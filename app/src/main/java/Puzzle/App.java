package Puzzle;
	
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;



public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            Board board = new Board();
            Group root = new Group();
            Board.pixelSizeX = 500;
            Board.pixelSizeY = 500;
 
            for (int i = 0; i < board.size*board.size; i++) {
                System.out.println(board.tiles.get(i));
            }
            // ThreadLocalRandom.current().nextInt(origin, bound);
            // for (int i = 0; i < Board.size; i++) {
            //     for (int j = 0; j < Board.size; j++) {
            //         if(i==Board.size && j==Board.size) continue;
            //         Tile tile = new Tile(i*Board.size+j+1, j, i);
            //         board.add(tile);
            //     }
            // }
            // root.getChildren().add(board);
			Scene scene = new Scene(board);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent arg0) {
					System.out.println(arg0.getCode() + ", is alt : " + arg0.isAltDown());
					if(arg0.getCode() ==  KeyCode.R){
                        board.shuffle();
                        board.generateTiles();System.out.println(board.isSolvable());
                        System.out.println(board.getInverseCount());
                    }
				}
			
			});
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
        Board.size = Integer.valueOf(args[0]);
        Tile.textSize = 50;
        Tile.init();
		launch(args);
	}
}

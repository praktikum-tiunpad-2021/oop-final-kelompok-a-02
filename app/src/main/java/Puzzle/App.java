package Puzzle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.checkerframework.common.reflection.qual.GetClass;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



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
            Properties prop = new Properties();
            App app = new App();
            InputStream fis = app.getClass().getResource("/puzzle.config").openStream();
            prop.load(fis);
            height = Integer.parseInt(prop.getProperty("windowHeight"));
            width = Integer.parseInt(prop.getProperty("windowWidth"));
            Tile.color0 = Color.valueOf(prop.getProperty("tileColor0"));
            Tile.color1 = Color.valueOf(prop.getProperty("tileColor1"));
            Tile.textColor0 = Color.valueOf(prop.getProperty("textColor0"));
            Tile.textColor1 = Color.valueOf(prop.getProperty("textColor1"));
            Tile.textFont = prop.getProperty("textFont");
            Tile.textWeight = FontWeight.valueOf(prop.getProperty("FontWeight"));
            size = Integer.parseInt(prop.getProperty("puzzleSize"));
            Tile.gap = Integer.parseInt(prop.getProperty("tileGap"));
            Tile.textSize = Double.parseDouble(prop.getProperty("tileTextSize"));
            Tile.transitionDuration = Duration.millis(Double.parseDouble(prop.getProperty("transitionDuration")));

        } catch (Exception e) {
            System.out.println("Config file not valid or doesn't exists, "+e.getMessage());
        }
        try {
            size = Integer.valueOf(args[0]);
            if(size < 2) throw new Exception("Size too small, minimal : 2");
        } catch (Exception e) {
            System.out.println("Arguments not valid or not specified, "+e.getMessage());
        }
		launch(args);
	}
}

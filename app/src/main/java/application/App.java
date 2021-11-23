package application;
	
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class App extends Application {
	@Override
	public void start(Stage stage) {
		try {
            Group root = new Group();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if(i==3 && j==3) continue;
                    Rectangle rectangle = new Rectangle(0,0,99,99);
                    if((j+(i%2)) % 2 == 0)
                    rectangle.setFill(Color.AQUA);
                    if((j+(i%2)) % 2 != 0)
                    rectangle.setFill(Color.CRIMSON);
                    Text text = new Text(49,49, ""+(j+1+(4*i)));
                    StackPane blok = new StackPane();
                    blok.getChildren().add(rectangle);
                    blok.getChildren().add(text);
                    blok.setLayoutX(j*100);
                    blok.setLayoutY(i*100);
                    blok.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    root.getChildren().add(blok);
                }
            }
			Scene scene = new Scene(root,400,400);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

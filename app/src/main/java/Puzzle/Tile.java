package Puzzle;

import javax.swing.JSpinner.NumberEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile extends StackPane{
    public static int pixelSizeX;
    public static int pixelSizeY;
    public static Paint textColor = Color.CRIMSON;
    public static String textFont = "Verdanna";
    public static double textSize = 50;
    public static FontWeight textWeight = FontWeight.BOLD;
    public static Paint color0 = Color.AQUA;
    public static Paint color1 = Color.GRAY;
    private int number;
    private int posX;
    private int posY;

    public static void init(){
        Tile.pixelSizeX = Board.pixelSizeX/Board.size;
        Tile.pixelSizeY = Board.pixelSizeY/Board.size;
    }

    public Tile(int number,int posX, int posY){
        super();
        this.number = number;
        this.posX = posX;
        this.posY = posY;

        Text text = new Text("" + this.number);
        text.setFont(Font.font(Tile.textFont, Tile.textWeight, Tile.textSize));
        text.setFill(Tile.textColor);
        Rectangle rectangle = new Rectangle(Tile.pixelSizeX-2,Tile.pixelSizeY-2);
        if((((this.number-1)/Board.size)%2+((this.number-1)%Board.size))%2 == 0){
            rectangle.setFill(Tile.color0);
        }
        else{
            rectangle.setFill(Tile.color1);
        }

        this.getChildren().add(rectangle);
        this.getChildren().add(text);
        this.setLayoutX(this.posX*Tile.pixelSizeX);
        this.setLayoutY(this.posY*Tile.pixelSizeY);
        
    }
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public int getNumber(){
        return this.number;
    }

    public void moveTo(int x, int y){
        this.posX = x;
        this.posY = y;
        this.relocate(this.posX*Tile.pixelSizeX, this.posY*Tile.pixelSizeY);

    }
}

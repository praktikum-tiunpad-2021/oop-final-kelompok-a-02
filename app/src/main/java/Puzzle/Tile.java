package Puzzle;

import javax.naming.ldap.Rdn;
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
    public int pixelSizeX;
    public int pixelSizeY;
    public static Paint textColor1 = Color.GREEN;
    public static Paint textColor0 = Color.CRIMSON;
    public static String textFont = "Verdanna";
    public double textSize = 50;
    public static FontWeight textWeight = FontWeight.BOLD;
    public static Paint color0 = Color.ORANGE;
    public static Paint color1 = Color.LIGHTGRAY;
    private int number;
    private int posX;
    private int posY;
    public Board board; //refrence to its board instance

    public void resize(){
        this.pixelSizeX = this.board.pixelSizeX/this.board.size;
        this.pixelSizeY = this.board.pixelSizeY/this.board.size;
        this.textSize = Math.min(pixelSizeX, pixelSizeY) * 0.4;
    }
    public void init(Board board,int posX, int posY){
        this.board = board;
        this.posX = posX;
        this.posY = posY;
        this.resize();
        this.setLayoutX(this.posX*this.pixelSizeX);
        this.setLayoutY(this.posY*this.pixelSizeY);
    }

    public Tile(Board board,int posX, int posY){
        super();
        this.init(board,posX,posY);

        Rectangle rectangle = new Rectangle(this.pixelSizeX-2,this.pixelSizeY-2);

        this.getChildren().add(rectangle);
    }
    
    public Tile(Board board,int number,int posX, int posY){
        super();
        this.init(board,posX,posY);
        this.number = number;

        Text text = new Text("" + this.number);
        text.setFont(Font.font(Tile.textFont, Tile.textWeight, this.textSize));
        text.setFill(Tile.textColor1);
        Rectangle rectangle = new Rectangle(this.pixelSizeX-2,this.pixelSizeY-2);
        if((((this.number-1)/this.board.size)%2+((this.number-1)%this.board.size))%2 == 0){
            rectangle.setFill(Tile.color0);
        }
        else{
            rectangle.setFill(Tile.color1);
        }

        this.getChildren().add(rectangle);
        this.getChildren().add(text);
    }

    public static Tile createInvisibleTile(Board board,int posX, int posY){
        Tile tile = new Tile(board,posX,posY);
        tile.setOpacity(0.0);
        return tile;
    }
    public boolean isMovable(){
        if(this.board.movableIndexes.contains(this.getIndex())) return true;
        return false;
    }
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public int getIndex(){
        return (this.posY*this.board.size) + this.posX;
    }
    public int getNumber(){
        return this.number;
    }

    public void moveTo(int x, int y){
        this.posX = x;
        this.posY = y;
        this.relocate(this.posX*this.pixelSizeX, this.posY*this.pixelSizeY);

    }
}

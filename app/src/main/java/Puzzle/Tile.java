package Puzzle;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Tile extends StackPane{
    public int pixelSizeX;
    public int pixelSizeY;
    public static Paint textColor1 = Color.GREEN;
    public static Paint textColor0 = Color.CRIMSON;
    public static String textFont = "Verdanna";
    public static double textSize = 0.5;
    public double pixelTextSize;
    public static FontWeight textWeight = FontWeight.BOLD;
    public static Paint color0 = Color.ORANGE;
    public static Paint color1 = Color.LIGHTGRAY;
    public static Duration transitionDuration = Duration.millis(200);
    private TranslateTransition transition;
    private int number;
    private int posX;
    private int posY;
    public Board board; //refrence to its board instance

    public void resize(){
        this.pixelSizeX = this.board.pixelSizeX/this.board.size;
        this.pixelSizeY = this.board.pixelSizeY/this.board.size;
        this.pixelTextSize = Math.min(pixelSizeX, pixelSizeY) * textSize;
    }
    public void init(Board board,int posX, int posY){
        this.board = board;
        this.posX = posX;
        this.posY = posY;
        this.resize();
        this.setLayoutX(this.posX*this.pixelSizeX);
        this.setLayoutY(this.posY*this.pixelSizeY);
        this.transition = new TranslateTransition(transitionDuration,this);
        this.transition.setInterpolator(Interpolator.EASE_IN);
    }

    public Tile(Board board,int posX, int posY){
        super();
        this.init(board,posX,posY);

        Rectangle rectangle = new Rectangle(this.pixelSizeX-board.gap,this.pixelSizeY-board.gap);
        rectangle.setVisible(false);
        this.getChildren().add(rectangle);
    }
    
    public Tile(Board board,int number,int posX, int posY){
        super();
        this.init(board,posX,posY);
        this.number = number;

        Text text = new Text("" + this.number);
        text.setFont(Font.font(Tile.textFont, Tile.textWeight, this.pixelTextSize));
        text.setFill(Tile.textColor1);
        text.setX(text.getX()-board.gap);
        text.setY(text.getX()-board.gap);
        Rectangle rectangle = new Rectangle(this.pixelSizeX-board.gap,this.pixelSizeY-board.gap);
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
        Text text = new Text("Press\nR");
        text.setFont(Font.font(Tile.textFont, Tile.textWeight, tile.pixelTextSize*0.5));
        text.setFill(textColor0);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(text.getX()-board.gap);
        text.setY(text.getX()-board.gap);
        tile.getChildren().add(text);
    
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
        System.out.println("from = "+(int)this.getBoundsInParent().getMinX()+", "+(int)this.getBoundsInParent().getMinY());
        this.transition.stop();
        double offsetX = x* this.pixelSizeX - this.getBoundsInParent().getMinX();
        double offsetY = y* this.pixelSizeY - this.getBoundsInParent().getMinY();
        System.out.println(" offset = "+offsetX+" ,"+offsetY);
        this.transition.setByX(offsetX);
        this.transition.setByY(offsetY);
        
        this.posX = x;
        this.posY = y;

        this.transition.play();
    }
}

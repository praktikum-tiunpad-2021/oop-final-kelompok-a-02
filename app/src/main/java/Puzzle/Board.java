package Puzzle;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class Board extends Group{
    public static int size = 4;
    public static int pixelSizeX = 400;
    public static int pixelSizeY = 400;
    public ArrayList<Integer> generationTileMap;
    private static ArrayList<Integer> correctTileMap;
    private ArrayList<Integer> movableIndexes;

    private static int toX(int index){
        return index%size;
    }
    private static int toY(int index){
        return index/size;
    }
    private static int toIndex(int x, int y){
        return (y*size) + x;
    }

    public Board(){
        super();
        this.generationTileMap = new ArrayList<Integer>(size*size);   // fill generation with tile map with correct order
        this.addTile(Tile.createInvisibleTile(0, 0),0); // create and add empty tile
        for (int i = 1; i < size*size; i++) { // create remaining tile with correct order
            Tile tile = new Tile(i,toX(i-1) , toY(i-1));
            tile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
				public void handle(MouseEvent arg0) {
                    System.out.println(Board.isInPlace((Tile)arg0.getSource()));
				}
            });
            this.addTile(tile,tile.getNumber());
        }
        correctTileMap = (ArrayList<Integer>)this.generationTileMap.clone(); // The correct tile map
    }
    public void addTile(Tile tile,int number){
        this.getChildren().add(tile);
        this.generationTileMap.add(number);
    }

    public void shuffle(){
        this.generationTileMap.clear();
        ArrayList<Integer> remains = new ArrayList<Integer>(); // numbers list
        for (int i = 0; i < size*size; i++) {
            remains.add(i);
        }
        for (int i = 0; i < size*size; i++) { // generate random tile map
            int rand = ThreadLocalRandom.current().nextInt(0, remains.size());
            this.generationTileMap.add(remains.get(rand));
            remains.remove(rand);
        }
        if(!this.isSolvable()){ // swap 1 with 2 if not solvable
            this.generationTileMap.set(this.generationTileMap.indexOf(1),-1);
            this.generationTileMap.set(this.generationTileMap.indexOf(2),1);
            this.generationTileMap.set(this.generationTileMap.indexOf(-1),2);
        }
    }
    public void refresh(){
        for (int i = 1; i < size*size; i++) {
            Tile tile = (Tile)this.getChildren().get(i);
            if(isInPlace(tile)){
                ((Text)tile.getChildren().get(1)).setFill(Tile.textColor1);
            }
            else{
                ((Text)tile.getChildren().get(1)).setFill(Tile.textColor0);
            }
        }
    }
    public static boolean isInPlace(Tile tile){
        if(tile.getNumber() == correctTileMap.get(tile.getIndex())){
            return true;
        }
        return false;
    }

    public void generateTiles(){ // move tiles' position based on generation tile map
        for (int i = 0; i < size*size; i++) {
            if(this.generationTileMap.get(i) == 0) continue;
            Tile tile = (Tile)(this.getChildren().get(this.generationTileMap.get(i)));
            tile.moveTo(toX(i),toY(i));
        }
    }
    public int getInverseCount(){
        int inverseCount = 0;
        for (int i = 0; i < size*size; i++) {
            if(this.generationTileMap.get(i) == 0) continue;
            int current = this.generationTileMap.get(i);
            for (int j = i+1; j < size*size; j++) {
                if(this.generationTileMap.get(j) == 0) continue;
                if(current > this.generationTileMap.get(j)) inverseCount++;
            }
        }
        return inverseCount;
    }
    public boolean isSolvable(){
        int inverseCount = this.getInverseCount();
        if(size%2 == 1){
            if(inverseCount%2 == 0) return true;
        }
        else{
            int zeroRow = 0; //dari Bawah
            for (int i = 0; i < size*size; i++) {
                if(this.generationTileMap.get(i) == 0) zeroRow = size-toY(i);
            }
            if(zeroRow%2 != inverseCount%2){
                return true;
            }
        }
        return false;
    }
}
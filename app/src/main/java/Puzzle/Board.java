package Puzzle;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Board extends Group{

    public static EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent arg0) {
            Tile tile = (Tile)arg0.getSource();
            Tile zeroTile = tile.board.getZeroTile();
            int zeroX = zeroTile.getPosX();
            int zeroY = zeroTile.getPosY();
            System.out.println(tile.board.isInPlace(tile));
            if(tile.isMovable() && !GameController.isPaused()){
                zeroTile.moveTo(tile.getPosX(),tile.getPosY());
                tile.moveTo(zeroX,zeroY);
                tile.board.setMovableIndexes();
                System.out.println("is Solved : " + tile.board.isSolved());
                tile.board.update(tile);
                if(tile.board.isSolved()){
                    GameController.showWonDialog();
                }
            }
        }
    };
    public static int gap = 4;
    public int size = 4;
    public int pixelSizeX = 400;
    public int pixelSizeY = 400;
    public ArrayList<Integer> generationTileMap;
    private ArrayList<Integer> correctTileMap;
    public ArrayList<Integer> movableIndexes = new ArrayList<Integer>(4); // [top,right,bottom,left] (-1 = none)

    private int toX(int index){
        return index%size;
    }
    private int toY(int index){
        return index/size;
    }
    private int toIndex(int x, int y){
        return (y*size) + x;
    }

    public Board(int size, int pixelSizeX, int pixelSizeY){
        super();
        this.size = size;
        this.pixelSizeX = pixelSizeX;
        this.pixelSizeY = pixelSizeY;
        this.generationTileMap = new ArrayList<Integer>(size*size);   // fill generation with tile map with correct order
        this.getChildren().add(Tile.createInvisibleTile(this,size-1, size-1)); // create and add empty tile (number 0)
        for (int i = 1; i < size*size; i++) { // create remaining tile with correct order
            Tile tile = new Tile(this,i,toX(i-1) , toY(i-1));
            tile.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            this.getChildren().add(tile);
            this.generationTileMap.add(tile.getNumber());
        }
        this.generationTileMap.add(0);
        this.setMovableIndexes();
        // movableIndexes = new ArrayList<Integer>(Arrays.asList(size*size-size-1,-1,-1,size*size-2));
        correctTileMap = (ArrayList<Integer>)this.generationTileMap.clone(); // The correct tile map
    }
    public Tile getZeroTile(){
        Tile zeroTile = (Tile)this.getChildren().get(0);
        return zeroTile;
    }
    public void setMovableIndexes(){
        this.movableIndexes = new ArrayList<Integer>(Arrays.asList(-1,-1,-1,-1));
        Tile zeroTile = this.getZeroTile();
        if(zeroTile.getPosY()-1 >= 0) this.movableIndexes.set(0,zeroTile.getIndex()-size);
        if(zeroTile.getPosX()+1 < size) this.movableIndexes.set(1,zeroTile.getIndex()+1);
        if(zeroTile.getPosY()+1 < size) this.movableIndexes.set(2,zeroTile.getIndex()+size);
        if(zeroTile.getPosX()-1 >= 0) this.movableIndexes.set(3,zeroTile.getIndex()-1);
    }
    public void generateTiles(){ // move tiles' position based on generation tile map
        for (int i = 0; i < size*size; i++) {
            Tile tile = (Tile)(this.getChildren().get(this.generationTileMap.get(i)));
            tile.moveTo(toX(i),toY(i));
        }
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
        generateTiles();
        this.setMovableIndexes();
        this.update();
    }
    public void update(Tile tile){
        if(isInPlace(tile)){
            ((Text)tile.getChildren().get(1)).setFill(Tile.textColor1);
        }
        else{
            ((Text)tile.getChildren().get(1)).setFill(Tile.textColor0);
        }
    }
    public void update(){
        for (int i = 1; i < size*size; i++) {
            Tile tile = (Tile)this.getChildren().get(i);
            update(tile);
        }
    }
    public boolean isInPlace(Tile tile){
        if(tile.getNumber() == this.correctTileMap.get(tile.getIndex())){
            return true;
        }
        return false;
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
    public boolean isSolved(){
        for (int i = 1; i < size*size; i++) {
            Tile tile = (Tile)this.getChildren().get(i);
            if(tile.getIndex()+1 != tile.getNumber()) return false;
        }
        return true;
    }
}
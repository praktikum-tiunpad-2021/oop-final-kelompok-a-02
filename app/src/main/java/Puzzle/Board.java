package Puzzle;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

public class Board extends Group{
    public static int size = 4;
    public static int pixelSizeX = 400;
    public static int pixelSizeY = 400;
    public ArrayList<Integer> tiles;

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
        this.tiles = new ArrayList<Integer>(size*size);
        for (int i = 0; i < size*size-1; i++) {
            Tile tile = new Tile(i+1,toX(i) , toY(i));
            this.add(tile);
        }
        this.tiles.add(0);
    }
    public void add(Tile tile){
        this.getChildren().add(tile);
        this.tiles.add(tile.getNumber());
    }

    public void shuffle(){
        this.tiles.clear();
        ArrayList<Integer> remains = new ArrayList<Integer>();
        for (int i = 0; i < size*size; i++) {
            remains.add(i);
        }
        for (int i = 0; i < size*size; i++) {
            int rand = ThreadLocalRandom.current().nextInt(0, remains.size());
            this.tiles.add(remains.get(rand));
            remains.remove(rand);
        }
        if(!this.isSolvable()){
            this.tiles.set(this.tiles.indexOf(1),-1);
            this.tiles.set(this.tiles.indexOf(2),1);
            this.tiles.set(this.tiles.indexOf(-1),2);
        }
    }
    public void generateTiles(){
        // boolean foundZero = false;
        int ii = 0;
        for (int i = 0; i < size*size; i++) {
            if(this.tiles.get(i) == 0) continue;
            Tile tile = (Tile)(this.getChildren().get(this.tiles.get(i)-1));
            tile.moveTo(toX(i),toY(i));
            ii++;
        }
    }
    public int getInverseCount(){
        int inverseCount = 0;
        for (int i = 0; i < size*size; i++) {
            if(this.tiles.get(i) == 0) continue;
            int current = this.tiles.get(i);
            for (int j = i+1; j < size*size; j++) {
                if(this.tiles.get(j) == 0) continue;
                if(current > this.tiles.get(j)) inverseCount++;
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
                if(this.tiles.get(i) == 0) zeroRow = size-toY(i);
            }
            if(zeroRow%2 != inverseCount%2){
                return true;
            }
        }
        return false;
    }
}
package Puzzle;

import java.util.ArrayList;

public class IntArray2D extends ArrayList<ArrayList<Integer>>{
    public Integer rows;
    public Integer cols;

    public IntArray2D(Integer rows, Integer cols){
        super(rows);
        this.rows = rows;
        this.cols = cols;
        for (Integer i = 0; i < rows; i++) {
            this.add(new ArrayList<Integer>(cols));
            for (Integer j = 0; j < cols; j++) {
                this.get(i).add(0);
            }
        }
    }
    public Integer get(Integer row, Integer col){
        return this.get(row).get(col);
    }
    public Integer iget(Integer index){
        Integer row = index/this.cols;
        Integer col = index%this.cols;
        
        return this.get(row,col);
    }
    public void set(Integer row, Integer col, Integer element){
        this.get(row).set(col, element);
    }
    public void iset(Integer index, Integer element){
        Integer row = index/this.cols;
        Integer col = index%this.cols;

        this.set(row, col, element);
    }
    public boolean icontains(Integer obj){
        for (Integer i = 0; i < rows; i++) {
            for (Integer j = 0; j < cols; j++) {
                System.out.println(this.get(i, j));
                if(this.get(i, j) == obj) return true;
            }
        }
        return false;
    }
}

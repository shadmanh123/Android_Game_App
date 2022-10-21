package ca.cmpt276.as3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineSeeker {
    private Singleton singleton = Singleton.getInstance();
    private int NUM_ROWS;
    private int NUM_COLS;
    private int NUM_MINES;
    private List<Cell> cell = new ArrayList<>();
    private static MineSeeker instance = null;


    public MineSeeker() {
        NUM_ROWS = singleton.getSavedBoardRow();
        NUM_COLS = singleton.getSavedBoardColumn();
        NUM_MINES = singleton.getSavedMinesValue();
    }

    public static synchronized MineSeeker getInstance() {
        if(instance == null) {
            instance = new MineSeeker();
        }
        return instance;
    }


    // 4 * 6
    //      0   1   2   3   4   5   col

    // 0    0   1   2   3   4   5
    // 1    6   7   8   9   10  11
    // 2    12  13  14  15  16  17
    // 3    18  19  20  21  22  23
    //row

    // eg. 20 (row, col) = (3, 2)
    // 20 = NUM_COLS * row + col
    // 20 = 6 * 3 + 2
    // make 20 the index of its cell stored in the List

    public void setBlank(){
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                cell.add(NUM_COLS * row + col, new Cell(Cell.BLANK, false, false));
            }
        }
    }

    public void setMines(){
        int current_mines = 0;
        while(current_mines < NUM_MINES){
            int row = new Random().nextInt(NUM_ROWS);
            int col = new Random().nextInt(NUM_COLS);
            if(cellAt(row, col).getValue() == Cell.BLANK){
                cellAt(row, col).setValue(Cell.BOMB);
                current_mines++;
            }
        }
    }

    public Cell cellAt(int row, int col) {
        if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS) {
            return null;
        }
        return cell.get(NUM_COLS * row + col);
    }

//    public boolean isRevealed(int row, int col){
//        return cellAt(row, col).isRevealed();
//    }
//
//    public boolean isScanned(int row, int col){
//        return cellAt(row, col).isScanned();
//    }

    public int countMines(int row, int col){
        int count = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if(i == row || j == col) {
                    if (cellAt(i, j).getValue() == Cell.BOMB && !cellAt(i, j).isRevealed()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int countForAll(int row, int col){
        int count = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if(i == row || j == col) {
                    if (cellAt(i, j).getValue() == Cell.BOMB && !cellAt(i, j).isRevealed()) {
                        count++;
                    }else if(cellAt(i, j).getValue() == Cell.BOMB && cellAt(i, j).isRevealed()){
                        if(count > 0){
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }
}

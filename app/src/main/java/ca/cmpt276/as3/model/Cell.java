package ca.cmpt276.as3.model;


public class Cell {
    private int value;
    private boolean isRevealed;
    private boolean isScanned;
    public static final int BOMB = -1;
    public static final int BLANK = 0;
    //private static Cell instance = null;

//    public Cell() {
//    }
//
//    public static synchronized Cell getInstance() {
//        if(instance == null) {
//            instance = new Cell();
//        }
//        return instance;
//    }

    public Cell(int value, boolean isRevealed, boolean isScanned) {
        this.value = value;
        this.isRevealed = isRevealed;
        this.isScanned = isScanned;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isScanned() {
        return isScanned;
    }

    public void setScanned(boolean scanned) {
        isScanned = scanned;
    }
}

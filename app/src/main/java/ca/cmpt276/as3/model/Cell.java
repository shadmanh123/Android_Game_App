package ca.cmpt276.as3.model;
/**
 * Set and Get each cell(btn) value, if its bomb or blank, isRevealed, isScanned,
 * and number of HiddenMines on its row and col
 */

public class Cell {
    private int value;
    private boolean isRevealed;
    private boolean isScanned;
    private int numberOfHiddenMines;
    public static final int BOMB = -1;
    public static final int BLANK = 0;


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

    public int getNumberOfHiddenMines() {
        return numberOfHiddenMines;
    }

    public void setNumberOfHiddenMines(int numberOfHiddenMines) {
        this.numberOfHiddenMines = numberOfHiddenMines;
    }
}

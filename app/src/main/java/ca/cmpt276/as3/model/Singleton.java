package ca.cmpt276.as3.model;

public class Singleton {

    private static Singleton instance = null;
    private int savedMinesValue;
    private int savedBoardRow;
    private int savedBoardColumn; // default value


    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int getSavedMinesValue() {
        return savedMinesValue;
    }

    public void setSavedMinesValue(int savedMinesValue) {
        this.savedMinesValue = savedMinesValue;
    }

    public int getSavedBoardRow() {
        return savedBoardRow;
    }

    public void setSavedBoardRow(int savedBoardRow) {
        this.savedBoardRow = savedBoardRow;
    }

    public int getSavedBoardColumn() {
        return savedBoardColumn;
    }

    public void setSavedBoardColumn(int savedBoardColumn) {
        this.savedBoardColumn = savedBoardColumn;
    }

}

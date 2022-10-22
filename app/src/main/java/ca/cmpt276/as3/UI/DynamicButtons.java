package ca.cmpt276.as3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ca.cmpt276.as3.R;
import ca.cmpt276.as3.model.Cell;
import ca.cmpt276.as3.model.MineSeeker;
import ca.cmpt276.as3.model.Singleton;

public class DynamicButtons extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    private Singleton singleton = Singleton.getInstance();
    private MineSeeker mineSeeker= MineSeeker.getInstance();
    private int NUM_ROWS = 10;  // add values >= options values to fix java.lang.ArrayIndexOutOfBoundsException
    private int NUM_COLS = 15;  // fix java.lang.ArrayIndexOutOfBoundsException
    private int NUM_MINES;
    private int FOUND_MINES = 0;
    private int SCANS_USED = 0;
    private int countMine = 0;
//    private int[][] countMines = new int[NUM_ROWS][NUM_COLS];
    //private List<Cell> cell = new ArrayList<>();
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];


    public static Intent makeIntent(Context context){
        return new Intent(context, DynamicButtons.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the app action bar
        setContentView(R.layout.activity_dynamic_buttons);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        lockButtonSizes();
//    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        populateButtons();
    }


    private void populateButtons() {
        MediaPlayer blank_sound = MediaPlayer.create(this, R.raw.blank_sound);
        MediaPlayer bomb_sound = MediaPlayer.create(this, R.raw.bomb_sound);

        // use singleton to get the values stored in the singleton
        NUM_ROWS = singleton.getSavedBoardRow();
        NUM_COLS = singleton.getSavedBoardColumn();
        NUM_MINES = singleton.getSavedMinesValue();
        TextView found = findViewById(R.id.foundMines);
        found.setText("Found " + FOUND_MINES + " of " + NUM_MINES + " mines.");
        TextView scanned = findViewById(R.id.scanUsed);
        scanned.setText("# Scans used: " + SCANS_USED);
        TextView time = findViewById(R.id.timesPlayed);
        //time.setText("Times Played: ");

        mineSeeker.setBlank();
        mineSeeker.setMines();

        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++) {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                //button.setText("" + col + "," + row);
                button.setPadding(0,0,0,0);
                // make text not clip on small buttons
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        found.setText("Found " + FOUND_MINES + " of " + NUM_MINES + " mines.");
                        scanned.setText("# Scans used: " + SCANS_USED);

                        //countMine = mineSeeker.countForAll(FINAL_ROW, FINAL_COL);

                        switch (mineSeeker.cellAt(FINAL_ROW, FINAL_COL).getValue()){
                            case Cell.BOMB:
                                if(!mineSeeker.cellAt(FINAL_ROW, FINAL_COL).isRevealed()){
                                bomb_sound.start();
                                gridButtonClicked(FINAL_ROW, FINAL_COL); // set bomb image
                                FOUND_MINES++;
                                //button.setText(" " + count);
                                mineSeeker.cellAt(FINAL_ROW, FINAL_COL).setRevealed(true);
                                }else if(mineSeeker.cellAt(FINAL_ROW, FINAL_COL).isRevealed() && !mineSeeker.cellAt(FINAL_ROW, FINAL_COL).isScanned()) {
                                    //button.setText(" " + countMine);
                                    SCANS_USED++;
                                    mineSeeker.cellAt(FINAL_ROW, FINAL_COL).setScanned(true);
                                }
                                //button.setText(" " + countMine);
                                break;

                            case Cell.BLANK:
                                if(!mineSeeker.cellAt(FINAL_ROW, FINAL_COL).isScanned()){
                                    //button.setText(" " + countMine);
                                    blank_sound.start();
                                    SCANS_USED++;
                                    mineSeeker.cellAt(FINAL_ROW, FINAL_COL).setScanned(true);
                                }else {
                                    //button.setText(" " + countMine);
                                    blank_sound.start();
                                }
                                break;


                        }

                        if(FOUND_MINES >= 0){
                            updateCount(FINAL_ROW, FINAL_COL);
                            handler = new Handler();
                            runnable = new Runnable() {
                                @Override
                                public void run() {
                                    updateMineCount(FINAL_ROW,FINAL_COL);
                                }
                            };
                            handler.postDelayed(runnable, 1000);
                        }

                    }

                });


                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        //Toast.makeText(this, "Button clicked: " + col + "," + row, Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];
        // set button color
        button.setTextColor(getResources().getColor(R.color.white));

        // lock button sizes
        lockButtonSizes(); // useless

        // scale image to button:
        int newWidth = button.getWidth() - 100;
        int newHeight = button.getHeight() - 100;
        // Image from Crystal Clear icon set, under LGPL
        // http://commons.wikimedia.org/wiki/Crystal_Clear
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_treasure);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));


        // change text on buttons
         //button.setText("" + col);
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
    }


    private void updateCount(int row, int col){
        countMine = mineSeeker.countForAll(row, col);
        mineSeeker.cellAt(row,col).setNumberOfHiddenMines(countMine);
        Button button = buttons[row][col];
        button.setText(" " + countMine);
    }


    public void updateMineCount(int row, int column){
        int exposedMineCount = 0;
        int hiddenMines = 0;
        Button button;
        countMine = mineSeeker.countForAll(row,column);
        mineSeeker.cellAt(row,column).setNumberOfHiddenMines(countMine);
        for (int rows = 0; rows < NUM_ROWS; rows++) {
            if(mineSeeker.cellAt(rows,column).getValue() == Cell.BOMB && mineSeeker.cellAt(rows,
                    column).isRevealed()){
                exposedMineCount++;
            }
        }
        for (int columns = 0; columns < NUM_COLS; columns++) {
            if (mineSeeker.cellAt(row, columns).getValue() == Cell.BOMB && mineSeeker.cellAt(row,
                    columns).isRevealed()) {
                exposedMineCount++;
            }
        }
        for(int rows = 0; rows < NUM_ROWS; rows++){
            int oldNumberOfMines = mineSeeker.countForAll(row,column);
            hiddenMines = oldNumberOfMines - exposedMineCount;
            if(hiddenMines < 0){
                hiddenMines = 0;
            }
            mineSeeker.cellAt(rows,column).setNumberOfHiddenMines(hiddenMines);

            if(mineSeeker.cellAt(rows,column).isRevealed()){
                button = buttons[rows][column];
                button.setText(" " + hiddenMines);
            }


        }
        for(int columns = 0; columns < NUM_ROWS; columns++){
            int oldNumberOfMines = mineSeeker.countForAll(row,column);
            hiddenMines = oldNumberOfMines - exposedMineCount;
            if(hiddenMines < 0){
                hiddenMines = 0;
            }
            mineSeeker.cellAt(row,columns).setNumberOfHiddenMines(hiddenMines);
            if(mineSeeker.cellAt(row,columns).isRevealed()){
                button = buttons[row][columns];
                button.setText(" " + hiddenMines);
            }

        }
    }


}
package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.cmpt276.as3.model.Cell;
import ca.cmpt276.as3.model.Singleton;

public class DynamicButtons extends AppCompatActivity {


    private Singleton singleton = Singleton.getInstance();
    private int NUM_ROWS = 10;  // add values >= options values to fix java.lang.ArrayIndexOutOfBoundsException
    private int NUM_COLS = 15;  // fix java.lang.ArrayIndexOutOfBoundsException
    private int NUM_MINES;
    private int FOUND_MINES = 0;
    private int SCANS_USED = 0;
    private List<Cell> cell = new ArrayList<>();
    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];


    public static Intent makeIntent(Context context){
        return new Intent(context, DynamicButtons.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the app action bar
        setContentView(R.layout.activity_dynamic_buttons);
        NUM_MINES = singleton.getSavedMinesValue();
        TextView found = findViewById(R.id.foundMines);
        found.setText("Found " + FOUND_MINES + " of " + NUM_MINES + " mines.");
        TextView scanned = findViewById(R.id.scanUsed);
        scanned.setText("#Scans used: " + SCANS_USED);
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
        // use singleton to get the values stored in the singleton
        NUM_ROWS = singleton.getSavedBoardRow();
        NUM_COLS = singleton.getSavedBoardColumn();

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
                button.setText("" + col + "," + row);
                button.setPadding(0,0,0,0);
                // make text not clip on small buttons
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       gridButtonClicked(FINAL_ROW, FINAL_COL);
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
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_mines);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));


        // change text on buttons
         button.setText("" + col);
    }

    private void lockButtonSizes() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinWidth(height);
                button.setMaxWidth(height);

            }
        }
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

    private void setBlank(){
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                cell.add(NUM_COLS * row + col, new Cell(Cell.BLANK, false, false));
            }
        }
    }

    private void setMines(){
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


}
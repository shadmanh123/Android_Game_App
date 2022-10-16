package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class DynamicButtons extends AppCompatActivity {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;

    //private final int NUM_ROWS = getIntent().getIntExtra("Saved_Row", 0);
    //private final int NUM_COLS = getIntent().getIntExtra("Saved_Col", 0);


    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];

    public static Intent makeIntent(Context context){
        return new Intent(context, DynamicButtons.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the app action bar
        setContentView(R.layout.activity_dynamic_buttons);

        populateButtons();
    }

    private void populateButtons() {
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
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }

                });
                        
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons[row][col];

        // lock button sizes
        lockButtonSizes();



        // android:scaleType="fitCenter"



        // does not scale image
//        button.setBackgroundResource(R.drawable.icon_cactus_at);

        // scale image to button:
        // only works in jellybean
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
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


}
package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.model.Singleton;

public class OptionActivity extends AppCompatActivity {

    private Singleton singleton;

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        createRadioBoardButtons();
        createRadioMinesButtons();

        int savedMinesValue = getNumMines(this); // selected num mines
        int savedBoardRow = getBoardRow(this); // selected board size row
        int savedBoardColumn = getBoardColumn(this); // selected board size column
        Toast.makeText(this, "Saved choice: " + savedBoardRow + " x " + savedBoardColumn + " board size, "
                + savedMinesValue + " mines", Toast.LENGTH_SHORT).show();


        // use singleton to set the values stored in the singleton
        singleton = Singleton.getInstance();



        //onBackPressed();
    }

    private void createRadioBoardButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);
        int[] boardSizes = getResources().getIntArray(R.array.board_sizes);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        // create the buttons
        for (int i = 0; i < boardSizes.length; i++) {
            int boardSize= boardSizes[i]; // row
            int numMine = numMines[i];    // column
            String btnText = boardSize + " rows x " + numMine + " columns";
            RadioButton button = new RadioButton(this);
            button.setText(btnText);
            //button.setText(boardSize + " rows x " + numMine + " columns");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You clicked " + btnText, Toast.LENGTH_SHORT).show();
                    saveBoardRow(boardSize);
                    saveBoardColumn(numMine);
                }
            });


            // add to radio group
            group.addView(button);
        }

    }

    private void createRadioMinesButtons(){
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_num_mines);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        // create the buttons
        for (int i = 0; i < numMines.length; i++) {
            final int numMine = numMines[i];
            RadioButton button = new RadioButton(this);
            button.setText(numMine + " mines");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You clicked " + numMine + " mines", Toast.LENGTH_SHORT).show();
                    saveNumMine(numMine);
                }
            });

            // add to radio group
            group.addView(button);
        }
    }


    // save user choice of num mines
    private void saveNumMine(int numMines){
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Num mines", numMines);
        // set the mines stored in the singleton
        singleton.setSavedMinesValue(numMines);
        editor.apply();
    }

    static public int getNumMines(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Num mines", 0);
    }

    // save user choice of board size row
    private void saveBoardRow(int row){
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Board size row", row);
        // set row stored in the singleton
        singleton.setSavedBoardRow(row);
        editor.apply();
    }

    static public int getBoardRow(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Board size row", 0);
    }

    // save user choice of board size column
    private void saveBoardColumn(int column){
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Board size column", column);
        // set column stored in the singleton
        singleton.setSavedBoardColumn(column);
        editor.apply();
    }

    static public int getBoardColumn(Context context){
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Board size column", 0);
    }


//    public void onBackPressed(){
//        startActivity(new Intent(OptionActivity.this, MainMenu.class));
//        finish();
//    }

}

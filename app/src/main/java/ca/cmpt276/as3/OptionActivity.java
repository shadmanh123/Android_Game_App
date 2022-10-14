package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        createRadioBoardButtons();
        createRadioMinesButtons();

    }

    private void createRadioBoardButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_board_size);
        int[] boardSizes = getResources().getIntArray(R.array.board_sizes);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        // create the buttons
        for (int i = 0; i < boardSizes.length; i++) {
            int boardSize= boardSizes[i];
            int numMine = numMines[i];
            RadioButton button = new RadioButton(this);
            button.setText(boardSize + " rows x " + numMine + " columns");

            // add to radio group
            group.addView(button);
        }

    }


    private void createRadioMinesButtons(){
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_num_mines);
        int[] numMines = getResources().getIntArray(R.array.num_mines);

        // create the buttons
        for (int i = 0; i < numMines.length; i++) {
            int numMine = numMines[i];
            RadioButton button = new RadioButton(this);
            button.setText(numMine + " mines");

            // meaningless message
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this, "You clicked " + numMine, Toast.LENGTH_SHORT).show();
                }
            });

            // add to radio group
            group.addView(button);
        }
    }


//    @Override
//    public void onBackPressed() {
//
//    }
}
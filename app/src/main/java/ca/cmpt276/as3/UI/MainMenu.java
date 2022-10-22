package ca.cmpt276.as3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ca.cmpt276.as3.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpOptionsButton();
        setUpPlayButton();
        setUpHelpButton();
    }

    private void setUpOptionsButton(){
        Button optionBtn = (Button)findViewById(R.id.option_btn);
        //btn.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, OptionActivity.class)));
        optionBtn.setOnClickListener(v -> {
            Intent intent = OptionActivity.makeIntent(MainMenu.this);
            startActivity(intent);
        });
    }

    private void setUpPlayButton() {
        Button playBtn = (Button)findViewById(R.id.play_btn);
        playBtn.setOnClickListener(v -> {
            Intent intent = DynamicButtons.makeIntent(MainMenu.this);
            startActivity(intent);
        });
    }

    private void setUpHelpButton() {
        Button helpBtn = (Button)findViewById(R.id.help_btn);
        helpBtn.setOnClickListener(v -> {
            Intent intent = HelpActivity.makeIntent(MainMenu.this);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
//        super.onBackPressed();
    }

}
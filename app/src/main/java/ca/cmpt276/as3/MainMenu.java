package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpOptionsButton();
    }


    private void setUpOptionsButton(){
        Button btn = (Button)findViewById(R.id.option_btn);
        btn.setOnClickListener(v -> startActivity(new Intent(MainMenu.this, OptionActivity.class)));
    }
}
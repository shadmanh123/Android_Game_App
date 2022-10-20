package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        dialog = new Dialog(MainActivity.this);
        startAnimation();
        setUpSkipButton();
    }


    private void setUpSkipButton(){
        Button btn = (Button)findViewById(R.id.skip);
        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainMenu.class)));

    }

    public void startAnimation(){
        dialog.setContentView(R.layout.welcome_message);
        dialog.getWindow().setWindowAnimations(R.style.AnimationsForDialog);
        dialog.show();
    }
}
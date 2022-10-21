package ca.cmpt276.as3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import ca.cmpt276.as3.MainMenu;
import ca.cmpt276.as3.R;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    Runnable runnable;
    private Handler handler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        dialog = new Dialog(MainActivity.this);
        setUpSkipButton();
        startAnimation();
        switchToMainMenu();
    }

    private void switchToMainMenu() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainMenu.class));
            }
        };
        handler.postDelayed(runnable, 4000);
    }

    private void setUpSkipButton(){
        Button btn = (Button)findViewById(R.id.skip);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                startActivity(new Intent(MainActivity.this, MainMenu.class));
            }
        });

    }

    public void startAnimation(){
        dialog.setContentView(R.layout.welcome_message);
        dialog.getWindow().setWindowAnimations(R.style.AnimationsForDialog);
        dialog.show();
    }
}
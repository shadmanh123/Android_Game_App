package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        dialog = new Dialog(MainActivity.this);
        setUpSkipButton();
        startAnimation();
        //TODO: Make activity change 4 seconds after animation is over
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainMenu.class));
            }
        }, 8000);

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
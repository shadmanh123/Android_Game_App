package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSkipButton();

    }


    private void setUpSkipButton(){
        Button btn = (Button)findViewById(R.id.skip);
        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainMenu.class)));

    }


}
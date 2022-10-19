package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import ca.cmpt276.as3.model.WelcomeMessageFragment;

public class MainActivity extends AppCompatActivity {
    TextView welcomeMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        welcomeMessage = findViewById(R.id.tvWelcomeMessage);
        FragmentManager manager = getSupportFragmentManager();
        WelcomeMessageFragment dialog = new WelcomeMessageFragment();
        dialog.show(manager, "Message Dialog");
        //startAnimation();
        setUpSkipButton();
    }


    private void setUpSkipButton(){
        Button btn = (Button)findViewById(R.id.skip);
        btn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainMenu.class)));

    }

    public void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        welcomeMessage.startAnimation(animation );
    }
}
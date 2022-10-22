package ca.cmpt276.as3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import ca.cmpt276.as3.R;

public class HelpActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setupHyperlink();
    }

    private void setupHyperlink() {
        // set hyperlink
        TextView linkTextView = findViewById(R.id.help_text);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
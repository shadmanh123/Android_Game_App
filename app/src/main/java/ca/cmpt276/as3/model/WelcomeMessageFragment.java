package ca.cmpt276.as3.model;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import ca.cmpt276.as3.R;

public class WelcomeMessageFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.welcome_message,null );
        //Build alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Bomb Searcher")
                .setView(v)
                .create();
    }
}

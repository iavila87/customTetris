package ar.edu.ips.aus.seminario2.tetrominos.infra.ui;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ar.edu.ips.aus.seminario2.tetrominos.R;
import ar.edu.ips.aus.seminario2.tetrominos.adapter.HighScoreHelper;

public class GameOverDialog extends DialogFragment {

    private final Activity callingActivity;
    private final HighScoreHelper helper;
    private String playerName;
    public GameOverDialog(Activity activity, HighScoreHelper helper, String playerName) {
        this.callingActivity = activity;
        this.helper = helper;
        this.playerName = playerName;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = null;
        if (helper.newHighScore) {
            view = inflater.inflate(R.layout.dialog_high_score, null);
            builder.setView(view);
            // TODO TP2 prellenar el nombre del jugador obtenido de las SharedPreferences
            /** Traemos EditText para rellenar en el ultimo jugador ingresado */
            EditText editPlayerName= view.findViewById(R.id.player_name);
            if(!playerName.isEmpty()) editPlayerName.setText(playerName);
        }

        final View parentView = view;
        builder.setMessage(helper.message)
                .setPositiveButton(R.string.continue_game, (dialog, which) -> {
                    if (helper.newHighScore) {
                        // TODO TP2 recuperar el nombre ingresado e insertarlo en la BBDD
                        /** Escribimos en el sharedPreferences el nombre del jugador */
                        SharedPreferences pref= callingActivity.getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor playerEditor = pref.edit();
                        EditText editPlayerName= parentView.findViewById(R.id.player_name);
                        playerEditor.putString("playerName",editPlayerName.getText().toString());
                        playerEditor.apply();
                        helper.repo.setPlayerScore(editPlayerName.getText().toString(), helper.playerScore);
                    }
                    callingActivity.finish();
                    Intent newStartIntent = new Intent(getContext(), HallOfFameActivity.class);
                    startActivity(newStartIntent);
                });
        return builder.create();
    }
}

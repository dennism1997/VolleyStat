package Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Identities.Player;
import Identities.Position;
import Identities.PositionAdapter;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 06-07-16.
 */
public class FragmentAddPlayerDialog extends DialogFragment {

    Player newPlayer = null;
    LayoutInflater inflater;
    View view;

    private EditText playerName;
    private EditText playerNumber;
    private Spinner positionSpinner;
    private Position position = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.addplayer_dialog, null);
        playerName = (EditText) view.findViewById(R.id.addPlayerName);
        playerName.requestFocus();
        playerNumber = (EditText) view.findViewById(R.id.addPlayerNumber);

        positionSpinner = (Spinner) view.findViewById(R.id.positionSpinner);
        positionSpinner.setAdapter(new PositionAdapter(view.getContext()));
        positionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = (Position) positionSpinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (isEditTextEmpty(playerName) || isEditTextEmpty(playerNumber)
                                || position == null) {
                            FragmentAddPlayerDialog.this.getDialog().cancel();
                            Toast toast = Toast.makeText(view.getContext(),
                                    "Please fill in all the fields!", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            newPlayer = new Player(getPlayerNumberInt(),
                                    playerName.getText().toString(), position);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentAddPlayerDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }



    public EditText getPlayerName() {
        return playerName;
    }

    public EditText getPlayerNumber() {
        return playerNumber;
    }

    public int getPlayerNumberInt() {
        return Integer.parseInt(playerNumber.getText().toString());
    }

    public Player getNewPlayer() {
        if (newPlayer != null) {
            return newPlayer;
        }
        return null;
    }

    private boolean isEditTextEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public interface AddPlayerDialogListener {
        void onFinish(Player createdPlayer);
    }

}

package Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Adapters.AvailPlayButtonAdapter;
import Identities.Game;
import Identities.Player;
import Identities.Team;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class FragmentNew extends Fragment{

    Team homeTeam;
    GridView availablePlayersGrid;
    AvailPlayButtonAdapter adapter;
    //Team awayTeam;
    private final String HOMETEAM_STORAGE = "homeTeam";

    //Game game;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeTeam = readTeamFromStorage();
        availablePlayersGrid = (GridView) view.findViewById(R.id.availablePlayersGridView);
        adapter = new AvailPlayButtonAdapter(view.getContext(), homeTeam.getPlayers());
        availablePlayersGrid.setAdapter(adapter);

        if (homeTeam.getPlayers().size() < 6) {
            showInvalidTeamDialog();
        }
    }

    private Team readTeamFromStorage() {
        Team result = new Team(new ArrayList<Player>(), "temp");
        FileInputStream fis;

        try {
            fis = getActivity().openFileInput(HOMETEAM_STORAGE);
            ObjectInputStream oi = new ObjectInputStream(fis);
            result = (Team) oi.readObject();
            oi.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            Context context = getView().getContext();
            //TODO implement edit
            CharSequence text = "Storage Reading Error";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return result;
    }

    private void showInvalidTeamDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialogInvalidTeamTitle));
        builder.setMessage(Html.fromHtml(getString(R.string.dialogInvalidTeamMessage)));
        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.show();
    }
}

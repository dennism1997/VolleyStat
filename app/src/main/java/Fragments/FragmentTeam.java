package Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Identities.Player;
import Adapters.TeamViewAdapter;
import Identities.Team;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class FragmentTeam extends Fragment implements FragmentAddPlayerDialog.AddPlayerDialogListener {

    private final String HOMETEAM_STORAGE = "homeTeam";
    DrawerLayout coordinatorLayout;
    FloatingActionButton addPlayerButton;
    ListView playerListView;
    TeamViewAdapter playerListAdapter;
    Toolbar toolbar;
    //ArrayList<Player> playerArray;
    Team team;
    final String PREFS_NAME = "sharedPrefs";
    private boolean firstTime = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_team, container, false);

        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("firstTime", true)) {
            firstTime = true;

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("firstTime", false).apply();
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFAB(view);
        setListView(view);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.playerListView) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.team_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.teamContextEdit:
                Context context = getView().getContext();
                //TODO implement edit
                CharSequence text = "Will be added soon!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            case R.id.teamContextDelete:
                deletePlayer(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void setListView(View view) {
        //playerArray = new ArrayList<Player>();
        team = new Team(new ArrayList<Player>(), "temp");
        if(!firstTime) {
            team = readTeamFromStorage();
        }

        if (team.isEmpty()) {
            showEmptyTeamDialog();
        }

        playerListView = (ListView) view.findViewById(R.id.playerListView);
        updatePlayerList();
        registerForContextMenu(playerListView);
    }



    private void setFAB(View view) {
        coordinatorLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        addPlayerButton = (FloatingActionButton) view.findViewById(R.id.addPlayerButton);
        addPlayerButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddPlayerDialog(view);
            }
        });
    }

    private void showAddPlayerDialog(View view) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentAddPlayerDialog addPlayerDialog = new FragmentAddPlayerDialog();
        addPlayerDialog.setTargetFragment(this, 0);
        addPlayerDialog.show(getActivity().getFragmentManager(), "addPlayer");
    }


    @Override
    public void addPlayer(Player createdPlayer) {
        CharSequence text = "A player with that number already exists";
        if(!team.containsNumber(createdPlayer.getNumber())) {
            team.addPlayer(createdPlayer);
            updatePlayerList();
            text = "Player " + createdPlayer.getName() + " added";
        }


        Toast toast = Toast.makeText(getView().getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void updatePlayerList() {
        playerListAdapter = new TeamViewAdapter(getView().getContext(), team.getPlayers());
        playerListView.setAdapter(playerListAdapter);
        writeTeamToStorage();
    }

    private void writeTeamToStorage() {
        try {
            FileOutputStream outputStream = getActivity().openFileOutput(HOMETEAM_STORAGE, Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(outputStream);
            of.writeObject(team);
            of.flush();
            of.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Context context = getView().getContext();
            //TODO implement edit
            CharSequence text = "Storage Writing Error";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private Team readTeamFromStorage() {
        Team result = team;
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

    private void deletePlayer(int index) {
        Context context = getView().getContext();
        CharSequence text = "Player " + team.getPlayers().get(index).getName() + " deleted";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        team.removePlayer(index);
        updatePlayerList();
    }

    private void showEmptyTeamDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialogTeamIsEmptyTitle));
        builder.setMessage(getString(R.string.dialogTeamIsEmptyMessage));
        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.show();
    }
}

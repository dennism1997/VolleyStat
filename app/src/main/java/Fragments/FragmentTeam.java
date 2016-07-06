package Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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

import java.util.ArrayList;

import Identities.Player;
import Identities.Position;
import Identities.TeamViewAdapter;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class FragmentTeam extends Fragment implements FragmentAddPlayerDialog.AddPlayerDialogListener {

    DrawerLayout coordinatorLayout;
    FloatingActionButton addPlayerButton;
    ListView playerListView;
    TeamViewAdapter playerListAdapter;
    Toolbar toolbar;
    ArrayList<Player> playerArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_team, container, false);
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
        if (v.getId()==R.id.playerListView) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.team_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.teamContextEdit:
                return true;
            case R.id.teamContextDelete:
                deletePlayer(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void setListView(View view) {
        playerArray = new ArrayList<Player>();
        playerArray.add(new Player(2, "Dennis", Position.SETTER, true));
        playerArray.add(new Player(134, "Tom", Position.MIDDLE, true));


        playerListView = (ListView) view.findViewById(R.id.playerListView);
        playerListAdapter = new TeamViewAdapter(view.getContext(), playerArray);
        playerListView.setAdapter(playerListAdapter);
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
        playerArray.add(createdPlayer);
        updatePlayerList();
        Context context = getView().getContext();
        //TODO insert player name
        CharSequence text = "Player " + createdPlayer.getName() +  " added!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void updatePlayerList(){
        playerListAdapter = new TeamViewAdapter(getView().getContext(), playerArray);
        playerListView.setAdapter(playerListAdapter);
    }

    private void deletePlayer(int index) {
        Context context = getView().getContext();
        CharSequence text = "Player " + playerArray.get(index).getName() +  " deleted!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        playerArray.remove(index);
        updatePlayerList();

    }

}

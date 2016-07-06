package Fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Identities.Player;
import Identities.TeamViewAdapter;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class FragmentTeam extends Fragment{

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

    private void setListView(View view) {
        playerArray = new ArrayList<Player>();
        playerArray.add(new Player(2, "Dennis", Player.Position.SETTER, true));
        playerArray.add(new Player(134, "Tom", Player.Position.MIDDLE, true));


        playerListView = (ListView) view.findViewById(R.id.playerListView);
        playerListAdapter = new TeamViewAdapter(view.getContext(), playerArray);
        playerListView.setAdapter(playerListAdapter);
    }

    private void setFAB(View view) {
        coordinatorLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        addPlayerButton = (FloatingActionButton) view.findViewById(R.id.addPlayerButton);
        addPlayerButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                //TODO insert player name
                CharSequence text = "Player added!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                showAddPlayerDialog(view);


            }
        });
    }

    private void showAddPlayerDialog(View view) {
        FragmentAddPlayerDialog addPlayerDialog = new FragmentAddPlayerDialog();
        addPlayerDialog.show(getActivity().getFragmentManager(), "addPlayer");
        //TODO dialog to add player
        //playerArray.add(addPlayerDialog.getNewPlayer());
        playerListAdapter = new TeamViewAdapter(view.getContext(), playerArray);
        playerListView.setAdapter(playerListAdapter);
    }


}

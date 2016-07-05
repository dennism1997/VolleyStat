package Fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class FragmentTeam extends Fragment{

    DrawerLayout coordinatorLayout;
    FloatingActionButton addPlayerButton;
    ListView playerListView;
    ArrayAdapter<String> playerListAdapter;

    String[] playerArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFAB(view);

        playerArray = new String[3];
        playerArray[0] = "hoi";
        playerArray[1] = "hoi2";


        playerListView = (ListView) view.findViewById(R.id.playerListView);
        playerListAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, playerArray);
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
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}

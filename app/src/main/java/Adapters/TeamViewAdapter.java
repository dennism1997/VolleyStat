package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Identities.Player;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class TeamViewAdapter extends ArrayAdapter<Player>{

    public TeamViewAdapter(Context context, ArrayList<Player> playerArrayList){
        super(context, 0, playerArrayList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Get the data item for this position
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, parent, false);
        }
        // Lookup view for data population
        TextView playerName = (TextView) view.findViewById(R.id.playerName);
        TextView playerNumber = (TextView) view.findViewById(R.id.playerNumber);
        TextView playerPosition = (TextView) view.findViewById(R.id.PlayerPosition);
        // Populate the data into the template view using the data object
        playerName.setText(player.getName());
        playerNumber.setText(String.valueOf(player.getNumber()));
        playerPosition.setText(player.getPosition().toString());
        // Return the completed view to render on screen
        return view;
    }
}

package Identities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import moumou.com.volleystat.R;

/**
 * Created by dennis on 05-07-16.
 */
public class TeamViewAdapter extends ArrayAdapter<Player>{

    public TeamViewAdapter(Context context, ArrayList<Player> playerArrayList){
        super(context, 0, playerArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, parent, false);
        }
        // Lookup view for data population
        TextView playerName = (TextView) convertView.findViewById(R.id.playerName);
        TextView playerNumber = (TextView) convertView.findViewById(R.id.playerNumber);
        TextView playerPosition = (TextView) convertView.findViewById(R.id.PlayerPosition);
        // Populate the data into the template view using the data object
        playerName.setText(player.getName());
        playerNumber.setText(String.valueOf(player.getNumber()));
        playerPosition.setText(player.getPosition().toString().toLowerCase());
        // Return the completed view to render on screen
        return convertView;
    }
}

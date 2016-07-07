package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import Identities.Player;
import moumou.com.volleystat.R;

/**
 * Created by dennis on 07-07-16.
 */
public class AvailPlayButtonAdapter extends ArrayAdapter<Player>{

    public AvailPlayButtonAdapter(Context context, ArrayList<Player> playerArrayList){
        super(context, 0, playerArrayList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.availableplayer_griditem, parent, false);
        }

        CheckBox available = (CheckBox) view.findViewById(R.id.playerAvailableCheckbox);
        //available.setChecked(true);
        TextView number = (TextView) view.findViewById(R.id.playerNumber);
        number.setText(String.valueOf(getItem(position).getNumber()));
        return view;
    }
}

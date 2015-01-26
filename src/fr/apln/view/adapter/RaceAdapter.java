package fr.apln.view.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.apln.model.Race;
import fr.apln.view.R;

/**
 * Custom adapter to represent race list data
 * @author Thiebaud Thomas
 *
 */
public class RaceAdapter extends ArrayAdapter<Race>{

	Context context;
    List<Race> drawerItemList;
    int layoutResID;

    /**
     * Constructor
     * @param context Context
     * @param layoutResourceID Layout to represent a data
     * @param listItems List of items
     */
    public RaceAdapter(Context context, int layoutResourceID,List<Race> listItems) {
          super(context, layoutResourceID, listItems);
          this.context = context;
          this.drawerItemList = listItems;
          this.layoutResID = layoutResourceID;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          View view = convertView;
          if (view == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(layoutResID, parent, false);
          }
          Race race = (Race) this.drawerItemList.get(position);
          TextView name = (TextView) view.findViewById(R.id.race_name);
          name.setText(race.getName());

          return view;
    }
}

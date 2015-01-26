package fr.apln.view.adapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.apln.model.Race;
import fr.apln.view.R;

public class ResultsAdapter extends ArrayAdapter<Race> {
	Context context;
	List<Race> drawerItemList;
	int layoutResID;

	/**
	 * Constructor
	 * @param context Context
	 * @param layoutResourceID Layout to represent a data
	 * @param listItems List of items
	 */
	public ResultsAdapter(Context context, int layoutResourceID,List<Race> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int millis = 0;

		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layoutResID, parent, false);
		}
		Race race = (Race) this.drawerItemList.get(position);
		System.out.println(race.getName());
		System.out.println(race.getTime());

		TextView name = (TextView) view.findViewById(R.id.results_name);
		name.setText(race.getName());

		TextView time = (TextView) view.findViewById(R.id.results_time);
		
		millis = race.getTime();
		
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		time.setText(hms);

		return view;
	}
}

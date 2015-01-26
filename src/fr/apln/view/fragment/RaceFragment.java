package fr.apln.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.apln.controller.listener.AllRaceListener;
import fr.apln.controller.listener.OneRaceListener;
import fr.apln.controller.services.RaceServices;
import fr.apln.model.Race;
import fr.apln.view.R;
import fr.apln.view.adapter.RaceAdapter;

/**
 * Choose race view
 * @author Thomas Thiebaud
 *
 */
public final class RaceFragment extends Fragment implements OnItemClickListener {
	private RaceAdapter adapter;
	private List<Race> races = new ArrayList<Race>();
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_race, container, false);
		
		listView = (ListView) v.findViewById(R.id.race_list);

		adapter = new RaceAdapter(getActivity(), R.layout.race_item,races);
		
		RaceServices.all(new AllRaceListener(adapter));
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);
		return v;
	}
	
	@Override
	public void onItemClick(final AdapterView<?> adapter, View view, final int position, long arg3) {
		Race race = (Race) adapter.getItemAtPosition(position);
		RaceServices.one(race,new OneRaceListener(getActivity(),adapter,position));
	}
}

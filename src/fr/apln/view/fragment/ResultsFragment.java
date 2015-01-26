package fr.apln.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.apln.controller.listener.ResultsListener;
import fr.apln.controller.services.RaceServices;
import fr.apln.model.Race;
import fr.apln.view.R;
import fr.apln.view.adapter.ResultsAdapter;

/**
 * Results view
 * @author Thomas Thiebaud
 *
 */
public class ResultsFragment extends Fragment {
	private ResultsAdapter adapter;
	private List<Race> races = new ArrayList<Race>();
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_results, container, false);
		
		listView = (ListView) v.findViewById(R.id.results_list);

		adapter = new ResultsAdapter(getActivity(), R.layout.results_item,races);
		
		RaceServices.results(new ResultsListener(adapter));
		listView.setAdapter(adapter);
		return v;
	}
}

package fr.apln.view.fragment;

import static fr.apln.controller.services.Constants.JSON_OBJECT;
import static fr.apln.controller.services.Constants.JSON_RACE_ID;
import static fr.apln.controller.services.Constants.JSON_RACE_NAME;
import static fr.apln.controller.services.Constants.JSON_TREE_CODE;
import static fr.apln.controller.services.Constants.JSON_TREE_CROWN_DIAMETER;
import static fr.apln.controller.services.Constants.JSON_TREE_GENRE;
import static fr.apln.controller.services.Constants.JSON_TREE_HEIGHT;
import static fr.apln.controller.services.Constants.JSON_TREE_ID;
import static fr.apln.controller.services.Constants.JSON_TREE_LATITUDE;
import static fr.apln.controller.services.Constants.JSON_TREE_LONGITUDE;
import static fr.apln.controller.services.Constants.JSON_TREE_NAME;
import static fr.apln.controller.services.Constants.JSON_TREE_SPECIES;
import static fr.apln.controller.services.Constants.JSON_TREE_TRUNK_DIAMETER;
import static fr.apln.controller.services.Constants.JSON_TREE_TYPE;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.apln.controller.MainController;
import fr.apln.controller.services.RaceServices;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Race;
import fr.apln.model.Tree;
import fr.apln.view.R;
import fr.apln.view.adapter.RaceAdapter;

public class RaceFragment extends Fragment implements OnItemClickListener, TaskListener {
	private RaceAdapter adapter;
	private List<Race> races = new ArrayList<Race>();
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_race, container, false);
		
		listView = (ListView) v.findViewById(R.id.race_list);

		adapter = new RaceAdapter(getActivity(), R.layout.race_item,races);
		
		RaceServices.all(this);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);
		return v;
	}
	
	@Override
	public void onItemClick(final AdapterView<?> adapter, View view, final int position, long arg3) {
		Race race = (Race) adapter.getItemAtPosition(position);
		
		TaskListener oneRaceListener = new TaskListener() {	
			@Override
			public void onSuccess(String content) {
				System.out.println(content);
				Race race = (Race) adapter.getItemAtPosition(position);
				
				try { 
					JSONObject treesList = new JSONObject(content);
					JSONArray treesArray = treesList.getJSONObject("content").getJSONArray("trees");
					
					for(int i=0; i<treesArray.length(); i++){
						JSONObject t = treesArray.getJSONObject(i);
						Tree tree = new Tree();
						tree.setId(t.getString(JSON_TREE_ID));
						tree.setCode(t.getString(JSON_TREE_CODE));
						tree.setName(t.getString(JSON_TREE_NAME));
						tree.setHeight(t.getDouble(JSON_TREE_HEIGHT));
						tree.setTrunkDiameter(t.getDouble(JSON_TREE_TRUNK_DIAMETER));
						tree.setCrownDiameter(t.getDouble(JSON_TREE_CROWN_DIAMETER));
						tree.setLongitude(t.getDouble(JSON_TREE_LONGITUDE));
						tree.setLatitude(t.getDouble(JSON_TREE_LATITUDE));
						tree.setGenre(t.getString(JSON_TREE_GENRE));
						tree.setSpecies(t.getString(JSON_TREE_SPECIES));
						tree.setType(t.getString(JSON_TREE_TYPE));
						
						race.addTree(tree);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				MainController.getInstance().setCurrentRace(race);
				MainController.getInstance().replaceFragment(R.id.content_frame, new PlayFragment(), getActivity());
			}
			
			@Override
			public void onFailure(ErrorCode errCode) {
				System.out.println(errCode);
			}
		};
		
		RaceServices.one(race,oneRaceListener);
	}

	@Override
	public void onSuccess(String content) {
		List<Race> races = new ArrayList<Race>();
		try {
			JSONObject treesList = new JSONObject(content);
			JSONArray treesArray = treesList.getJSONArray(JSON_OBJECT);
			for(int i=0; i<treesArray.length(); i++){
				JSONObject t = treesArray.getJSONObject(i);
				Race race = new Race();
				race.setId(t.getString(JSON_RACE_ID));
				race.setName(t.getString(JSON_RACE_NAME));
				races.add(race);
				adapter.add(race);
				adapter.notifyDataSetChanged();

			}
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFailure(ErrorCode errCode) {
		System.out.println(errCode);
	}
}

package fr.apln.controller.listener;

import static fr.apln.controller.services.Constants.JSON_OBJECT;
import static fr.apln.controller.services.Constants.JSON_RACE_ID;
import static fr.apln.controller.services.Constants.JSON_RACE_NAME;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Race;
import fr.apln.view.adapter.RaceAdapter;

/**
 * @author Thomas Thiebaud 
 * All race listener in order to get all races from database
 */
public class AllRaceListener implements TaskListener {

	private RaceAdapter adapter;
	
	/**
	 * Constructor
	 * @param adapter Reference to the race adapter
	 */
	public AllRaceListener(RaceAdapter adapter) {
		this.adapter = adapter;
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

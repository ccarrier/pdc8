package fr.apln.controller.listener;

import static fr.apln.controller.services.Constants.JSON_OBJECT;
import static fr.apln.controller.services.Constants.JSON_RACE_ID;
import static fr.apln.controller.services.Constants.JSON_RACE_NAME;
import static fr.apln.controller.services.Constants.JSON_RACE_TIME;
import static fr.apln.controller.services.Constants.JSON_RACE_RESULTS;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Race;
import fr.apln.view.adapter.ResultsAdapter;

public class ResultsListener implements TaskListener{

	private ResultsAdapter adapter;
	
	/**
	 * Constructor
	 * @param adapter Reference to the results adapter
	 */
	public ResultsListener(ResultsAdapter adapter) {
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
				race.setTime(t.getJSONArray(JSON_RACE_RESULTS).getJSONObject(0).getInt(JSON_RACE_TIME));
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

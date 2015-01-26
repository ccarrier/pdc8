package fr.apln.controller.listener;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.widget.AdapterView;
import fr.apln.controller.MainController;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Race;
import fr.apln.model.Tree;
import fr.apln.view.R;
import fr.apln.view.fragment.PlayFragment;

/**
 * @author thomasthiebaud
 * One race listener in order to get all informations about one race from the database
 */
public final class OneRaceListener implements TaskListener {

	private Activity activity;
	private AdapterView<?> adapter;
	private int position;
	
	/**
	 * Constructor
	 * @param activity Reference to an activity
	 * @param adapter Reference to an adapter
	 * @param position Position (index) in the adapter
	 */
	public OneRaceListener(Activity activity,AdapterView<?> adapter, int position) {
		this.activity = activity;
		this.adapter = adapter;
		this.position = position;
	}

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
		MainController.getInstance().replaceFragment(R.id.content_frame, new PlayFragment(), activity);
	}
	
	@Override
	public void onFailure(ErrorCode errCode) {
		System.out.println(errCode);
	}

}

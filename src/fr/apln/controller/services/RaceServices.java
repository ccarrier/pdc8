package fr.apln.controller.services;

import static fr.apln.controller.services.Constants.PARAMETER_RACE_ID;
import static fr.apln.controller.services.Constants.PARAMETER_USER_ID;
import static fr.apln.controller.services.Constants.JSON_RACE_NAME;
import static fr.apln.controller.services.Constants.JSON_RACE_TIME;
import static fr.apln.controller.services.Constants.JSON_RACE_TREES;
import static fr.apln.controller.services.Constants.PARAMETER_TREE_ID;
import static fr.apln.controller.services.Constants.SERVICE_RACE_ADD;
import static fr.apln.controller.services.Constants.SERVICE_RACE_ADD_TIME;
import static fr.apln.controller.services.Constants.SERVICE_RACE_ALL;
import static fr.apln.controller.services.Constants.SERVICE_RACE_ONE;
import static fr.apln.controller.services.Constants.SERVICE_RACE_RESULTS;
import static fr.apln.controller.services.Constants.URL_BASE;
import static fr.apln.controller.services.Constants.URL_RACE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.apln.controller.MainController;
import fr.apln.controller.delegate.RequestDelegate;
import fr.apln.controller.listener.ResultsListener;
import fr.apln.controller.utils.TaskListener;
import fr.apln.model.Race;
import fr.apln.model.Tree;

/**
 * @author Thomas Thiebaud
 * Race services to manage operations about races
 */
public class RaceServices {
	static final Gson gson = new Gson();

	/**
	 * Get all races
	 * @param listener Race listener
	 */
	public static void all(TaskListener listener) {
		String url = URL_BASE + URL_RACE + SERVICE_RACE_ALL;
		new RequestDelegate("", url, RequestDelegate.RequestType.GET, listener).execute();
	}
	
	/**
	 * Get one race
	 * @param race Race to get
	 * @param listener race listener
	 */
	public static void one(Race race, TaskListener listener) {
		String url = URL_BASE + URL_RACE + SERVICE_RACE_ONE;
		List<NameValuePair> listParams = new ArrayList<NameValuePair>();
		listParams.add(new BasicNameValuePair(PARAMETER_RACE_ID, race.getId()));

		new RequestDelegate(listParams, url, RequestDelegate.RequestType.GET, listener).execute();	
	}

	/**
	 * Link user, race and final time
	 * @param listener Race listener
	 */
	public static void addTime(TaskListener listener) {
		String url = URL_BASE + URL_RACE + SERVICE_RACE_ADD_TIME;
		String jsonParams = "";
		JsonObject jsonObject = new JsonObject();
		try {
			jsonObject.addProperty(PARAMETER_RACE_ID, URLEncoder.encode(MainController.getInstance().getCurrentRace().getId(), "UTF-8"));
			jsonObject.addProperty(PARAMETER_USER_ID, URLEncoder.encode(MainController.getInstance().getCurrentUser().getId(), "UTF-8"));
			jsonObject.addProperty(JSON_RACE_TIME, URLEncoder.encode(MainController.getInstance().getTotalTime()+"", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		jsonParams = gson.toJson(jsonObject);
		
		new RequestDelegate(jsonParams, url, RequestDelegate.RequestType.POST, listener).execute();
	} 
	
	/**
	 * Add race in database
	 * @param race Race to add
	 * @param listener Race listener
	 */
	public static void add(Race race,TaskListener listener) {
		String url = URL_BASE + URL_RACE + SERVICE_RACE_ADD;
		String jsonParams = "";
		JsonObject jsonObject = new JsonObject();
		JsonArray jsonArray = new JsonArray();
 
		try {
			jsonObject.addProperty(JSON_RACE_NAME, URLEncoder.encode(race.getName(), "UTF-8"));
			for(Tree t : race.getTrees()) {
				JsonObject o = new JsonObject();
				o.addProperty(PARAMETER_TREE_ID, t.getId());
				jsonArray.add(o);
			}
			System.out.println(jsonArray);
			jsonObject.add(JSON_RACE_TREES, jsonArray);
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace(); 
		}
		jsonParams = gson.toJson(jsonObject);
		
		new RequestDelegate(jsonParams, url, RequestDelegate.RequestType.POST, listener).execute();
	}

	/**
	 * Get all races with user's time
	 * @param listener
	 */
	public static void results(ResultsListener listener) {
		String url = URL_BASE + URL_RACE + SERVICE_RACE_RESULTS;
		List<NameValuePair> listParams = new ArrayList<NameValuePair>();
		listParams.add(new BasicNameValuePair(PARAMETER_USER_ID, MainController.getInstance().getCurrentUser().getId()));

		new RequestDelegate(listParams, url, RequestDelegate.RequestType.GET, listener).execute();
	}
	
}

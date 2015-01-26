package fr.apln.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import fr.apln.controller.MainController;
import fr.apln.controller.services.Constants;
import fr.apln.controller.services.RaceServices;
import fr.apln.controller.services.TreeServices;
import fr.apln.controller.utils.ErrorCode;
import fr.apln.controller.utils.TaskListener;
import fr.apln.dao.ArbreDAO;
import fr.apln.dao.CircuitDAO;
import fr.apln.model.Race;
import fr.apln.model.Tree;


public class Services {
	
	public ArbreDAO arbreDAO = new ArbreDAO();
	private CircuitDAO circuitDAO = new CircuitDAO();
	
	//CONSTANT
	private final double DISTANCE_MIN = 0.001;
	private final double DISTANCE_MAX = 0.01;
	private final double RAYON_PARC = 0.006;
	private final LatLng PARC = new LatLng(45.778,4.855);
	
	
	private Tree rechercheArbreProximite(List<Tree> tousArbres,List<Tree> arbresChoisis,double latitude, double longitude)
	{
		double distance_min = 99999;
		Tree arbreVoisin = null;
		for (Tree arbre : tousArbres) {
			double x = arbre.getLongitude() - longitude;
			double y = arbre.getLatitude() - latitude;
			double distance = Math.sqrt(x*x+y*y);
			if(distance < distance_min && !arbresChoisis.contains(arbre))
			{
				distance_min = distance;
				arbreVoisin = arbre;
			}
		}
		return arbreVoisin;
	}
	
	private Tree rechercheArbreSuivant(List<Tree> tousArbres,List<Tree> arbresChoisis,Tree arbre, double angle_troncon)
	{
		Random r = new Random();
		//r.nextDouble(); entre 0 et 1.0
		double rayon,angle,latitude,longitude;
		if(arbre == null)
		{
			rayon = r.nextDouble()*RAYON_PARC;
			angle = 2*Math.PI*r.nextDouble();
			latitude = PARC.latitude + rayon*Math.sin(angle);
			longitude = PARC.longitude + rayon*Math.cos(angle);
		}
		else
		{
			rayon = (DISTANCE_MAX - DISTANCE_MIN)*r.nextDouble() + DISTANCE_MIN;
			angle = Math.PI/2*r.nextDouble()+angle_troncon;
			latitude = arbre.getLatitude() + rayon*Math.sin(angle);
			longitude = arbre.getLongitude()+ rayon*Math.cos(angle);			
		}
		
		return rechercheArbreProximite(tousArbres,arbresChoisis, latitude, longitude);
	}
	


	
	public void generateRaces()
	{
		TaskListener allTreeListener = new TaskListener() {	
		  	@Override
		  	public void onSuccess(String content) {					
				List<Tree> trees = new ArrayList<Tree>();
				try {
					JSONObject treesList = new JSONObject(content);
					JSONArray treesArray = treesList.getJSONArray(Constants.JSON_OBJECT);
								
					for(int i=0; i<treesArray.length(); i++){
						JSONObject t = treesArray.getJSONObject(i);
						Tree tree = new Tree();
						tree.setId(t.getString(Constants.JSON_TREE_ID));
						tree.setCode(t.getString(Constants.JSON_TREE_CODE));
						tree.setName(t.getString(Constants.JSON_TREE_NAME));
						tree.setHeight(t.getDouble(Constants.JSON_TREE_HEIGHT));
						tree.setTrunkDiameter(t.getDouble(Constants.JSON_TREE_TRUNK_DIAMETER));
						tree.setCrownDiameter(t.getDouble(Constants.JSON_TREE_CROWN_DIAMETER));
						tree.setLongitude(t.getDouble(Constants.JSON_TREE_LONGITUDE));
						tree.setLatitude(t.getDouble(Constants.JSON_TREE_LATITUDE));
						tree.setGenre(t.getString(Constants.JSON_TREE_GENRE));
						tree.setSpecies(t.getString(Constants.JSON_TREE_SPECIES));
						tree.setType(t.getString(Constants.JSON_TREE_TYPE));
					 		
						trees.add(tree);
						
					}
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				//Call function generating race here
				Log.d("Services", "Size all trees:  "+trees.size());
				for (int i = 4; i < 15; i+=5) {
					Log.d("Services", "i= "+i);
					List<Tree> treesOfOneRace = generateOneRace(i, trees);
					Log.d("Services", "size race = " +treesOfOneRace.size());
					createRace(treesOfOneRace);
					Log.d("Services", "RAce create succesfully"+i);
					
				}
				
				//MainController.getInstance().setAllTrees(trees);
				
			}

			@Override
			public void onFailure(ErrorCode errCode) {
				System.out.println(errCode);
			} 
		};

		TreeServices.all(allTreeListener);
	}
	
	public List<Tree> generateOneRace(int n,List<Tree> allTrees)
	{
		if(n<2)
			return null;
		List<Tree> sousArbres = new ArrayList<Tree>();
		Tree arbre_tete = rechercheArbreSuivant(allTrees,sousArbres,null, 0);
		sousArbres.add(arbre_tete);
		double angle_troncon = 0;
		for (int i = 0; i < n; i++) {
			Tree arbre_queue;
			if(i==n-1 ) // fin de circuit
				break;
			else
				arbre_queue = rechercheArbreSuivant(allTrees,sousArbres, arbre_tete,angle_troncon);
			if(arbre_queue == null)
				break;
			sousArbres.add(arbre_queue);
			
			double x = arbre_queue.getLongitude() - arbre_tete.getLongitude();
			double y = arbre_queue.getLatitude() - arbre_tete.getLatitude();
			angle_troncon = Math.atan2(y, x);
			
			arbre_tete = arbre_queue;
		}
		
		return sousArbres;
	}
	
	public void createRace(List<Tree> trees)
	{
		TaskListener addRaceListener = new TaskListener() {
			
			@Override
			public void onSuccess(String content) {
				System.out.println(content);
			}
						
			@Override
			public void onFailure(ErrorCode errCode) {
				System.out.println(errCode);
			} 
		};

		Race race = new Race();
		race.setName("Test "+trees.size());
		race.setTrees(trees);
		RaceServices.add(race, addRaceListener);

	}
}

package fr.apln.controller.services;

/**
 * @author Thomas Thiebaud 
 * Define some constants
 */
public class Constants {
	private Constants() {
        // restrict instantiation
	}
	
	public static final String URL_BASE = "https://pdc8.herokuapp.com" ;
	//public static final String URL_BASE = "http://localhost:5000" ;
	
	public static final String URL_RACE = "/race";
	public static final String URL_TREE = "/tree";
	public static final String URL_USER = "/user";
	
	public static final String SERVICE_USER_CONNECT = "/login";
	public static final String SERVICE_TREE_ALL = "/all";
	
	public static final String SERVICE_RACE_ALL = "/all";
	public static final String SERVICE_RACE_ONE = "/one";
	public static final String SERVICE_RACE_ADD = "/add";
	public static final String SERVICE_RACE_ADD_TIME = "/addTime";
	public static final String SERVICE_RACE_RESULTS = "/results";

	public static final String JSON_OBJECT = "content";
	
	public static final String PARAMETER_USER_ID = "userId";
	public static final String JSON_USER_ID = "_id";
	public static final String JSON_USER_NAME = "name";
	
	public static final String PARAMETER_TREE_ID = "treeId";
	public static final String JSON_TREE_ID = "_id";
	public static final String JSON_TREE_NAME = "name";
	public static final String JSON_TREE_CODE = "code";
	public static final String JSON_TREE_HEIGHT = "height";
	public static final String JSON_TREE_TRUNK_DIAMETER = "trunk_diameter";
	public static final String JSON_TREE_CROWN_DIAMETER = "crown_diameter";
	public static final String JSON_TREE_LONGITUDE = "longitude";
	public static final String JSON_TREE_LATITUDE = "latitude";
	public static final String JSON_TREE_GENRE = "genre";
	public static final String JSON_TREE_SPECIES = "species";
	public static final String JSON_TREE_TYPE = "type";
	
	public static final String PARAMETER_RACE_ID = "raceId";
	public static final String JSON_RACE_ID = "_id";
	public static final String JSON_RACE_NAME = "name";
	public static final String JSON_RACE_TREES = "trees";
	public static final String JSON_RACE_TIME = "time";
	public static final String JSON_RACE_RESULTS = "results";
	
}

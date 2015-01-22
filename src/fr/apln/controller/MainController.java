package fr.apln.controller;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import fr.apln.model.Race;
import fr.apln.model.Tree;
import fr.apln.model.User;
import fr.apln.view.R;

public class MainController {
    private static volatile MainController instance = null;

    private static final double rayon_terre = 6378137.0;
    
    private Account account = null;
    
    private User currentUser = null;
    private Race currentRace = null;
    
    private int treeIndex = 0;
    private Tree treeToFind = null;
    
    private long startTime = 0;
    private long endTime = 0;
    
    private MainController() {
        super();
    }

    public final static MainController getInstance() {   
        if (MainController.instance == null) {
           synchronized(MainController.class) {
             if (MainController.instance == null) {
            	 MainController.instance = new MainController();
             }
           }
        }
        return MainController.instance;
    }

	public boolean performGetGoogleAccount(Activity activity) {
		List<Account> googleAccounts = new ArrayList<Account>();
        Account[] accounts = AccountManager.get(activity).getAccounts();
        for (Account account : accounts) {
          if (account.type.equals("com.google")) {
            googleAccounts.add(account);
          }
        }
        if(googleAccounts.size() > 0) {
        	account = googleAccounts.get(0);
        	return true;
        } 
        return false;
	}
	
	public void replaceFragment(int container, Fragment fragment, Activity activity) {
		FragmentManager fragmentManager = activity.getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();
	}
	
	public void addFragment(int container, Fragment fragment, Activity activity) {
		FragmentManager fragmentManager = activity.getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .add(R.id.content_frame, fragment)
	                   .commit();
	}
	
	public Account getAccount() {
		return account;
	}

	public void setCurrentRace(Race race) {
		this.currentRace = race;
		this.treeToFind = race.getTrees().get(0);
	}
	
	public Race getCurrentRace() {
		return this.currentRace;
	}
	
	public Tree getTreeToFind() {
		return this.treeToFind;
	}

    public double distance(double long1, double lat1, double long2, double lat2) {
    	return rayon_terre * Math.acos(Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(long2 - long1)));
    }
    
    public boolean updateTreeToFind() {
    	if(startTime == 0)
    		this.startTime = System.currentTimeMillis();
    	
    	treeIndex ++;
    	
    	if(treeIndex < currentRace.getTrees().size()) {
    		treeToFind = currentRace.getTrees().get(treeIndex);
    		return true;
    	}
    	else {
    		treeIndex = 0;
    		this.endTime = System.currentTimeMillis();
    		return false;
    	}
    }
    
    public long getTotalTime() {
    	return endTime - startTime;
    }

	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
}

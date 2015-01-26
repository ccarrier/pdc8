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

/**
 * @author Thomas Thiebaud 
 * This controller manage some global variables and functions. It use singleton pattern.
 */
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

	/**
	 * Default private constructor
	 */
	private MainController() {
		super();
	}

	/**
	 * Get main controller unique instance
	 * @return Main controller instance
	 */
	public final static MainController getInstance() {   
		if (MainController.instance == null) {
			synchronized(MainController.class) {
				if (MainController.instance == null)
					MainController.instance = new MainController();
			}
		}
		return MainController.instance;
	}

	/**
	 * Check into the android account manager and get the first google account. The name of the
	 * account will be used as the username.
	 * @param activity Reference to an activity 
	 * @return true if a google account is found, false otherwise
	 */
	public boolean performGetGoogleAccount(Activity activity) {
		List<Account> googleAccounts = new ArrayList<Account>();
		Account[] accounts = AccountManager.get(activity).getAccounts();
		for (Account account : accounts) {
			if (account.type.equals("com.google"))
				googleAccounts.add(account);
		}
		if(googleAccounts.size() > 0) {
			account = googleAccounts.get(0);
			return true;
		} 
		return false;
	}

	/**
	 * Replace a fragment in an activity by putting a new fragment inside a given container
	 * @param container Id of the container
	 * @param fragment New fragment
	 * @param activity Reference to an activity
	 */
	public void replaceFragment(int container, Fragment fragment, Activity activity) {
		FragmentManager fragmentManager = activity.getFragmentManager();
		fragmentManager.beginTransaction()
		.replace(container, fragment)
		.commit();
	}

	/**
	 * Get account
	 * @return Account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Set current race
	 * @param race New race
	 */
	public void setCurrentRace(Race race) {
		this.currentRace = race;
		this.treeToFind = race.getTrees().get(0);
	}

	/**
	 * Get current race
	 * @return Current race
	 */
	public Race getCurrentRace() {
		return this.currentRace;
	}

	/**
	 * Get tree to find
	 * @return Tree to find
	 */
	public Tree getTreeToFind() {
		return this.treeToFind;
	}

	/**
	 * Get distance
	 * @param long1 Longitute of the first point
	 * @param lat1 Latitude of the first point
	 * @param long2 Longitude of the second point
	 * @param lat2 Latitude of the second point
	 * @return
	 */
	public double distance(double long1, double lat1, double long2, double lat2) {
		return rayon_terre * Math.acos(Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(long2 - long1)));
	}

	/**
	 * Update the tree to find. Set the start time if the tree is the first. Set the end time if the tree is the last.
	 * @return true if the tree is not the last, false otherwise
	 */
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

	/**
	 * Get the total time of the current race (in millis)
	 * @return Total time
	 */
	public long getTotalTime() {
		return endTime - startTime;
	}

	/**
	 * Set current user
	 * @param user New user
	 */
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	/**
	 * Get current user
	 * @return Current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Reset current race variables
	 */
	public void resetRace() {
		this.startTime = 0;
		this.endTime = 0;
		this.treeIndex = 0;
	}
}

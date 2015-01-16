package fr.apln.controller;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import fr.apln.view.R;

public class MainController {
    private static volatile MainController instance = null;

    private Account account = null;
    
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
}

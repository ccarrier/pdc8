package fr.apln.controller.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.apln.view.activity.MainActivity;

/**
 * 
 * @author Thomas Thiebaud
 * Navigation drawer listener
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {
	private MainActivity mainActivity;
	
	/**
	 * Constructor
	 * @param mainActivity Reference to the main activity
	 */
	public DrawerItemClickListener(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mainActivity.selectItem(position);
    }
}

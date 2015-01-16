package fr.apln.view.activity;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.apln.controller.MainController;
import fr.apln.view.R;
import fr.apln.view.adapter.DrawerAdapter;
import fr.apln.view.element.DrawerItem;
import fr.apln.view.fragment.AboutFragment;
import fr.apln.view.fragment.HomeButtonFragment;
import fr.apln.view.fragment.HomeErrorFragment;
import fr.apln.view.fragment.PlayFragment;
import fr.apln.view.fragment.ResultsFragment;
import fr.apln.view.fragment.RulesFragment;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
		mNavigationArray = new ArrayList<DrawerItem>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
        mNavigationArray.add(new DrawerItem(R.string.home, R.drawable.ic_action_home_green));
        mNavigationArray.add(new DrawerItem(R.string.play, R.drawable.ic_action_playback_play_green));
        mNavigationArray.add(new DrawerItem(R.string.results, R.drawable.ic_action_achievement_green));
        mNavigationArray.add(new DrawerItem(R.string.rules, R.drawable.ic_action_book_green));
        mNavigationArray.add(new DrawerItem(R.string.about, R.drawable.ic_action_info_green));

		mDrawerList.setAdapter(new DrawerAdapter(this,R.layout.drawer_item, mNavigationArray));
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
		
        if(MainController.getInstance().performGetGoogleAccount(this)) {
        	MainController.getInstance().replaceFragment(R.id.content_frame, new HomeButtonFragment(), this);
        	mDrawerToggle.setDrawerIndicatorEnabled(true);
        	getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true); 
        }
        else {
        	MainController.getInstance().replaceFragment(R.id.content_frame, new HomeErrorFragment(), this);
        	mDrawerToggle.setDrawerIndicatorEnabled(false);
        	getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setHomeButtonEnabled(false); 
        }
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
	public void selectItem(int position) {
		Fragment fragment = null;
		
		switch(position) {
			case 1:
				fragment = new PlayFragment();
				break;
			case 2:
				fragment = new ResultsFragment();
				break;
			case 3:
				fragment = new RulesFragment();
				break;
			case 4:
				fragment = new AboutFragment();
				break;
			default:
				fragment = new HomeButtonFragment();
				break;
		}

		MainController.getInstance().replaceFragment(R.id.content_frame,fragment,this);
		
	    // Highlight the selected item, update the title, and close the drawer
	    if(position > 0) {
	    	mDrawerList.setItemChecked(position, true);
		    setTitle(getResources().getString(mNavigationArray.get(position).getNameId()));
	    }
	    
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
	}
	
	public void displayFragment(View v) {
		switch(v.getId()) {
			case R.id.play_btn:
				this.selectItem(1);
				break;
			case R.id.results_btn:
				this.selectItem(2);
				break;
			case R.id.rules_btn:
				this.selectItem(3);
				break;
		}
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}
}

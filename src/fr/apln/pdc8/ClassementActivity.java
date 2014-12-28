package fr.apln.pdc8;


import java.util.ArrayList;

import fr.apln.modele.ModeleClassementElement;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ClassementActivity extends ListActivity {

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
 
        // if extending Activity
        //setContentView(R.layout.activity_main);
 
        // 1. pass context and data to the custom adapter
        ClassementAdapter adapteur = new ClassementAdapter(this, genererDonneesTest());
 
        // if extending Activity 2. Get ListView from activity_main.xml
        //ListView listView = (ListView) findViewById(R.id.listview);
 
        // 3. setListAdapter
        //listView.setAdapter(adapter); if extending Activity
        setListAdapter(adapteur);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getActionBar().setDisplayShowTitleEnabled(false);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {		
		case R.id.menu_accueil:
			// Redirige sur l'écran d'accueil
			intent = new Intent(this, AccueilActivity.class);
			startActivity(intent);
			return true;
		case R.id.menu_resultats:	
			// Redirige sur l'écran des résultats
			intent = new Intent(this, ClassementActivity.class);
			startActivity(intent);
			return true;
		case R.id.menu_a_propos:
			// Redirige sur l'écran a propos
			intent = new Intent(this, AProposActivity.class);
			startActivity(intent);
			return true;
		case R.id.menu_regles:			
			// Redirige sur l'écran regles du jeu
			intent = new Intent(this, ReglesActivity.class);
			startActivity(intent);
			return true;
		case R.id.menu_options:
			// Redirige sur l'écran de jeu
			intent = new Intent(this, OptionsActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton precedent
	 * @param view 
	 */
	public void allerPrecedent(View view) {
		//TODO
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton suivant
	 * @param view 
	 */
	public void allerSuivant(View view) {
	    //TODO
	}
 
	/**
	 * Donnees test
	 * @return
	 */
    private ArrayList<ModeleClassementElement> genererDonneesTest(){
        ArrayList<ModeleClassementElement> models = new ArrayList<ModeleClassementElement>();
        models.add(new ModeleClassementElement("Circuit 1"));
        models.add(new ModeleClassementElement("Maud","1"));
        models.add(new ModeleClassementElement("Van","2"));
        models.add(new ModeleClassementElement("Thomas","3"));
        return models;
    }
}

package fr.apln.pdc8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class JeuActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jeu);
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
			// Comportement du bouton "A Propos"
			return true;
		case R.id.menu_regles:
			// Comportement du bouton "Regles du jeu"
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

}

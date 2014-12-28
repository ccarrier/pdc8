package fr.apln.pdc8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AProposActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
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
			intent = new Intent(this, AProposActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

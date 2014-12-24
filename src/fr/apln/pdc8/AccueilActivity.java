package fr.apln.pdc8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccueilActivity  extends Activity {

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton jouer
	 * @param view 
	 */
	public void jouer(View view) {
	
		
		// Redirige sur l'écran de jeu
		Intent intent = new Intent(this, JeuActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton resultats
	 * @param view 
	 */
	public void montrerResultats(View view) {
		
		
		// Redirige sur l'écran de jeu
		Intent intent = new Intent(this, ClassementActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton options
	 * @param view 
	 */
	public void montrerOptions(View view) {
	
		
		// Redirige sur l'écran de jeu
		Intent intent = new Intent(this, OptionsActivity.class);
		startActivity(intent);
	}
	
	
}

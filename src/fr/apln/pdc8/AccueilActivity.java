package fr.apln.pdc8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AccueilActivity  extends Activity {

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // supprime la bar de titre
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,                 
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
		
		
		// Redirige sur l'écran des résultats
		Intent intent = new Intent(this, ClassementActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton regles du jeu
	 * @param view 
	 */
	public void montrerRegles(View view) {
	
		
		// Redirige sur l'écran des regles du jeu
		Intent intent = new Intent(this, ReglesActivity.class);
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
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton a propos
	 * @param view 
	 */
	public void montrerAPropos(View view) {
	
		
		// Redirige sur l'écran a propos
		Intent intent = new Intent(this, AProposActivity.class);
		startActivity(intent);
	}
	
	
}

package fr.apln.pdc8;


import fr.apln.pdc8.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	}
	
	/**
	 * Appelee quand l'utilisateur clique sur le bouton enregistrer
	 * @param view 
	 */
	public void enregistrerUtilisateur(View view) {
	    // Enregistre l'utilisateur 
		
		
		// Redirige sur l'écran d'accueil
		Intent intent = new Intent(this, AccueilActivity.class);
		startActivity(intent);

	}
}

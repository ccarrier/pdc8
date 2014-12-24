package fr.apln.pdc8;

import java.util.ArrayList;

import fr.apln.modele.ModeleClassementElement;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class ClassementAdapter extends ArrayAdapter<ModeleClassementElement> {
 
        private final Context context;
        private final ArrayList<ModeleClassementElement> listeClassements;
 
        public ClassementAdapter(Context context, ArrayList<ModeleClassementElement> listeClassements) { 
            super(context, R.layout.classement_element, listeClassements); 
            this.context = context;
            this.listeClassements = listeClassements;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            // 1. Creation de l'inflater
            LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
            // 2. Get rowView from inflater 
            View vueLigne = null;
            if(!listeClassements.get(position).isNomCircuit()){
            
            	vueLigne = inflater.inflate(R.layout.classement_element, parent, false);
            	
            	// 3. Recuperation du nom du joueur et de son rang                
                TextView titleView = (TextView) vueLigne.findViewById(R.id.elem_nom_joueur);
                TextView counterView = (TextView) vueLigne.findViewById(R.id.elem_rang);
 
                // 4. Affectation du nom et du rang
                titleView.setText(listeClassements.get(position).getNom());
                counterView.setText(listeClassements.get(position).getRang());
            }
            else
            {
            	//Nom du circuit
            	vueLigne = inflater.inflate(R.layout.classement_entete, parent, false);
                TextView titleView = (TextView) vueLigne.findViewById(R.id.nom_circuit);
                titleView.setText(listeClassements.get(position).getNom()); 
            }
 
            // 5. return vueLigne
            return vueLigne;
        }
}
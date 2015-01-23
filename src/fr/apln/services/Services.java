package fr.apln.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.apln.dao.ArbreDAO;
import fr.apln.dao.CircuitDAO;
import fr.apln.modele.entite.Arbre;
import fr.apln.modele.entite.Circuit;
import fr.apln.modele.entite.Troncon;

public class Services {
	
	public ArbreDAO arbreDAO = new ArbreDAO();
	private CircuitDAO circuitDAO = new CircuitDAO();
	
	//CONSTANT
	private double DISTANCE_MIN = 0.001;
	private double DISTANCE_MAX = 0.01;
	
	
	private Arbre rechercheArbreProximite(List<Arbre> arbres,double latitude, double longitude)
	{
		double distance_min = 99999;
		Arbre arbreVoisin = null;
		for (Arbre arbre : arbres) {
			double x = arbre.getLongitude() - longitude;
			double y = arbre.getLatitude() - latitude;
			double distance = Math.sqrt(x*x+y*y);
			if(distance < distance_min)
			{
				distance_min = distance;
				arbreVoisin = arbre;
			}
		}
		return arbreVoisin;
	}
	
	private Arbre rechercheArbreSuivant(List<Arbre> arbres,Arbre arbre, double angle_troncon)
	{
		Random r = new Random();
		//r.nextDouble(); entre 0 et 1.0
		double rayon = (DISTANCE_MAX - DISTANCE_MIN)*r.nextDouble() + DISTANCE_MIN;
		double angle = Math.PI/2*r.nextDouble()+angle_troncon;
		double latitude = arbre.getLatitude() + rayon*Math.sin(angle);
		double longitude = arbre.getLongitude()+ rayon*Math.cos(angle);
		return rechercheArbreProximite(arbres, latitude, longitude);

	}
	
	public Circuit genererCircuit(int n,double latitude, double longitude)
	{
		if(n<2)
			return null;
		List<Arbre> tousArbres = arbreDAO.getAll();
		Circuit circuit = new Circuit();
		Arbre arbre_tete = rechercheArbreProximite(tousArbres, latitude, longitude);
		double angle_troncon = 0;
		for (int i = 0; i < n; i++) {
			Arbre arbre_queue;
			if(i==n-1 && circuit.getListeTroncons().size()>0) // fin de circuit
				arbre_queue = circuit.getListeTroncons().get(0).getArbre();
			else
				arbre_queue = rechercheArbreSuivant(tousArbres, arbre_tete,angle_troncon);
			Troncon t = new Troncon(arbre_tete, arbre_queue);
			circuit.addTroncon(t);
			arbre_tete = arbre_queue;
			angle_troncon = t.getAngle();
		}
		
		circuitDAO.create(circuit);
		
		return circuit;
	}

	public List<Arbre> genererTestArbres()
	{
		List<Arbre> arbres = new ArrayList<Arbre>();
		Arbre a1 = new Arbre("Arbre 1", 10, 3, 2, null, 45.775246, 4.85438);
		Arbre a2 = new Arbre("Arbre 2", 10, 3, 2, null, 45.776458, 4.856912);
		Arbre a3 = new Arbre("Arbre 3", 10, 3, 2, null, 45.780544, 4.857298);
		Arbre a4 = new Arbre("Arbre 4", 10, 3, 2, null, 45.783207, 4.853328);
		Arbre a5 = new Arbre("Arbre 5", 10, 3, 2, null, 45.778808, 4.846655);
		arbres.add(a1);arbres.add(a2);arbres.add(a3);arbres.add(a4);arbres.add(a5);
		
		return arbres;
		
	}
}

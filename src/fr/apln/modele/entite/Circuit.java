package fr.apln.modele.entite;

import java.util.ArrayList;
import java.util.List;

public class Circuit {

	/**
	 * Attributs
	 */
	private int id;
	private double longueur;
	private Essence essence;
	
	private List<Troncon> listeTroncons;
	private List<Specificite> listeSpecificites;

	/**
	 * Constructeur de circuit
	 * @param longueur la longueur de circuit
	 * @param essence l'essence du circuit
	 */
	public Circuit(double longueur, Essence essence) {
		this.longueur = longueur;
		this.essence = essence;
		listeTroncons = new ArrayList<Troncon>();
		listeSpecificites = new ArrayList<Specificite>();
	}
	
	/**
	 * Constructeur de circuit
	 * @param longueur la longueur de circuit
	 */
	public Circuit(double longueur) {
		this(longueur, null);
	}
	
	public Circuit()
	{
		this(0,null);
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public Essence getEssence() {
		return essence;
	}

	public void setEssence(Essence essence) {
		this.essence = essence;
	}

	public List<Troncon> getListeTroncons() {
		return listeTroncons;
	}

	public void setListeTroncons(List<Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
	}
	
	public void addTroncon(Troncon troncon)
	{
		this.listeTroncons.add(troncon);
		this.longueur += troncon.getLongueur();
	}

	public List<Specificite> getListeSpecificites() {
		return listeSpecificites;
	}

	public void setListeSpecificites(List<Specificite> listeSpecificites) {
		this.listeSpecificites = listeSpecificites;
	}

	public int getId() {
		return id;
	}
	
	
	
	
}

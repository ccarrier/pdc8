package fr.apln.modele.entite;

import java.util.ArrayList;
import java.util.List;


public class Arbre {

	/**
	 * Attributs
	 */
	private int id;
	private String code;
	private int hauteur;
	private int diametre_tronc;
	private int diametre_couronne;
	private Essence essence;
	private double latitude;
	private double longitude;

	private List<Specificite> listeSpecificites;

	/**
	 * Constructeur de Arbre
	 * @param code l'identifiant de l'arbre
	 * @param hauteur la hauteur de l'arbre
	 * @param diametre_tronc le diamètre du tronc
	 * @param diametre_couronne le diamètre de la couronne
	 * @param essence l'essence de l'arbre
	 */
	public Arbre(String code, int hauteur, int diametre_tronc,
			int diametre_couronne, Essence essence, double latitude, double longitude)
	{
		this.code = code;
		this.hauteur = hauteur;
		this.diametre_tronc = diametre_tronc;
		this.diametre_couronne = diametre_couronne;
		this.essence = essence;
		this.listeSpecificites = new ArrayList<Specificite>();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	public int getDiametre_tronc() {
		return diametre_tronc;
	}
	
	public void setDiametre_tronc(int diametre_tronc) {
		this.diametre_tronc = diametre_tronc;
	}
	
	public int getDiametre_couronne() {
		return diametre_couronne;
	}
	
	public void setDiametre_couronne(int diametre_couronne) {
		this.diametre_couronne = diametre_couronne;
	}
	
	public Essence getEssence() {
		return essence;
	}
	
	public void setEssence(Essence essence) {
		this.essence = essence;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public List<Specificite> getListeSpecificites() {
		return listeSpecificites;
	}

	public void setListeSpecificites(List<Specificite> listeSpecificites) {
		this.listeSpecificites = listeSpecificites;
	}
	
	
	
	
}

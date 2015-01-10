package fr.apln.modele.entite;

import java.util.Date;

public class Photo {

	/**
	 * Attributs
	 */
	private int id;
	private Arbre arbre;
	private Date date; 
	private String titre;
	private String chemin;
	
	/**
	 * Constructeur de Photo
	 * @param arbre l'arbre de la photo
	 * @param date la date de la photo
	 * @param titre le titre de la photo
	 * @param chemin le chemin vers la photo
	 */
	public Photo(Arbre arbre, Date date, String titre, String chemin) {
		super();
		this.arbre = arbre;
		this.date = date;
		this.titre = titre;
		this.chemin = chemin;
	}

	public Arbre getArbre() {
		return arbre;
	}

	public void setArbre(Arbre arbre) {
		this.arbre = arbre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public int getId() {
		return id;
	}
	
	
	
}

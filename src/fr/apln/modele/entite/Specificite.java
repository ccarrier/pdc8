package fr.apln.modele.entite;

public class Specificite {

	/**
	 * Attributs
	 */
	private int id;
	private String nom;
	
	/**
	 * Constructeur de Specificite
	 * @param nom le nom de la specificite
	 */
	public Specificite(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}
	
	
	
}

package fr.apln.modele.entite;

public class Utilisateur {

	/**
	 * Attributs
	 */
	private int id;
	private String nom;
	private String mot_de_passe;
	
	/**
	 * Constructeur de Utilisateur
	 * @param nom le nom de l'utilisateur 
	 * @param mot_de_passe le mot de passe de l'utilisateur
	 */
	public Utilisateur(String nom, String mot_de_passe) {
		super();
		this.nom = nom;
		this.mot_de_passe = mot_de_passe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public int getId() {
		return id;
	}
	
	
	
}

package fr.apln.modele.entite;

public class Essence {

	/** 
	 * Attributs
	 */
	private int id;
	private String nom;
	private String genre;
	private String espece;
	private String type;
	
	/**
	 * Constructeur de Essence
	 * @param nom le nom de l'essence
	 * @param genre le genre de l'essence
	 * @param espece l'espèce de l'essence
	 * @param type le type d'arbre
	 */
	public Essence(String nom, String genre, String espece, String type) {
		super();
		this.nom = nom;
		this.genre = genre;
		this.espece = espece;
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	
	
	
}

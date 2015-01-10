package fr.apln.modele.entite;

public class Troncon {

	/**
	 * Attributs
	 */
	private int id;
	private Arbre arbre;
	private Arbre suivant;
	
	/**
	 * Constructeur de Troncon
	 * @param circuit le circuit auquel appartient le troncon
	 * @param arbre l'arbre de depart du troncon
	 * @param suivant l'arbre de fin du troncon
	 */
	public Troncon(Arbre arbre, Arbre suivant) {
		super();
		this.arbre = arbre;
		this.suivant = suivant;
	}

	public Arbre getArbre() {
		return arbre;
	}

	public void setArbre(Arbre arbre) {
		this.arbre = arbre;
	}

	public Arbre getSuivant() {
		return suivant;
	}

	public void setSuivant(Arbre suivant) {
		this.suivant = suivant;
	}
	
	public int getId() {
		return this.id;
	}
	
}
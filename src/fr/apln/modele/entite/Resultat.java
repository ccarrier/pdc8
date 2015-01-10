package fr.apln.modele.entite;

public class Resultat {

	/**
	 * Attributs
	 */
	private Utilisateur utilisateur;
	private Circuit circuit;
	private int score;
	
	/**
	 * Constructeur de resultat
	 * @param utilisateur l'utilisateur
	 * @param circuit le circuit
	 * @param score le score de l'utilisateur sur ce circuit
	 */
	public Resultat(Utilisateur utilisateur, Circuit circuit, int score) {
		super();
		this.utilisateur = utilisateur;
		this.circuit = circuit;
		this.score = score;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}

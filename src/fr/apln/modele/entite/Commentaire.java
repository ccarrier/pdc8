package fr.apln.modele.entite;

import java.util.Date;

public class Commentaire {

	/**
	 * Attributs
	 */
	private int id;
	private Arbre arbre;
	private Date date; 
	private String commentaire;
	
	
	/**
	 * Constructeur de Commentaire
	 * @param arbre l'arbre du commentaire
	 * @param date la date du commentaire
	 * @param commentaire le texte du commentaire
	 */
	public Commentaire(Arbre arbre, Date date, String commentaire) {
		super();
		this.arbre = arbre;
		this.date = date;
		this.commentaire = commentaire;
	}

	public int getId() {
		return id;
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
	
	public String getCommentaire() {
		return commentaire;
	}
	
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
		
}

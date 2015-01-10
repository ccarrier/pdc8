package fr.apln.dao;

import java.util.List;

public abstract class DAO<T> {

	/**
	 * Constructeur de DAO
	 */
	public DAO(){}

	/**
	 * Methode pour creer
	 * @param obj objet a creer
	 */
    public abstract void create(T obj);

    /**
     * Methode pour supprimer
     * @param obj l'objet à supprimer
     * @return le succes ou non de la suppression
     */
    public abstract boolean delete(T obj);

    /**
     * Methode pour mettre a jour
     * @param obj l'objet a mettre a jour
     * @return le succes ou non de la mise a jour
     */
    public abstract boolean update(T obj);

    /**
     * Methode pour trouver un objet par son id
     * @param id l'id de l'objet a trouver
     * @return l'objet trouve
     */
    public abstract T findById(int id);
    
    /**
     * Methode qui renvoie l'ensemble des objets
     * @return la liste de tous les objets
     */
    public abstract List<T> getAll();
}

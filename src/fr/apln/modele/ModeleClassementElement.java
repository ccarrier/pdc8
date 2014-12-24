package fr.apln.modele;

public class ModeleClassementElement {

   
    private String nom;
    private String rang;
 
    private boolean isNomCircuit = false;
 
    public ModeleClassementElement(String nom_circuit) {
        this(nom_circuit,null);
        isNomCircuit = true;
    }
    public ModeleClassementElement(String nom_joueur, String rang) {
        super();
        this.nom = nom_joueur;
        this.rang = rang;
    }
    
    public boolean isNomCircuit()
    {
    	return this.isNomCircuit;
    }
    
    public String getNom()
    {
    	return this.nom;
    }
    
    public String getRang()
    {
    	return this.rang;
    }
}

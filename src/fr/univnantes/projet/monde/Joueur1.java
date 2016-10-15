package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Joueur1
{

	private String pseudo_;
	private Color couleur_;
	private List<Chemin> chemin_;

	/**
	 * Constructeur
     */
	public Joueur1(String pseudo, Color couleur)
	{
		pseudo_ = pseudo;
		couleur_ = couleur;
		chemin_ = new ArrayList<Chemin>();

	}

	/**
     * Accesseur
     */
    public String getPseudo()
    {
        return pseudo_;
    }

    public Color getCouleur()
    {
    	return couleur_;
    }
    
    public List<Chemin> getLChemin()
    {
    	return chemin_;
    }

    /**
     * Mutateur
     */
    public void setPseudo(String pseudo)
    {
        pseudo_=pseudo;
    }

    public void setCouleur(Color couleur)
    {
    	couleur_=couleur;
    }

    public void setChemin(ArrayList<Chemin> chemin)
    {
    	chemin_=chemin;
    }
    
    public int nbChemin()
    {
    	return chemin_.size();
    }
    
    public void ajouterChemin(Chemin chemin)
    {
    	chemin_.add(chemin);
    }
    
    public void supprimerChemin(Chemin chemin)
    {
    	chemin_.remove(chemin);
    }
    
    /**
	 * Accesseur de la population au rang i
	 * @param i Le ième rang
	 * @return La ième position de classe_
	 */
	public Chemin getChemin(int i){
		return chemin_.get(i);
	}
    
	@Override
	public String toString()
	{
		return getPseudo();
	}

}
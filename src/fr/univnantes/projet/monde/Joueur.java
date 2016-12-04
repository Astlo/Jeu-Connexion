package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Joueur 
{
	private String pseudo_;
	private Color couleur_;
	private List<Case> composante_;
	
	public Joueur(String pseudo, Color couleur)
	{
		pseudo_ = pseudo;
		couleur_ = couleur;
		composante_ = new ArrayList<Case>();	
	}

	public String getPseudo() {
		return pseudo_;
	}

	public void setPseudo(String pseudo) {
		pseudo_ = pseudo;
	}

	public Color getCouleur() {
		return couleur_;
	}

	public void setCouleur(Color couleur) {
		couleur_ = couleur;
	}

	public List<Case> getComposante() {
		return composante_;
	}

	public void setComposante(List<Case> composante) {
		composante_ = composante;
	}
	 
	public void ajouterComposante(Case c)
	{
		composante_.add(c);
		if(c.getPere() != null)
		{
			miseAJourComposante(c);			
		}
	}
	
	public void miseAJourComposante(Case c)
	{
	
	}

}
package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Joueur 
{
	private String pseudo_;
	private Color couleur_;
	private List<Case> composante_;
	private boolean abandon_;
	
	public Joueur(String pseudo, Color couleur)
	{
		pseudo_ = pseudo;
		couleur_ = couleur;
		composante_ = new ArrayList<Case>();
		abandon_ = false;
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
	}
	
	public void supprimerComposante(Case c)
	{
		composante_.remove(c);
		//System.out.println(composante_.remove(c));
	}


	public boolean getAbandon() {
		return abandon_;
	}

	public void setAbandon(boolean abandon) {
		abandon_ = abandon;
	}

	public void abandonner()
	{
		abandon_ = true;
	}
	
}
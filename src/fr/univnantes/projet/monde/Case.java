package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import fr.univnantes.projet.Constante;

public class Case {
	private Position position_;
	private Color couleur_;
	private boolean etoile_;
	private Case pere_;
	private int nbDescendant_;
	private List<Case> fils_;
	
	public Case(Position position)
	{
		position_ = position;
		couleur_ = Color.white;
		etoile_ = false;
		pere_ = null;
		fils_ = new ArrayList<Case>();
		nbDescendant_ = 0;
	}

	public Position getPosition() 
	{
		return position_;
	}

	public void setPosition(Position position) 
	{
		position_ = position;
	}

	public Color getCouleur() 
	{
		return couleur_;
	}

	public void setCouleur(Color couleur) 
	{
		couleur_ = couleur;
	}

	public boolean getEtoile() 
	{
		return etoile_;
	}

	public void estEtoile() 
	{
		etoile_ = true;
	}

	public Case getPere() 
	{
		return pere_;
	}

	public void setPere(Case pere) 
	{
		pere_ = pere;
	}

	public List<Case> getFils() 
	{
		return fils_;
	}

	public void setFils(List<Case> fils) 
	{
		fils_ = fils;
	}

	public int getNbDescendant() 
	{
		return nbDescendant_;
	}

	public void setNbDescendant(int nbDescendant) 
	{
		nbDescendant_ = nbDescendant;
	}

	public Case getRacine()
	{
		Case parcours = this;
		while(parcours.getPere() != null)
		{
			parcours = parcours.getPere();

		}
		return parcours;
	}
	
	public void parcoursPrefixe()
	{
		System.out.println(position_.toString());
		//if(fils_.size() != 0)
		for(Case c : fils_)
		{
			c.parcoursPrefixe();
		}
	}

	public int parcoursEtoile(int cpt)
	{
		if(etoile_)
		{
			cpt++;
		}
		
		if(fils_.size() != 0)
		{
			for(Case c : fils_)
			{
				
				cpt = c.parcoursEtoile(cpt);

			}
		}
		return cpt;
	}
	
	public void analysePeripherieComposante(boolean[][] visitee, ArrayList<Case> peripherie, boolean[][] inaccessible,Case[][] carte)
	{
		visitee[position_.getY()][position_.getX()] = true;
		if(fils_.size()>0){
			
			for(Case fils : fils_){
				System.out.println("wut");

				fils.analysePeripherieComposante(visitee,peripherie,inaccessible,carte);
				
			}		
		}
		
		for (int i = -1; i < 2; ++i)
		{
			for( int j = -1; j < 2; ++j)
			{	
				int x1 = position_.getX()+i; // abscisse de la case adjacente
				int y1 = position_.getY()+j; // ordonnée de la case adjacente
				
				// on analyse la périphérie de chaque case de la composante
				if((x1 == position_.getX() && y1 == position_.getY())){
				} else
				if((x1 >= 0) && (y1>=0) && (x1 <=Constante.N -1) && (y1<=Constante.N -1) && !visitee[y1][x1] && !inaccessible[y1][x1]){
					
					Case adj = carte[y1][x1]; // Case adjacente en cours d'analyse
					if(adj.getCouleur() == Color.white && !visitee[y1][x1]){
						visitee[y1][x1] = true;
						peripherie.add(adj);
						
					} else { //optimisable en n'ajoutant pas les cases déjà visitées... et encore la conditionnelle pourrait couter plus que l'opération
						inaccessible[y1][x1] = true;
					}
				}
			}
		}
	}
	
    public boolean caseEgale(Case autre)
    {
    	return position_.positionEgale(autre.getPosition());
    }
    
    public void ajoutFils(Case c)
    {
    	fils_.add(c);
    	//nbDescendant_ = nbDescendant_ + 1 + c.getNbDescendant();
    	c.setPere(this);
    }
        
    public Case classe()
    {
    	Case racine;
    	if(pere_ != null)
    	{
    		racine = pere_.classe();
    		getPere().setNbDescendant(getPere().getNbDescendant() - nbDescendant_);
    		getPere().getFils().remove(this);
    		setPere(racine);
    		racine.ajoutFils(this);
    	}
    	else
    	{
    		racine = this;
    	}
    	return racine;
    }
    
    public void union(Case c, Joueur joueur)
    {
    	//vérifier la qu'ils sont pas dans la meme classe ou avant
    	if(classe() != c.classe())
    	{
	    	if(nbDescendant_ >= c.getNbDescendant())
	    	{
	    		c.setPere(this);
	    		fils_.add(c);
	    		nbDescendant_ = nbDescendant_ + 1 + c.getNbDescendant();
	    		joueur.supprimerComposante(c);
	    	}
	    	else
	    	{
	    		pere_ = c.getPere();
	    		c.ajoutFils(this);
	    		c.setNbDescendant(nbDescendant_ + 1 + c.getNbDescendant());
	    		joueur.supprimerComposante(this);
	    	}
    	}
    }
    
	public String toString(){
		String str = "";
		str = position_.toString();
		str = str + " ( ";
		for(Case c : fils_)
		{
			str = str + c.toString();
		}
		str = str + " ) ";
		return str;
	}
	
}

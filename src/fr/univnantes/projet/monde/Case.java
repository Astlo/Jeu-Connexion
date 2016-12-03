package fr.univnantes.projet.monde;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

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
		
		//if(fils_.size() != 0)
		
			for(Case c : fils_)
			{
				
				cpt = cpt + c.parcoursEtoile(cpt);

			}
		
		return cpt;
	}

	/*public boolean caseAdjacent(Case autre)
	{
    	boolean test = false;
    	if(position_.getX() == autre.getPosition().getX()-1 || position_.getX() == autre.getPosition().getX() || position_.getX() == autre.getPosition().getX()+1)
    	{
    		if(position_.getY() == autre.getPosition().getY()-1 || position_.getY() == autre.getPosition().getY() || position_.getY() == autre.getPosition().getY()+1)
    		{
    			if(!caseEgale(autre))
    			{
    				test = true;
    			}
    		}
    	}
    	return test;	
	}*/
	
	
	
    public boolean caseEgale(Case autre)
    {
    	return position_.positionEgale(autre.getPosition());
    }
    
    public void ajoutFils(Case c)
    {
    	fils_.add(c);
    	nbDescendant_ = nbDescendant_ + 1 + c.getNbDescendant();
    	c.setPere(this);
    }
        
    public Case classe()
    {
    	Case racine;
    	if(pere_ != null)
    	{
    		racine = pere_.classe();
    		getPere().getFils().remove(this);
    		setPere(racine);
    	}
    	else
    	{
    		racine = this;
    	}
    	return racine;
    }
    
    public void Union(Case c)
    {
    	//vérifier la qu'ils sont pas dans la meme classe ou avant
    	if(classe() != c.classe())
    	{
	    	if(nbDescendant_ > c.getNbDescendant())
	    	{
	    		c.setPere(pere_);
	    		nbDescendant_ = nbDescendant_ + 1 + c.getNbDescendant();
	    	}
	    	else
	    	{
	    		pere_ = c.getPere();
	    		c.setNbDescendant(nbDescendant_ + 1 + c.getNbDescendant());
	    		
	    	}
    	}
    }
    
	public String toString(){
		return position_.toString();
	}
	
}

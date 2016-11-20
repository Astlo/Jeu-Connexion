package fr.univnantes.projet.monde;
/**
 * Identifie une position par son abscisse et son ordonnée.
 * @author LE BARS Yannis & BROHAN Romain
 */
public class Position {
    /**
     * Abscisse de la position
     */
    private int x_;
    /**
     * Ordonnée de la position
     */
    private int y_;

    /**
	 * Constructeur
     * @param x abscisse de la position
     * @param y ordonnée de la position
     */
    public Position(int x, int y){
        x_ = x;
        y_ = y;
    }

    /**
     * Accesseur de l'abscisse de la position
     * @return l'abscisse
     */
    public int getX(){
        return x_;
    }

    /**
     * Accesseur de l'ordonnée de la position
     * @return l'ordonnée
     */
    public int getY(){
        return y_;
    }

    /**
     * Mutateur de l'abscisse de la position
     * @param x_ Abscisse remplaçante
     */
    public void setX(int x){
        x_=x;
    }

    /**
     * Mutateur de l'ordonnée de la position
     * @param y_ Ordonnée remplaçante
     */
    public void setY(int y){
        y_=y;
    }
    
    public boolean positionAdjacente(Position autre){
    	boolean test;
    	if(x_ == autre.getX()-1 || x_ == autre.getX() || x_ == autre.getX()+1)
    	{
    		if(y_ == autre.getY()-1 || y_ == autre.getY() || y_ == autre.getY()+1)
    		{
    			if(!positionEgale(autre))
    			{
    				test = true;
    			}
    			else
    			{
    				test = false;
    			}
    		}
    		else
    		{
    			test = false;
    		}
    	}
    	else
    	{
    		test = false;
    	}
    	return test;
    }
    
    public boolean positionEgale(Position autre){
    	return x_ == autre.getX() && y_ == autre.getY();
    }
    
    @Override
	public String toString(){
		return "("+x_+","+y_+")";
	}
}

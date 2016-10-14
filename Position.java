/**
 * Classe Position définit par une latitude et une longitude
 */
public class Position {
    private int x_;
    private int y_;


    /**
	 * Constructeur
     */
    public Position(int x, int y){
        x_ = x;
        y_ = y;
    }
    /**
     * Accesseur
     */
    public int getX(){
        return x_;
    }

    public int getY(){
        return y_;
    }

    /**
     * Mutateur
     */
    public void setX(int x){
        x_=x;
    }

    public void setY(int y){
        y_=y;
    }

    
    @Override
	public String toString(){
		return "("+x_+","+y_+")";
	}
}

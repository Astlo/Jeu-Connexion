
import java.util.ArrayList;
import java.lang.String;

class Monde
{

	private ArrayList<ArrayList<String>> carte_ = new ArrayList<ArrayList<String>>();
	private int taille_;

	/**
	 * Constructeur
     */
	public Monde(int taille)
	{
		taille_=taille;
        for(int i = 0;i<taille;++i)
        {
            carte_.add(new ArrayList<String>());
            for(int j = 0;j<taille;++j)
            {
                carte_.get(i).add(".");
            }
        }

	}


	/**
     * Accesseur
     */
    public ArrayList<ArrayList<String>> getCarte()
    {
        return carte_;
    }

    public int getTaille()
    {
    	return taille_;
    }

    /**
     * Mutateur
     */
    public void setX(ArrayList<ArrayList<String>> carte)
    {
        carte_=carte;
    }

    public void setTaille(int taille)
    {
    	taille_=taille;
    }

    public void remplirCase(Position position, Joueur1 joueur)
    {
    	carte_.get(position.getX()).set(position.getY(),joueur.getPseudo());
    }


    @Override
	public String toString()
	{
		String str = "" ;
		for(int i = 0;i<taille_;++i)
		{
			for(int j = 0;j<taille_ ;++j)
			{
				str = str.concat((carte_.get(i)).get(j));
			}
			str = str.concat("\n");
		}
		return str;
	}
}
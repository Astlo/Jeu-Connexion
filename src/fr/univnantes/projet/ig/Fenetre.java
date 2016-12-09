package fr.univnantes.projet.ig;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.univnantes.projet.monde.Joueur;
import fr.univnantes.projet.monde.Monde;

/**
 * Classe dont les instances sont des fenêtres graphiques
 * dérivées de JFrame.
 */
public class Fenetre extends JFrame implements ActionListener{
	
	/**
	 * Construteur.
	 * @param titre Titre de la fenetre afficé dans le bandeau
	 * @param panel Contenu de la fenetre
	 */
	private JButton colorer = new JButton("colorerCase");
	private JButton composante = new JButton("afficheComposante");
	private JButton chemin = new JButton("existeCheminCases");
	private JButton relierCasesMin = new JButton("relierCasesMin");
	private JButton nbEtoiles = new JButton("nombreEtoiles");
	private JButton Score = new JButton("afficheScores");
	private JButton relieComposantes = new JButton("relieComposantes");
	private JButton abandon = new JButton("Abandonner(Alors, on loose ?)");
	private Monde monde_;
	private Joueur courant_;
	private boolean partieOrdi_;
	
	public Fenetre(String titre, JPanel panel, Monde monde, boolean partieOrdi) 
	{
		
		// instanciation de l'instance de JFrame et de son contenu

		
		super(titre);		
		monde_ = monde;
		courant_ = monde_.getJoueur1();
		partieOrdi_ = partieOrdi;
		Box b = new Box(BoxLayout.PAGE_AXIS);
		setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);

		// paramétrage de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(true);
		
		// ajout des boutons

		
		colorer.addActionListener(this);
		composante.addActionListener(this);
		chemin.addActionListener(this);
		relierCasesMin.addActionListener(this);
		nbEtoiles.addActionListener(this);
		Score.addActionListener(this);
		relieComposantes.addActionListener(this);
		abandon.addActionListener(this);
		
		b.add(colorer);
		b.add(composante);
		b.add(chemin);
		b.add(relierCasesMin);
		b.add(nbEtoiles);
		b.add(Score);
		b.add(relieComposantes);
		b.add(abandon);
		
		getContentPane().add(b, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0){
		
		if(arg0.getSource() == colorer){
			if(courant_ == monde_.getJoueur1())
			{
				monde_.colorerCase(monde_.getJoueur1());
				monde_.testJeuTermine(courant_);
				courant_ = monde_.getJoueur2();
				repaint();
			} 
			else 
			{
				if(partieOrdi_==false)
				{
					monde_.colorerCase(monde_.getJoueur2());
					if (monde_.testJeuTermine(courant_)) {
						setVisible(false);
					}
					else
					{
						courant_ = monde_.getJoueur1();
						repaint();
					}
				}
				else
				{
					monde_.colorerCaseAleatoire(monde_.getJoueur2());
					if (monde_.testJeuTermine(courant_)) {
						setVisible(false);
					}
					else
					{
						courant_ = monde_.getJoueur1();
						repaint();
					}
				}
			}
			System.out.println();
			System.out.println("Joueur suivant :" + courant_.getPseudo());
		}
		if(arg0.getSource() == composante){
			
			monde_.afficheComposante();
			
		}
		
		if(arg0.getSource() == chemin){
			
			monde_.existeCheminCases();
			
		}
		
		if(arg0.getSource() == relierCasesMin){
			
			monde_.relierCasesMin();
			
		}
		if(arg0.getSource() == nbEtoiles){
			
			monde_.nombreEtoiles();
			
		}
		if(arg0.getSource() == Score){
			
			monde_.afficherScores();
			
		}
		if(arg0.getSource() == relieComposantes)
		{

			System.out.println(monde_.relieComposantes(courant_.getCouleur()));
		}
		if(arg0.getSource() == abandon){
			
			if(courant_ == monde_.getJoueur1()){
				System.out.println("abandon de : " + monde_.getJoueur1().getPseudo());
				//monde_.getJoueur1().abandonner();
			} else {
				System.out.println("abandon de : " + monde_.getJoueur2().getPseudo());
				//monde_.getJoueur2().abandonner();
			}
		colorer.setEnabled(false);
		abandon.setEnabled(false);	
		}
	}

}
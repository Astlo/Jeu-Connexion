package fr.univnantes.projet.ig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import fr.univnantes.projet.monde.Monde;
import fr.univnantes.projet.monde.Position;
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
	private JButton nouvellePartie = new JButton("Nouvelle Partie !");
	private Monde monde_;
	
	public Fenetre(String titre, JPanel panel, Monde monde) {
		
		// instanciation de l'instance de JFrame et de son contenu

		
		super(titre);		
		monde_ = monde;
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
		nouvellePartie.addActionListener(this);
		
		b.add(colorer);
		b.add(composante);
		b.add(chemin);
		b.add(relierCasesMin);
		b.add(nbEtoiles);
		b.add(Score);
		b.add(relieComposantes);
		b.add(abandon);
		b.add(nouvellePartie);
		
		getContentPane().add(b, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0){
		
		if(arg0.getSource() == colorer){
			if(monde_.getNumTour()%2==0){
				monde_.colorerCase(monde_.getj1());
			} else {
				monde_.colorerCase(monde_.getj2());
			}
			monde_.setNumTour(monde_.getNumTour()+1);
			System.out.println("Joueur suivant :");
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
			
			monde_.afficheScores();
			
		}
		if(arg0.getSource() == relieComposantes){
			
			monde_.relieComposantes();
			
		}
		if(arg0.getSource() == abandon){
			
			if(monde_.getNumTour()%2==0){
				System.out.println("abandon de : " + monde_.getj1().getPseudo());
				monde_.getj1().abandonner();
			} else {
				System.out.println("abandon de : " + monde_.getj2().getPseudo());
				monde_.getj2().abandonner();
			}
		colorer.setEnabled(false);
		abandon.setEnabled(false);	
		
		}
		if(arg0.getSource() == nouvellePartie){
			
			
			
			
		}
	}

}
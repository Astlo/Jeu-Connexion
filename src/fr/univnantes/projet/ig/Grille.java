package fr.univnantes.projet.ig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.JPanel;

import projet.Constante;
import projet.monde.Joueur;

/* Grille de jeu affichant les cases colorées et à colorer */

public class Grille extends JPanel {


// Dimensions de la grille : 0..N lignes et 0..M colonnes
	private int N_;

	// Dimensions de la fenêtre d'affichage en pixels

	private int cote_;

	/**
	 * Constructeur
	 * @param N N+1 lignes et colonnes indicées de 0 à N
	 * 
	 * TODO : à compléter
	 */
	public Grille(int N) {
		// dimensions pour les positions
		N_ = N;

		// dimensions de la fenêtre
		cote_ = Constante.Pix*(N+2);

		setPreferredSize(new Dimension(cote_,cote_));
	}

	/**
	 * @return hauteur et largeur de la fenêtre graphique en pixels
	 */
	public int getCote(){

		return cote_;

	}

	/**
	 * Méthode publique de dessin de la grille dans la fenêtre graphique
	 */
	public void dessiner() {
		repaint();  // appel de paintComponent redéfinie ci-après
	}

	/**
	 * Dessin effectif de la grille
	 * @param g Composant graphique de dessin
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		// fond
		Color couleur = new Color(80,80,80);
		g2d.setColor(couleur);
		g2d.fillRect(0,0,cote_,cote_);

		// superposition des couleurs
		g2d.setXORMode(couleur);
		
		// la grille et les choses
		for (int x=0; x<=N_; ++x) {
			int px = (x+1)*Constante.Pix;
			for (int y=0; y<=N_; ++y) {
				int py = (y+1)*Constante.Pix;
				
				// Affichage pour la position (x,y) sur le pixel (px,py)
				
				// Ici un petit carré de couleur blanche est affiché
				// pour montrer que la position est libre

				// TODO : afficher les cases colorées des joueurs là où elles se trouvent

				g2d.setColor(Color.WHITE);
				g2d.fillRect(px,py,3,3);
			}
		}
	}

}
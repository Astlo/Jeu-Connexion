package fr.univnantes.projet;

public class AbandonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AbandonException() {
		super("Sortie du programme demandée.");
	}

}

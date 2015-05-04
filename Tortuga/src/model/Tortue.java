package model;

import java.util.Observable;

public class Tortue extends Observable {

	// Attributs statiques	
	public static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

	// Attributs
	
	//plus besoin de ça
	//		protected ArrayList<Segment> listSegments; // Trace de la tortue
//	protected boolean crayon; // par defaut on suppose qu'on dessine
	
	
	protected int x, y;	// Coordonnees de la tortue
	protected int dir;	// Direction de la tortue (angle en degres)
	protected int coul;

	// Get Set
	public int getColor() {return coul;}
	public int getX() {return x;}
	public int getY() {return y;}
	public int getDirection() {return dir;}
	
	public void setColor(int n) {
		coul = n;
		setChanged();
		notifyObservers();
	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
		setChanged();
		notifyObservers();
	}
	
	public void setDirection(int dir) {
		this.dir = dir;
		setChanged();
		notifyObservers();
	}

	// constructeur
	public Tortue() {
		// feuille = f;
		// feuille.addTortue(this);	

		// plus besoin de ça
		//			listSegments = new ArrayList<Segment>();

		//			reset();
					
	}

	// méthodes
	
	public void reset() {
		this.setPosition(0, 0);
		this.setDirection(-90);
		this.setColor(0);
		setChanged();
		notifyObservers();
	}

}
	

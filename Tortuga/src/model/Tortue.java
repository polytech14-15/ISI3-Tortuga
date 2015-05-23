package model;

import java.util.Observable;

import view.FeuilleDessin;

public abstract class Tortue extends Observable {

	// Attributs statiques	
	public static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
	
	protected static int NB_TORTUE = 0;

	// Attributs
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
		NB_TORTUE++;
	}
	
	public Tortue(int color, int newX, int newY){
		NB_TORTUE++;
		this.coul = color;
		this.x = newX;
		this.y = newY;
		this.dir = -90;
	}

	// m�thodes
	public void reset() {
		this.setPosition(0, 0);
		this.setDirection(-90);
		setChanged();
		notifyObservers();
	}

	// avancer de n pas
	public void avancer(int dist) {
		int newX = (int) Math.round(this.getX()+dist*Math.cos(Tortue.ratioDegRad*this.getDirection()));
		int newY = (int) Math.round(this.getY()+dist*Math.sin(Tortue.ratioDegRad*this.getDirection()));
		
		if (newY < FeuilleDessin.FEUILLE_HEIGHT && newY > 0 && newX < FeuilleDessin.FEUILLE_WIDTH && newX > 0){
			// on v�rifie que la tortue ne d�passe pas de la feuille
			this.setPosition(newX, newY);
		} else {
			this.setDirection(this.getDirection()+90);
			avancer(dist);
		}
	}

	// aller a droite
	public void droite(int ang) {
		this.setDirection((this.getDirection() + ang) % 360);
	}

	// aller a gauche
	public void gauche(int ang) {
		this.setDirection((this.getDirection() - ang) % 360);
	}

	public static void resetNB_TORTUE(){
		Tortue.NB_TORTUE = 0;
	}

}
	

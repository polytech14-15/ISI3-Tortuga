package model;

import java.util.ArrayList;
import java.util.Observable;

public class Jeu extends Observable {

	private ArrayList<Tortue> tortues;
	
	public Jeu(){
		this.tortues = new ArrayList<>();
	}
	
	public void addTortue(Tortue t){
		tortues.add(t);
		
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Tortue> getTortues(){
		return tortues;
	}
	
	
}

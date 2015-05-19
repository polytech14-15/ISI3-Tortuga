package model;

import java.util.Observable;

public class JeuDeBalle extends Observable implements Runnable {
	
	private Jeu jeu;
	
	public JeuDeBalle(Jeu jeu){
		this.jeu = jeu;
	}

	public Jeu getJeu(){
		return this.jeu;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
			
		for(Tortue t : this.jeu.getTortues()){
			System.out.println(t);
		}
		
		setChanged();
		notifyObservers();
		
	}

}

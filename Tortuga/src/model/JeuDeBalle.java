package model;

import java.util.Observable;
import java.util.Random;

import controller.TortugaController;

public class JeuDeBalle extends Observable implements Runnable {
	
	private static final int MAX_JUMP_VALUE = 5;
	private static final int MAX_JUMP_ANGLE = 360;
	private static final int WAIT_FOR_PASS = 2000; // 3s

	private Jeu jeu;
	
	private boolean canPass; // booleen qui determine si la tortue peux faire une passe.

	public JeuDeBalle(Jeu jeu) {
		this.jeu = jeu;
		this.canPass = true;
	}

	public Jeu getJeu() {
		return this.jeu;
	}

	@Override
	public void run() {
		
		Random rand = new Random();
		
		// Tant que la simulation est en cours
		while (TortugaController.SIMULATION_ON){
			// Pour chaque tortue du jeu
			for (Tortue t : this.jeu.getTortues()){
				// Si la tortue n'est pas une balle et n'est pas controlee par l'utilisateur
				if (t != this.jeu.getTortugaBall() && t != this.jeu.getTortugaCourante()){
					
					// Cinq fois plus de chance d'avance que de tourner
					if (rand.nextInt(6) > 1){
						//Avance
						t.avancer(JeuDeBalle.MAX_JUMP_VALUE);
					} else {
						// Change de direction
						t.gauche(rand.nextInt(JeuDeBalle.MAX_JUMP_ANGLE));
					}
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// S'il peut faire la passe
			if (this.canPass){
				TortueAmelioree tAvecBalle = (TortueAmelioree) ((TortueBalle) this.jeu.getTortugaBall()).getMamanTortue();
				Tortue laPlusProche = tAvecBalle;
				double distanceMin = Double.MAX_VALUE;
				double distance;
				for (Tortue t : tAvecBalle.getFriends()){
					distance = tAvecBalle.distanceEuclidienne(t);
					if (distance < distanceMin){
						distanceMin = distance;
						laPlusProche = t;
					}
				}
				
				((TortueBalle) this.jeu.getTortugaBall()).setMamanTortue(laPlusProche);
				changeCanPass();
				waitPass();
				
			}
			setChanged();
			notifyObservers();
		}
	}
	
	 private void waitPass() {
		 // Etablis le Thread 
        new Thread(new Runnable() {
            public void run()
            {
            	try {
					Thread.sleep(JeuDeBalle.WAIT_FOR_PASS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            changeCanPass();
            }
	    }).start();;
	 }

	private void changeCanPass() {
		this.canPass = !this.canPass;
	}
}

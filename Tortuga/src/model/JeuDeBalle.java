package model;

import java.util.Observable;
import java.util.Random;

import controller.TortugaController;

public class JeuDeBalle extends Observable implements Runnable {
	
	private static final int MAX_JUMP_VALUE = 5;
	private static final int MAX_JUMP_ANGLE = 360;

	private Jeu jeu;

	public JeuDeBalle(Jeu jeu) {
		this.jeu = jeu;
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
			
			// TODO - faire une passe au bout d'un certain temps. Une passe au plus proche de ses copains ou alétoire parmis sa bande de pote ?
			
			
			
			setChanged();
			notifyObservers();
		}
	}
	
	//new Thread(() -> waitPass()).run();
	 private void waitPass() {
//	        try {
//	            Thread.sleep(JeuDeBalle.WAIT_FOR_PASS);
//	            this.canPass = true;
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
	        
        new Thread(new Runnable() {
            public void run()
            {
            	Thread.sleep(JeuDeBalle.WAIT_FOR_PASS);
            	this.canPass = true;// Insert some method call here.
            }
	    });
	 }
}

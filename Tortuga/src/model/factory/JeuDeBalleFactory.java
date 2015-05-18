package model.factory;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.plaf.ColorUIResource;

import view.FeuilleDessin;
import model.*;

public class JeuDeBalleFactory {

	public JeuDeBalleFactory(int nbTortues){
		Random rand = new Random();
		Jeu jeu = new Jeu();
		int couleur;
		HashMap<Integer, ArrayList<TortueAmelioree>> tortuesCouleurs = new HashMap<Integer, ArrayList<TortueAmelioree>>();
		
		for (int i = 0; i<nbTortues; i++){
			couleur = rand.nextInt(ColorUtil.NB_COLOR);
			if (!tortuesCouleurs.containsKey(couleur)){
				tortuesCouleurs.put(couleur, new ArrayList<TortueAmelioree>());
			}
			tortuesCouleurs.get(couleur).add(new TortueAmelioree(couleur, rand.nextInt(FeuilleDessin.WIDTH), rand.nextInt(FeuilleDessin.HEIGHT), "Tortue " + i));

		}
		
		for (int c : tortuesCouleurs.keySet()){
			for (int i = 0; i < tortuesCouleurs.get(c).size(); i++){
				for (int j = 0; j < tortuesCouleurs.get(c).size(); j++){
					if (i != j){
						tortuesCouleurs.get(c).get(i).addFriend(tortuesCouleurs.get(c).get(j));
					}
				}
				jeu.addTortue(tortuesCouleurs.get(c).get(i));
			}
		}
		
		TortueBalle ball = new TortueBalle(rand.nextInt(ColorUtil.NB_COLOR), jeu.getTortues().get(rand.nextInt(nbTortues)));
		jeu.addTortue(ball);
		
		new JeuDeBalle(jeu);
	}
}

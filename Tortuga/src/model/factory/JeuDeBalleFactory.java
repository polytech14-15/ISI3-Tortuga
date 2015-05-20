package model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;




import view.FeuilleDessin;
import model.*;

public final class JeuDeBalleFactory {
	
	@SuppressWarnings("unused")
	private JeuDeBalleFactory(){}

	public static JeuDeBalle createJeuDeBalle(int nbTortues){
		Random rand = new Random();
		Jeu jeu = new Jeu();
		int couleur;
		HashMap<Integer, ArrayList<TortueAmelioree>> tortuesCouleurs = new HashMap<Integer, ArrayList<TortueAmelioree>>();
		
		Tortue.resetNB_TORTUE(); // reset le nb_tortue a 0
		
		for (int i = 0; i<nbTortues; i++){
			couleur = rand.nextInt(ColorUtil.NB_COLOR);
			if (!tortuesCouleurs.containsKey(couleur)){
				tortuesCouleurs.put(couleur, new ArrayList<TortueAmelioree>());
			}
			tortuesCouleurs.get(couleur).add(new TortueAmelioree(couleur, rand.nextInt(FeuilleDessin.FEUILLE_WIDTH), rand.nextInt(FeuilleDessin.FEUILLE_HEIGHT), null));

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
		

		// Set la tortue courante dans le jeu
		jeu.setTortugaCourante(jeu.getTortues().get(rand.nextInt(nbTortues)));
		
		TortueBalle ball = new TortueBalle(rand.nextInt(ColorUtil.NB_COLOR), jeu.getTortues().get(rand.nextInt(nbTortues)));
		jeu.addTortue(ball);
		// Set la tortue ball dans le jeu
		jeu.setTortugaBall(ball);

		
		return new JeuDeBalle(jeu);
	}
}

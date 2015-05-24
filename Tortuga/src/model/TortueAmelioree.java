package model;

import java.util.ArrayList;
import java.util.List;

public class TortueAmelioree extends Tortue{

	private static final int PROXIMITY = 30;
	
	private String name;
	private List<Tortue> friends;
	
	public TortueAmelioree(){
		super();
		this.name = "Tortue" + Tortue.NB_TORTUE;
		this.friends = new ArrayList<>();
	}
	
	public TortueAmelioree(int color, int newX, int newY, String name){
		super(color, newX, newY);
		this.name = name == null || name.isEmpty() ? "Tortue" + Tortue.NB_TORTUE : name;
		this.friends = new ArrayList<>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Tortue> getFriends(){
		return this.friends;
	}
	
	public void setFriends(List<Tortue> l){
		this.friends = l;
	}
	
	public void addFriend(Tortue t){
		this.friends.add(t);
	}
	
	public void removeFriend(Tortue t){
		this.friends.remove(t);
	}
	
	public void checkProximity(List<Tortue> l){
		for (Tortue t : l){
			if (this.getFriends().contains(t) && this.distanceEuclidienne(t) <= TortueAmelioree.PROXIMITY){
				if (t instanceof TortueAmelioree){
				System.out.println(this.name+" said: Hey "+((TortueAmelioree) t).getName()+"! Go away!");
				} else {
					System.out.println(this.name+" said: Hey shity-Tortue-that-has-no-name! Go away!");
				}
				t.droite(90); t.avancer(10); //Deplacement de son pote
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public int distanceEuclidienne(Tortue t) {
        return Integer.valueOf((int) Math.sqrt(Math.pow(t.getX() - this.getX(), 2) + Math.pow(t.getY() - this.getY(), 2)));
    }
}

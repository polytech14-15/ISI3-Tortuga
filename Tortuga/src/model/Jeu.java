package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Jeu extends Observable {

	private List<Tortue> tortues;
	private Tortue tortugaCourante;
	private TortueBalle tortugaBall;
	
	public Jeu(){
		this.tortues = new ArrayList<>();
	}
	
	public Jeu(Tortue tortugaCourante){
		this.tortues = new ArrayList<>();
		this.tortugaCourante = tortugaCourante;
		this.addTortue(this.tortugaCourante);
	}
	
	public Jeu(List<Tortue> tortues, Tortue tortugaCourante){
		this.tortues = tortues;
		this.tortugaCourante = tortugaCourante;
	}
	
	
	public Jeu(List<Tortue> tortues, Tortue tortugaCourante, TortueBalle tortugaBall){
		this.tortues = tortues;
		this.tortugaCourante = tortugaCourante;
		this.tortugaBall = tortugaBall;
	}
	
	
	public void addTortue(Tortue t){
		this.tortues.add(t);
		
		setChanged();
		notifyObservers();
	}
	
	public List<Tortue> getTortues(){
		return this.tortues;
	}
	
	public Tortue getTortugaCourante(){
		return this.tortugaCourante;
	}
	
	public void setTortugaCourante(Tortue t){
		this.tortugaCourante = t;
	}
	
	public TortueBalle getTortugaBall(){
		return this.tortugaBall;
	}
	
	public void setTortugaBall(TortueBalle t){
		this.tortugaBall = t;
	}
	
	
}

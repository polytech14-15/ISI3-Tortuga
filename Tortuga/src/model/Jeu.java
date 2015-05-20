package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Jeu extends Observable {

	private List<Tortue> tortues;
	private Tortue tortugaCourante;
	private Tortue tortugaBall;
	
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
		if (this.tortugaCourante != null){
			((TortueAmelioree) this.tortugaCourante).setIsControlled(!((TortueAmelioree) this.tortugaCourante).getIsControlled());
		}
		this.tortugaCourante = t;
		((TortueAmelioree) this.tortugaCourante).setIsControlled(!((TortueAmelioree) this.tortugaCourante).getIsControlled());
		
	}
	
	public Tortue getTortugaBall(){
		return this.tortugaCourante;
	}
	
	public void setTortugaBall(Tortue t){
		this.tortugaBall = t;
	}
	
	
}

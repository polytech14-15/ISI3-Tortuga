package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Jeu extends Observable {

	private List<TortueAmelioree> tortues;
	private TortueAmelioree tortugaCourante;
	private TortueBalle tortugaBall;
	
	public Jeu(){
		this.tortues = new ArrayList<>();
	}
	
	public Jeu(TortueAmelioree tortugaCourante){
		this.tortues = new ArrayList<>();
		this.tortugaCourante = tortugaCourante;
		this.addTortue(this.tortugaCourante);
	}
	
	public Jeu(List<TortueAmelioree> tortues, TortueAmelioree tortugaCourante){
		this.tortues = tortues;
		this.tortugaCourante = tortugaCourante;
	}
	
	
	public Jeu(List<TortueAmelioree> tortues, TortueAmelioree tortugaCourante, TortueBalle tortugaBall){
		this.tortues = tortues;
		this.tortugaCourante = tortugaCourante;
		this.tortugaBall = tortugaBall;
	}
	
	
	public void addTortue(TortueAmelioree t){
		this.tortues.add(t);
		
		setChanged();
		notifyObservers();
	}
	
	public List<TortueAmelioree> getTortues(){
		return this.tortues;
	}
	
	public TortueAmelioree getTortugaCourante(){
		return this.tortugaCourante;
	}
	
	public void setTortugaCourante(TortueAmelioree t){
		this.tortugaCourante = t;
	}
	
	public TortueBalle getTortugaBall(){
		return this.tortugaBall;
	}
	
	public void setTortugaBall(TortueBalle t){
		this.tortugaBall = t;
	}
	
	
}

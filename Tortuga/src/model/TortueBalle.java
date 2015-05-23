package model;

public class TortueBalle extends Tortue{
	
	private Tortue mamanTortue;
	
	public TortueBalle(){
		super();
	}
	
	public TortueBalle(int color, Tortue t){
		super(color, t.getX(), t.getY());
		this.mamanTortue = t;
	}
	
	public Tortue getMamanTortue(){
		return mamanTortue;
	}
	
	public void setMamanTortue(Tortue mamanTortue){
		this.mamanTortue = mamanTortue;
	}

}

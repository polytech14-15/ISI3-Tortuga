package model;

import java.awt.Color;

public class ColorUtil {
	
	public static int NB_COLOR = 9;
	
	public static Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.red);
			case 4: return(Color.green);
			case 5: return(Color.magenta);
			case 6: return(Color.orange);
			case 7: return(Color.pink);
			case 8: return(Color.yellow);
			default : return(Color.black);
		}
	}
}

package view;

// package logo;

import java.awt.*;

import javax.swing.*;

import controller.TortugaController;
import model.Jeu;
import model.Tortue;
import model.TortueAmelioree;

import java.awt.event.*;
import java.util.*;
import java.io.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

				J. Ferber - 1999-2001

				Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/


**************************************************************************/


public class SimpleLogo extends JFrame implements Observer{
	public static final Dimension VGAP = new Dimension(1,5);
	public static final Dimension HGAP = new Dimension(5,1);

	private Jeu jeu;
	private TortugaController con;
	private FeuilleDessin feuille;
//	private Tortue courante;
	private JTextField inputDistance;
	private JTextField inputName;
	private JComboBox colorList;
	private JLabel tCourante;
	private JLabel lDistEucli;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   SwingUtilities.invokeLater(new Runnable(){
				public void run(){

					SimpleLogo fenetre = new SimpleLogo();
					fenetre.setVisible(true);
				}
			});
			
		}
	
	public FeuilleDessin getFeuille(){
		return this.feuille;
	}
	
	public void quitter() {
		System.exit(0);
	}

	public SimpleLogo() {
		super("Tortuga");
		
		this.jeu = new Jeu();
		this.initFeuille();
		this.con = new TortugaController(this, jeu);
		logoInit();
		
		
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent arg0) {
		        super.windowClosing(arg0);
		        System.exit(0);
		    }
		});
		
		
		
	
	}

	public void logoInit() {
		getContentPane().setLayout(new BorderLayout(10,10));

		// Boutons
		JToolBar toolBar = new JToolBar();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(toolBar);

		getContentPane().add(buttonPanel,"North");

		addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");
		
		toolBar.add(Box.createRigidArea(HGAP));
		inputDistance=new JTextField("45",5);
		toolBar.add(inputDistance);
		addButton(toolBar, "Avancer", "Avancer", null);
		addButton(toolBar, "Droite", "Droite", null);
		addButton(toolBar, "Gauche", "Gauche", null);
//		addButton(toolBar, "Lever", "Lever Crayon", null);
//		addButton(toolBar, "Baisser", "Baisser Crayon", null);

		
//				new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JComboBox cb = (JComboBox)e.getSource();
//				int n = cb.getSelectedIndex();
//				courante.setColor(n);
//			}
//		}
//				);
		
		
		// Menus
		JMenuBar menubar=new JMenuBar();
		setJMenuBar(menubar);	// on installe le menu bar
		JMenu menuFile=new JMenu("File"); // on installe le premier menu
		menubar.add(menuFile);

		addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
		addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

		JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
		menubar.add(menuCommandes);
		addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
		addMenuItem(menuCommandes, "Droite", "Droite", -1);
		addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
//		addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
//		addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

		JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
		menubar.add(menuHelp);
		addMenuItem(menuHelp, "Aide", "Help", -1);
		addMenuItem(menuHelp, "A propos", "About", -1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// les boutons du bas
		JToolBar toolBar2 = new JToolBar();
		JPanel buttonPanel2 = new JPanel();
		buttonPanel2.add(toolBar2);

		getContentPane().add(buttonPanel2,"South");
//		JButton b20 = new JButton("Proc1");
//		p2.add(b20);
//		b20.addActionListener(con);
//		JButton b21 = new JButton("Proc2");
//		p2.add(b21);
//		b21.addActionListener(con);
//		JButton b22 = new JButton("Proc3");
//		p2.add(b22);
//		b22.addActionListener(con);
		
		JLabel colorLabel = new JLabel("Nom: ");
		toolBar2.add(colorLabel);
		inputName =new JTextField(5);
		toolBar2.add(inputName);
		
		String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
				 "vert", "gris clair", "magenta", "orange",
				 "gris", "rose", "jaune"};

		// Create the combo box
//		buttonPanel2.add(Box.createRigidArea(HGAP));
		JLabel colorLabel2 = new JLabel("Couleur: ");
		toolBar2.add(colorLabel2);
		this.colorList = new JComboBox(colorStrings);
		colorList.setActionCommand("choixCouleur");
		toolBar2.add(colorList);
		
		colorList.addActionListener(con);
		addButton(toolBar2, "Ajouter", "Ajouter une tortue", null);
//		getContentPane().add(p2,"South");

//		feuille = new FeuilleDessin(this.jeu); //500, 400);
//		feuille.setBackground(Color.white);
//		feuille.setSize(new Dimension(600,400));
//		feuille.setPreferredSize(new Dimension(600,400));
			
		getContentPane().add(feuille,"Center");
		
		JPanel panel3 = new JPanel(new GridLayout(0,1,0,0));;
		getContentPane().add(panel3, BorderLayout.LINE_END);
		
		JLabel lInfos = new JLabel("<html> -Clic gauche sur une tortue pour<br>la sélectionner<br><br>"
				+ "-Clic droit sur une tortue pour <br> ajouter/enlever un ami<br><br>"
				+ "-Clic milieu sur une tortue pour <br>calculer la distance euclidinne <br>depuis la tortue courante");
		panel3.add(lInfos);
		
		tCourante = new JLabel();
		panel3.add(tCourante);
		
		lDistEucli = new JLabel();
		panel3.add(lDistEucli);
		
//		// Creation de la tortue
//		Tortue tortue = new Tortue();
//		
//		// Deplacement de la tortue au centre de la feuille
//		tortue.setPosition(500/2, 400/2); 		
//		
//		courante = tortue;
//		feuille.addTortue(tortue);
		
		feuille.addMouseListener(con);

		pack();
		setVisible(true);
	}
	
	private void initFeuille(){
		feuille = new FeuilleDessin(this.jeu); //500, 400);
		feuille.setBackground(Color.white);
		feuille.setSize(new Dimension(600,400));
		feuille.setPreferredSize(new Dimension(600,400));
	}

	public String getInputDistance(){
		String s = inputDistance.getText();
		return(s);
	}
	
	public String getInputName(){
		if (inputName == null) System.out.println("ytvukblnl");
		return inputName.getText();
	}
	
	public int getColorIndex(){
		return colorList.getSelectedIndex();
	}

//	/** la gestion des actions des boutons */
//	public void actionPerformed(ActionEvent e)
//	{
//		String c = e.getActionCommand();
//
//		// actions des boutons du haut
//		if (c.equals("Avancer")) {
//			System.out.println("command avancer");
//			try {
//				int v = Integer.parseInt(inputValue.getText());
//				courante.avancer(v);
//			} catch (NumberFormatException ex){
//				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
//			}
//			
//		}
//		else if (c.equals("Droite")) {
//			try {
//				int v = Integer.parseInt(inputValue.getText());
//				courante.droite(v);
//			} catch (NumberFormatException ex){
//				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
//			}
//		}
//		else if (c.equals("Gauche")) {
//			try {
//				int v = Integer.parseInt(inputValue.getText());
//				courante.gauche(v);
//			} catch (NumberFormatException ex){
//				System.err.println("ce n'est pas un nombre : " + inputValue.getText());
//			}
//		}
//		else if (c.equals("Lever")) 
//			courante.leverCrayon();
//		else if (c.equals("Baisser"))
//			courante.baisserCrayon();
//		// actions des boutons du bas
//		else if (c.equals("Proc1"))
//			proc1();
//		else if (c.equals("Proc2"))
//			proc2();
//		else if (c.equals("Proc3"))
//			proc3();
//		else if (c.equals("Effacer"))
//			effacer();
//		else if (c.equals("Quitter"))
//			quitter();
//
//		feuille.repaint();
//	}

//  	/** les procedures Logo qui combine plusieurs commandes..*/
//	public void proc1() {
//		courante.carre();
//	}
//
//	public void proc2() {
//		courante.poly(60,8);
//	}
//
//	public void proc3() {
//		courante.spiral(50,40,6);
//	}
//
//	// efface tout et reinitialise la feuille
//	public void effacer() {
//		feuille.reset();
//		feuille.repaint();
//
//		// Replace la tortue au centre
//		Dimension size = feuille.getSize();
//		courante.setPosition(size.width/2, size.height/2);
//	}

	//utilitaires pour installer des boutons et des menus
	public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
		JButton b;
		if ((imageName == null) || (imageName.equals(""))) {
			b = (JButton)p.add(new JButton(name));
		}
		else {
			java.net.URL u = this.getClass().getResource(imageName);
			if (u != null) {
				ImageIcon im = new ImageIcon (u);
				b = (JButton) p.add(new JButton(im));
			}
			else
				b = (JButton) p.add(new JButton(name));
			b.setActionCommand(name);
		}

		b.setToolTipText(tooltiptext);
		b.setBorder(BorderFactory.createRaisedBevelBorder());
		b.setMargin(new Insets(0,0,0,0));
		b.addActionListener(con);
	}

	public void addMenuItem(JMenu m, String label, String command, int key) {
		JMenuItem menuItem;
		menuItem = new JMenuItem(label);
		m.add(menuItem);

		menuItem.setActionCommand(command);
		menuItem.addActionListener(con);
		if (key > 0) {
			if (key != KeyEvent.VK_DELETE)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
			else
				menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.feuille.setTortues(this.jeu.getTortues());
		this.feuille.repaint();
		
		if (this.con != null){
			this.tCourante.setText("Tortue Courante: "+((TortueAmelioree)this.con.getTortugaCourante()).getName()+"   ");
			
			if (this.con.getLastDistanceCalculated() != null){
				this.lDistEucli.setText("<html>La distance euclidienne <br>est de : "+this.con.getLastDistanceCalculated()+"</html>");
			}else{
				this.lDistEucli.setText("");
			}
		}
	}

}

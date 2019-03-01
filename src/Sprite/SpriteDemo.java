package Sprite;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;
import Monde.Monde;

public class SpriteDemo extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image tSprite;
	private Image PokemonFeu;
	private Image PokemonFeuEvolue;
	private Image PokemonEau;
	private Image PokemonEauEvolue;
	private Image Apple;
	private Image ApplePourri;
	private Image Chasseur;
	public static int dx;
	public static int dy;
	private int spriteLength = 32;

	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("water.png"));
			treeSprite = ImageIO.read(new File("arbref.png"));
			grassSprite = ImageIO.read(new File("grass.png"));
			tSprite = ImageIO.read(new File("tree.png"));
			PokemonFeu = ImageIO.read(new File("hericendre.png"));
			PokemonFeuEvolue = ImageIO.read(new File("FeurissonTrans.png")); 
			PokemonEau = ImageIO.read(new File("carapuce.png"));
			PokemonEauEvolue = ImageIO.read(new File("CarabaffeTrans.png")); 
			Apple = ImageIO.read(new File("pomme.png"));
			ApplePourri = ImageIO.read(new File("pommeP.png"));
			Chasseur = ImageIO.read(new File("chasseur.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(965,1000);
		frame.setVisible(true);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < dx ; i++ )
			for ( int j = 0 ; j < dy ; j++ )
			{
				try {
				
					if ( Monde.testC(i, j) ==null ) {
						
						g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						continue;
					}
						if (Monde.testC(i, j) instanceof M1) {
							if (((M1) Monde.testC(i, j)).getNb_evolution() == 0)
								g2.drawImage(PokemonFeu,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
							if (((M1) Monde.testC(i, j)).getNb_evolution() == 1)
								g2.drawImage(PokemonFeuEvolue,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						}
						if (Monde.testC(i, j) instanceof M2) {
							if (((M2) Monde.testC(i, j)).getNb_evolution() == 0)
								g2.drawImage(PokemonEau,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
							if (((M2) Monde.testC(i, j)).getNb_evolution() == 1)
								g2.drawImage(PokemonEauEvolue,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						}
						if (Monde.testC(i, j) instanceof Arbre) {
							g2.drawImage(tSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						}
						if (Monde.testC(i, j)instanceof Pomme) {
							g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
							if ((((Pomme) Monde.testC(i, j)).isEstPourrie() == false))
								g2.drawImage(Apple,spriteLength*i,spriteLength*j,spriteLength-10,spriteLength-10, frame);
							if ((((Pomme) Monde.testC(i, j)).isEstPourrie() == true))
								g2.drawImage(ApplePourri,spriteLength*i,spriteLength*j,spriteLength-10,spriteLength-10, frame);
						}
						if (Monde.testC(i, j) instanceof Braconnier)
							g2.drawImage(Chasseur,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);

				}catch(Exception E) {
				}
			}
	
	}
	public static void main(String[] args) {
		Monde monde = new Monde(dx=30,dy=30,10);
		SpriteDemo a =new SpriteDemo();
		int step=0;
		while(true) {
			a.repaint();
			monde.Refresh();
			monde.pomme_pop(step);
			Pomme.duree();
			Pomme.delete();
			//Braconnier.chasser();
			try{
				Thread.sleep(400); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			step++;
		}
	}
}

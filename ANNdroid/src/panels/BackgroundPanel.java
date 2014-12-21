package ANNdroid.src.panels;

import ANNdroid.src.util.Utility;

import java.io.File;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class BackgroundPanel extends JPanel{

	public BufferedImage bgImage;

	public BackgroundPanel(int height, int width){

		setPreferredSize(new Dimension(width, height));
		setBounds(0, 0, width, height);

		try{
			bgImage = ImageIO.read(new File("ANNdroid/resources/img/bg.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
	}

	public void resize(){
		bgImage = Utility.scaleImage(bgImage, getWidth(), getHeight());
		repaint();
	}

}
package Modelo;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Picterodatilo {

	private ImageIcon imgPterodatilo;
	public int velPtero;
	private Point posPtero;

	public Picterodatilo(String url) {

		posPtero = new Point(0, 80);
		velPtero = 30;
		imgPterodatilo = new ImageIcon(getClass().getResource(url));
	}
	

	public Point getPosPtero() {
		return posPtero;
	}


	public ImageIcon getImgPterodatilo() {
		return imgPterodatilo;
	}
	
}

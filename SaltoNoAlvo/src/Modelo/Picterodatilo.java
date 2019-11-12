package Modelo;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Picterodatilo {

	private BufferedImage imgPterodatilo;
	public int velPtero;
	private Point posPtero;

	public Picterodatilo(String url) {

		posPtero = new Point(0, 25);
		velPtero = 30;
		try {
			imgPterodatilo =ImageIO.read(getClass().getClassLoader().getResourceAsStream(url));
		} catch (IOException e) {
			System.out.println("erro na imagem picterodatilo");
		}
	}
	public BufferedImage getImgPterodatilo() {
		return imgPterodatilo;
	}

	public Point getPosPtero() {
		return posPtero;
	}
}

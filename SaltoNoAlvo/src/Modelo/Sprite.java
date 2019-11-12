package Modelo;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite extends Thread {

	private int x, y;
	private int altura, largura;
	int rows, columns;
	public int aparencia;
	public BufferedImage[] sprites;
	BufferedImage spriteSheet; 
	public Point2D.Float velJogador;
	public Point2D.Float posJogador;

	public Sprite(String url, int aparencia, int columns, int rows, int posX, int posY) throws IOException {
		spriteSheet = ImageIO.read(getClass().getClassLoader().getResource(url));
		this.aparencia=aparencia;

		this.largura = spriteSheet.getWidth()/columns;
		this.altura = spriteSheet.getHeight()/rows;

		posJogador = new Point2D.Float(0, 0);
		velJogador = new Point2D.Float();

		this.rows = columns;
		this.columns = rows;
		this.x=posX;
		this.y=posY;

		sprites = new BufferedImage[columns * rows];
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * largura, j * altura, largura, altura);
			}
		}
	}
	public int getX() {
		return x;
	}

	public int getY() {	
		return y;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}

}

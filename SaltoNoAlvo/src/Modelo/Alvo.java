package Modelo;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Alvo {

	private BufferedImage alvo;
	private Point posAlvo;
	private int largAlvo;
	private String cor="";
	private String caminho="";
	
	
	public Alvo(String url,String cor) {

		posAlvo = new Point(0, 546);
		largAlvo = 50;
		try {
			alvo =ImageIO.read(getClass().getClassLoader().getResourceAsStream(url));
		} catch (IOException e) {
			System.out.println("erro ao carregar alvo");
		}
		
		this.cor=cor;
		
	}
	public BufferedImage getAlvo() {
		return alvo;
	}
	public Point getPosAlvo() {
		return posAlvo;
	}
	public int getLargAlvo() {
		return largAlvo;
	}
	public void setAlvo(BufferedImage alvo) {
		this.alvo = alvo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}

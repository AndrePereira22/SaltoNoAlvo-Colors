package Visao;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modelo.Alvo;
import Modelo.Jogo;
import Modelo.Cenario;
import Modelo.Picterodatilo;
import Modelo.Sprite;

public class FaseCores  extends Jogo  {

	private static final long serialVersionUID = 1L;
	private Cenario mapa1,mapa2;
	private Sprite caverna;
	private Alvo alvo;
	private BufferedImage alvoVerde,alvoAmarelo,alvoAzul,alvoVermelho,alvoRosa,alvoLaranja,alvoCinza,alvoBranco,alvoPreto,alvoMarrom;
	private Picterodatilo picterodatilo;
	
	public FaseCores(int largura,int altura) {
		super(largura,altura);
		Load();
		FecharVisible();		
	}
	
	public void Load() {
		mapa1= new Cenario("tileset.png","camada1.txt");
		mapa2= new Cenario("tileset.png","camada2.txt");

		mapa1.montarMapa();
		mapa2.montarMapa();
		try {
			caverna = new Sprite("sprite.png",2,3,1,170,170);
		
	} catch (IOException e) {
	System.out.println("Imagem caverna fase1");
	}
		picterodatilo = new Picterodatilo("aviao1.png");
		alvo = new Alvo("alvo1.png","BLUE");
		
		
		try {
			alvoVerde=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo2.png")));
			alvoAmarelo=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo3.png")));
			alvoAzul=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo1.png")));
			alvoVermelho=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo4.png")));
			alvoRosa=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo5.png")));
			alvoLaranja=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo6.png")));
			alvoCinza=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo7.png")));
			alvoBranco=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo8.png")));
			alvoPreto=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo9.png")));
			alvoMarrom=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo10.png")));
		} catch (IOException e) {
			System.out.println("erro ao carregar alvo");
		}
		
	}
	public void Update() {
		mapa1.montarMapa();	
	}
	public void Render() {
		
		g.drawImage(mapa1.getMapa(),0, 0, null);
		g.drawImage(mapa2.getMapa(),0, 0, null);
		

		g.drawImage(picterodatilo.getImgPterodatilo(), picterodatilo.getPosPtero().x,
				picterodatilo.getPosPtero().y, null);
		
		g.drawImage(alvo.getAlvo(), alvo.getPosAlvo().x, alvo.getPosAlvo().y-2, null);
		
		
		g.drawImage(caverna.sprites[caverna.aparencia],(int) caverna.posJogador.x +26,
                (int) caverna.posJogador.y-38 , null);		
	}  
	public Sprite getCaverna() {
		return caverna;
	}
	public Picterodatilo getPicterodatilo() {
		return picterodatilo;
	}
	public Alvo getAlvo() {
		return alvo;
	}
	public Picterodatilo getAviao() {
		return picterodatilo;
	}
	public void setMapa1(Cenario mapa1) {
		this.mapa1 = mapa1;
	}
	public void setMapa2(Cenario mapa2) {
		this.mapa2 = mapa2;
	}
	public void setCaverna(Sprite caverna) {
		this.caverna = caverna;
	}
	public void setAlvo(Alvo alvo) {
		this.alvo = alvo;
	}
	public void setPicterodatilo(Picterodatilo picterodatilo) {
		this.picterodatilo = picterodatilo;
	}
	public void FecharVisible() {
		setVisible(false);
	}
	public void AbriVisible() {
		setVisible(true);	
	}
	public BufferedImage getAlvoVerde() {
		return alvoVerde;
	}
	public void setAlvoVerde(BufferedImage alvoVerde) {
		this.alvoVerde = alvoVerde;
	}
	public BufferedImage getAlvoAmarelo() {
		return alvoAmarelo;
	}
	public void setAlvoAmarelo(BufferedImage alvoAmarelo) {
		this.alvoAmarelo = alvoAmarelo;
	}
	public BufferedImage getAlvoVermelho() {
		return alvoVermelho;
	}
	public void setAlvoVermelho(BufferedImage alvoVermelho) {
		this.alvoVermelho = alvoVermelho;
	}
	public BufferedImage getAlvoAzul() {
		return alvoAzul;
	}
	public void setAlvoAzul(BufferedImage alvoAzul) {
		this.alvoAzul = alvoAzul;
	}
	public BufferedImage getAlvoRosa() {
		return alvoRosa;
	}
	public void setAlvoRosa(BufferedImage alvoRosa) {
		this.alvoRosa = alvoRosa;
	}
	public BufferedImage getAlvoLaranja() {
		return alvoLaranja;
	}
	public void setAlvoLaranja(BufferedImage alvoLaranja) {
		this.alvoLaranja = alvoLaranja;
	}
	public BufferedImage getAlvoCinza() {
		return alvoCinza;
	}
	public void setAlvoCinza(BufferedImage alvoCinza) {
		this.alvoCinza = alvoCinza;
	}
	public BufferedImage getAlvoBranco() {
		return alvoBranco;
	}
	public void setAlvoBranco(BufferedImage alvoBranco) {
		this.alvoBranco = alvoBranco;
	}
	public BufferedImage getAlvoPreto() {
		return alvoPreto;
	}
	public void setAlvoPreto(BufferedImage alvoPreto) {
		this.alvoPreto = alvoPreto;
	}
	public BufferedImage getAlvoMarrom() {
		return alvoMarrom;
	}
	public void setAlvoMarrom(BufferedImage alvoMarrom) {
		this.alvoMarrom = alvoMarrom;
	}
	
}


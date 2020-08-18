package Visao;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Modelo.Alvo;
import Modelo.Cenario;
import Modelo.Jogo;
import Modelo.Picterodatilo;
import Modelo.Sprite;

public class FaseBandeiras  extends Jogo  {

	private static final long serialVersionUID = 1L;

	private Cenario mapa1,mapa2;
	private Sprite caverna;
	private Alvo alvo;
	private BufferedImage canada,espanha,eua,franca,italia,noruega,sui�a,belgica,alemanha,finlandia;
	
	private Picterodatilo picterodatilo;
	
	public FaseBandeiras(int largura,int altura) {
		super(largura,altura);
		Load();
		FecharVisible();		
	}
	public void Load() {
		mapa1= new Cenario("tileset.png","camada3.txt");
		mapa2= new Cenario("tileset.png","camada4.txt");

		mapa1.montarMapa();
		mapa2.montarMapa();
		try {
			caverna = new Sprite("sprite.png",2,3,1,170,170);
			alvo = new Alvo("alvo1.png","BLUE");
		
	} catch (IOException e) {
		System.out.println("a imagem caverna fase2");
	}
		try {
			canada=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo2.png")));
			espanha=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo3.png")));
			eua=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo1.png")));
			franca=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo4.png")));
			italia=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo5.png")));
			noruega=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo6.png")));
			sui�a=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo7.png")));
			belgica=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo8.png")));
			alemanha=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alemanha.png")));
			finlandia=(ImageIO.read(getClass().getClassLoader().getResourceAsStream("alvo10.png")));
		} catch (IOException e) {
			System.out.println("erro ao carregar alvo");
		}
		picterodatilo = new Picterodatilo("aviao2.png");
				
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
			
		g.drawImage(caverna.sprites[caverna.aparencia],(int) caverna.posJogador.x -17,
                (int) caverna.posJogador.y-44 , null);
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
	public Cenario getMapa1() {
		return mapa1;
	}
	public Cenario getMapa2() {
		return mapa2;
	}
	public void FecharVisible() {
		setVisible(false);	
	}
	public void AbriVisible() {
		setVisible(true);	
	}
	public BufferedImage getCanada() {
		return canada;
	}
	public void setCanada(BufferedImage canada) {
		this.canada = canada;
	}
	public BufferedImage getEspanha() {
		return espanha;
	}
	public void setEspanha(BufferedImage espanha) {
		this.espanha = espanha;
	}
	public BufferedImage getEua() {
		return eua;
	}
	public void setEua(BufferedImage eua) {
		this.eua = eua;
	}
	public BufferedImage getFranca() {
		return franca;
	}
	public void setFranca(BufferedImage franca) {
		this.franca = franca;
	}
	public BufferedImage getItalia() {
		return italia;
	}
	public void setItalia(BufferedImage italia) {
		this.italia = italia;
	}
	public BufferedImage getNoruega() {
		return noruega;
	}
	public void setNoruega(BufferedImage noruega) {
		this.noruega = noruega;
	}
	public BufferedImage getSui�a() {
		return sui�a;
	}
	public void setSui�a(BufferedImage sui�a) {
		this.sui�a = sui�a;
	}
	public BufferedImage getBelgica() {
		return belgica;
	}
	public void setBelgica(BufferedImage belgica) {
		this.belgica = belgica;
	}
	public BufferedImage getAlemanha() {
		return alemanha;
	}
	public void setAlemanha(BufferedImage alemanha) {
		this.alemanha = alemanha;
	}
	public BufferedImage getFinlandia() {
		return finlandia;
	}
	public void setFinlandia(BufferedImage finlandia) {
		this.finlandia = finlandia;
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
	
	
}



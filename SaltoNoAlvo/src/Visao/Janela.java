package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Janela extends JFrame {

	private static final long serialVersionUID = 9139033148377613572L;
	private static final int LARGURA = 649, ALTURA = 601;


	Cores cores;
	FaseCores fase1;
	FaseBandeiras fase2;
	Menu menu;
	Creditos creditos;
	Ajuda ajuda;
	Ranking ranking;
	Configuracoes config;
	Fundo inventario;
	Score score;

	public Janela(int largura, int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(new FlowLayout());

		setSize(largura, altura);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setUndecorated(true);
		setLocationRelativeTo(null);

		cores = new Cores(LARGURA, ALTURA);
		fase1 = new FaseCores();
		fase2 = new FaseBandeiras();
		menu = new Menu(LARGURA, ALTURA);
		creditos = new Creditos(LARGURA, ALTURA);
		ajuda = new Ajuda(LARGURA, ALTURA);
		ranking = new Ranking(LARGURA, ALTURA);
		config = new Configuracoes(LARGURA, ALTURA);
		inventario = new Fundo(640, 640);
		score = new Score(LARGURA, ALTURA);
	
		inventario.setVisible(true);
		
		add(menu);
		add(cores);
		add(ajuda);
		add(creditos);
		add(ranking);
		add(config);
		add(inventario);
		add(score);
	

		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("baseDados\\caverna.png");
		this.setIconImage(iconeTitulo);
	}

	public void setTamanhoTela(Janela janela) {

		janela.setSize(649, 601);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getLargura() {
		return LARGURA;
	}

	public static int getAltura() {
		return ALTURA;
	}



	public Cores getCores() {
		return cores;
	}

	public FaseCores getFase1() {
		return fase1;
	}

	public FaseBandeiras getFase2() {
		return fase2;
	}

	public Menu getMenu() {
		return menu;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public Ajuda getAjuda() {
		return ajuda;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public Configuracoes getConfig() {
		return config;
	}

	public Fundo getInventario() {
		return inventario;
	}

	public Score getScore() {
		return score;
	}

}

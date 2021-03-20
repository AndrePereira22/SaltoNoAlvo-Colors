package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;

public class Fundo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  JLabel pontuacao,tentativas,jogador,nivel,fundo;
	private static String pergunta;
	private Font jokerman;	
	JPanel PanelFase;
	
	public Fundo(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 20);

		setPergunta("Qual a cor do alvo?");
		
		pontuacao = new JLabel("0");
		pontuacao.setForeground(Color.WHITE);
		pontuacao.setFont(jokerman);
		pontuacao.setBounds(460, 33, 46, 19);
		add(pontuacao);

		tentativas = new JLabel("10");
		tentativas.setForeground(Color.WHITE);
		tentativas.setFont(jokerman);
		tentativas.setBounds(597, 29, 52, 26);
		add(tentativas);

		jogador = new JLabel("Andre");
		jogador.setForeground(Color.WHITE);
		jogador.setFont(jokerman);
		jogador.setBounds(90, 22, 169, 39);
		add(jogador);
		
		nivel = new JLabel("1");
		nivel.setForeground(Color.WHITE);
		nivel.setFont(jokerman);
		nivel.setBounds(350, 33, 46, 19);
		add(nivel);

		
		JLabel ImgInverntario = new JLabel("New label");
		ImgInverntario.setIcon(new ImageIcon(getClass().getResource("/inventario.png")));
		ImgInverntario.setBounds(0, 0, largura, 67);
		add(ImgInverntario);
		
		PanelFase = new JPanel();
		PanelFase.setBounds(0, 67, largura, 640);
		PanelFase.setBackground(Color.black);
		add(PanelFase);

		setVisible(getFocusTraversalKeysEnabled());
	}

	public JLabel getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(JLabel pontuacao) {
		this.pontuacao = pontuacao;
	}

	public JLabel getTentativas() {
		return tentativas;
	}

	public void setTentativas(JLabel tentativas) {
		this.tentativas = tentativas;
	}

	public JLabel getJogador() {
		return jogador;
	}

	public void setJogador(JLabel jogador) {
		this.jogador = jogador;
	}

	public JLabel getNivel() {
		return nivel;
	}

	public void setNivel(JLabel nivel) {
		this.nivel = nivel;
	}

	public JLabel getFundo() {
		return fundo;
	}

	public void setFundo(JLabel fundo) {
		this.fundo = fundo;
	}

	public static String getPergunta() {
		return pergunta;
	}

	public static void setPergunta(String pergunta) {
		Fundo.pergunta = pergunta;
	}

	public Font getJokerman() {
		return jokerman;
	}

	public void setJokerman(Font jokerman) {
		this.jokerman = jokerman;
	}

	public JPanel getPanelFase() {
		return PanelFase;
	}

	public void setPanelFase(JPanel panelFase) {
		PanelFase = panelFase;
	}
	
	
}

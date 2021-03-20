package Visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Pergunta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel mens, alvo;
	private JTextField resposta;
	private Random sorteio = new Random();
	private String[] nomes = { "BLUE", "GREEN", "YELLOW", "RED", "PINK", "ORANGE", "GRAY", "WHITE", "BLACK", "BROWN" };
	private String[] flags = { "ALEMANHA", "BELGICA", "CANADÁ", "ESPANHA", "EUA", "FINLANDIA", "FRANÇA", "ITALIA", "NORUEGA", "SUIÇA" };
	private Font jokerman;
	private JPanel jpResposta, jpConteudo;

	public Pergunta(javax.swing.JFrame janela, boolean vf) {

		super(janela, vf);
		setJokerman(new Font("Jokerman", Font.BOLD, 26));

		setBounds(0, 100, 600, 202);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.gray);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		alvo = new JLabel(new ImageIcon(getClass().getResource("/alvo2.png")));
		alvo.setBounds(130, 25, 113, 47);
		contentPanel.add(alvo);

		mens = new JLabel("Qual a cor ?");
		mens.setBounds(200, 20, 150, 20);
		mens.setFont(new Font("Jokerman", Font.BOLD, 20));

		jpResposta = new JPanel();
		jpResposta.setLayout(new FlowLayout());
		jpResposta.setBounds(0, 100, 640, 100);

		jpConteudo = new JPanel();
		jpConteudo.setLayout(new BorderLayout());
		jpConteudo.setBounds(0, 0, 640, 100);
		jpConteudo.add(alvo);
		jpConteudo.add(mens);

		contentPanel.add(jpResposta);
		contentPanel.add(jpConteudo);
		setLocationRelativeTo(janela);

		setVisible(false);

	}
	
	public void AddBotoesBanderas() {

		jpResposta.removeAll();

		boolean status = true;
		while (status) {

			int numero = sorteio.nextInt(10);
			if (verificarFlag(numero)) {
				JButton op1 = new JButton(flags[numero]);
				op1.setForeground(Color.BLACK);
				op1.setIcon(new ImageIcon(getClass().getResource("/flag.png")));
				op1.setBounds(0, 125, 100, 53);
				op1.setContentAreaFilled(false);
				op1.setBorder(null);
				op1.setVerticalTextPosition(SwingConstants.BOTTOM);
				op1.setHorizontalTextPosition(SwingConstants.CENTER);
				jpResposta.add(op1);
			}
			if (jpResposta.getComponents().length == 5) {
				status = false;
			}

		}
	}
	

	public void AddBotoesCores() {

		jpResposta.removeAll();

		boolean status = true;
		while (status) {

			int numero = sorteio.nextInt(10);
			if (verificar(numero)) {
				JButton op1 = new JButton(nomes[numero]);
				op1.setForeground(Color.BLACK);
				op1.setIcon(new ImageIcon(getClass().getResource("/icone.png")));
				op1.setBounds(0, 125, 100, 53);
				op1.setContentAreaFilled(false);
				op1.setBorder(null);
				op1.setVerticalTextPosition(SwingConstants.BOTTOM);
				op1.setHorizontalTextPosition(SwingConstants.CENTER);
				jpResposta.add(op1);
			}
			if (jpResposta.getComponents().length == 5) {
				status = false;
			}

		}
	}
	
	public boolean verificarFlag(int numero) {

		if (jpResposta.getComponents().length == 0) {
			return true;

		} else {
			for (Component botao : jpResposta.getComponents()) {
				JButton b = (JButton) botao;

				if (b.getText().equals(flags[numero])) {
					return false;
				}
			}
		}

		return true;
	}
	

	public boolean verificar(int numero) {

		if (jpResposta.getComponents().length == 0) {
			return true;

		} else {
			for (Component botao : jpResposta.getComponents()) {
				JButton b = (JButton) botao;

				if (b.getText().equals(nomes[numero])) {
					return false;
				}
			}
		}

		return true;
	}

	public void vericarResposta(String resposta) {

		boolean r = false;

		for (Component botao : jpResposta.getComponents()) {

			JButton b = (JButton) botao;
			if (b.getText().equals(resposta)) {
				r = true;
			}
		}
		if (r == false) {

			int numero = sorteio.nextInt(5);
			Component botao = jpResposta.getComponent(numero);
			JButton b = (JButton) botao;
			b.setText(resposta);

		}

	}

	public JLabel getAlvo() {
		return alvo;
	}

	public JTextField getResposta() {
		return resposta;
	}

	public void mudarAlvo(String url) {
		alvo.setIcon(new ImageIcon(getClass().getResource(url)));
	}

	public JLabel getMens() {
		return mens;
	}

	public Font getJokerman() {
		return jokerman;
	}

	public void setJokerman(Font jokerman) {
		this.jokerman = jokerman;
	}

	public JPanel getJpResposta() {
		return jpResposta;
	}

}

package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Score extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblPontuacao,lblJogador;
	private Font jokerman;
	
	public  Score(int largura,int altura){
		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 35);
		
		lblPontuacao = new JLabel("Pontuacao");
		lblPontuacao.setFont(jokerman);
		lblPontuacao.setForeground(Color.ORANGE);
		lblPontuacao.setBounds(250, 281, 200, 41);
		add(lblPontuacao);
		
		lblJogador = new JLabel("Jogador");
		lblJogador.setFont(jokerman);
		lblJogador.setForeground(Color.ORANGE);
		lblJogador.setBounds(193, 218, 400, 41);
		add(lblJogador);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/resultados.png")));
		label.setBounds(0, 0, 651, 601);
		add(label);
		FecharVisible();
	}
	public void FecharVisible() {
		setVisible(false);

	}
	public void AbriVisible() {
		setVisible(true);

	}
	public JLabel getLblPontuacao() {
		return lblPontuacao;
	}
	public void setLblPontuacao(JLabel lblPontuacao) {
		this.lblPontuacao = lblPontuacao;
	}
	public JLabel getLblJogador() {
		return lblJogador;
	}
	public void setLblJogador(JLabel lblJogador) {
		this.lblJogador = lblJogador;
	}	
}

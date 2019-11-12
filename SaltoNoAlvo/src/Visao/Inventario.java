package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Inventario extends JPanel {

	private static final long serialVersionUID = 1L;

	private  JLabel pontuacao,tentativas,jogador,nivel,fundo;
	private static String pergunta;

	private Font jokerman;

	public Inventario(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 20);

		setPergunta("Qual a cor do alvo?");
		
		pontuacao = new JLabel("0");
		pontuacao.setForeground(Color.WHITE);
		pontuacao.setFont(jokerman);
		pontuacao.setBounds(585, 51, 46, 19);
		add(pontuacao);

		tentativas = new JLabel("10");
		tentativas.setForeground(Color.WHITE);
		tentativas.setFont(jokerman);
		tentativas.setBounds(436, 46, 52, 26);
		add(tentativas);

		jogador = new JLabel("Andre");
		jogador.setForeground(Color.WHITE);
		jogador.setFont(jokerman);
		jogador.setBounds(135, 34, 169, 39);
		add(jogador);
		
		nivel = new JLabel("1");
		nivel.setForeground(Color.WHITE);
		nivel.setFont(jokerman);
		nivel.setBounds(730, 51, 46, 19);
		add(nivel);

		fundo = new JLabel(new ImageIcon(getClass().getResource("/inventario.png")));
		fundo.setBounds(0, 0, 849, 86);
		add(fundo);
		
		
		
		FecharVisible();

	}
	

		public void FecharVisible() { setVisible(false);	}

		public void AbriVisible() { setVisible(true);	}

		public  String getTxtPontuacao() { return pontuacao.getText();}

		public String getTxtTentativas() {return tentativas.getText();}

		public String getTxtJogador() { return jogador.getText(); }

		public JLabel getPontuacao() { return pontuacao;}

		public JLabel getTentativas() {return tentativas;}

		public JLabel getJogador() { return jogador; }
		
		public JLabel getNivel() {	return nivel;	}


		public static String getPergunta() {
			return pergunta;
		}


		public static void setPergunta(String pergunta) {
			Inventario.pergunta = pergunta;
		}
		
}	
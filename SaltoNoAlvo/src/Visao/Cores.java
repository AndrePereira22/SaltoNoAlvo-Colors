package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cores extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblBlue,lblRed,lblGreen,lblBlack,lblWhite,lblBrown,lblGrey,lblPink,lblYellow, lblOrange, lblAjuda,fundo;
	private JLabel  alvoAzul,alvoVerde,alvoPreto,alvoBranco,alvoMarrom,alvoRosa,alvoAmarelo,alvoLaranja,alvoVermelho,alvoCinza;
	private JButton Voltar,avançar;
	private Font jokerman;
	

	public Cores (int largura,int altura){

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 30);

		lblAjuda = new JLabel("AJUDA");
		lblAjuda.setForeground(Color.WHITE);
		lblAjuda.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAjuda.setBounds(286, 37, 141, 27);
		add(lblAjuda);

		lblBlue = new JLabel("BLUE");
		lblBlue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBlue.setForeground(Color.CYAN);
		lblBlue.setBounds(205, 409, 68, 14);
		add(lblBlue);

		lblRed = new JLabel("RED");
		lblRed.setForeground(Color.RED);
		lblRed.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRed.setBounds(205, 251, 46, 14);
		add(lblRed);

		lblGreen = new JLabel("GREEN");
		lblGreen.setForeground(Color.GREEN);
		lblGreen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGreen.setBounds(205, 179, 68, 14);
		add(lblGreen);

		lblBlack = new JLabel("BLACK");
		lblBlack.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBlack.setBounds(545, 399, 76, 35);
		add(lblBlack);

		lblWhite = new JLabel("WHITE");
		lblWhite.setForeground(new Color(255, 255, 255));
		lblWhite.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWhite.setBounds(545, 179, 76, 14);
		add(lblWhite);

		lblBrown = new JLabel("BROWN");
		lblBrown.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBrown.setForeground(new Color(139, 69, 19));
		lblBrown.setBounds(205, 322, 68, 14);
		add(lblBrown);

		lblGrey = new JLabel("GREY");
		lblGrey.setForeground(new Color(169, 169, 169));
		lblGrey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGrey.setBounds(545, 251, 56, 14);
		add(lblGrey);

		lblPink = new JLabel("PINK");
		lblPink.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPink.setForeground(new Color(255, 20, 147));
		lblPink.setBounds(205, 109, 46, 14);
		add(lblPink);

		lblYellow = new JLabel("YELLOW");
		lblYellow.setForeground(new Color(255, 255, 0));
		lblYellow.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYellow.setBounds(545, 97, 76, 14);
		add(lblYellow);

		lblOrange = new JLabel("ORANGE");
		lblOrange.setForeground(new Color(255, 140, 0));
		lblOrange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrange.setBounds(545, 318, 76, 23);
		add(lblOrange);

		alvoAzul = new JLabel(new ImageIcon(getClass().getResource("/alvo1.png")));
		alvoAzul.setBounds(56, 400, 110, 43);
		add(alvoAzul);

		alvoVerde = new JLabel(new ImageIcon(getClass().getResource("/alvo2.png")));
		alvoVerde.setBounds(49, 168, 117, 43);
		add(alvoVerde);

		alvoVermelho  = new JLabel(new ImageIcon(getClass().getResource("/alvo4.png")));
		alvoVermelho.setBounds(49, 236, 117, 43);
		add(alvoVermelho);

		alvoMarrom = new JLabel(new ImageIcon(getClass().getResource("/alvo10.png")));
		alvoMarrom.setBounds(49, 309, 124, 49);
		add(alvoMarrom);

		alvoRosa  = new JLabel(new ImageIcon(getClass().getResource("/alvo5.png")));
		alvoRosa.setBounds(49, 97, 110, 43);
		add(alvoRosa);

		alvoAmarelo = new JLabel(new ImageIcon(getClass().getResource("/alvo3.png")));
		alvoAmarelo.setBounds(399, 91, 110, 32);
		add(alvoAmarelo);

		alvoBranco  = new JLabel(new ImageIcon(getClass().getResource("/alvo8.png")));
		alvoBranco.setBounds(399, 167, 117, 43);
		add(alvoBranco);

		alvoCinza = new JLabel(new ImageIcon(getClass().getResource("/alvo7.png")));
		alvoCinza.setBounds(399, 239, 110, 43);
		add(alvoCinza);

		alvoLaranja  = new JLabel(new ImageIcon(getClass().getResource("/alvo6.png")));
		alvoLaranja.setBounds(399, 304, 110, 55);
		add(alvoLaranja);

		alvoPreto = new JLabel(new ImageIcon(getClass().getResource("/alvo9.png")));
		alvoPreto.setBounds(399, 391, 110, 55);
		add(alvoPreto);

		Voltar = new JButton("VOLTAR");
		Voltar.setBounds(100, 493, 189, 43);
		Voltar.setFont(jokerman ); 
		Voltar.setForeground(Color.WHITE);
		Voltar.setContentAreaFilled(false);
		add(Voltar);

		avançar = new JButton("AVAN\u00C7AR");
		avançar.setBounds(367, 493, 228, 43);
		avançar.setFont(jokerman ); 
		avançar.setForeground(Color.WHITE);
		avançar.setContentAreaFilled(false);
		add(avançar);

		fundo = new JLabel("New label");
		fundo.setIcon((new ImageIcon(getClass().getResource("/menu.gif"))));
		fundo.setBounds(0, 0, 649, 602);
		add(fundo);
		
		setVisible(false);

	}

	public JButton getVoltar() {
		return Voltar;
	}


	public void setVoltar(JButton voltar) {
		Voltar = voltar;
	}


	public JButton getAvançar() {
		return avançar;
	}


	public void setAvançar(JButton avançar) {
		this.avançar = avançar;
	}

}

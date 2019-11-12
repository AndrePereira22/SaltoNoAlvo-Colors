package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnJogar,btnAjuda,btnSair,btnCreditos,btnRecordes;
	private JLabel lblGameQuedaLivre,label;
	private Font jokerman;
	
	public Menu(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		
		jokerman =new Font("Jokerman",Font.BOLD, 26);
		
		lblGameQuedaLivre = new JLabel("Salto No Alvo");
		lblGameQuedaLivre.setForeground(Color.ORANGE);
		lblGameQuedaLivre.setFont(new Font("Jokerman",Font.BOLD, 33));
		lblGameQuedaLivre.setBounds(170, 73, 407, 69);
		add(lblGameQuedaLivre);
		
		btnJogar = new JButton("JOGAR");
		btnJogar.setFont(jokerman);
		btnJogar.setForeground(Color.white);
		btnJogar.setBounds(228, 180, 185, 39);
		btnJogar.setContentAreaFilled(false);
		btnJogar.setBorderPainted(true);
		add(btnJogar);
		
		btnAjuda = new JButton("Ajuda");
		btnAjuda.setFont(jokerman);
		btnAjuda.setForeground(Color.white);
		btnAjuda.setBounds(236, 360, 157, 46);
		btnAjuda.setContentAreaFilled(false);
		//btnAjuda.setBorderPainted(false);
		add(btnAjuda);

		btnCreditos = new JButton("Creditos");
		btnCreditos.setFont(jokerman);
		btnCreditos.setForeground(Color.white);
		btnCreditos.setBounds(217, 300, 210, 39);
		btnCreditos.setContentAreaFilled(false);
		//btnCreditos.setBorderPainted(false);
		add(btnCreditos);

		btnRecordes = new JButton("Recordes");
		btnRecordes.setFont(jokerman);
		btnRecordes.setForeground(Color.white);
		btnRecordes.setBounds(212, 240, 220, 39);
		btnRecordes.setContentAreaFilled(false);
		//btnRecordes.setBorderPainted(false);
		add(btnRecordes);
		
		btnSair = new JButton("Sair");
		btnSair.setFont(jokerman);
		btnSair.setForeground(Color.white);
		btnSair.setBounds(253, 420, 127, 39);
		btnSair.setContentAreaFilled(false);
		//btnSair.setBorderPainted(false);
		add(btnSair);

		label = new JLabel(new ImageIcon(getClass().getResource("/menu.gif")));
		label.setBounds(0, 0, 851, 600);
		add(label);	 
	}

	public void FecharVisible() {
		setVisible(false);
	}
	public void AbriVisible() {
		setVisible(true);
	}
	public JButton getBtnJogar() {	return btnJogar;}
	public JButton getBtnAjuda() {	return btnAjuda;}
	public JButton getBtnSair() {	return btnSair;}
	public JButton getBtnCreditos() {return btnCreditos;}
	public JButton getBtnRecordes() {return btnRecordes;}

}

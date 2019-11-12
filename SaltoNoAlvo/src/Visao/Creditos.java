package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class Creditos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label,lblGameQuedaLivre;
	private JButton btnVoltar;
	private Font jokerman;
	private JTextArea caixaTXT;
	
	public Creditos(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 33);

		lblGameQuedaLivre = new JLabel("Salto No Alvo");
		lblGameQuedaLivre.setForeground(Color.ORANGE);
		lblGameQuedaLivre.setFont(jokerman);
		lblGameQuedaLivre.setBounds(170, 73, 407, 69);
		add(lblGameQuedaLivre);
		
		
		caixaTXT = new JTextArea();
		caixaTXT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		caixaTXT.setForeground(Color.WHITE);
		caixaTXT.setText("\r\nUNIVERSIDADE FEDERAL RUAL DE PERNAMBUCO - UAST \r\n\r\n\r\nCurso : Bacharelado em Sistemas de Informa\u00E7ao - 2018.1\r\nDiciplina: Modelagem  e Progama\u00E7ao Orientada  a Objetos\r\nProfessor: Richarlyson Alves D'Emery\r\nAluno: Andre Pereira dos Santos\"");
		caixaTXT.setBounds(61, 176, 530, 222);
		add(caixaTXT);

		caixaTXT.setOpaque(false);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(100, 460, 166, 44);
		btnVoltar.setFont(jokerman); 
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setContentAreaFilled(false); 
		add(btnVoltar);

		label = new JLabel(new ImageIcon(getClass().getResource("/menu.gif")));
		label.setBounds(0, 0, 851, 600);
		add(label);
		FecharVisible();
	}
	public void FecharVisible() {
		setVisible(false);
	}
	public void AbriVisible() {
		setVisible(true);
	}
	public JButton getBtnVoltar() {
		return btnVoltar;
	}
}
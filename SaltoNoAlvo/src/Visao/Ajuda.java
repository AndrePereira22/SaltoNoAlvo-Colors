package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Ajuda extends JPanel {

private static final long serialVersionUID = 1L;
	
	private JButton btnVoltar;
	private JLabel lblCenario;
	private JTextPane txtpnSpace;
	private Font jokerman;

	public Ajuda(int largura,int altura) {
		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		
		jokerman =new Font("Jokerman",Font.BOLD, 26);

		txtpnSpace = new JTextPane();
		txtpnSpace.setForeground(Color.WHITE);
		txtpnSpace.setFont(jokerman);
		txtpnSpace.setText("SPACE   -   \t Saltar\r\nALT        -     Voltar ao menu\r\nENTER   -     Confirmar\r\nESC        -      Sair\r\nUP          -      Aumentar Delay\r\nDOWN  -      Diminuir  Delay");
		txtpnSpace.setBounds(107, 132, 466, 300);
		add(txtpnSpace);
		txtpnSpace.setOpaque(false);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(70, 490, 170, 33);
		btnVoltar.setFont(jokerman); 
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setContentAreaFilled(false);
		add(btnVoltar);
		
		lblCenario = new JLabel(new ImageIcon(getClass().getResource("/menu.gif")));
		lblCenario.setBounds(0, 0, 651, 601);
		add(lblCenario);
			
		FecharVisible();
	}
	public JButton getBtnVoltar() {return btnVoltar;}
	
	public void FecharVisible() {
		setVisible(false);
	}
	public void AbriVisible() {
		setVisible(true);
	}
}



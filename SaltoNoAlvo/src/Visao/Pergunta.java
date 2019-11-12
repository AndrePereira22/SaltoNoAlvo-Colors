package Visao;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Pergunta extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private JLabel lblPergunta,lblResposta,lblX,Fundo;
	private Font jokerman;
	

	public Pergunta(int largura,int altura) {

		setPreferredSize(new Dimension(851, 53));
		setLayout(null);
		jokerman =new Font("Jokerman",Font.BOLD, 25);
		
		textField = new JTextField();
		textField.setBounds(552, 8, 200, 39);
		add(textField);
		textField.setFont(jokerman);
		
		lblPergunta = new JLabel("PERGUNTA :");
		lblPergunta.setForeground(Color.BLACK);
		lblPergunta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPergunta.setBounds(22, 11, 110, 35);
		add(lblPergunta);

		lblResposta = new JLabel("RESPOSTA");
		lblResposta.setForeground(Color.BLACK);
		lblResposta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResposta.setBounds(445, 11, 101, 35);
		add(lblResposta);

		lblX = new JLabel(" ");
		lblX.setFont(jokerman);
		lblX.setBounds(142, 11, 500, 31);
		add(lblX);
		
		Fundo = new JLabel(new ImageIcon(getClass().getResource("/perg.png")));
		Fundo.setBounds(0, 0, 851, 53);
		add(Fundo);
		
		 FecharVisible();
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public void setLblX(String lblX) {
		this.lblX.setText( lblX);
	}
	
	public void FecharVisible() {
		setVisible(false);
	}
	public void AbriVisible() {
		setVisible(true);
	}
}

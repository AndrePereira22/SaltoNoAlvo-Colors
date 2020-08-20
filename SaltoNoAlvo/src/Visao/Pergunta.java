package Visao;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Pergunta extends JDialog {
	
	
	private JButton btnOk;
	private  JLabel mens,alvo;
	private JTextField resposta;
	public Pergunta( javax.swing.JFrame janela, boolean vf) {
		
		 super(janela, vf);
		setLocationRelativeTo(janela);
		setLayout(new FlowLayout());
		setSize(290,150);
		alvo = new JLabel(new ImageIcon(getClass().getResource("/alvo2.png")));
	
		resposta= new JTextField(15);
		mens = new JLabel("qual a cor?");
		btnOk =new JButton("OK");
		
		add(alvo);
		add(mens);
		add(resposta);
		add(btnOk);
		
		setVisible(false);
		
		
	}
	public JButton getBtnOk() {
		return btnOk;
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

}

package Visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Pergunta extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnOk;
	private  JLabel mens,alvo;
	private JTextField resposta;
	public Pergunta( javax.swing.JFrame janela, boolean vf) {
		
		super(janela, vf);
		
		setBounds(100, 100, 200, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
			alvo = new JLabel(new ImageIcon(getClass().getResource("/alvo2.png")));
			alvo.setBounds(33, 11, 113, 47);
			contentPanel.add(alvo);
		
			mens = new JLabel("Qual a cor ?");
			mens.setBounds(64, 69, 80, 14);
			contentPanel.add(mens);
			
			resposta = new JTextField();
			resposta .setBounds(33, 94, 141, 20);
			contentPanel.add(resposta );
			resposta .setColumns(10);
		
			btnOk = new JButton("OK");
			btnOk.setForeground(Color.BLACK);
			btnOk.setBounds(70, 125, 60, 33);
			contentPanel.add(btnOk);
			btnOk.setText("OK");
		
	
			setLocationRelativeTo(janela);
		
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

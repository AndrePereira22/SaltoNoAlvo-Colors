package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class Configuracoes extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel  lblDificuldade, lblJogador, lblConfig,lblImagem;
	private JButton btnVoltar, btnAvancar;
	private JTextField txtJogador;
	private JRadioButton radioMedio,radioFacil,radioDificil;
	private ButtonGroup bg;
	private Font jokerman;
	
	public Configuracoes(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);
		
		jokerman =new Font("Jokerman",Font.BOLD, 30);
			 
		lblDificuldade = new JLabel("DIFILCUDADE: ");
		lblDificuldade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDificuldade.setForeground(Color.white);
		lblDificuldade.setBounds(65, 292, 153, 34);
		add(lblDificuldade);

		lblJogador = new JLabel("NOME JOGADOR:");
		lblJogador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblJogador.setForeground(Color.white);
		lblJogador.setBounds(65, 235, 153, 26);
		add(lblJogador);

		lblConfig = new JLabel("CONFIGURA\u00C7OES");
		lblConfig.setForeground(Color.ORANGE);
		lblConfig.setFont(jokerman);
		lblConfig.setBounds(100, 89, 362, 54);
		add(lblConfig);

		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(100, 480, 170, 40);
		btnVoltar.setFont(jokerman ); 
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setContentAreaFilled(false);
		add(btnVoltar);

		btnAvancar = new JButton("Avancar");
		btnAvancar.setBounds(400, 480, 200, 40);
		btnAvancar.setFont(jokerman ); 
		btnAvancar.setForeground(Color.WHITE);
		btnAvancar.setContentAreaFilled(false); 
		add(btnAvancar);

		radioMedio = new JRadioButton("MEDIO");
		radioMedio.setBounds(300, 297, 65, 23);
		add(radioMedio);

		radioFacil = new JRadioButton("FACIL",true);
		radioFacil.setBounds(223, 297, 59, 23);
		add(radioFacil);

		radioDificil = new JRadioButton("DIFICIL");
		radioDificil.setBounds(388, 297, 69, 23);
		add(radioDificil);

		txtJogador = new JTextField();
		txtJogador.setBounds(228, 235, 262, 26);
		add(txtJogador);
		txtJogador.setColumns(10);

		lblImagem = new JLabel(new ImageIcon(getClass().getResource("/menu.gif")));
		lblImagem.setBounds(0, 0, 851, 600);
		add(lblImagem);

		bg = new ButtonGroup();
		bg.add(radioFacil);
		bg.add(radioMedio);
		bg.add(radioDificil);
		txtJogador.grabFocus();	 
				
		FecharVisible();
	}
	public boolean VerificarSelecao(JRadioButton b) {
		if(b.isSelected()) {
			return true;
		}else {
			return false;
		}	
	}
	public JButton getBtnVoltar() {		return btnVoltar;}
	public JButton getBtnAvancar() {	return btnAvancar;}
	public String getTxtJogador() { return txtJogador.getText();}
	public JRadioButton getRadioMedio() {	 return radioMedio;}
	public JRadioButton getRadioFacil() {	 return radioFacil;}
	public JRadioButton getRadioDificil() {	 return radioDificil;}
	
	public void FecharVisible() {
		setVisible(false);	
	}
	public void AbriVisible() {
		setVisible(true);	
	}
	
}
	


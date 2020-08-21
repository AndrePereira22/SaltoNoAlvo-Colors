package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JFrame;

public class Janela extends JFrame  {
	
	private static final long serialVersionUID = 9139033148377613572L;

	public Janela(int largura,int altura) {
		
		setPreferredSize(new Dimension(largura, altura));
		setLayout(new FlowLayout());
		
		setSize(largura, altura);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		//setUndecorated(true);
		//setLocationRelativeTo();
		
		  
		Image iconeTitulo = Toolkit.getDefaultToolkit().getImage("baseDados\\caverna.png");
		this.setIconImage(iconeTitulo);
	}
public void setTamanhoTela(Janela janela) {
		
		janela.setSize(649,601);
	}

}

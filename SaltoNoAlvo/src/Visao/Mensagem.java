package Visao;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mensagem {

	private JLabel label;
	private String caminho="";
	
	public  void exibirMensagem(String msg,String caminho){
		
		
		
		label =  new JLabel(new ImageIcon(getClass().getResource(caminho)));
		try {
			UIManager.put("OptionPane.okButtonText", msg);
		    JOptionPane.showMessageDialog(null, label, "A COR É ", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
}

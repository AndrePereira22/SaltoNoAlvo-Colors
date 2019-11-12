package Visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.Usuario;

public class Ranking extends JPanel{

	private static final long serialVersionUID = 1L;

	private JButton btnVoltar;
    private JTable table;
	private JScrollPane scrow;
	private ImageIcon fundo;
	private JLabel ranking;
	private Font jokerman;
	
	public  Ranking(int largura,int altura){
		setPreferredSize(new Dimension(649, 601));
		jokerman =new Font("Jokerman",Font.BOLD, 30);
		
		fundo = new ImageIcon(getClass().getResource("/menu.gif"));
		
		ranking = new JLabel("Ranking");
		ranking.setFont(jokerman); 
		ranking.setForeground(Color.ORANGE);
		add(ranking);
		
		table = new JTable();
		scrow = new JScrollPane(table);
		scrow.setPreferredSize(new Dimension(600,430));
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredSize(new Dimension(590,400) );
		table.setRowHeight(55);
		table.setFont(jokerman);
		
		add(scrow);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(100, 460, 146, 40);
		btnVoltar.setFont(jokerman); 
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setContentAreaFilled(false);
		add(btnVoltar);
		
		FecharVisible();
	}
	public  void  editarCampos(ArrayList<Usuario> users) {
		try {
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"1", users.get(0).getNome(), users.get(0).getPontuacao()},
					{"2", users.get(1).getNome(), users.get(1).getPontuacao()},
					{"3", users.get(2).getNome(), users.get(2).getPontuacao()},
					{"4", users.get(3).getNome(), users.get(3).getPontuacao()},
					{"5", users.get(4).getNome(), users.get(4).getPontuacao()},
					{"6", users.get(5).getNome(), users.get(5).getPontuacao()},
				},
				new String[] {
						"", "Nome", "Pontuacao"
				}
				));
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
		}catch(NullPointerException e) {
			
		}catch(IndexOutOfBoundsException x) {}
	}
	public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     g.drawImage(fundo.getImage(), 0, 0, this);
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
	public void setRankind(JLabel rankind) {
		this.ranking = rankind;
	}
	
}
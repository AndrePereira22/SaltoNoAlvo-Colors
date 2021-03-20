 package App;

import Controle.ControleGeral;
import Visao.Janela;

public class app {

	private static final int LARGURA=649,ALTURA=601;

	
	public static void main(String[] args) {
		
		Janela janela = new Janela(LARGURA, ALTURA);
		

		ControleGeral control = new ControleGeral(janela);
		control.run();
		
	}
}

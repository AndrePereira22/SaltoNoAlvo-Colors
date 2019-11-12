 package App;

import Controle.ControleGeral;
import Visao.Ajuda;
import Visao.Configuracoes;
import Visao.Cores;
import Visao.Creditos;
import Visao.Fase1;
import Visao.Fase2;
import Visao.Inventario;
import Visao.Janela;
import Visao.Menu;
import Visao.Pergunta;
import Visao.Ranking;
import Visao.Score;

public class app {

	private static final int LARGURA=649,ALTURA=601;
	private static final int A_INVENTARIO=86,L_INVENTARIO=849; 
	private static final int L_FASE=885,A_FASE=511;
	private static final int L_QUESTOES=851,A_QUESTOES=53;
	
	public static void main(String[] args) {
		
		Janela janela = new Janela(LARGURA, ALTURA);
		Cores cores = new Cores(LARGURA, ALTURA);
		Fase1 fase1 = new Fase1(L_FASE,A_FASE);
		Fase2 fase2 = new Fase2(L_FASE,A_FASE);
		Menu menu = new Menu(LARGURA, ALTURA);
		Creditos creditos= new Creditos(LARGURA, ALTURA);
		Ajuda ajuda = new Ajuda(LARGURA, ALTURA);
		Ranking ranking = new Ranking(LARGURA, ALTURA);
		Configuracoes config = new Configuracoes(LARGURA, ALTURA);
		Inventario inventario = new Inventario(L_INVENTARIO, A_INVENTARIO);
		Score score = new Score(LARGURA, ALTURA);
		Pergunta perguntas = new Pergunta(L_QUESTOES,A_QUESTOES);

		ControleGeral control = new ControleGeral(fase1,fase2,janela,menu,inventario,
				creditos,config,ajuda,ranking,score,perguntas,cores);
		control.run();
		
	}
}

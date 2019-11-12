package Controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;

import Modelo.Alvo;
import Modelo.Audio;
import Modelo.Picterodatilo;
import Modelo.SalvarDadosXml;
import Modelo.Sprite;
import Modelo.Usuario;
import Visao.Ajuda;
import Visao.Configuracoes;
import Visao.Cores;
import Visao.Creditos;
import Visao.Fase1;
import Visao.Fase2;
import Visao.Inventario;
import Visao.Janela;
import Visao.Mensagem;
import Visao.Menu;
import Visao.Pergunta;
import Visao.Ranking;
import Visao.Score;

public class ControleGeral implements ActionListener,Runnable,KeyListener   {

	Fase1 fase1;
	Fase2 fase2;
	Janela janela;
	Menu menu;
	Cores cores;
	Inventario inventario;
	Creditos creditos;
	Configuracoes config;
	Ajuda ajuda;
	Score score;
	Ranking ranking;
	Pergunta perguntas;
	Picterodatilo picterodatilo;
	Sprite caverna;
	Audio audio;
	Alvo alvo;
	Controle_Fase2 controleFase2;
	Mensagem mensagem;

	HashMap<Integer, Boolean> keyEventos;  // Eventos de Teclado
	boolean ativo;						// controlar Thread da classe controle Geral
	
	//Estados do Jogo
	final int EST_VOANDO = 0;
	final int EST_CAINDO = 1;
	final int EST_ERRO = 2;
	final int EST_FINAL = 3;
	final int EST_PARADO = 4;
	final int NEXT_FASE = 100;     //pontuacao pra passar de fase

	//Variaveis pra mudar os valores do inventario
	int tentativas, pontuacao;;
	String nome,mens;

	// Variaveis pra controlar o jogo
	int delay,estado,ajustarAlvo,OpcaoVelocidade,OpcaoVelocidadeMinima;
	int alvoMinimo,AlvoMaximo,espera=100;
	Random rnd;

	// converter as strings em int
	int opicao=0; 
	String op="op";

	// indices que serï¿½ pego no array de perguntas e o tamanho
	int i=0;
	int tamanho;

	public ControleGeral(Fase1 fase,Fase2 fase2,Janela janela,Menu menu,Inventario inventario,Creditos creditos,Configuracoes config,
			Ajuda ajuda,Ranking recordes,Score resultados,Pergunta perguntas,Cores cores) {

		this.fase1=fase;
		this.fase2=fase2;
		this.janela=janela;
		this.menu=menu;
		this.inventario=inventario;
		this.creditos=creditos;
		this.config=config;
		this.ajuda=ajuda;
		this.ranking=recordes;
		this.score=resultados;
		this.perguntas=perguntas;
		this.cores =cores;

		picterodatilo=fase.getPicterodatilo();
		caverna=fase.getCaverna();
		alvo=fase.getAlvo();

		keyEventos = new HashMap<Integer, Boolean>();
		rnd = new Random();
		audio =new Audio();
		mensagem= new Mensagem();
		
		delay = 1;
		tentativas=10;
		pontuacao=0;
		OpcaoVelocidade=10;
		OpcaoVelocidadeMinima=5;
		alvoMinimo=50;
		AlvoMaximo=600;
		estado = EST_ERRO;

		janela.add(inventario);
		janela.add(cores);
		janela.add(menu);
		janela.add(config);
		janela.add(ajuda);
		janela.add(creditos);
		janela.add(recordes);
		janela.add(resultados);
		janela.add(fase);
		janela.add(fase2);
		janela.add(perguntas);
		ControleEventos();
	
		

		janela.setVisible(true);

		audio.getSndMusic().loop();
	}
	private void ControleEventos() {

		fase1.addKeyListener(this);
		fase2.addKeyListener(this);
		menu.addKeyListener(this);
		score.addKeyListener(this);
		menu.getBtnJogar().addActionListener(this);
		menu.getBtnAjuda().addActionListener(this);
		menu.getBtnSair().addActionListener(this);
		menu.getBtnCreditos().addActionListener(this);
		menu.getBtnRecordes().addActionListener(this);
		config.getBtnAvancar().addActionListener(this);
		config.getBtnVoltar().addActionListener(this);
		creditos.getBtnVoltar().addActionListener(this);
		ranking.getBtnVoltar().addActionListener(this);
		ajuda.getBtnVoltar().addActionListener(this);
		cores.getVoltar().addActionListener(this);
		cores.getAvançar().addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==menu.getBtnJogar()) {
			AtualizarTela(cores,menu);	
		}
		if(e.getSource()==cores.getAvançar()) {
			AtualizarTela(config,cores);	
		}
		if(e.getSource()==cores.getVoltar()) {
			AtualizarTela(menu,cores);	
		}
		if(e.getSource()==menu.getBtnAjuda()) {
			AtualizarTela(ajuda,menu);
		}
		if(e.getSource()==menu.getBtnCreditos()) {
			AtualizarTela(creditos,menu);
		}
		if(e.getSource()==menu.getBtnSair()) {
			System.exit(0);
		}
		if(e.getSource()==menu.getBtnRecordes()){	
			separarRanking();
			AtualizarTela(ranking,menu);	
		}
		if(e.getSource()==ranking.getBtnVoltar()) {

			AtualizarTela(menu,ranking);
		}
		if(e.getSource()==creditos.getBtnVoltar()) {
			AtualizarTela(menu,creditos);
		}
		if(e.getSource()==ajuda.getBtnVoltar()) {
			AtualizarTela(menu,ajuda);
		}
		if(e.getSource()==config.getBtnVoltar()){
			AtualizarTela(cores,config);
		}
		
		if(e.getSource()==config.getBtnAvancar()) {

			inventario.getJogador().setText(config.getTxtJogador());
			if(config.VerificarSelecao(config.getRadioMedio())) {
				OpcaoVelocidade=15;
				OpcaoVelocidadeMinima=4;
				alvoMinimo=150;
				AlvoMaximo=500;
			}else{
				if(config.VerificarSelecao(config.getRadioDificil())) {
					OpcaoVelocidade=15;
					OpcaoVelocidadeMinima=10;
					alvoMinimo=220;
					AlvoMaximo=450;
				}
			}
			Iniciar();
		}
	}
	public void Iniciar() {

		audio.getSndMusic().stop();

		if(Thread.interrupted() && estado !=EST_FINAL){
			run();
		}
		score.FecharVisible();
		fase1.setLocation(0,0);
		AtualizarTela(fase1,config);
		janela.setSize(845,663 );
		inventario.AbriVisible();
		perguntas.AbriVisible();
		runReinicio();
		Atualizar();
	}
	public void run() {
		ativo=true;
		while(ativo) {
			Atualizar();
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				System.out.println("thread do controle geral parou");
			}	
		}
	}
	public void Atualizar() {

		runControleDoJogo();

		if(estado!= EST_PARADO) {

			if (estado == EST_FINAL) {
				runEstadoFinal();
			} else {
				runMoveAviao();
				if (estado == EST_VOANDO) {
					runEstadoVoando();
				} else if (estado == EST_CAINDO) {
					runEstadoCaindo();
				}
			}
		}
	}

	public void runEstadoFinal() {

		fase1.setLocation(1000, 0);
		score.getLblJogador().setText(inventario.getTxtJogador());
		score.getLblPontuacao().setText(inventario.getTxtPontuacao());
		AtualizarTela(score, fase1);
		inventario.FecharVisible();
		perguntas.FecharVisible();
		janela.setSize( score.getWidth(),score.getHeight());

		if (keyEventos.get(KeyEvent.VK_ENTER) != null) {
			ReiniciarFase();
		}
	}
	public void runEstadoVoando() {
		caverna.aparencia=1;
		caverna.posJogador.x = picterodatilo.getPosPtero().x + 50;
		caverna.posJogador.y = picterodatilo.getPosPtero().y + 90;

		if (keyEventos.get(KeyEvent.VK_SPACE) != null && estado==EST_VOANDO ) {

			caverna.velJogador.x = picterodatilo.velPtero * 0.6f;
			caverna.velJogador.y = 5;
			estado = EST_CAINDO;
			audio.getSndCaindo().play();
		}
	}
	public void runMoveAviao() {

		if(estado==EST_CAINDO && picterodatilo.getPosPtero().x > fase1.getLARGURA()) {

			picterodatilo.getPosPtero().x += 10;	
		}else {
			picterodatilo.getPosPtero().x += picterodatilo.velPtero;
		}

		if (picterodatilo.getPosPtero().x >fase1.getLARGURA() + 200) {
			runReinicio();
		}
	}
	public void runControleDoJogo() {

		if(pontuacao==NEXT_FASE && fase1.isVisible() && estado !=EST_PARADO) {
			try {
				mensagem.exibirMensagem("Nivel 2!","/aviao2.png");
				estado=EST_FINAL;
				fase1.FecharVisible();
				perguntas.FecharVisible();
				fase1.setLocation(1000, 0);
				Thread.sleep(1000);
			} catch (InterruptedException e) {}

			Controle_Fase2 controle = new Controle_Fase2(fase2,janela,inventario,
					score, perguntas);	
			controle.run();
		}
		if(pontuacao==30 && !config.VerificarSelecao(config.getRadioDificil())) {
			OpcaoVelocidade=15;
			OpcaoVelocidadeMinima=7;
			alvoMinimo=150;
			AlvoMaximo=500;
		}
		if(pontuacao==65) {
			OpcaoVelocidade=17;
			OpcaoVelocidadeMinima=10;
			alvoMinimo=220;
			AlvoMaximo=450;
		}
		if (keyEventos.get(KeyEvent.VK_ESCAPE) != null) {
			System.exit(0);
		}
		if (keyEventos.get(KeyEvent.VK_UP) != null) {
			if (delay > 1) {
				delay--;
			}
		}
		if (keyEventos.get(KeyEvent.VK_DOWN) != null) {
			if (delay < 100) {
				delay++;
			}}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ex) {
		}
	}
	public void runEstadoCaindo() {
		// Atualiza a posicao do jogador com base em sua velocidade.
		caverna.posJogador.x += 	caverna.velJogador.x;
		caverna.posJogador.y += 	caverna.velJogador.y;
		if (caverna.velJogador.x > 0) {

			caverna.velJogador.x -= 0.1f;
		}
		// A velocidade vertical 
		caverna.velJogador.y += 0.1f;

		int alvo2=alvo.getPosAlvo().y-5;
		if (caverna.posJogador.y > alvo2) {

			// Se a posicao vertical do jogador passou da altura do alvo na tela ï¿½ porque ele chegou ao chï¿½o
			audio.getSndCaindo().stop();

			// Ajuda a posicao vertical para fica exatamente na linha do alvo.
			caverna.posJogador.y = alvo.getPosAlvo().y;

			// Verifica se o jogador esta dentro do alvo.
			if (alvo.getPosAlvo().x-alvo.getLargAlvo() <= 	caverna.posJogador.x && caverna.posJogador.x <= alvo.getPosAlvo().x+63) {

				// Se esta sobre o alvo, muda o estado para acerto.
				estado = EST_PARADO;
				caverna.aparencia=0;

				pontuacao+=5;
				inventario.getPontuacao().setText(new String(""+pontuacao));

				// Som de acerto.
				audio.getSndAcerto().play();
				audio.getSndPterodatilo().stop();


				perguntas.setLblX(Inventario.getPergunta());

				perguntas.getTextField().grabFocus();

				perguntas.getTextField().addKeyListener(new KeyAdapter() {

					public void keyPressed(KeyEvent k) {

						if(k.getKeyCode()==KeyEvent.VK_ENTER && fase1.isVisible()) {

							if(pontuacao!=NEXT_FASE) {

								op=perguntas.getTextField().getText();
								
								if(!op.equals("")) {
								
								if(op.equalsIgnoreCase(alvo.getCor()) ) {
									pontuacao+=5;
									inventario.getPontuacao().setText(new String(""+pontuacao));
								
									mensagem.exibirMensagem("Acertou : "+alvo.getCor(),alvo.getCaminho());
									
								}else 
									mensagem.exibirMensagem("Errou ! \nResposta : "+alvo.getCor(),alvo.getCaminho());
								
								}
							}
							if(pontuacao>100)pontuacao=100;  fase1.requestFocus();
							runReinicio();
							}
						}
					
				});
				 
			} else {
				// Se esta fora do alvo, muda o estado para erro.
				estado = EST_ERRO;
				caverna.aparencia=2;
				tentativas--;
				inventario.getTentativas().setText(new String("0"+tentativas));

				// Som de erro.
				audio.getSndErro().play();

				try {
					Thread.sleep(2000);
					runReinicio();
				} catch (InterruptedException ex) {
				}
				if (tentativas == 0 || tentativas<0) {
					// Se as tentativas chegaram a zero, muda o estado para final.
					estado = EST_FINAL;

					audio.getSndPterodatilo().stop();

					// Salvar dados em um arquivo xml
					salvarXML();
				}
			}
			
		}
	}	
	public void runReinicio() {

		perguntas.setLblX("");
		perguntas.getTextField().setText("");

		estado = EST_VOANDO;
		picterodatilo.getPosPtero().x=-100;

		// velocidade minima do pterodatilo, e a maxima.
		picterodatilo.velPtero = OpcaoVelocidadeMinima + rnd.nextInt(OpcaoVelocidade);

		// Sorteia a posicao do alvo.
		mudarAlvo();

		alvo.getPosAlvo().x = alvoMinimo+rnd.nextInt(AlvoMaximo);

		// Para o som atual do pterodatilo.
		if(fase1.isVisible() && pontuacao !=NEXT_FASE) {
			audio.getSndPterodatilo().stop();
			audio.getSndPterodatilo().loop();
		}
		
	}
	public void keyPressed(KeyEvent e) {
		keyEventos.put(e.getKeyCode(), true);

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(estado!= EST_FINAL && estado== EST_VOANDO) {

				runEstadoCaindo();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ALT && estado !=EST_CAINDO) {
			VoltarMenu();
		}
	}
	public void keyReleased(KeyEvent e) {
		keyEventos.remove(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e) {}

	public void AtualizarTela(JPanel abrirTela, JPanel fecharTela){
		fecharTela.setVisible(false);
		abrirTela.setVisible(true);
		abrirTela.requestFocus();
	}
	public void VoltarMenu() {
		audio.getSndMusic().loop();
		ResetarValores();
		fase1.setLocation(1000, 0);
		fase2.setLocation(1000, 0);
		fase2.FecharVisible();
		AtualizarTela(menu,fase1);
		inventario.FecharVisible();
		estado=EST_PARADO;
		perguntas.FecharVisible();
		janela.setSize(menu.getWidth(), menu.getHeight());
		score.FecharVisible();
		audio.getSndPterodatilo().stop();
		audio.getSndCaindo().stop();		
	}
	public void ReiniciarFase() {
		ResetarValores();
		fase1.setLocation(0, 81);
		janela.setSize(845, 608);
		AtualizarTela(fase1,score);
		inventario.AbriVisible();
		janela.setSize(845,663 );
		perguntas.AbriVisible();
		runReinicio();
		Atualizar();
	}

	public void ResetarValores(){
		inventario.getPontuacao().setText("0");
		inventario.getTentativas().setText("10");
		inventario.getNivel().setText("1");
		tentativas=10;
		pontuacao=0;
	}
	public void separarRanking(){
		try{
			ArrayList<Usuario> jogadores = SalvarDadosXml.listar();

			Collections.sort(jogadores);
			ranking.editarCampos(jogadores);

		}catch(Exception ex){}

	}
	public void salvarXML() {
		if(SalvarDadosXml.listar()!=null) {
			ArrayList<Usuario> u = SalvarDadosXml.listar();
			u.add(new Usuario(inventario.getJogador().getText(),inventario.getPontuacao().getText()));
			SalvarDadosXml.gravarXML(u);
		}else {
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users.add(new Usuario(inventario.getJogador().getText(),inventario.getPontuacao().getText()));
			SalvarDadosXml.gravarXML(users);
		}
	}
	public void mudarAlvo(){
	
		switch(alvo.getCor()){

		case "BLUE":
			alvo.setAlvo(fase1.getAlvoVerde());
			alvo.setCor("GREEN");
			alvo.setCaminho("/alvo2.png");
			break;
		case "GREEN":
			alvo.setAlvo(fase1.getAlvoAmarelo());
			alvo.setCor("YELLOW");
			alvo.setCaminho("/alvo3.png");
			break;
		case "YELLOW":
			alvo.setAlvo(fase1.getAlvoVermelho());
			alvo.setCor("RED");
			alvo.setCaminho("/alvo4.png");
			break;
		case "RED":
			alvo.setAlvo(fase1.getAlvoRosa());
			alvo.setCor("PINK");
			alvo.setCaminho("/alvo5.png");
			break;
		case "PINK":
			alvo.setAlvo(fase1.getAlvoLaranja());
			alvo.setCor("ORANGE");
			alvo.setCaminho("/alvo6.png");
			break;
		case "ORANGE":
			alvo.setAlvo(fase1.getAlvoCinza());
			alvo.setCor("GREY");
			alvo.setCaminho("/alvo7.png");
			break;
		case "GREY":
			alvo.setAlvo(fase1.getAlvoBranco());
			alvo.setCor("WHITE");
			alvo.setCaminho("/alvo8.png");
			break;
		case "WHITE":
			alvo.setAlvo(fase1.getAlvoPreto());
			alvo.setCor("BLACK");
			alvo.setCaminho("/alvo9.png");
			break;
		case "BLACK":
			alvo.setAlvo(fase1.getAlvoMarrom());
			alvo.setCor("BROWN");
			alvo.setCaminho("/alvo10.png");
			break;
		case "BROWN":
			alvo.setAlvo(fase1.getAlvoAzul());
			alvo.setCor("BLUE");
			alvo.setCaminho("/alvo1.png");
			break;
		}
	}
}




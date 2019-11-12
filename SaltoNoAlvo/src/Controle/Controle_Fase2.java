package Controle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JPanel;
import Modelo.Alvo;
import Modelo.Audio;
import Modelo.Picterodatilo;
import Modelo.SalvarDadosXml;
import Modelo.Sprite;
import Modelo.Usuario;
import Visao.Fase2;
import Visao.Inventario;
import Visao.Janela;
import Visao.Mensagem;
import Visao.Pergunta;
import Visao.Score;

public class Controle_Fase2 implements Runnable,KeyListener   {

	Fase2 fase;
	Janela janela;
	Inventario inventario;
	Score resultados;
	Pergunta perguntas;
	Picterodatilo picterodatilo;
	Sprite caverna;
	Audio audio;
	Alvo alvo;
	Mensagem mensagem;

	HashMap<Integer, Boolean> keyEventos;  // Eventos de Teclado
	boolean ativo;						// controlar Thread da classe controle Fase2
	boolean acerto=false;
	//Estados do Jogo
	final int EST_VOANDO = 0;
	final int EST_CAINDO = 1;
	final int EST_ERRO = 2;
	final int EST_FINAL = 3;
	final int EST_PARADO = 4;
	final int FINAL_SCORE = 200;      //pontuacao pra passar finalizar o jogo

	//Variaveis pra mudar os valores do inventario
	int tentativas;
	int pontuacao;
	String nome;

	// Variaveis pra controlar o jogo
	int delay,estado, ajustarAlvo,OpcaoVelocidade,OpcaoVelocidadeMinima; 
	int alvoMinimo,AlvoMaximo,espera=100;
	Random rnd;

	// converter as strings em int
	int opicao;
	String op;


	public Controle_Fase2(Fase2 fase,Janela janela,Inventario inventario,
			Score resultados,Pergunta perguntas) {

		this.fase=fase;
		this.janela=janela;
		this.inventario=inventario;
		this.resultados=resultados;
		this.perguntas=perguntas;

		picterodatilo=fase.getPicterodatilo();
		caverna=fase.getCaverna();
		alvo=fase.getAlvo();
		alvo.setCor("BLUE");
		alvo.setCaminho("/alvo1.png");
		keyEventos = new HashMap<Integer, Boolean>();
		rnd = new Random();
		audio =new Audio();
		mensagem= new Mensagem();
		pontuacao=Integer.parseInt(inventario.getPontuacao().getText());
		delay = 1;
		estado = EST_ERRO;
		OpcaoVelocidade=15;
		OpcaoVelocidadeMinima=7;
		alvoMinimo=150;
		AlvoMaximo=500;
		opicao=0; 
		op="";

		ControleEventos();	
	}
	private void ControleEventos() {

		fase.addKeyListener(this);
		resultados.addKeyListener(this);	
	}
	public void iniciar() {

		audio.getSndMusic().stop();
		resetarValores();
		resultados.FecharVisible();
		fase.setLocation(0,0);
		fase.AbriVisible();
		fase.requestFocus();
		janela.setSize(845,663 );
		inventario.AbriVisible();
		perguntas.AbriVisible();
		RunReinicio();
	}
	public void run() {
		ativo=true;
		iniciar();
		while(ativo) {
			atualizar();

			if(!fase.isVisible()){
				ativo=false;
			}
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				System.out.println("thread control 2 parou");
			}	
		}
	}
	public void atualizar() {
		RunControleDoJogo();

		if(estado!= EST_PARADO) {

			if (estado == EST_FINAL) {
				RunEstadoFinal();
			} else {
				RunMoveAviao();
				if (estado == EST_VOANDO) {
					RunEstadoVoando();
				} else if (estado == EST_CAINDO) {
					RunEstadoCaindo();
				}}}}

	public void RunEstadoFinal() {

		AtualizarTela(resultados, fase);
		inventario.FecharVisible();
		perguntas.FecharVisible();
		audio.getSndPterodatilo().stop();
		fase.setLocation(1000, 0);
		resultados.getLblJogador().setText(inventario.getTxtJogador());
		resultados.getLblPontuacao().setText(inventario.getTxtPontuacao());
		janela.setSize( resultados.getWidth(),resultados.getHeight());
	}
	public void RunEstadoVoando() {
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
	public void RunMoveAviao() {

		if(estado==EST_CAINDO && picterodatilo.getPosPtero().x > fase.getLARGURA()) {

			picterodatilo.getPosPtero().x += 10;	
		}else {
			picterodatilo.getPosPtero().x += picterodatilo.velPtero;
		}

		if (picterodatilo.getPosPtero().x >fase.getLARGURA() + 200 && estado!=EST_FINAL) {
			RunReinicio();
		}
	}
	public void RunControleDoJogo() {

		if(pontuacao== FINAL_SCORE && fase.isVisible() ) {
			try {
				audio.getSndPterodatilo().stop();
				salvarXML();
				Thread.sleep(2000);
			} catch (InterruptedException e) {}

			estado=EST_FINAL;
			mensagem.exibirMensagem("Voc� terminou o jogo!","");
			fase.setLocation(1000, 0);
		}	
		if(pontuacao==130) {
			OpcaoVelocidade=15;
			OpcaoVelocidadeMinima=8;
			alvoMinimo=155;
			AlvoMaximo=495;
		}
		if(pontuacao==165) {
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
	public void RunEstadoCaindo() {

		caverna.posJogador.x += 	caverna.velJogador.x;   // Atualiza a posicao do jogador com base em sua velocidade.
		caverna.posJogador.y += 	caverna.velJogador.y;

		if (caverna.velJogador.x > 0) {

			caverna.velJogador.x -= 0.1f;
		}
		caverna.velJogador.y += 0.1f;   // A velocidade vertical 

		int alvo2=alvo.getPosAlvo().y-5;
		if (caverna.posJogador.y > alvo2) {     // Se a posicao vertical do jogador passou da altura do alvo na tela � porque ele chegou ao ch�o

			audio.getSndCaindo().stop();
			caverna.posJogador.y = alvo.getPosAlvo().y;	// Ajusta a posicao vertical para fica exatamente na linha do alvo.

			// Verifica se o jogador esta dentro do alvo.
			if (alvo.getPosAlvo().x-alvo.getLargAlvo() <= 	caverna.posJogador.x && caverna.posJogador.x <= alvo.getPosAlvo().x+63) {

				estado = EST_PARADO;     // Se esta sobre o alvo, muda o estado para acerto.
				caverna.aparencia=0;

				pontuacao+=5;
				inventario.getPontuacao().setText(new String(""+pontuacao));

				audio.getSndAcerto().play();				// Som de acerto.
				audio.getSndPterodatilo().stop();

				try {
					perguntas.setLblX(Inventario.getPergunta()); // perguntas indice i
				}catch(IndexOutOfBoundsException e) {}

				perguntas.getTextField().grabFocus();

				perguntas.getTextField().addKeyListener(new KeyAdapter() {

					public void keyPressed(KeyEvent k) {

						if(k.getKeyCode()==KeyEvent.VK_ENTER) {

							if(pontuacao!= FINAL_SCORE) {


								op=perguntas.getTextField().getText();


								if(!op.equals("")) {

									if(op.equalsIgnoreCase(alvo.getCor()) ) {
										pontuacao+=5;
										inventario.getPontuacao().setText(new String(""+pontuacao));

										mensagem.exibirMensagem("Acertou:  "+alvo.getCor(),alvo.getCaminho());

									}else 
										mensagem.exibirMensagem("Errou ! \nResposta : "+alvo.getCor(),alvo.getCaminho());

								}

								if(pontuacao>200)pontuacao=100;  fase.requestFocus();
								RunReinicio();
							}

						}}
				});

			} else {
				// Se esta fora do alvo, muda o estado para erro.
				estado = EST_ERRO;
				caverna.aparencia=2;
				tentativas--;
				inventario.getTentativas().setText(new String("0"+tentativas));

				audio.getSndErro().play();	// Som de erro.

				try {
					Thread.sleep(2000);
					RunReinicio();
				} catch (InterruptedException ex) {
				}
				if (tentativas == 0 || tentativas<0) { // Se as tentativas chegaram a zero, muda o estado para final.
					try {
						salvarXML();
						Thread.sleep(1000);
					} catch (InterruptedException e) {}

					estado = EST_FINAL;
					audio.getSndPterodatilo().stop();		
				}
			}
		}	
	}	
	public void RunReinicio() {

		perguntas.setLblX("");
		perguntas.getTextField().setText("");

		estado = EST_VOANDO;
		picterodatilo.getPosPtero().x=-100;         // posiciona o  pterodatilo
		picterodatilo.velPtero = OpcaoVelocidadeMinima + rnd.nextInt(OpcaoVelocidade);// velocidade minima do pterodatilo, e a maxima.

		mudarAlvo();

		alvo.getPosAlvo().x = alvoMinimo+rnd.nextInt(AlvoMaximo); // Sorteia a posicao do alvo.

		if( fase.isVisible() && estado!=EST_FINAL) {	// Para o som atual do pterodatilo.
			audio.getSndPterodatilo().stop();
			audio.getSndPterodatilo().loop();
		}
	}
	public void keyPressed(KeyEvent e) {
		keyEventos.put(e.getKeyCode(), true);

		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(estado!= EST_FINAL && estado== EST_VOANDO) {  RunEstadoCaindo(); }
		}
		if(e.getKeyCode()==KeyEvent.VK_ALT) {
			audio.getSndPterodatilo().stop();
			audio.getSndCaindo().stop();
			fase.FecharVisible();
		}
	}
	public void keyReleased(KeyEvent e) {  keyEventos.remove(e.getKeyCode());    }
	public void keyTyped(KeyEvent e) {}

	public void AtualizarTela(JPanel abrirTela, JPanel fecharTela){
		fecharTela.setVisible(false);
		abrirTela.setVisible(true);
		abrirTela.requestFocus();
	}
	public void resetarValores(){
		inventario.getPontuacao().setText(""+pontuacao);
		inventario.getTentativas().setText("10");
		inventario.getNivel().setText("2");
		tentativas=10;

	}	
	public void salvarXML() {
		if(SalvarDadosXml.listar()!=null) {
			ArrayList<Usuario> u = SalvarDadosXml.listar();
			u.add(new Usuario(inventario.getJogador().getText(),inventario.getPontuacao().getText()));
			SalvarDadosXml.gravarXML(u);
		}
	}
	public void mudarAlvo(){

		switch(alvo.getCor()){

		case "BLUE":
			alvo.setAlvo(fase.getAlvoVerde());
			alvo.setCor("GREEN");
			alvo.setCaminho("/alvo2.png");
			break;
		case "GREEN":
			alvo.setAlvo(fase.getAlvoAmarelo());
			alvo.setCor("YELLOW");
			alvo.setCaminho("/alvo3.png");
			break;
		case "YELLOW":
			alvo.setAlvo(fase.getAlvoVermelho());
			alvo.setCor("RED");
			alvo.setCaminho("/alvo4.png");
			break;
		case "RED":
			alvo.setAlvo(fase.getAlvoRosa());
			alvo.setCor("PINK");
			alvo.setCaminho("/alvo5.png");
			break;
		case "PINK":
			alvo.setAlvo(fase.getAlvoLaranja());
			alvo.setCor("ORANGE");
			alvo.setCaminho("/alvo6.png");
			break;
		case "ORANGE":
			alvo.setAlvo(fase.getAlvoCinza());
			alvo.setCor("GREY");
			alvo.setCaminho("/alvo7.png");
			break;
		case "GREY":
			alvo.setAlvo(fase.getAlvoBranco());
			alvo.setCor("WHITE");
			alvo.setCaminho("/alvo8.png");
			break;
		case "WHITE":
			alvo.setAlvo(fase.getAlvoPreto());
			alvo.setCor("BLACK");
			alvo.setCaminho("/alvo9.png");
			break;
		case "BLACK":
			alvo.setAlvo(fase.getAlvoMarrom());
			alvo.setCor("BROWN");
			alvo.setCaminho("/alvo10.png");
			break;
		case "BROWN":
			alvo.setAlvo(fase.getAlvoAzul());
			alvo.setCor("BLUE");
			alvo.setCaminho("/alvo1.png");
			break;
		}
	}
}




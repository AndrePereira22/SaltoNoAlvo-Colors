package Controle;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
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
import Visao.FaseBandeiras;
import Visao.FaseCores;
import Visao.Fundo;
import Visao.Janela;
import Visao.Menu;
import Visao.Pergunta;
import Visao.Ranking;
import Visao.Score;

public class ControleGeral implements ActionListener, Runnable, KeyListener {

	FaseCores faseAlvo;
	FaseBandeiras faseBandeiras;
	Janela janela;
	Menu menu;
	Cores cores;
	Creditos creditos;
	Configuracoes config;
	Ajuda ajuda;
	Score score;
	Ranking ranking;
	Picterodatilo picterodatilo;
	Sprite caverna;
	Audio audio;
	Alvo alvo;
	Pergunta pergunta;
	Random rd;
	Fundo inventario;
	HashMap<Integer, Boolean> keyEventos; // Eventos de Teclado
	boolean ativo; // controlar Thread da classe controle Geral

	// Estados do Jogo
	final int EST_VOANDO = 0;
	final int EST_CAINDO = 1;
	final int EST_ERRO = 2;
	final int EST_FINAL = 3;
	final int EST_PARADO = 4;
	final int EST_AVANCO = 5;
	final int NEXT_FASE = 20; // pontuacao pra passar de fase

	// Variaveis pra mudar os valores do inventario
	int tentativas, pontuacao;;
	String nome, mens;

	// Variaveis pra controlar o jogo
	int delay, estado, ajustarAlvo, OpcaoVelocidade, OpcaoVelocidadeMinima;
	int alvoMinimo, AlvoMaximo, espera = 100;
	Random rnd;

	// converter as strings em int
	int opicao = 0;

	// indices que serï¿½ pego no array de perguntas e o tamanho
	int i = 0;
	int tamanho;

	public ControleGeral(Janela janela) {

		this.faseAlvo = janela.getFase1();
		this.faseBandeiras = janela.getFase2();
		this.janela = janela;
		this.menu = janela.getMenu();
		this.creditos = janela.getCreditos();
		this.config = janela.getConfig();
		this.ajuda = janela.getAjuda();
		this.ranking = janela.getRanking();
		this.score = janela.getScore();
		this.cores = janela.getCores();
		this.inventario = janela.getInventario();

		pergunta = new Pergunta(janela, true);
		picterodatilo = faseAlvo.getPicterodatilo();
		caverna = faseAlvo.getCaverna();
		alvo = faseAlvo.getAlvo();

		keyEventos = new HashMap<Integer, Boolean>();
		rnd = new Random();
		rd = new Random();
		audio = new Audio();

		delay = 1;
		tentativas = 1;
		pontuacao = 0;
		OpcaoVelocidade = 10;
		OpcaoVelocidadeMinima = 5;
		alvoMinimo = 40;
		AlvoMaximo = 480;
		estado = EST_ERRO;

		ControleEventos();

		janela.setVisible(true);

		audio.getSndMusic().loop();
	}

	public void addEventoBotoes() {
		for (Component botao : pergunta.getJpResposta().getComponents()) {
			JButton b = (JButton) botao;
			b.addActionListener(this);
		}

	}

	private void ControleEventos() {

		faseAlvo.addKeyListener(this);
		faseBandeiras.addKeyListener(this);
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

		ajuda.getBtnAvancar().addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menu.getBtnJogar()) {
			AtualizarTela(config, menu);
		}

		if (e.getSource() == ajuda.getBtnAvancar()) {
			AtualizarTela(cores, ajuda);
		}

		if (e.getSource() == cores.getVoltar()) {
			AtualizarTela(ajuda, cores);
		}
		if (e.getSource() == menu.getBtnAjuda()) {
			AtualizarTela(ajuda, menu);
		}
		if (e.getSource() == menu.getBtnCreditos()) {
			AtualizarTela(creditos, menu);
		}
		if (e.getSource() == menu.getBtnSair()) {
			System.exit(0);
		}
		if (e.getSource() == menu.getBtnRecordes()) {
			separarRanking();
			AtualizarTela(ranking, menu);
		}
		if (e.getSource() == ranking.getBtnVoltar()) {

			AtualizarTela(menu, ranking);
		}
		if (e.getSource() == creditos.getBtnVoltar()) {
			AtualizarTela(menu, creditos);
		}
		if (e.getSource() == ajuda.getBtnVoltar()) {
			AtualizarTela(menu, ajuda);
		}
		if (e.getSource() == config.getBtnVoltar()) {
			AtualizarTela(menu, config);
		}

		for (Component botao : pergunta.getJpResposta().getComponents()) {
			JButton b = (JButton) botao;

			if (e.getSource() == b) {

				verificarResposta(b.getText());
			}

		}

		if (e.getSource() == config.getBtnAvancar()) {

			if (config.VerificarSelecao(config.getRadioMedio())) {
				OpcaoVelocidade = 15;
				OpcaoVelocidadeMinima = 4;
				alvoMinimo = 80;
				AlvoMaximo = 500;
			} else {
				if (config.VerificarSelecao(config.getRadioDificil())) {
					OpcaoVelocidade = 15;
					OpcaoVelocidadeMinima = 10;
					alvoMinimo = 120;
					AlvoMaximo = 500;
				}
			}
			Iniciar();
		}
	}

	public void Iniciar() {

		audio.getSndMusic().stop();

		if (Thread.interrupted() && estado != EST_FINAL) {
			run();
		}
		score.FecharVisible();
		inventario.getPanelFase().add(faseAlvo);
		AtualizarTela(inventario, config);
		AtualizarTela(faseAlvo, ajuda);
		faseAlvo.requestFocus();
		janela.setSize(640, 640);
		runReinicio();
		Atualizar();
	}

	public void run() {
		ativo = true;
		while (ativo) {
			Atualizar();
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
			}
		}
	}

	public void Atualizar() {

		runControleDoJogo();

		if (estado != EST_PARADO && estado != EST_AVANCO) {

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
		if (estado == EST_AVANCO) {
			faseAlvo.FecharVisible();
			faseAlvo.setLocation(1000, 0);
			setarItens();
			inventario.getPanelFase().removeAll();
			inventario.getPanelFase().add(faseBandeiras);
			faseBandeiras.AbriVisible();
			faseBandeiras.requestFocus();
			runReinicio();
			Atualizar();
		}
	}

	public void runEstadoFinal() {

		AtualizarTela(score, inventario);

		inventario.getPanelFase().removeAll();

		janela.setSize(score.getWidth(), score.getHeight());

		if (keyEventos.get(KeyEvent.VK_ENTER) != null) {
			ReiniciarFase();
		}
	}

	public void runEstadoVoando() {
		caverna.aparencia = 1;
		caverna.posJogador.x = picterodatilo.getPosPtero().x + 50;
		caverna.posJogador.y = picterodatilo.getPosPtero().y + 90;

		if (keyEventos.get(KeyEvent.VK_SPACE) != null && estado == EST_VOANDO) {

			caverna.velJogador.x = picterodatilo.velPtero * 0.6f;
			caverna.velJogador.y = 5;
			estado = EST_CAINDO;
			audio.getSndCaindo().play();
		}
	}

	public void runMoveAviao() {

		if (estado == EST_CAINDO && picterodatilo.getPosPtero().x > faseAlvo.getLARGURA()) {

			picterodatilo.getPosPtero().x += 10;
		} else {
			picterodatilo.getPosPtero().x += picterodatilo.velPtero;
		}

		if (picterodatilo.getPosPtero().x > faseAlvo.getLARGURA() + 200) {
			runReinicio();
		}
	}

	public void runControleDoJogo() {

		if (pontuacao == NEXT_FASE && faseAlvo.isVisible() && estado != EST_PARADO) {
			try {

				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

		}
		if (pontuacao == 30 && !config.VerificarSelecao(config.getRadioDificil())) {
			OpcaoVelocidade = 15;
			OpcaoVelocidadeMinima = 7;
			alvoMinimo = 150;
			AlvoMaximo = 500;
		}
		if (pontuacao == 65) {
			OpcaoVelocidade = 17;
			OpcaoVelocidadeMinima = 10;
			alvoMinimo = 220;
			AlvoMaximo = 450;
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
			}
		}
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ex) {
		}
	}

	public void runEstadoCaindo() {
		// Atualiza a posicao do jogador com base em sua velocidade.
		caverna.posJogador.x += caverna.velJogador.x;
		caverna.posJogador.y += caverna.velJogador.y;
		if (caverna.velJogador.x > 0) {

			caverna.velJogador.x -= 0.1f;
		}
		// A velocidade vertical
		caverna.velJogador.y += 0.1f;

		int alvo2 = alvo.getPosAlvo().y - 5;

		if (caverna.posJogador.y > alvo2) {

			// Se a posicao vertical do jogador passou da altura do alvo na tela ï¿½ porque
			// ele chegou ao chï¿½o
			audio.getSndCaindo().stop();

			// Ajuda a posicao vertical para fica exatamente na linha do alvo.
			caverna.posJogador.y = alvo.getPosAlvo().y;

			// Verifica se o jogador esta dentro do alvo.
			if (alvo.getPosAlvo().x - alvo.getLargAlvo() <= caverna.posJogador.x
					&& caverna.posJogador.x <= alvo.getPosAlvo().x + 63) {

				acertou();

			} else {
				errou();

				try {
					Thread.sleep(2000);
					runReinicio();
				} catch (InterruptedException ex) {
				}
				if (tentativas == 0 || tentativas < 0) {
					gameOver();
				}
			}

		}
	}

	private void gameOver() {
		// Se as tentativas chegaram a zero, muda o estado para final.
		estado = EST_FINAL;

		audio.getSndPterodatilo().stop();

		// Salvar dados em um arquivo xml
		salvarXML();

	}

	private void errou() {
		// Se esta fora do alvo, muda o estado para erro.
		estado = EST_ERRO;
		caverna.aparencia = 2;
		tentativas--;
		inventario.getTentativas().setText(tentativas + "");
		audio.getSndErro().play();

	}

	private void acertou() {
		// Se esta sobre o alvo, muda o estado para acerto.
		estado = EST_PARADO;
		caverna.aparencia = 0;
		pontuacao += 5;
		inventario.getPontuacao().setText(pontuacao + "");

		// Som de acerto.
		audio.getSndAcerto().play();
		audio.getSndPterodatilo().stop();

		if (faseAlvo.isVisible()) {
			pergunta.AddBotoesCores();
		}
		if (faseBandeiras.isVisible()) {
			pergunta.AddBotoesBanderas();
		}

		pergunta.vericarResposta(alvo.getCor());
		pergunta.mudarAlvo(alvo.getCaminho());
		addEventoBotoes();
		pergunta.setVisible(true);

	}

	public void runReinicio() {

		estado = EST_VOANDO;
		picterodatilo.getPosPtero().x = -120;

		// velocidade minima do pterodatilo, e a maxima.
		picterodatilo.velPtero = OpcaoVelocidadeMinima + rnd.nextInt(OpcaoVelocidade);

		// Sorteia a posicao do alvo.
		mudarAlvo();

		alvo.getPosAlvo().x = alvoMinimo + rnd.nextInt(AlvoMaximo);

		if (faseAlvo.isVisible() || faseBandeiras.isVisible()) {
			audio.getSndPterodatilo().stop();
			audio.getSndPterodatilo().loop();
		}

	}

	public void keyPressed(KeyEvent e) {
		keyEventos.put(e.getKeyCode(), true);

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (estado != EST_FINAL && estado == EST_VOANDO) {
				runEstadoCaindo();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ALT && estado != EST_CAINDO) {
			VoltarMenu();
		}

	}

	public void keyReleased(KeyEvent e) {
		keyEventos.remove(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
	}

	public void AtualizarTela(JPanel abrirTela, JPanel fecharTela) {
		fecharTela.setVisible(false);
		abrirTela.setVisible(true);
		abrirTela.requestFocus();
	}

	public void VoltarMenu() {
		audio.getSndMusic().loop();
		ResetarValores();
		faseAlvo.setLocation(1000, 0);
		faseBandeiras.setLocation(1000, 0);
		faseBandeiras.FecharVisible();
		AtualizarTela(menu, faseAlvo);
		pergunta.getMens().setText("Qual a Cor?");
		estado = EST_PARADO;
		janela.setSize(menu.getWidth(), menu.getHeight());
		score.FecharVisible();
		audio.getSndPterodatilo().stop();
		audio.getSndCaindo().stop();
	}

	public void ReiniciarFase() {
		ResetarValores();
		faseAlvo.setLocation(0, 81);
		AtualizarTela(faseAlvo, score);
		janela.setSize(886, 620);
		runReinicio();
		Atualizar();
	}

	public void ResetarValores() {

		tentativas = 10;
		pontuacao = 0;
	}

	public void separarRanking() {
		try {
			ArrayList<Usuario> jogadores = SalvarDadosXml.listar();

			Collections.sort(jogadores);
			ranking.editarCampos(jogadores);

		} catch (Exception ex) {
		}

	}

	public void salvarXML() {

	}

	public void verificarResposta(String resposta) {

		if (resposta.equalsIgnoreCase(alvo.getCor())) {
			pontuacao += 5;
			inventario.getPontuacao().setText(pontuacao + "");
		} else {

		}

		pergunta.setVisible(false);
		faseAlvo.requestFocus();

		runReinicio();
		if (pontuacao >= 20)
			estado = EST_AVANCO;

	}

	public void mudarAlvo() {
		int numero = rd.nextInt(10);
		if (faseAlvo.isVisible()) {
			alvo.setAlvo(faseAlvo.getImagens().get(numero));
			alvo.setCor(faseAlvo.getListaCores()[numero]);
			alvo.setCaminho(faseAlvo.getUrls()[numero]);
		}

		if (faseBandeiras.isVisible()) {
			alvo.setAlvo(faseBandeiras.getImagens().get(numero));
			alvo.setCor(faseBandeiras.getListaCores()[numero]);
			alvo.setCaminho(faseBandeiras.getUrl()[numero]);
		}

	}

	public void setarItens() {
		picterodatilo = faseBandeiras.getPicterodatilo();
		alvo = faseBandeiras.getAlvo();
		caverna = faseBandeiras.getCaverna();
		pergunta.getMens().setText("Qual o País?");
	}

}

package Modelo;


import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Jogo extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	//dimensões
	 private int LARGURA;
	 private int ALTURA;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	protected Graphics2D g;
	private int FPS =10;

	@SuppressWarnings("unused")
	private Double averageFPS;

	public Jogo(int LARGURA, int ALTURA) {
		super();
		this.ALTURA=ALTURA;
		this.LARGURA=LARGURA;
		
		setPreferredSize(new Dimension(LARGURA,ALTURA));
		setFocusable(true);
		requestFocus();
		Load();
	}
	
	public int getLARGURA() {
		return LARGURA;
	}

	public abstract void Load();
	
	 public void terminate() {
		 
	     running=false;
	    }
	 public void addNotify() {
			super.addNotify();

			if (thread == null) {
				thread = new Thread(this);
				thread.start();
			}
		}

	// Funçoes
	public void run() {
		running = true;

		image = new BufferedImage(LARGURA,ALTURA, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 30;

		long tragetTime = 100 / FPS;

		// Looping do Jogo
		while (running) {

			startTime = System.nanoTime();

			Update();
			Render();
			gameDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = tragetTime - URDTimeMillis;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
		
		gameDraw();
	}

	public abstract void Update();
	
	public abstract void Render();

	private void gameDraw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public boolean isRunning() {
		return running;
	}

}

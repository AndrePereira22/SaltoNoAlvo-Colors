package Modelo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Cenario {
	private final int numeroColunas =27 ;
	private final int numeroLinhas = 16;
	private final int colunasTileSet = 8;
	private final int tileSize = 32;

	private int camada[][];
	private int mapaLargura = numeroColunas * tileSize;
	private int mapaAltura = numeroLinhas * tileSize;

	private BufferedImage tileSet;
	private BufferedImage mapa = new BufferedImage(mapaLargura, mapaAltura, BufferedImage.TYPE_4BYTE_ABGR);

	private Graphics2D dbg = mapa.createGraphics();

	public Cenario(String nomeTileSet,String nomeMapaMatriz) {

		try {
			tileSet=ImageIO.read(getClass().getClassLoader().getResourceAsStream(nomeTileSet));
		} catch (IOException e) {
			System.out.println("Não conseguiu ler Tileset");
			e.printStackTrace();
		}
		camada=carregarMatriz(nomeMapaMatriz);
	}
	public void montarMapa() {
		mapa.createGraphics();

		int tile;
		int tileRow;
		int tileCol;
		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 0;
				tileRow = (tile / colunasTileSet) | 0;
				tileCol = (tile % colunasTileSet) | 0;
				dbg.drawImage(tileSet, (j * tileSize), (i * tileSize), (j * tileSize) + tileSize,
						(i * tileSize) + tileSize, (tileCol * tileSize), (tileRow * tileSize),
						(tileCol * tileSize) + tileSize, (tileRow * tileSize) + tileSize, null);
			}
		}
	}

	public ArrayList<Rectangle> montarColi() {
		ArrayList<Rectangle> tmp=new ArrayList<>();
		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				if(camada[i][j] != 0) {
					tmp.add(new Rectangle( (j * tileSize), (i * tileSize), 32, 32));
				}
			}
		}
		System.out.println(tmp.size());
		return tmp;
	}

	public int[][] carregarMatriz(String nomeMapa) {
		int[][] matz = new int[numeroLinhas][numeroColunas];

		InputStream input = getClass().getClassLoader().getResourceAsStream(nomeMapa);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));

		ArrayList<String> linhas = new ArrayList<>();
		String linha = "";

		try {
			while ((linha = br.readLine()) != null)
				linhas.add(linha);

			int coluna = 0;
			for (int i = 0; i < linhas.size(); i++) {
				StringTokenizer token = new StringTokenizer(linhas.get(i), ",");

				while (token.hasMoreElements()) {
					matz[i][coluna] = Integer.parseInt(token.nextToken());
					coluna++;
				}
				coluna = 0;
			}

		} catch (IOException e) {
			System.out.println("Não carregou Matriz");
			e.printStackTrace();
		}
		return matz;
	}
	public int getMapaLargura() {
		return mapaLargura;
	}
	public int getMapaAltura() {
		return mapaAltura;
	}
	public BufferedImage getMapa() {
		return mapa;
	}

}
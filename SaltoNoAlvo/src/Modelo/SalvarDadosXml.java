package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SalvarDadosXml {

		@XStreamAlias("Usuarios")
		static	
		ArrayList<Usuario>usuarios;
		static ArrayList<Usuario> lista;
		
		//Listar Usuarios
		public static ArrayList<Usuario> listar(){
			
			usuarios = new ArrayList<Usuario>();
			
			try {
				XStream xStream = new XStream(new DomDriver());
				xStream.alias("usuarios", ArrayList.class);
				xStream.processAnnotations(Usuario.class);
				
				BufferedReader input = new BufferedReader(new FileReader("baseDados/basedados.xml"));
				
				usuarios = (ArrayList) xStream.fromXML(input);
				input.close();
				
				lista = new ArrayList<Usuario>();
				
				for(Usuario users:usuarios){
					lista.add(new Usuario(users.getNome(),users.getPontuacao()));
				}		
				
			} catch (IOException ex) {}
			
			return lista;
		}
		//Gravar arquivos no xml
		public static String gravarXML(ArrayList<Usuario> users){
			String retorno = "";
			File file = new File("baseDados/basedados.xml");
			
			XStream xStream = new XStream();
			xStream.aliasField("Usuarios", ArrayList.class,"usuarios");
			xStream.alias("usuario",Usuario.class);
			PrintWriter gravar;
			try {
				
				gravar = new PrintWriter(file);	
				gravar.println("<?xml version='1.0' encoding='UTF-8'?>");
				gravar.println(xStream.toXML(users));
				gravar.flush();
				gravar.close();
				retorno = "Jogador adicionado ao xml";
				System.out.println("Gravado com sucesso!");
				
			} catch (Exception e) {}
			
			return retorno;
		}
		
	}
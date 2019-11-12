package Modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("usuario")
public class Usuario  implements Comparable<Usuario>  {

	private String nome,pontuacao;

	public Usuario() {}

	public Usuario(String nome,String pontuacao) {
		this.nome=nome;
		this.pontuacao=pontuacao;
	}
	public String getPontuacao() {return pontuacao;}
	public String getNome() {return nome;}

	public int compareTo(Usuario o) {

		int valor = pontuacao.compareTo(o.pontuacao) * -1; 
		if (valor == 0){
			return nome.compareTo(o.nome);
		}
		return valor;
	}
}

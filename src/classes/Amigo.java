package classes;

import java.util.ArrayList;

import android.R.color;

public class Amigo {
	private long idAmigo;
	private String nome;
	
	
	public Amigo(String nome) {
		this.idAmigo = 0;
		this.nome = nome;		
	}

	public Amigo() {
		
		this.idAmigo = 0;
		this.nome = "";
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public long getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(long idAmigo) {
		this.idAmigo = idAmigo;
	}
		

	
}

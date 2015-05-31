package classes;

import java.util.ArrayList;

import android.R.color;

public class Amigo {
	private long idAmigo;
	private String nome;
	
	
	public Amigo(int idAmigo, String nome) {
		super();
		this.idAmigo = idAmigo;
		this.nome = nome;
		
	}

	public Amigo() {
		super();
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

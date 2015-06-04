package classes;

import java.util.Calendar;

public class Coisa {
	
	private long idCoisa;
	private String nome;
	private int emprestada; // 1 para "emprestada" 0 para "de posse"
	private Amigo amigoEmprestado;
	private String date;
	
	
	public Coisa() {
		this.idCoisa = 0;
		this.nome = "";		
		this.amigoEmprestado = new Amigo();
		this.emprestada = 0;
		this.date = "";
	
	}

	public Coisa(String nome, Amigo amigoEmprestado , String date) {
		
		this.idCoisa = 0;
		this.nome = nome;
		this.amigoEmprestado = amigoEmprestado;
		this.emprestada = 0;
		this.date = date;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Amigo getAmigoEmprestado() {
		return amigoEmprestado;
	}

	public void setAmigoEmprestado(Amigo amigoEmprestado) {
		this.amigoEmprestado = amigoEmprestado;
	}

	public int isEmprestada() {
		return emprestada;
	}

	public void setEmprestada(int emprestada) {
		this.emprestada = emprestada;
	}

	public long getIdCoisa() {
		return idCoisa;
	}

	public void setIdCoisa(long idCoisa) {
		this.idCoisa = idCoisa;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString()
	{
		return getNome();
		
	}
	

	
	
	
}

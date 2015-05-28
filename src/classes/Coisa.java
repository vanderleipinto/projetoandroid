package classes;

import java.util.Calendar;

public class Coisa {
	
	private String nome;
	private String amigoEmprestado;
	private boolean emprestada;	
	
	
	public Coisa() {
		this.nome = "";		
		this.amigoEmprestado = "";
		this.emprestada = false;
	
	}

	public Coisa(String nome, String amigoEmprestado, boolean emprestada) {
		
		this.nome = nome;
		this.amigoEmprestado = amigoEmprestado;
		this.emprestada = emprestada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAmigoEmprestado() {
		return amigoEmprestado;
	}

	public void setAmigoEmprestado(String amigoEmprestado) {
		this.amigoEmprestado = amigoEmprestado;
	}

	public boolean isEmprestada() {
		return emprestada;
	}

	public void setEmprestada(boolean emprestada) {
		this.emprestada = emprestada;
	}
	
	
	


}

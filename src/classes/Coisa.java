package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import android.app.DatePickerDialog;
import android.net.ParseException;



public class Coisa {
	
	private long idCoisa;
	private String nome;
	private int emprestada; // 1 para "emprestada" 0 para "de posse"
	private Amigo amigoEmprestado;
	private Date date;
	
	///________________________________
	int dia,mes,ano;
	
	
	public Coisa() {
		this.idCoisa = 0;
		this.nome = "";		
		this.amigoEmprestado = new Amigo();
		this.emprestada = 0;
		Date data = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.date = this.stringToDate(df.format(data));
	
	}

	public Coisa(String nome, Amigo amigoEmprestado , String xxx) {
		
		this.idCoisa = 0;
		this.nome = nome;
		this.amigoEmprestado = amigoEmprestado;
		this.emprestada = 0;		
		Date data = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.date = this.stringToDate("10/05/2013");
//		this.date = this.stringToDate(df.format(data));
		
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
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}

	public void setDate(String date) {
		
		this.date = stringToDate(date);
	}
	
	@Override
	public String toString()
	{
		return getNome();
		
	}
	
	boolean comparadata (Date dataDaCoisa)	{
		
		Date dataAtual = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dataAtual = this.stringToDate(df.format(dataAtual));
				
		criaData(df.format(dataDaCoisa));
		
		Date aux = stringToDate(Calendario(7,  dia,  mes,  ano));
		
		if (aux.after(dataAtual)) 
		{
			return true;
		}
		
		return false;
	}
	
	private String Calendario(int diasDiferenca, int dia, int mes, int ano) {
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat("dd/MM/yyyy");
		String d = null;

		GregorianCalendar cal = new GregorianCalendar(ano, mes - 1, dia);

		cal.add(Calendar.DAY_OF_MONTH, diasDiferenca);

		Date futuro = cal.getTime();
		// formata a data
		try {
			d = formatter.format(futuro);// user entry date

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return d + "";
	}
	
	public Date stringToDate(String data1) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		// f.setLenient(false);
		Date d1 = null;
		try {
			d1 = f.parse(data1);
		} catch (ParseException e) {
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d1;
	}
	
	public void criaData(String data) {		

		StringTokenizer st3 = new StringTokenizer(data, "/");

		//
		while (st3.hasMoreElements()) {
			dia = Integer.parseInt(st3.nextElement().toString());
			mes = Integer.parseInt(st3.nextElement().toString());
			ano = Integer.parseInt(st3.nextElement().toString());
		}

	}
	
	public void setDataAtual()
	{
		Date dataAtual = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dataAtual = this.stringToDate(df.format(dataAtual));
		this.date = dataAtual;
	}
	

	
	
	
}

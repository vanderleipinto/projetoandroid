package classes;

import java.util.ArrayList;

import com.example.empresta_iii.R;
import classes.Coisa;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CoisasAdapter extends ArrayAdapter<Coisa> {

	//Construtor da classe
		public CoisasAdapter(Context vrContexto, ArrayList<Coisa> vrListaCoisa){
			super(vrContexto, 0, vrListaCoisa);
		}
		
		//Metodo utilizado para o preenchimento das celulas
		public View getView(int indice, View viewReciclada, ViewGroup viewPai){
			Coisa vrCoisa = this.getItem(indice);
			
			//Verifica se e necessario validar a celula
			if (viewReciclada == null){
				viewReciclada = LayoutInflater.from(getContext()).inflate(R.layout.coisa_lv , viewPai, false);
			}
			
			//Obtem a referencia para os componentes da view
			TextView vrTextViewNome = (TextView)viewReciclada.findViewById(R.id.txtCoisa);
			TextView vrTextViewEmprestada = (TextView)viewReciclada.findViewById(R.id.txtStatusCoisa);
			TextView vrTextIdCoisa = (TextView) viewReciclada.findViewById(R.id.txtIdCoisa);
			TextView txtDataEmprestimo = (TextView)viewReciclada.findViewById(R.id.txtDataEmprestimo);
			TextView txtlblEmprestadoEm = (TextView)viewReciclada.findViewById(R.id.txtlblEmprestadoEm);
			
			//Seta as propriedades da view
			vrTextViewNome.setText(vrCoisa.getNome());
			vrTextIdCoisa.setText(String.valueOf(vrCoisa.getIdCoisa()));
			if (vrCoisa.isEmprestada()==1) {
				vrTextViewEmprestada.setText(vrCoisa.getAmigoEmprestado().getNome());
				vrTextViewEmprestada.setTextColor(Color.rgb(168, 36, 0));
				txtDataEmprestimo.setText(vrCoisa.getDate());
				txtlblEmprestadoEm.setText("Emprestado em:");
				
				
			}else
			{
				vrTextViewEmprestada.setText("Comigo");
				vrTextViewEmprestada.setTextColor(Color.rgb(16, 123, 17));
				txtDataEmprestimo.setVisibility(View.INVISIBLE);
				txtlblEmprestadoEm.setVisibility(View.INVISIBLE);				
			}
			
			if (indice % 2 == 0)
				viewReciclada.setBackgroundColor(Color.LTGRAY);
			else
				viewReciclada.setBackgroundColor(Color.WHITE);			
			
			
			return viewReciclada;
		}
}


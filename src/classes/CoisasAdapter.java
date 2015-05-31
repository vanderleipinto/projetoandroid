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
			
			//Seta as propriedades da view
			vrTextViewNome.setText(vrCoisa.getNome());
			if (vrCoisa.isEmprestada()==1) {
				vrTextViewEmprestada.setText("Emprestada");
				vrTextViewEmprestada.setTextColor(Color.RED);
			}else
			{
				vrTextViewEmprestada.setText("De posse");
				vrTextViewEmprestada.setTextColor(Color.GREEN);
			}
			
			if (indice % 2 == 0)
				viewReciclada.setBackgroundColor(Color.LTGRAY);
			else
				viewReciclada.setBackgroundColor(Color.WHITE);
			
			
			
			return viewReciclada;
		}
}

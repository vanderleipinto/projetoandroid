package classes;

import java.util.ArrayList;

import com.example.empresta_iii.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;



public class AmigosAdapter extends ArrayAdapter<Amigo> {
	
	DataBaseHelper dataBase = new DataBaseHelper(getContext());
	//Construtor da classe
		public AmigosAdapter(Context vrContexto, ArrayList<Amigo> vrListaCoisas){
			super(vrContexto, 0, vrListaCoisas);
		}
		
		//Metodo utilizado para o preenchimento das celulas
		public View getView(int indice, View viewReciclada, ViewGroup viewPai){
			Amigo vrAmigo = this.getItem(indice);
			
			//Verifica se e necessario validar a celula
			if (viewReciclada == null){
				viewReciclada = LayoutInflater.from(getContext()).inflate(R.layout.amigo_lv , viewPai, false);
			}

			//Obtem a referencia para os componentes da view
			TextView vrTextViewNomeAmigo = (TextView)viewReciclada.findViewById(R.id.txtNomeAmigo);
			TextView vrTextViewNumeroCoisas = (TextView)viewReciclada.findViewById(R.id.txtNumeroCoisas);
			TextView vrTextViewIdAmigo = (TextView)viewReciclada.findViewById(R.id.txtIdAmigo);
			
			
			vrTextViewIdAmigo.setText(String.valueOf(vrAmigo.getIdAmigo()));
			vrTextViewNumeroCoisas.setText(String.valueOf(dataBase.getNumCoisasEmprestadasAmigo(vrAmigo))); 
			
			//Seta as propriedades da view
			vrTextViewNomeAmigo.setText(vrAmigo.getNome());
			if (indice % 2 == 0)
				viewReciclada.setBackgroundColor(Color.LTGRAY);
			else
				viewReciclada.setBackgroundColor(Color.WHITE);
			
			
			return viewReciclada;

			}
		}

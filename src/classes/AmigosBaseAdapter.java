package classes;

import java.util.ArrayList;

import com.example.empresta_iii.R;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;



public class AmigosBaseAdapter extends BaseAdapter{
	
	private Context contexto;
	private ArrayList<Amigo> arrayAmigos;
	
	
	    
	
	//Construtor da classe
		public AmigosBaseAdapter(Context vrContexto, ArrayList<Amigo> arrayAmigos){
			this.contexto = vrContexto;
			this.arrayAmigos = arrayAmigos;
		}
		
		@Override
		public int getCount() {
			return arrayAmigos.size();
		}

		@Override
		public Object getItem(int position) {
			return arrayAmigos.get(position);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		
		//Metodo utilizado para o preenchimento das celulas
		public View getView(int indice, View viewReciclada, ViewGroup viewPai){
			
			Amigo vrAmigo = arrayAmigos.get(indice);
			View layout;
			DataBaseHelper dataBase = new DataBaseHelper(viewPai.getContext());
			
			//Verifica se e necessario validar a celula
			if (viewReciclada == null){
				LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
				layout = inflater.inflate(R.layout.amigo_lv, null);
			}else
			{
				layout = viewReciclada;
			}
			
			
			
//			coisasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			//coisasAdapter.getDropDownView(indice, viewReciclada, viewPai);
//			SpinnerCoisasAdapter coisasAdapter= new SpinnerCoisasAdapter(layout.getContext(), dataBase.getAllCoisa());
//			Spinner sp = (Spinner)viewReciclada.findViewById(R.id.SpinnerCoisasEmprestadas);//			
//			sp.setAdapter(coisasAdapter);
			
			
			//Obtem a referencia para os componentes da view
			TextView vrTextViewNomeAmigo = (TextView)viewReciclada.findViewById(R.id.txtNomeAmigo);	
			TextView vrTextViewNumeroCoisas = (TextView)viewReciclada.findViewById(R.id.txtNumeroCoisas);	
			
			vrTextViewNumeroCoisas.setText(String.valueOf(vrAmigo.getIdAmigo()));
			
			//Seta as propriedades da view
			vrTextViewNomeAmigo.setText(vrAmigo.getNome());
			if (indice % 2 == 0)
				layout.setBackgroundColor(Color.LTGRAY);
			
			
			
			return layout;

			}

		
		}

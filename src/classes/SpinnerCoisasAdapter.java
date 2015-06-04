package classes;

import java.util.ArrayList;

import com.example.empresta_iii.R;
import classes.Coisa;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;


public class SpinnerCoisasAdapter extends ArrayAdapter<Coisa> {
		Amigo amigo = new Amigo();
	//Construtor da classe
		public SpinnerCoisasAdapter(Context vrContexto, ArrayList<Coisa> vrListaCoisa, Amigo vrAmigo){
			super(vrContexto, 0, vrListaCoisa);
			this.amigo = vrAmigo;
		}
		
		//Metodo utilizado para o preenchimento das celulas
		public View getView(int indice, View viewReciclada, ViewGroup viewPai){
			Coisa vrCoisa = this.getItem(indice);
			
			//Verifica se e necessario validar a celula
			if (viewReciclada == null){
				viewReciclada = LayoutInflater.from(getContext()).inflate(R.layout.spinner_coisa , viewPai, false);
			}
			
			//Obtem a referencia para os componentes da view
			TextView vrTextViewNome = (TextView)viewReciclada.findViewById(R.id.txtSpinnerCoisa);
			TextView vrTextViewEmprestada = (TextView)viewReciclada.findViewById(R.id.tglbtnStatusCoisa);
			
			//Seta as propriedades da view
			vrTextViewNome.setText(vrCoisa.getNome());
			Switch vrSwitchEmprestada = (Switch)viewReciclada.findViewById(R.id.tglbtnStatusCoisa);
			
			if (vrCoisa.getIdCoisa()==3) {///teste
				vrCoisa.setEmprestada(1);
				Amigo teste = new Amigo();
				teste.setIdAmigo((long)17);
				vrCoisa.setAmigoEmprestado(teste);
			}
			
			if (vrCoisa.getIdCoisa()==5) {///teste
				vrCoisa.setEmprestada(1);
				vrCoisa.setAmigoEmprestado(amigo);
			}
			
			
			
//			Seta as propriedades da view
			vrTextViewNome.setText(vrCoisa.getNome());
			if(vrCoisa.isEmprestada()==1) // está emprestada
				{
					vrSwitchEmprestada.setChecked(true); /// marca como emprestada
					if(vrCoisa.getAmigoEmprestado().getIdAmigo() != amigo.getIdAmigo()) /// está emprestada para outro amigo 
						vrSwitchEmprestada.setEnabled(false); /// não tem como mexer aqui dentro desta view
				}
			
			
			vrSwitchEmprestada.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
						{
							
							
							Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
						}
					if(!isChecked) 
						{
							Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
						}
					
				}
			});
				
			
			
			if (vrCoisa.getIdCoisa()==3) {///teste
				vrCoisa.setEmprestada(1);
				Amigo teste = new Amigo();
				teste.setIdAmigo(17);
				vrCoisa.setAmigoEmprestado(teste);
			}
			
			if (vrCoisa.getIdCoisa()==5) {///teste
				vrCoisa.setEmprestada(1);
				vrCoisa.setAmigoEmprestado(amigo);
			}
			
			
			
			
			
			if(vrCoisa.isEmprestada()==1 && vrCoisa.getAmigoEmprestado()== amigo) // Se tiver comigo, pode editar
			{
				vrSwitchEmprestada.setEnabled(false);
				vrSwitchEmprestada.setBackgroundColor(Color.LTGRAY);  
			}
			
			
			if (indice % 2 == 0)
				viewReciclada.setBackgroundColor(Color.LTGRAY);
			else
				viewReciclada.setBackgroundColor(Color.WHITE);
			
			
			
			return viewReciclada;
		}
}

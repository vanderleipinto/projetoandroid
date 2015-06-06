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
	DataBaseHelper dataBase = null;
	//Construtor da classe
	public SpinnerCoisasAdapter(Context vrContexto, ArrayList<Coisa> vrListaCoisa, Amigo vrAmigo){
		super(vrContexto, 0, vrListaCoisa);
		this.amigo = vrAmigo;
		dataBase = new DataBaseHelper(vrContexto);
	}

	//Metodo utilizado para o preenchimento das celulas
	public View getView(int indice, View viewReciclada, ViewGroup viewPai){
		final Coisa vrCoisa = this.getItem(indice);

		//Verifica se e necessario validar a celula
		if (viewReciclada == null){
			viewReciclada = LayoutInflater.from(getContext()).inflate(R.layout.spinner_coisa , viewPai, false);
		}

		//Obtem a referencia para os componentes da view
		TextView vrTextViewNome = (TextView)viewReciclada.findViewById(R.id.txtSpinnerCoisa);		

		//Seta as propriedades da view
		vrTextViewNome.setText(vrCoisa.getNome());
		Switch vrSwitchEmprestada = (Switch)viewReciclada.findViewById(R.id.tglbtnStatusCoisa);

		if(vrCoisa.isEmprestada()==1) // está emprestada
		{
			vrSwitchEmprestada.setChecked(true); /// marca como emprestada
			if(vrCoisa.getAmigoEmprestado().getIdAmigo() != amigo.getIdAmigo()) /// está emprestada para outro amigo 
				vrSwitchEmprestada.setEnabled(false); /// não tem como mexer aqui dentro desta view
				vrSwitchEmprestada.setTextOn(vrCoisa.getAmigoEmprestado().getNome());
		}


		vrSwitchEmprestada.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					vrCoisa.setEmprestada(1);
					vrCoisa.setAmigoEmprestado(amigo);
					dataBase.updateCoisa(vrCoisa);


					Toast.makeText(getContext(), "checked" + vrCoisa.getIdCoisa(), Toast.LENGTH_SHORT).show();
				}
				if(!isChecked) 
				{
					
					vrCoisa.setEmprestada(0);
					vrCoisa.setAmigoEmprestado(amigo);
					dataBase.updateCoisa(vrCoisa);
					Toast.makeText(getContext(), "unchecked", Toast.LENGTH_SHORT).show();
				}

			}
		});
		if (indice % 2 == 0)
			viewReciclada.setBackgroundColor(Color.LTGRAY);
		else
			viewReciclada.setBackgroundColor(Color.WHITE);



		return viewReciclada;
	}
}

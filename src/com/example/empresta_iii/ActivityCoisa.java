package com.example.empresta_iii;


import java.util.ArrayList;

import org.w3c.dom.Text;

import classes.Amigo;
import classes.AmigosAdapter;
import classes.Coisa;
import classes.CoisasAdapter;
import classes.DataBaseHelper;
import classes.SpinnerCoisasAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class ActivityCoisa extends FragmentActivity implements OnItemClickListener {
	
	DataBaseHelper dataBase = null;
	Coisa coisa= null;
	Amigo amigo= null;
	ArrayList<Amigo> arrayAmigos = null;
	AmigosAdapter amigoAdapter;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coisa);
        setTitle("Lista de amigos");
        
        this.dataBase = new DataBaseHelper(this);
        arrayAmigos = dataBase.getAllAmigo();
        
        
        Intent intent = getIntent();
		
		if(intent != null){
			Bundle params = intent.getExtras();
			
			if(params != null){
				
				//Busca o coisa enviado como parametro			
				
				coisa = dataBase.getOneCoisa(Integer.parseInt(params.getString("idCoisa")));
				
			}
		}       
        
        
		TextView txtActvityCoisaNomeCoisa = (TextView) findViewById(R.id.txtActvityCoisaNomeCoisa);
		txtActvityCoisaNomeCoisa.setText("Emprestar "+ coisa.getNome()+" para:");
        ListView lvCoisas = (ListView)findViewById(R.id.lvActivityCoisaListaAmigos);
//		CoisasAdapter coisasAdapter = new CoisasAdapter(this, dataBase.getAllCoisa());/// TODO Passar amigo como parametro
        amigoAdapter = new AmigosAdapter(this, dataBase.getAllAmigo());/// TODO Passar amigo como parametro
		lvCoisas.setAdapter(amigoAdapter); 
		
		lvCoisas.setOnItemClickListener(this);
        
		
    }

	@Override
	public void onItemClick(AdapterView<?> viewAdapter, View vrView, int position, long arg3) {

		
		TextView txtIdAmigo = (TextView) vrView.findViewById(R.id.txtIdAmigo);
		
		coisa.setAmigoEmprestado(dataBase.getOneAmigo(Integer.valueOf(txtIdAmigo.getText().toString())));
		coisa.setEmprestada(1);
		dataBase.updateCoisa(coisa);
		
		Toast.makeText(vrView.getContext(), "Emprestou para "+ coisa.getAmigoEmprestado().getNome() , Toast.LENGTH_LONG).show();
		finish();
	}

}

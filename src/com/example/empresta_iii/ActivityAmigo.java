package com.example.empresta_iii;


import java.util.ArrayList;

import org.w3c.dom.Text;

import classes.Amigo;
import classes.Coisa;
import classes.CoisasAdapter;
import classes.DataBaseHelper;
import classes.SpinnerCoisasAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;


public class ActivityAmigo extends Activity{
	
	DataBaseHelper dataBase = null;
	Amigo amigo = null;
	ArrayList<Coisa> arrayCoisas = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigo);
        setTitle("Lista de amigos");
        
        this.dataBase = new DataBaseHelper(this);
        arrayCoisas = dataBase.getAllCoisa();
        
        
        Intent intent = getIntent();
		
		if(intent != null){
			Bundle params = intent.getExtras();
			
			if(params != null){
				
				//Busca o amigo enviado como parametro
				
				
				amigo = dataBase.getOneAmigo(Integer.parseInt(params.getString("idAmigo")));
				
			}
		}
        
        
        
		TextView txtActvityAmigoNomeAmigo = (TextView) findViewById(R.id.txtActvityAmigoNomeAmigo);
		txtActvityAmigoNomeAmigo.setText(amigo.getNome());
        ListView lvCoisas = (ListView)findViewById(R.id.lvActivityAmigoListaCoisas);
//		CoisasAdapter coisasAdapter = new CoisasAdapter(this, dataBase.getAllCoisa());/// TODO Passar amigo como parametro
        SpinnerCoisasAdapter coisasAdapter = new SpinnerCoisasAdapter(this, dataBase.getAllCoisa(), amigo);/// TODO Passar amigo como parametro
		lvCoisas.setAdapter(coisasAdapter); 
		
	
        
		
    }

}

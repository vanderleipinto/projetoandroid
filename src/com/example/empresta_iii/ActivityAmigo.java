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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ActivityAmigo extends FragmentActivity implements OnItemClickListener{
	
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
		
		lvCoisas.setOnItemClickListener(this);        
		
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View vrView, int arg2, long arg3) {
		Switch swStatus = (Switch) vrView.findViewById(R.id.tglbtnStatusCoisa);
		TextView txtIdAmigo = (TextView) vrView.findViewById(R.id.spinnerIdAmigo);
		
		Toast.makeText(vrView.getContext(), "id amigo = " + txtIdAmigo.getText().toString() ,Toast.LENGTH_LONG).show();
		
	}

	
}

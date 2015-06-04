package classes;

import java.util.ArrayList;

import com.example.empresta_iii.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment") public class DialogFragmentAmigo extends DialogFragment{

	DataBaseHelper dataBase;
	ArrayList<Amigo> arrayAmigos;
	
	public DialogFragmentAmigo(DataBaseHelper dataBase, ArrayList<Amigo> arrayAmigos){
	this.dataBase= dataBase; 	
	this.arrayAmigos = arrayAmigos;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("Script", "onCreate()");
		
		int style;
		int theme;
		
		int estilo = 4;
		int tema = 2;	
		
		switch(estilo){
			case 1: style = DialogFragment.STYLE_NO_TITLE; break;
			case 2: style = DialogFragment.STYLE_NO_INPUT; break;
			case 3: style = DialogFragment.STYLE_NO_FRAME; break;
			default: style = DialogFragment.STYLE_NORMAL; break;
		}
		
		switch(tema){
			case 1: theme = android.R.style.Theme_Holo_Light; break;
			case 2: theme = android.R.style.Theme_Holo_Dialog; break;
			default: theme = android.R.style.Theme_Holo_Light_DarkActionBar; break;
		}
		
		setStyle(style, theme);
		setCancelable(false);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		Log.i("Script", "onCreateView()");
		
		final View view = inflater.inflate(R.layout.dialog_fragment_layout_amigo, container);
		Button btnSalvarAmigo = (Button) view.findViewById(R.id.btnSalvarAmigo);
		Button btnCancelarSalvarAmigo = (Button) view.findViewById(R.id.btnCancelaSalvarAmigo);
		TextView txtCoisaNomeAdicionar = (TextView) view.findViewById(R.id.txtAdiconarAmigo);
		
		btnSalvarAmigo.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				
				TextView txtAdiconarAmigo = (TextView) view.findViewById(R.id.txtAdiconarAmigo);

				if (txtAdiconarAmigo.getText().equals("")) {
					return;
				}
				
				Amigo amigo = new Amigo();	
				amigo.setNome(txtAdiconarAmigo.getText().toString());
				amigo.setIdAmigo(dataBase.addAmigo(amigo));
				arrayAmigos.add(amigo);
				txtAdiconarAmigo.setText("");
				dismiss();
				
			}
		});
		
		
		btnCancelarSalvarAmigo.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		return(view);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Log.i("Script", "onActivityCreated()");
		
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
	}
	
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		Log.i("Script", "onAttach()");
	}
	
	
	@Override
	public void onCancel(DialogInterface dialog){
		super.onCancel(dialog);
		Log.i("Script", "onCancel()");
	}
	
	/*@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		super.onCreateDialog(savedInstanceState);
		Log.i("Script", "onCreateDialog()");
		
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
			.setTitle("DialogFragment")
			.setIcon(R.drawable.ic_launcher)
			.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getActivity(), "Ok pressed", Toast.LENGTH_LONG).show();
				}
			}).setNegativeButton("Sair", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dismiss();
				}
			});
		
		return(alert.show());
	}*/
	
	
	@Override
	public void onDestroyView(){
		super.onDestroyView();
		Log.i("Script", "onDestroyView()");
	}
	
	
	@Override
	public void onDetach(){
		super.onDetach();
		Log.i("Script", "onDetach()");
	}
	
	
	@Override
	public void onDismiss(DialogInterface dialog){
		super.onDismiss(dialog);
		Log.i("Script", "onDismiss()");
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		Log.i("Script", "onSaveInstanceState()");
	}
	
	
	@Override
	public void onStart(){
		super.onStart();
		Log.i("Script", "onStart()");
	}
	
	
	@Override
	public void onStop(){
		super.onStop();
		Log.i("Script", "onStop()");
	}
	

}

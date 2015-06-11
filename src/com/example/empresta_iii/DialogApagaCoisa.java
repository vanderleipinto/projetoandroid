package com.example.empresta_iii;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class DialogApagaCoisa extends DialogFragment {
	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	}
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//		super.onCreateView(inflater, container, savedInstanceState);
//		Log.i("Script", "onCreateView()");
//		
//		View view = inflater.inflate(R.layout.dialog_fragment_layout_coisa, container);
//		Button btExit = (Button) view.findViewById(R.id.btnDialFragCoisaNao);
//		btExit.setOnClickListener(new Button.OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				dismiss();
//				
//				//((MainActivity) getActivity()).turnOffDialogFragment();
//			}
//		});
//		
//		return(view);
//	}
	
	@Override
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
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Log.i("Script", "onActivityCreated()");
		
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.MAGENTA));
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

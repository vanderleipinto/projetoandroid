package com.example.empresta_iii;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;


import classes.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	static SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	static ViewPager mViewPager;
	static ArrayList<Amigo> arrayAmigos = new ArrayList<Amigo>();
	static ArrayList<Coisa> arrayCoisas = new ArrayList<Coisa>();
	static DataBaseHelper dataBase;
	String TESTE_DATA = "31/05/2015";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		dataBase = new DataBaseHelper(getBaseContext());

		arrayAmigos = dataBase.getAllAmigo();
		arrayCoisas = dataBase.getAllCoisa();
		mSectionsPagerAdapter.notifyDataSetChanged();
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Log.d(this.toString(), "onPageSelected "+ mViewPager.getContext().toString());
//				arrayAmigos = dataBase.getAllAmigo();
//				arrayCoisas = dataBase.getAllCoisa();
				mSectionsPagerAdapter.notifyDataSetChanged();
				
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				//Log.d(this.toString(), "onPageScrolled");
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.d(this.toString(), "onPageScrollStateChanged");
				
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			//            Fragment fragment = new DummySectionFragment();
			//            Bundle args = new Bundle();
			//            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			//            fragment.setArguments(args);

			switch (position) {
			case 0:
				return new DummySectionFragment();

			case 1:
				return new AmigoFragment();

			case 2:
				return new CoisaFragment();    			

			default:
				break;
			}
			return null;

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "Principal";
			case 1:
				return "Lista de Amigos";
			case 2:
				return "Lista de Coisas";
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText("texto");
			return rootView;
		}
	}

	public static class CoisaFragment extends Fragment implements OnItemClickListener,OnItemLongClickListener {


		View rootView = null;
		private boolean longclick = false;
		//		public static FragmentTransaction ft;

		public  CoisaFragment() {
		}
		
		@Override
		public void onDetach()
		{
			super.onDetach();
			Log.d(this.toString(), "OnDetach");
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.layout_fragment_coisa, container, false);

			ListView lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
			CoisasAdapter coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
			lvCoisas.setAdapter(coisasAdapter);    

			lvCoisas.setOnItemLongClickListener(this);
			lvCoisas.setOnItemClickListener(this);



			return rootView;
		}



		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
			// TODO enviar para Coisa activity que visualisa os amigos que ela está emprestada
			if (longclick)
			{
				longclick  = false;
				return;
			}
			TextView vrTextViewIdCoisa = (TextView)view.findViewById(R.id.txtIdCoisa);

			Log.d(this.toString(), "id coisa é "+vrTextViewIdCoisa.getText());
			Toast.makeText(this.rootView.getContext(),"id coisa é "+vrTextViewIdCoisa.getText(), Toast.LENGTH_SHORT).show();
			Bundle params = new Bundle();
			params.putString("idAmigo", vrTextViewIdCoisa.getText().toString());
			Log.d("mandando coisa", vrTextViewIdCoisa.getText().toString());			
			Intent intent = new Intent(getActivity(), ActivityAmigo.class);
			intent.putExtras(params);			
			startActivity(intent);


		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, final View view,
				int arg2, long arg3) {
			// TODO após fazer os esquemas aqu, atualizar lista de coisas 

			TextView vrTextViewIdCoisa = (TextView)view.findViewById(R.id.txtIdCoisa);
			final Coisa coisa = dataBase.getOneCoisa((long) Integer.valueOf(vrTextViewIdCoisa.getText().toString()));
			longclick = true;

			if (coisa.isEmprestada()==1) 
			{
				new AlertDialog.Builder(view.getContext())
				.setTitle("Apagar Coisa?")
				.setMessage("Esta coisa está emprestada. O amigo devolveu?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 


						dataBase.delCoisa(coisa.getIdCoisa());		        		

					}
				})
				.setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// do nothing 
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();


				
			}else{


				new AlertDialog.Builder(view.getContext())
				.setTitle("Apagar Coisa?")
				.setMessage("Are you sure you want to delete this entry?")
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 

						dataBase.delCoisa(coisa.getIdCoisa());		        		

					}
				})
				.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// do nothing 
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();
			}		
			mSectionsPagerAdapter.notifyDataSetChanged();
			
			return false;
		}
	}
	public static class AmigoFragment extends Fragment implements OnItemLongClickListener, OnItemClickListener {


		public AmigoFragment() {
		}

		View rootView = null;
		private boolean longclick = false;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.rootView = inflater.inflate(R.layout.layout_fragment_amigo, container, false);



			ListView lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
			AmigosAdapter amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
			lvAmigos.setAdapter(amigosAdapter);	
			lvAmigos.setOnItemLongClickListener(this);

			ImageButton btnAdd = (ImageButton)rootView.findViewById(R.id.btnAdicionarAmigo);
			TextView teste = (TextView) rootView.findViewById(R.id.txtAmigoNomeAdicionar);

			lvAmigos.setOnItemClickListener(this);    		
			amigosAdapter.notifyDataSetChanged();


			return rootView;
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View view,
				int position, long arg3) {
			// TODO Gerar uma Dialog com alteração de nome do amigo.

			TextView vrTextViewIdCoisa = (TextView)view.findViewById(R.id.txtIdAmigo);
			final Amigo amigo = dataBase.getOneAmigo((long) Integer.valueOf(vrTextViewIdCoisa.getText().toString()));
			final ArrayList<Coisa> arrayCoisa = dataBase.getListCoisaAmigo(amigo);
			longclick = true;
			
			if (dataBase.getNumCoisasEmprestadasAmigo(amigo) == 0) 
			{
				new AlertDialog.Builder(view.getContext())
				.setTitle("Apagar Amigo?")
				.setMessage("Deseja excluir "+ amigo.getNome() + " da sua lista de contatos?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 


						dataBase.delAmigo(amigo.getIdAmigo());        		

					}
				})
				.setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// do nothing 
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();
			}else{

				
				
				new AlertDialog.Builder(view.getContext())
				.setTitle("Apagar amigo?")
				.setMessage("Esse amigo está com coisas suas  emprestadas?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 

//						dataBase.delCoisa(amigo.getIdAmigo());		        		

					}
				})
				.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// do nothing 
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();
			}
			return false;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			//mostra coisas que o amigo possui com ele 

			if (longclick)
			{
				longclick = false;
				return;
			}
			TextView vrTextViewIdAmigo = (TextView)view.findViewById(R.id.txtIdAmigo);			
			Bundle params = new Bundle();
			params.putString("idAmigo", vrTextViewIdAmigo.getText().toString());
			Log.d("mandando", vrTextViewIdAmigo.getText().toString());			
			Intent intent = new Intent(getActivity(), ActivityAmigo.class);
			intent.putExtras(params);			
			startActivity(intent);


		}



	}
	public void adicionarAmigo (View vrView) {
		//TODO Adicionar amigo no banco de dados

		TextView txtAmigoNomeAdicionar = (TextView) findViewById(R.id.txtAmigoNomeAdicionar);

		if (txtAmigoNomeAdicionar.getText().equals("")) {
			return;
		}

		Amigo amigo = new Amigo(txtAmigoNomeAdicionar.getText().toString());
		arrayAmigos.add(amigo);
		amigo.setIdAmigo(dataBase.addAmigo(amigo));

		Toast.makeText(getBaseContext(), String.valueOf(amigo.getIdAmigo()), Toast.LENGTH_SHORT).show();

		txtAmigoNomeAdicionar.setText("");

	}

	public void adicionarCoisa (View vrView) {
		//TODO adicionar coisa no banco de dados

		TextView txtCoisaNomeAdicionar = (TextView) findViewById(R.id.txtCoisaNomeAdicionar);

		if (txtCoisaNomeAdicionar.getText().equals("")) {
			return;
		}

		Coisa coisa = new Coisa(txtCoisaNomeAdicionar.getText().toString(),new Amigo() ,TESTE_DATA);
		coisa.setIdCoisa(dataBase.addCoisa(coisa));

		Toast.makeText(getBaseContext(), String.valueOf(coisa.getIdCoisa()), Toast.LENGTH_SHORT).show();

		arrayCoisas.add(coisa);
		txtCoisaNomeAdicionar.setText(""); 
	}
	//	
	//	public void apagarCoisa (View vrView)
	//	{
	//		
	//	}
}

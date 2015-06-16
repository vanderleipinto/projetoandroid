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
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
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
	
	
	public boolean onMenuItemSelected(int menuId, MenuItem vrMenu)
	    {
		Intent intent = new Intent();
	    	startActivity(intent.setClass(this, ActivityAbout.class));
	    	return false;
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
		public void notifyDataSetChanged() {
			
			Log.i(this.toString(), "notifyDataSetChanged() tutu");
			
			super.notifyDataSetChanged();
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
		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			// TODO Auto-generated method stub
			super.setPrimaryItem(container, position, object);
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
		public View rootView;
		public TextView numtotalamigos;
		public TextView numtotalcoisas;
		public TextView numcoisasemprestadas;
		public TextView numamigoscomcoisas; 
		
		
		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			dataBase = new DataBaseHelper(container.getContext());
			numtotalamigos= (TextView) rootView.findViewById(R.id.numtotalamigos);
			numtotalcoisas= (TextView) rootView.findViewById(R.id.numtotalcoisas);
			numcoisasemprestadas= (TextView) rootView.findViewById(R.id.numcoisasemprestadas);
			numamigoscomcoisas= (TextView) rootView.findViewById(R.id.numamigoscomcoisa);
			
			numtotalamigos.setText(String.valueOf(dataBase.getAllAmigo().size()));
			numtotalcoisas.setText(String.valueOf(dataBase.getAllCoisa().size()));
			numcoisasemprestadas.setText(String.valueOf(dataBase.getNumCoisasEmprestadasTotal()));
			numamigoscomcoisas.setText(String.valueOf(dataBase.getListAmigosComCoisas()));
			
			return rootView;
		}
		
		@Override
		public void onResume() {
			numtotalamigos.setText(String.valueOf(dataBase.getAllAmigo().size()));
			numtotalcoisas.setText(String.valueOf(dataBase.getAllCoisa().size()));
			numcoisasemprestadas.setText(String.valueOf(dataBase.getNumCoisasEmprestadasTotal()));
			numamigoscomcoisas.setText(String.valueOf(dataBase.getListAmigosComCoisas()));
			
			super.onResume();
		}
		
	}

	public static class CoisaFragment extends Fragment implements OnItemClickListener,OnItemLongClickListener {


		View rootView = null;
		private boolean longclick = false;
		ListView lvCoisas;
		CoisasAdapter coisasAdapter;

		public  CoisaFragment() {
		}
		
		
		
		//____________________________________________________________________________________
		
		@Override
		public void onPause() {
			Log.d(this.toString(), "onPause");    
			super.onPause();
		}
		
		@Override
		public void onDetach()
		{
			
			Log.d(this.toString(), "OnDetach");
			super.onDetach();
		
			
		}
		
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			Log.d(this.toString(), "OnContextItemSelected");
			return super.onContextItemSelected(item);
		}
		
		@Override
		public void onHiddenChanged(boolean hidden) {
			Log.d(this.toString(), "onHiddenChanged");
			super.onHiddenChanged(hidden);
		}
		
		@Override
		public void onViewStateRestored(Bundle savedInstanceState) {
			Log.d(this.toString(), "onViewStateRestored");
			super.onViewStateRestored(savedInstanceState);
			}
		
		//____________________________________________________________________________________		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.layout_fragment_coisa, container, false);

			lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
			coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
			lvCoisas.setAdapter(coisasAdapter);   
			coisasAdapter.notifyDataSetChanged();

			lvCoisas.setOnItemLongClickListener(this);
			lvCoisas.setOnItemClickListener(this);



			return rootView;
		}



		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
			// TODO se tiver emprestada, perguntar se devolve. se estiver comigo, abre tela para quem emprestar
			if (longclick)
			{
				longclick  = false;
				return;
			}
			TextView vrTextViewIdCoisa = (TextView)view.findViewById(R.id.txtIdCoisa);
			final Coisa coisa = dataBase.getOneCoisa(Long.parseLong(vrTextViewIdCoisa.getText().toString()));
			
			if (coisa.isEmprestada()==1)
			{
				new AlertDialog.Builder(view.getContext())
				.setTitle("Coisa devolvida?")
				.setMessage("O "+ coisa.getAmigoEmprestado().getNome()+" devolveu?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 

						coisa.setEmprestada(0);
						coisa.setAmigoEmprestado(new Amigo());
						dataBase.updateCoisa(coisa);
						arrayCoisas.clear();
						arrayCoisas = dataBase.getAllCoisa();
						lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
						coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
						lvCoisas.setAdapter(coisasAdapter);   
						coisasAdapter.notifyDataSetChanged();		

					}
				})
				.setNegativeButton("Não", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 
						// do nothing 
					}
				})
				.setIcon(android.R.drawable.ic_dialog_info)
				.show();
				
				
			}else
			{
				Bundle params = new Bundle();
				params.putString("idCoisa", vrTextViewIdCoisa.getText().toString());
				Log.d("mandando coisa", vrTextViewIdCoisa.getText().toString());			
				Intent intent = new Intent(getActivity(), ActivityCoisa.class);
				intent.putExtras(params);			
				startActivity(intent);
				
			}
			
			


		}
		
		@Override
		public void onResume() {
			
			arrayCoisas.clear();
			arrayCoisas = dataBase.getAllCoisa();
			lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
			coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
			lvCoisas.setAdapter(coisasAdapter);   
			coisasAdapter.notifyDataSetChanged();			
			
			super.onResume();
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
						arrayCoisas.clear();
						arrayCoisas = dataBase.getAllCoisa();
						lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
						coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
						lvCoisas.setAdapter(coisasAdapter);   
						coisasAdapter.notifyDataSetChanged();		

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
				.setMessage("Tem certeza que deseja apagar?")
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) { 

						dataBase.delCoisa(coisa.getIdCoisa());	
						arrayCoisas.clear();
						arrayCoisas = dataBase.getAllCoisa();
						lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
						coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
						lvCoisas.setAdapter(coisasAdapter);   
						coisasAdapter.notifyDataSetChanged();		

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
		ListView lvAmigos;
		AmigosAdapter amigosAdapter;
		public AmigoFragment() {
			
		}

		View rootView = null;
		private boolean longclick = false;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			this.rootView = inflater.inflate(R.layout.layout_fragment_amigo, container, false);



			lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
			amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
			lvAmigos.setAdapter(amigosAdapter);	
			lvAmigos.setOnItemLongClickListener(this);

			ImageButton btnAdd = (ImageButton)rootView.findViewById(R.id.btnAdicionarAmigo);
			TextView teste = (TextView) rootView.findViewById(R.id.txtAmigoNomeAdicionar);

			lvAmigos.setOnItemClickListener(this);    		
			amigosAdapter.notifyDataSetChanged();


			return rootView;
		}
		
		
		@Override
		public void onResume() {
			
			Log.d("onResume", "onResume");
			
			lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
			amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
			lvAmigos.setAdapter(amigosAdapter);	
			amigosAdapter.notifyDataSetChanged();
			
			super.onResume();
			
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
			
			lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
			amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
			lvAmigos.setAdapter(amigosAdapter);
			amigosAdapter.notifyDataSetChanged();


		}



	}
	public void adicionarAmigo (View vrView) {
		//TODO Adicionar amigo no banco de dados

		TextView txtAmigoNomeAdicionar = (TextView) findViewById(R.id.txtAmigoNomeAdicionar);

		if (!txtAmigoNomeAdicionar.getText().toString().equals("")) {
			Amigo amigo = new Amigo(txtAmigoNomeAdicionar.getText().toString());
			arrayAmigos.add(amigo);
			amigo.setIdAmigo(dataBase.addAmigo(amigo));

			txtAmigoNomeAdicionar.setText("");
		}else{
		Toast.makeText(getBaseContext(), "Campo vazio!", Toast.LENGTH_SHORT).show();
		}
	}

	public void adicionarCoisa (View vrView) {
		//TODO adicionar coisa no banco de dados

		TextView txtCoisaNomeAdicionar = (TextView) findViewById(R.id.txtCoisaNomeAdicionar);

		if (!txtCoisaNomeAdicionar.getText().toString().equals("")) {
			Coisa coisa = new Coisa(txtCoisaNomeAdicionar.getText().toString(),new Amigo() ,TESTE_DATA);
			coisa.setIdCoisa(dataBase.addCoisa(coisa));
			arrayCoisas.add(coisa);
			txtCoisaNomeAdicionar.setText("");
		}else{
		
		Toast.makeText(getBaseContext(), "Campo vazio!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void principalClickCoisas(View vrView)
	{
		mSectionsPagerAdapter.getItem(2);
	}
	
	public void principalClickAmigos(View vrView)
	{
		mSectionsPagerAdapter.getItem(1);
	}

}

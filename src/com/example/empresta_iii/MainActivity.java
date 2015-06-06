package com.example.empresta_iii;

import java.util.ArrayList;
import java.util.Locale;
import classes.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	static ArrayList<Amigo> arrayAmigos = new ArrayList<Amigo>();
	static ArrayList<Coisa> arrayCoisas = new ArrayList<Coisa>();
	DataBaseHelper dataBase;
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
		
		Log.d("", "");
		

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

	public static class CoisaFragment extends Fragment implements OnItemClickListener {


		View rootView = null;

		public  CoisaFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.layout_fragment_coisa, container, false);

			ListView lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
			CoisasAdapter coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
			lvCoisas.setAdapter(coisasAdapter);    

			lvCoisas.setOnItemClickListener(this);


			return rootView;
		}

		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO enviar para Coisa activity que visualisa os amigos que ela está emprestada
			
			
			Toast.makeText(this.rootView.getContext(), "adiciona coisa", Toast.LENGTH_SHORT).show();

		}
	}



	public static class AmigoFragment extends Fragment implements OnItemLongClickListener, OnItemClickListener {


		public AmigoFragment() {
		}

		View rootView = null;

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
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			//TODO mostrar coisas que o amigo possui com ele 
			TextView vrTextViewNomeAmigo = (TextView)view.findViewById(R.id.txtNomeAmigo);
			TextView vrTextViewIdAmigo = (TextView)view.findViewById(R.id.txtIdAmigo);
			
			
			Bundle params = new Bundle();
			params.putString("idAmigo", vrTextViewIdAmigo.getText().toString());
			Log.d("mandando", vrTextViewIdAmigo.getText().toString());
			
			Intent intent = new Intent(getActivity(), ActivityAmigo.class);
			intent.putExtras(params);
			
			startActivity(intent);


		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View view,
				int position, long arg3) {
			// TODO Gerar uma Dialog com alteração de nome do amigo.

			TextView vrTextViewNomeAmigo = (TextView)view.findViewById(R.id.txtNomeAmigo);
			TextView vrTextViewIdAmigo = (TextView)view.findViewById(R.id.txtIdAmigo);
			Toast.makeText(this.rootView.getContext(), vrTextViewNomeAmigo.getText()+" id=  "+ vrTextViewIdAmigo.getText(), Toast.LENGTH_SHORT).show();

			return false;
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
}

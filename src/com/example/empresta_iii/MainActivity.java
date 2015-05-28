package com.example.empresta_iii;

import java.util.ArrayList;
import java.util.Locale;

import classes.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
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
    static AmigosAdapter amigosAdapter = null;
    
    
    

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
    
    public static class CoisaFragment extends Fragment {
    	
        public  CoisaFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.layout_fragment_coisa, container, false);
            
            
            arrayCoisas.add(new Coisa("Sofá", arrayAmigos.get(2).getNome(), true));
            arrayCoisas.add(new Coisa("Bola", arrayAmigos.get(2).getNome(), true));
            arrayCoisas.add(new Coisa("Video Game", "", false));
            arrayCoisas.add(new Coisa("Tamburete", arrayAmigos.get(2).getNome(), true));
            arrayCoisas.add(new Coisa("Imbira", "", false));
            
            ListView lvCoisas = (ListView)rootView.findViewById(R.id.listViewCoisas);
    		CoisasAdapter coisasAdapter= new CoisasAdapter(rootView.getContext(), arrayCoisas);
    		lvCoisas.setAdapter(coisasAdapter);
            
            
            
            return rootView;
        }
    }
    
    public static class AmigoFragment extends Fragment implements OnItemClickListener {
    	
    	
        public AmigoFragment() {
        }
        
        View rootView = null;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            this.rootView = inflater.inflate(R.layout.layout_fragment_amigo, container, false);
            
            
            arrayAmigos.add(new Amigo("jose"));
            arrayAmigos.add(new Amigo("joao"));
            arrayAmigos.add(new Amigo("maria"));
            arrayAmigos.add(new Amigo("carla"));
            arrayAmigos.add(new Amigo("Mustafá"));
            arrayAmigos.add(new Amigo("Izidoro"));
            
            ListView lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
    		AmigosAdapter amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
    		lvAmigos.setAdapter(amigosAdapter);	
     		lvAmigos.setOnItemClickListener(this);
    		amigosAdapter.notifyDataSetChanged();
            
            
            
            return rootView;
        }
        
        public void adicionarAmigo(View vrView)
    	{
    		 
    		 return;
    	}

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			
			TextView vrTextViewNomeAmigo = (TextView)view.findViewById(R.id.txtNomeAmigo);
			Toast.makeText(this.rootView.getContext(), vrTextViewNomeAmigo.getText(), Toast.LENGTH_SHORT).show();
			
		}
        
    }


}

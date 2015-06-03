//package classes;
//
//import java.util.ArrayList;
//
//import com.example.empresta_iii.*;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemLongClickListener;
//
//import com.example.empresta_iii.R;
//
//public class AmigoFragment extends Fragment implements OnItemLongClickListener, OnItemClickListener {
//	
//	View rootView = null;
//	private ArrayList<Amigo> arrayAmigos;	
//	private BaseAdapter amigosAdapter;
//	
//    public AmigoFragment() 
//    {
//    	 
//    }
//    
//    
//	
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//        this.rootView = inflater.inflate(R.layout.layout_fragment_amigo, container, false);
//        
//        
//        
//        
//        ListView lvAmigos = (ListView)rootView.findViewById(R.id.listViewAmigos);
//		AmigosAdapter amigosAdapter= new AmigosAdapter(rootView.getContext(), arrayAmigos);
//		lvAmigos.setAdapter(amigosAdapter);	
// 		lvAmigos.setOnItemLongClickListener(this);
//		
// 		ImageButton btnAdd = (ImageButton)rootView.findViewById(R.id.btnAdicionarAmigo);
// 		TextView teste = (TextView) rootView.findViewById(R.id.txtAmigoNomeAdicionar);
// 		
// 		lvAmigos.setOnItemClickListener(this);
// 		
// 		//btnAdd.setOnItemClickListener((OnClickListener) rootView.getContext());
// 		
//        
//        return rootView;
//    }
//    
//    
//    
//   
//    public void adicionarAmigo() {
//    	// TODO Auto-generated method stub
//    	TextView txtAmigoNomeAdicionar = (TextView) rootView.findViewById(R.id.txtAmigoNomeAdicionar);
//
//    	if (txtAmigoNomeAdicionar.getText().equals("")) {
//    		return;
//    	}
//    	arrayAmigos.add(new Amigo(1, txtAmigoNomeAdicionar.getText().toString()));
//    	txtAmigoNomeAdicionar.setText("");
//    	amigosAdapter.notifyDataSetChanged();
//   	 
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> arg0, View view, int position,
//			long arg3) {
//		
//		TextView vrTextViewNomeAmigo = (TextView)view.findViewById(R.id.txtNomeAmigo);
//		Toast.makeText(this.rootView.getContext(), vrTextViewNomeAmigo.getText(), Toast.LENGTH_SHORT).show();
//		
//	}
//
//	@Override
//	public boolean onItemLongClick(AdapterView<?> arg0, View view,
//			int position, long arg3) {
//		// TODO Gerar uma Dialog com alteração de nome do amigo.
//		
//		TextView vrTextViewNomeAmigo = (TextView)view.findViewById(R.id.txtNomeAmigo);
//		Toast.makeText(this.rootView.getContext(), vrTextViewNomeAmigo.getText()+" "+ String.valueOf(position), Toast.LENGTH_SHORT).show();
//		
//		return false;
//	}
//}
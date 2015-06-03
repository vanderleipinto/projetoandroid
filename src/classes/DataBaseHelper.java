package classes;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	// Logcat tag
    private static final String LOG = "DatabaseHelper";
    
    // Database Version
    private static final int DATABASE_VERSION = 2;
 
    // Database Name
    private static final String DATABASE_NAME = "Empresta";
    
    // Table Names
    private static final String TABLE_AMIGO = "amigo";
    private static final String TABLE_COISA = "coisa";

    
    // AMIGO Table - column names
    private static final String KEY_ID_AMIGO = "_id";
    private static final String KEY_NOME_AMIGO = "nome";    
    
    
    // COISA Table - column names
    private static final String KEY_ID_COISA = "_id";
    private static final String KEY_NOME_COISA = "nome";
    private static final String KEY_EMPRESTADA = "emprestada";
    private static final String KEY_COISA_IDAMIGO = "idamigo";
    private static final String KEY_DATE = "date";
    
    // Table Create Statements
    private static final String CREATE_TABLE_AMIGO = "CREATE TABLE " + TABLE_AMIGO + " (" + KEY_ID_AMIGO + " INTEGER PRIMARY KEY, " + KEY_NOME_AMIGO+ " TEXT);";
    private static final String CREATE_TABLE_COISA = "CREATE TABLE " + TABLE_COISA + " (" + KEY_ID_COISA + " INTEGER PRIMARY KEY, " + KEY_NOME_COISA + " TEXT, " + KEY_COISA_IDAMIGO + " NUMERIC, " + KEY_EMPRESTADA + " NUMERIC, " + KEY_DATE + " TEXT);";
    
    public DataBaseHelper (Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
       
    	// creating required tables
        db.execSQL(CREATE_TABLE_AMIGO);
        db.execSQL(CREATE_TABLE_COISA);
        Log.d("CRIA COISA", CREATE_TABLE_COISA); 
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AMIGO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COISA);
 
        // create new tables
        onCreate(db);
    }
    
    public long addAmigo(Amigo amigo) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NOME_AMIGO, amigo.getNome());
        
     
        // insert row
        long idAmigo = db.insert(TABLE_AMIGO, null, values);
        
        Log.d("inseriu amigo na posição", String.valueOf(idAmigo)+ amigo.getNome());
     
        return idAmigo;
    }
    
    public void delAmigo(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_AMIGO, KEY_ID_AMIGO + " = ?", new String[] { String.valueOf(id) });
        
        // delObjetivoSemana(id); verificar se o amigo tem coisas com ele
    }
    
    private Amigo CreateAmigo(Cursor c){
    	Amigo amigo = new Amigo();
        amigo.setIdAmigo(c.getInt(c.getColumnIndex(KEY_ID_AMIGO)));        
        amigo.setNome(c.getString(c.getColumnIndex(KEY_NOME_AMIGO)));
        
        return amigo;
    }
    
   
    
    public Amigo getOneAmigo(int id){
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        String selectQuery = "SELECT  * FROM " + TABLE_AMIGO + " WHERE " + KEY_ID_AMIGO + " = " + id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null && c.moveToFirst())
        	return CreateAmigo(c);
        
        return null;
    }
    
    public ArrayList<Amigo> getAllAmigo(){
    	ArrayList<Amigo> listAmigos = new ArrayList<Amigo>();  //TODO Emiliano Porque List<Amigo> instead of ArrayList<Amigo>????
        String selectQuery = "SELECT  * FROM " + TABLE_AMIGO;
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c.moveToFirst()) {
           do {
        	   listAmigos.add(CreateAmigo(c));
           } while (c.moveToNext());
        }

        return listAmigos;
    }
    
    
    
    public long addCoisa(Coisa coisa) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        
        values.put(KEY_NOME_COISA, coisa.getNome());
        values.put(KEY_COISA_IDAMIGO, coisa.getAmigoEmprestado().getIdAmigo());
        values.put(KEY_EMPRESTADA, coisa.isEmprestada());        
        values.put(KEY_DATE, coisa.getDate());
        
     
        // insert row
        long idCoisa = db.insert(TABLE_COISA, null, values);
        
        Log.d("inseriu amigo na posição", String.valueOf(idCoisa));
     
        return idCoisa;
    }
    
    
    public void delCoisa(long id_objetivo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COISA, KEY_ID_COISA + " = ?", new String[] { String.valueOf(id_objetivo) });
    }
    
    private Coisa CreateCoisa(Cursor c){
    	Coisa coisa = new Coisa();
        coisa.setIdCoisa(c.getInt(c.getColumnIndex(KEY_ID_COISA)));
        coisa.setNome(c.getString(c.getColumnIndex(KEY_NOME_COISA)));
        coisa.setEmprestada(c.getInt(c.getColumnIndex(KEY_EMPRESTADA)));
        coisa.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
        coisa.setAmigoEmprestado(getOneAmigo(c.getInt(c.getColumnIndex(KEY_COISA_IDAMIGO))));
        
        
        return coisa;
    }
    
//    public ObjetivoSemana getOneObjetivoSemanaNaoDepositado(int idObjetivo){
//    	SQLiteDatabase db = this.getReadableDatabase();
//    	 
//        String selectQuery = "SELECT  * FROM " + TABLE_OBJETIVO_SEMANA + " WHERE " + KEY_ID_OBJETIVO + " = " + idObjetivo + " AND " + KEY_DEPOSITADO + " = 0";
//     
//        Log.e(LOG, selectQuery);
//     
//        Cursor c = db.rawQuery(selectQuery, null);
//     
//        if (c != null && c.moveToFirst())
//        	return CreateObjetivoSemana(c);
//        
//        return null;
//    }
//    
   
    
    public List<Coisa> getListCoisaAmigo(Amigo amigo){
    	List<Coisa> objs = new ArrayList<Coisa>();
        String selectQuery = "SELECT  * FROM " + TABLE_COISA + " WHERE " + KEY_COISA_IDAMIGO + " = " + amigo.getIdAmigo()+ " ORDER BY " + KEY_NOME_COISA ;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c.moveToFirst()) {
           do {
               objs.add(CreateCoisa(c));
           } while (c.moveToNext());
        }

        return objs;
    }
    
    public List<Coisa> getListCoisaEmprestada(Amigo amigo){
    	List<Coisa> objs = new ArrayList<Coisa>();
        String selectQuery = "SELECT  * FROM " + TABLE_COISA + " WHERE " + KEY_EMPRESTADA + " = 1" + " ORDER BY " + KEY_NOME_COISA ;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c.moveToFirst()) {
           do {
               objs.add(CreateCoisa(c));
           } while (c.moveToNext());
        }

        return objs;
    }
    
    public List<Coisa> getListCoisaDePosse(Amigo amigo){
    	List<Coisa> objs = new ArrayList<Coisa>();
        String selectQuery = "SELECT  * FROM " + TABLE_COISA + " WHERE " + KEY_EMPRESTADA + " = 0" + " ORDER BY " + KEY_NOME_COISA ;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c.moveToFirst()) {
           do {
               objs.add(CreateCoisa(c));
           } while (c.moveToNext());
        }

        return objs;
    }
    
    public ArrayList<Coisa> getAllCoisa(){
    	ArrayList<Coisa> objs = new ArrayList<Coisa>();
        String selectQuery = "SELECT  * FROM " + TABLE_COISA + " ORDER BY " + KEY_NOME_COISA;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c.moveToFirst()) {
           do {
               objs.add(CreateCoisa(c));
           } while (c.moveToNext());
        }

        return objs;
    }
    
//
//    
//    
//    public int getCountVencimento(int idObjetivo, String date){
//        String selectQuery = "SELECT * FROM " + TABLE_OBJETIVO_SEMANA + " WHERE " + KEY_DATA + " = '" + date + "' AND " + KEY_DEPOSITADO + " = '0'";
//     
//        if(idObjetivo > 0)
//        	selectQuery += " AND " + KEY_ID_OBJETIVO + " = " +idObjetivo;
//        
//        Log.e(LOG, selectQuery);
//     
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        int cont = 0;
//        if (c.moveToFirst()) {
//           do {
//        	   cont++;
//           } while (c.moveToNext());
//        }
//
//        return cont;
//    }
//    
//    public int getCountDepositados(int idObjetivo){
//        String selectQuery = "SELECT * FROM " + TABLE_OBJETIVO_SEMANA + " WHERE " + KEY_ID_OBJETIVO + " = " + idObjetivo + " AND " + KEY_DEPOSITADO + " = '1'";
//     
//        Log.e(LOG, selectQuery);
//     
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        int cont = 0;
//        if (c.moveToFirst()) {
//           do {
//        	   cont++;
//           } while (c.moveToNext());
//        }
//
//        return cont;
//    }
//    
//    public int getCountVencido(int objetivo){
//        int cont = 0;
//        
//        List<Objetivo> listaObjetivo;
//        
//        if(objetivo == 0)
//        	listaObjetivo = getAllObjetivo();
//        else
//        {
//        	listaObjetivo = new ArrayList<Objetivo>();
//        	listaObjetivo.add(getOneObjetivo(objetivo));
//        }
//        	
//        List<ObjetivoSemana> listaObjSemana;
//        Calendar data1 = Calendar.getInstance();
//		Calendar data2 = Calendar.getInstance();
//		String[] partes;
//        
//        for(int i = 0; i < listaObjetivo.size(); i++)
//        {
//        	listaObjSemana = getObjetoSituacaoNaoDepositado(listaObjetivo.get(i).getId());
//        	
//        	for(int j = 0; j < listaObjSemana.size(); j++)
//        	{
//				partes = listaObjSemana.get(j).getData().split("/");
//				data2.set(Integer.parseInt(partes[2]), Integer.parseInt(partes[1])-1, Integer.parseInt(partes[0]));
//				
//				if(data2.compareTo(data1) < 0)
//				{
//					cont++;
//					break;
//				}
//        	}
//        	
//        	if(cont > 0)
//        		break;
//        }
//        closeDB();
//        return cont;
//    }
//    
//    private String Calendario(int diasDiferenca, int dia, int mes, int ano) {
//  		SimpleDateFormat formatter;
//
//  		formatter = new SimpleDateFormat("dd/MM/yyyy");
//  		String d = null;
//
//  		GregorianCalendar cal = new GregorianCalendar(ano, mes - 1, dia);
//
//  		cal.add(Calendar.DAY_OF_MONTH, diasDiferenca);
//
//  		Date futuro = cal.getTime();
//  		// formata a data
//  		try {
//  			d = formatter.format(futuro);// user entry date
//
//  		} catch (ParseException e) {
//  			e.printStackTrace();
//  		}
//
//  		return d + "";
//  	}
    
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}

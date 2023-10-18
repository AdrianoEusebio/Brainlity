package com.example.brainlity.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static String NAME = "DATABASE";
    private static int VERSION = 2;

    // TABELA FRASE
    public static final String TABLE_FRASE = "Frase";
    public static final String KEY_FRASE_ID = "id";
    public static final String KEY_FRASE_TEXTO = "texto";
    public static final String KEY_FRASE_FUNDO = "fundo";
    public static final String KEY_FRASE_AUTOR = "autor";

    //TABELA USUARIO
    public static final String TABLE_REGISTRO = "Registro";
    public static final String KEY_REGISTRO_DESCRICAO = "descricao";
    public static final String KEY_REGISTRO_HUMOR = "humor";
    public static final String KEY_REGISTRO_ID = "Id";
    public static final String KEY_REGISTRO_FRASE = "frase";
    public static final String KEY_REGISTRO_DATE = "data";

    public DataBaseDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_FRASE + "("
                + KEY_FRASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FRASE_TEXTO + " TEXT,"
                + KEY_FRASE_AUTOR + " TEXT,"
                + KEY_FRASE_FUNDO + " TEXT"
                + ")";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_REGISTRO + "("
                + KEY_REGISTRO_ID + " TEXT PRIMARY KEY,"
                + KEY_REGISTRO_DESCRICAO + " TEXT NOT NULL,"
                + KEY_REGISTRO_HUMOR + " TEXT NOT NULL,"
                + KEY_REGISTRO_FRASE + " TEXT NOT NULL,"
                + KEY_REGISTRO_DATE + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql2);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 2){
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_REGISTRO + "("
                    + KEY_REGISTRO_ID + " TEXT PRIMARY KEY,"
                    + KEY_REGISTRO_DESCRICAO + " TEXT NOT NULL,"
                    + KEY_REGISTRO_HUMOR + " TEXT NOT NULL,"
                    + KEY_REGISTRO_FRASE + " TEXT NOT NULL,"
                    + KEY_REGISTRO_DATE + " TEXT NOT NULL"
                    + ")");
        }
    }
}

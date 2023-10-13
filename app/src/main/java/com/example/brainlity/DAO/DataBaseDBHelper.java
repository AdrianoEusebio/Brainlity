package com.example.brainlity.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static String NAME = "DATABASE";
    private static int VERSION = 2;

    // TABELA FRASE
    public static final String TABLE_FRASE = "Frase";
    public static final String KEY_ID = "id";
    public static final String KEY_TEXTO = "texto";
    public static final String KEY_FUNDO = "fundo";
    public static final String KEY_AUTOR = "autor";

    //TABELA USUARIO
    public static final String TABLE_USUARIO = "Usuario";
    public static final String KEY_NOME = "Nome";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SENHA = "Senha";

    public DataBaseDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_FRASE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TEXTO + " TEXT,"
                + KEY_AUTOR + " TEXT,"
                + KEY_FUNDO + " TEXT"
                + ")";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_USUARIO + "("
                + KEY_EMAIL + " TEXT PRIMARY KEY,"
                + KEY_SENHA + " TEXT NOT NULL,"
                + KEY_NOME + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 2){
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO + "("
                    + KEY_EMAIL + " TEXT PRIMARY KEY,"
                    + KEY_SENHA + " TEXT NOT NULL,"
                    + KEY_NOME + " TEXT NOT NULL"
                    + ")");
        }
    }
}

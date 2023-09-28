package com.example.brainlity.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static String NAME = "DATABASE";
    private static int VERSION = 1;

    // Nome da tabela
    public static final String TABLE_FRASE = "Frase";

    // Colunas da tabela
    public static final String KEY_ID = "id";
    public static final String KEY_TEXTO = "texto";
    public static final String KEY_FUNDO = "fundo";
    public static final String KEY_AUTOR = "autor";

    public DataBaseDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_FRASE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TEXTO + " TEXT,"
                + KEY_FUNDO + " TEXT,"
                + KEY_AUTOR + " TEXT"
                + ")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRASE);
        onCreate(db);

    }
}

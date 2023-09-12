package com.example.brainlity.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public MyDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open()throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long inserirDados(String texto, String autor, int fundoResId) {
        ContentValues values = new ContentValues();
        values.put("texto", texto);
        values.put("autor", autor);
        values.put("fundo_res_id", fundoResId);
        return database.insert("dados", null, values);
    }

    public Cursor recuperarDados() {
        String[] allColumns = {"_id", "texto", "autor", "fundo_res_id"};
        return database.query("dados", allColumns, null, null, null, null, null);
    }
}

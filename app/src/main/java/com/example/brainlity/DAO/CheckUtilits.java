package com.example.brainlity.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainlity.Usuario;
import com.example.brainlity.utils.Standard;

public class CheckUtilits {
    private DataBaseDBHelper dbHelper;
    private Context context;

    private AppCompatActivity appCompatActivity;

    public CheckUtilits(Context context){
        this.context = context;
        dbHelper = new DataBaseDBHelper(context);
    }
    public Usuario checkUser(String email, String password){
        Usuario usuario = new Usuario();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colluns = {dbHelper.KEY_EMAIL, dbHelper.KEY_SENHA, dbHelper.KEY_NOME};
        String selection = dbHelper.KEY_EMAIL + "= ? " + "AND " + dbHelper.KEY_SENHA + "= ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(dbHelper.TABLE_USUARIO, colluns, selection,selectionArgs, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                usuario.setEmail(cursor.getString(0));
                usuario.setSenha(cursor.getString(1));
                usuario.setNome(cursor.getString(2));
            } else {
                // caso não retornar nenhum usuario do cursor, o retorno da função será nula
                return null;
            }
        }

        //finalizar o cursor
        cursor.close();

        // finalizar o SQLiteDatabase
        db.close();
        return usuario;
    }

    public boolean checkEmailExist(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colluns = {dbHelper.KEY_EMAIL};
        String selection = dbHelper.KEY_EMAIL + "= ? ";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(dbHelper.TABLE_USUARIO, colluns, selection,selectionArgs, null, null, null, null);
        int cursorCount = cursor.getCount();

        //finalizar o cursor
        cursor.close();

        // finalizar o SQLiteDatabase
        db.close();

        // Se o cursorCount for maior que 0, isso significa que o usuário com as credenciais fornecidas existe
        return cursorCount > 0;
    }

}

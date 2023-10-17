package com.example.brainlity.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.brainlity.entidade.Insight;
import com.example.brainlity.entidade.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseBDLocal {

    private DatabaseReference databaseReference;
    private DataBaseDBHelper dbHelper;
    private Context context;

    public FirebaseBDLocal(Context context) {
        this.context = context;
        dbHelper = new DataBaseDBHelper(context);
        //fixme Inicialize o DatabaseReference com a referência ao nó "Frases" do Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Frases");
    }



    public void syncFirebaseDataToLocalDatabaseInsight() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deleteAllFrases();
                int i = 1;

                for ( DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer image = snapshot.child("frase"+i).child("fundo").getValue(Integer.class);
                    String texto = snapshot.child("frase"+i).child("texto").getValue(String.class);
                    String autor = snapshot.child("frase"+i).child("autor").getValue(String.class);
                    long id = i;
                    Insight insight = new Insight(texto,autor,image, id);
                    inserirFrase(insight);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //METODOS DA TABELA FRASE -----------------------------------------------------------------------------------------------
    public long inserirFrase(Insight insight) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long idInserido = -1; // Valor padrão se a inserção falhar
        try {
            ContentValues values = new ContentValues();
            values.put(dbHelper.KEY_TEXTO, insight.getText());
            values.put(dbHelper.KEY_FUNDO, insight.getFundo());
            values.put(dbHelper.KEY_AUTOR, insight.getAuthor());
            values.put(dbHelper.KEY_ID, insight.getId());

            idInserido = db.insert(dbHelper.TABLE_FRASE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return idInserido;
    }

    public void deleteAllFrases() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(dbHelper.TABLE_FRASE, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @SuppressLint("Range")
    public List<Insight> getAllFrases() {
        List<Insight> insights = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.query(dbHelper.TABLE_FRASE, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Insight insight = new Insight();
                        insight.setId(cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_ID)));
                        insight.setText(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_TEXTO)));
                        insight.setFundo(cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_FUNDO)));
                        insight.setAuthor(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_AUTOR)));
                        insights.add(insight);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return insights;
    }
}

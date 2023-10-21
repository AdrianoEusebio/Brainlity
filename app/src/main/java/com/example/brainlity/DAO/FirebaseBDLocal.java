package com.example.brainlity.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Adapter;

import androidx.annotation.NonNull;

import com.example.brainlity.Entidade.Insight;
import com.example.brainlity.Entidade.Registro;
import com.example.brainlity.Registro.RegistroAdapter;
import com.example.brainlity.Utils.Standard;
import com.example.brainlity.Utils.VerificarConexaoAsyncTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseBDLocal {

    private DatabaseReference databaseReference;
    private DataBaseDBHelper dbHelper;
    private Context context;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colecao = db.collection("Users");

    public FirebaseBDLocal(Context context) {
        this.context = context;
        dbHelper = new DataBaseDBHelper(context);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Frases");

    }


    public void syncFirebaseDataToLocalDatabaseInsight() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deleteAllFrases();
                int i = 1;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer image = snapshot.child("frase" + i).child("fundo").getValue(Integer.class);
                    String texto = snapshot.child("frase" + i).child("texto").getValue(String.class);
                    String autor = snapshot.child("frase" + i).child("autor").getValue(String.class);
                    long id = i;
                    Insight insight = new Insight(texto, autor, image, id);
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
            values.put(dbHelper.KEY_FRASE_TEXTO, insight.getText());
            values.put(dbHelper.KEY_FRASE_FUNDO, insight.getFundo());
            values.put(dbHelper.KEY_FRASE_AUTOR, insight.getAuthor());
            values.put(dbHelper.KEY_FRASE_ID, insight.getId());


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
                        insight.setId(cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_FRASE_ID)));
                        insight.setText(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_FRASE_TEXTO)));
                        insight.setFundo(cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_FRASE_FUNDO)));
                        insight.setAuthor(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_FRASE_AUTOR)));
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

    //METODOS DA TABELA Registro -----------------------------------------------------------------------------------------------
    // todo Preciso de um metodo para deletar um registro/ um metodo para inserir no firestore
    //  um metodo para deletar todos os registro do firestore


    public long inserirRegistro(Registro registro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long idInserido = -1; 
        try {
            ContentValues values = new ContentValues();
            values.put(dbHelper.KEY_REGISTRO_DESCRICAO, registro.getDescricao());
            values.put(dbHelper.KEY_REGISTRO_HUMOR, registro.getHumor());
            values.put(dbHelper.KEY_REGISTRO_DATE, registro.getData());

            idInserido = db.insert(dbHelper.TABLE_REGISTRO, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return idInserido;
    }

    public void inserirRegistroFirestore(String email){
        List<Registro> registros = getAllRegistro();
        CollectionReference collectionReference = colecao.document(email).collection("Registros");
        int i = 1;
        for (Registro registro : registros){
            Map<String, String> dados = new HashMap<>();
            dados.put("descricao",registro.getDescricao());
            dados.put("humor",registro.getHumor());
            dados.put("data",registro.getData());
            collectionReference.document(String.valueOf(i)).set(dados).addOnSuccessListener(command -> {

            }).addOnFailureListener(e -> {

            });
            i++;
        }
    }
    @SuppressLint("Range")
    public List<Registro> getAllRegistro() {
        List<Registro> registros = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.query(dbHelper.TABLE_REGISTRO, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Registro registro = new Registro();
                        registro.setId(cursor.getLong(cursor.getColumnIndex(dbHelper.KEY_REGISTRO_ID)));
                        registro.setDescricao(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_REGISTRO_DESCRICAO)));
                        registro.setHumor(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_REGISTRO_HUMOR)));
                        registro.setData(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_REGISTRO_DATE)));
                        registros.add(registro);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return registros;
    }


    public void deleteAllRegistro() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(dbHelper.TABLE_REGISTRO, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void deleteRegistro(long registroId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = dbHelper.KEY_REGISTRO_ID + " = ?";
        String[] whereArgs = { String.valueOf(registroId) };

        int rowsDeleted = db.delete(dbHelper.TABLE_REGISTRO, whereClause, whereArgs);

        if (rowsDeleted > 0) {
            Log.d("Delete", "Registro deletado com sucesso!");
        } else {
            Log.d("Delete", "Falha ao deletar o registro.");
        }
    }

}

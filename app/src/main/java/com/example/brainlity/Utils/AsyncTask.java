package com.example.brainlity.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.brainlity.DAO.FirebaseBDLocal;
import com.example.brainlity.Utils.Standard;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class AsyncTask extends android.os.AsyncTask<Void,Void,Void> {
    private FirebaseBDLocal firebaseBDLocal;
    SharedPreferences sharedPreferences;
    private Standard standard = standard = new Standard();

    private CollectionReference usersCollection;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;

    public AsyncTask(Context context){
        this.context = context;
        firebaseBDLocal = new FirebaseBDLocal(context);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        sharedPreferences = context.getSharedPreferences("Usuario",Context.MODE_PRIVATE);
        if(standard.avaliarConexao(context)){
            String email = sharedPreferences.getString("email","");
            realizarSincronizacaoFirestore();
            realizarSincronizacaoInsight();
            firebaseBDLocal.syncRegistroFirestore(email);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Este método é chamado após a conclusão do trabalho em segundo plano
        // Você pode atualizar a interface do usuário aqui
    }

    public void realizarSincronizacaoFirestore() {
        if(standard.verificacaoCount(context)){
            usersCollection = db.getInstance().collection("Users");
            String emailToSearch = sharedPreferences.getString("email", "");
            String nomeUpdate = sharedPreferences.getString("nome","");
            Query query = usersCollection.whereEqualTo("email", emailToSearch);
            query.get().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    QuerySnapshot queryDocument = task.getResult();
                    if (queryDocument != null && !queryDocument.isEmpty()) {
                        for(DocumentSnapshot documentSnapshot: queryDocument.getDocuments()){
                            if(documentSnapshot.getReference().update("nome",nomeUpdate).isSuccessful()){
                            }
                        }
                    }
                }
            });
        }
    }

    public void realizarSincronizacaoInsight() {
        FirebaseBDLocal firebaseBDLocal = new FirebaseBDLocal(context);
        firebaseBDLocal.syncFirebaseDataToLocalDatabaseInsight();
    }

}

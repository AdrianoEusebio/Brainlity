package com.example.brainlity.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brainlity.CadastroActivity;
import com.example.brainlity.Usuario;
import com.example.brainlity.utils.Standard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class CheckUtilits {
    private DataBaseDBHelper dbHelper;
    private Context context;
    private Standard standard = new Standard();

    public CheckUtilits(Context context){
        this.context = context;
        dbHelper = new DataBaseDBHelper(context);
    }

    /*todo Checar se o email existe ------------------------------------------------ */
    public void checkEmailInvite(FirebaseUser user, AppCompatActivity activity){

        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        // E-mail de verificação enviado com sucesso
                        standard.toast(activity, "Email enviado, verifique seus emails.",1);
                    } else {
                        // Falha ao enviar o e-mail de verificação
                        standard.toast(activity, "Falha ao enviar o email",1);

                        // Lide com o erro aqui
                        Exception exception = task.getException();
                        if (exception != null) {
                            // Trate o erro aqui
                        }
                    }
                }
            });
        }
    }


    //todo Check campo vazio -------------------------
    public boolean checkFieldsVoid(String nome, String email, String password){
        if(nome.equals("") || email.equals("") || password.equals("")){
            return false;
        } else {
            return true;
        }
    }

    //todo Check de senha tem que ter 6 caracteres
    public boolean checkPasswordChar(String password){
        if(password.length() < 6){
            return false;
        }else {
            return true;
        }
    }
}

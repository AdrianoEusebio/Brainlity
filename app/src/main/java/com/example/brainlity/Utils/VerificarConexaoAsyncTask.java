package com.example.brainlity.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class VerificarConexaoAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private OnConexaoVerificadaListener listener;

    public VerificarConexaoAsyncTask(Context context, OnConexaoVerificadaListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return isConexaoDisponivel();
    }

    @Override
    protected void onPostExecute(Boolean isConectado) {
        listener.onConexaoVerificada(isConectado);
    }

    private boolean isConexaoDisponivel() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }

    public interface OnConexaoVerificadaListener {
        void onConexaoVerificada(boolean isConectado);
    }
}

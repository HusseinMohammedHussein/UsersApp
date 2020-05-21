package com.example.getjson.Retrofit.APIControl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class CheckConnection {
    public static boolean isConnection(Context context){
        boolean connected = false;
        try {
            ConnectivityManager cManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cManager.getActiveNetworkInfo();
                connected = netInfo != null && netInfo.isAvailable() && netInfo.isConnected();
                return connected;

        } catch (Exception e) {
            Log.e("Connectivity Manager", e.getLocalizedMessage());
        }
        return connected;
    }
}

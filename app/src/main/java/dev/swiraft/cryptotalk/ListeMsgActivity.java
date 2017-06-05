package dev.swiraft.cryptotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dev.swiraft.cryptotalk.ressources.AuthentificationManager;

public class ListeMsgActivity extends AppCompatActivity {

    public final static String TAG = "ListeMsgActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_msg);
        Log.i(TAG, "onCreate()");
        AuthentificationManager.init();
        testConnexion();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        testConnexion();
    }

    private void testConnexion(){
        if(!AuthentificationManager.authed()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

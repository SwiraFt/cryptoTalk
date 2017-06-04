package dev.swiraft.cryptotalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dev.swiraft.cryptotalk.ressources.AuthentificationManager;
import dev.swiraft.cryptotalk.ressources.LoginActivity;

public class ListeMsgActivity extends AppCompatActivity {

    public final static String TAG = "ListeMsgActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_msg);
        AuthentificationManager.init();

        if(!AuthentificationManager.authed()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }
}

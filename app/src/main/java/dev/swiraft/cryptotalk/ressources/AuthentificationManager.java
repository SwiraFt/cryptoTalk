package dev.swiraft.cryptotalk.ressources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Amin on 04/06/2017.
 */

public abstract class AuthentificationManager {

    private final static String  TAG = "Authentifiacation";

    private static FirebaseAuth mAuth;

    private static FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        }
    };

    public static void init(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    public static boolean authed(){
        FirebaseUser user = mAuth.getCurrentUser();
        return user != null;

    }

    public static void connect(Context context, String eMail, String Mdp) {
        Toast.makeText(context, "eMail : " + eMail + " Mot de passe " + Mdp , Toast.LENGTH_SHORT).show();
    }
}

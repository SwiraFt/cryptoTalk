package dev.swiraft.cryptotalk.ressources;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Amin on 04/06/2017.
 */

public abstract class AuthentificationManager {

    private final static String  TAG = "Authentifiacation";

    private static boolean isOk = true;

    private static String messageErreur;

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

    public boolean exists(String eMail){
        return true;
    }

    public static boolean connect(final String eMail, String Mdp, Activity context) {
        AuthentificationManager.isOk = true;
        mAuth.signInWithEmailAndPassword(eMail, Mdp).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()) {
                    Log.e(TAG, "Erreur de connexion pour : " + eMail);
                    messageErreur = task.getException().getMessage();
                    AuthentificationManager.isOk = false;
                    return;
                }

            }
        });
        return AuthentificationManager.isOk;
    }

    public static boolean createUser(final String email, String Mdp, Activity context){
        AuthentificationManager.isOk = true;
        mAuth.createUserWithEmailAndPassword(email, Mdp).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    Log.e(TAG, "Erreur de création de l\'utilisateur : " + email);
                    messageErreur = task.getException().getMessage();
                    AuthentificationManager.isOk = false;
                }
            }
        });
        return AuthentificationManager.isOk;
    }

    public static String getMessage(){
        return AuthentificationManager.messageErreur == null ? "" : messageErreur;
    }
}

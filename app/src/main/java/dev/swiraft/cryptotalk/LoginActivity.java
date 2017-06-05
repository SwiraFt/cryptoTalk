package dev.swiraft.cryptotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.swiraft.cryptotalk.ressources.AuthentificationManager;
import dev.swiraft.cryptotalk.ressources.Outils;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivity";

    private EditText editTextEmail;
    private EditText editTextMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "onCreate()");
        this.editTextEmail = (EditText) findViewById(R.id.editText_EmailInput);
        this.editTextMdp = (EditText) findViewById(R.id.editText_MdpInput);
        findViewById(R.id.editTextMdpConfInput).setVisibility(View.GONE);
        Button boutton = (Button) findViewById(R.id.buttonSignIn);
        boutton.setY(-230f);
        TextView textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        textViewRegister.setY(-270f);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.buttonSignIn :
                        if (validerCredentials())
                            if(AuthentificationManager.connect( editTextEmail.getText().toString().trim(), editTextMdp.getText().toString().trim(), LoginActivity.this)) {
                                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(LoginActivity.this, ListeMsgActivity.class);
                                startActivity(intent);
                            }
                        break;
                    case R.id.textViewRegister :
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                }


            }
        };

        boutton.setOnClickListener(onClickListener);
        textViewRegister.setOnClickListener(onClickListener);

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
    }

    private boolean validerCredentials() {
        return validerEmail() && validerMdp();

    }


    private boolean validerMdp() {
        String mdp = editTextMdp.getText().toString().trim();
        if(mdp.isEmpty() || mdp.length() < 3){
            editTextMdp.setError(getString(R.string.error_incorrect_password));
            Outils.requestFocus(editTextMdp, this);
            return false;
        }
        return true;
    }

    private boolean validerEmail() {
        String email = editTextEmail.getText().toString().trim();

        if(email.isEmpty() || !Outils.isValideEmail(email)) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            Outils.requestFocus(editTextEmail, this);
            return false;
        }
        return true;
    }

    @Override
    public Window getWindow() {
        return super.getWindow();
    }
}

package dev.swiraft.cryptotalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.swiraft.cryptotalk.R;
import dev.swiraft.cryptotalk.ressources.AuthentificationManager;
import dev.swiraft.cryptotalk.ressources.Outils;

public class RegisterActivity extends AppCompatActivity {
    private final static String TAG = "RegisterActivity";

    EditText editTextEmail;
    EditText editTextMdp;
    EditText editTextMdpConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "onCreate()");

        ((TextView) findViewById(R.id.LoginTitleText)).setText(getString(R.string.title_activity_register));
        findViewById(R.id.textViewRegister).setVisibility(View.GONE);

        editTextEmail = (EditText) findViewById(R.id.editText_EmailInput);
        editTextMdp = (EditText) findViewById(R.id.editText_MdpInput);
        editTextMdpConf = (EditText) findViewById(R.id.editTextMdpConfInput);

        Button buttonRegister =  (Button) findViewById(R.id.buttonSignIn);

        buttonRegister.setText(getString(R.string.title_activity_register));
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validerCredentials())
                    if(!AuthentificationManager.createUser(editTextEmail.getText().toString().trim(), editTextMdp.getText().toString().trim(), RegisterActivity.this))
                        Toast.makeText(RegisterActivity.this, AuthentificationManager.getMessage(), Toast.LENGTH_SHORT).show();
                    else{
                        Intent intent = new Intent(RegisterActivity.this, ListeMsgActivity.class);
                        startActivity(intent);
                    }


            }
        });

    }

    private boolean validerCredentials() {
        return(validerEmail() && validerMdp());
    }

    private boolean validerMdp() {
        String mdp = editTextMdp.getText().toString().trim();
        if(mdp.isEmpty() || mdp.length() < 3){
            editTextMdp.setError(getString(R.string.error_incorrect_password));
            Outils.requestFocus(editTextMdp, this);
            return false;
        }
        if(!mdp.equals(editTextMdpConf.getText().toString().trim())){
            editTextMdpConf.setError(getString(R.string.error_notMatches_password));
            Outils.requestFocus(editTextMdpConf, this);
            return false;
        }
        return true;
    }

    private boolean validerEmail() {
        String eMail = editTextEmail.getText().toString().trim();
        if(eMail.isEmpty() && !Outils.isValideEmail(eMail)){
            editTextEmail.setError(getString(R.string.error_invalid_email));
            Outils.requestFocus(editTextEmail, this);
            return false;
        }
        return true;
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
}

package dev.swiraft.cryptotalk.ressources;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import dev.swiraft.cryptotalk.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.editTextEmail = (EditText) findViewById(R.id.editText_EmailInput);
        this.editTextMdp = (EditText) findViewById(R.id.editText_MdpInput);
        Button boutton = (Button) findViewById(R.id.buttonSignIn);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validerCredentials())
                    AuthentificationManager.connect((Context) LoginActivity.this, editTextEmail.getText().toString().trim(), editTextMdp.getText().toString().trim());
            }
        };

        boutton.setOnClickListener(onClickListener);

    }

    private boolean validerCredentials() {
        return validerEmail() && validerMdp();

    }


    private boolean validerMdp() {
        String mdp = editTextMdp.getText().toString().trim();
        if(mdp.isEmpty() || mdp.length() < 3){
            editTextMdp.setError(getString(R.string.error_incorrect_password));
            requestFocus(editTextMdp);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validerEmail() {
        String email = editTextEmail.getText().toString().trim();

        if(email.isEmpty() || !isValideEmail(email)) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            requestFocus(editTextEmail);
            return false;
        }
        return true;
    }

    private boolean isValideEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

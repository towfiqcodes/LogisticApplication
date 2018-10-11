package com.rit.logisticapplication.Actitvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;



import com.rit.logisticapplication.R;
import com.rit.logisticapplication.database.UserDatabaseManager;
import com.rit.logisticapplication.models.User;

public class SignIn_Activity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    User user;
    UserDatabaseManager userDatabaseManager;

    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) TextView _loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_in );
        ButterKnife.bind( this );
        userDatabaseManager=new UserDatabaseManager( this );

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 user=new User(_nameText.getText().toString(),_passwordText.getText().toString());
                long insertedRow=userDatabaseManager.addUser( user );
                if(insertedRow>0){
                    login();
                }else{
                    Toast.makeText(SignIn_Activity.this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                }
            }



        });


    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignIn_Activity.this,
                                                                 R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();
       boolean userIdentify=userDatabaseManager.findpassword( name,password );
        if(userIdentify==true) {
            Intent intent = new Intent(getApplicationContext(), DashBoard.class);
            intent.putExtra( "name", name );
            startActivity( intent );
        }else {
            Toast.makeText( getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT ).show();
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }




    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
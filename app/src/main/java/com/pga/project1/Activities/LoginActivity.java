package com.pga.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.ValidationMessage;
import com.pga.project1.Utilities.Webservice;

public class LoginActivity extends Activity {


    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;
    ProgressBar loaderBar;
    LinearLayout panel;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        context = this;

        if (Account.getInstant(this).alreadyHaveToken()) {

            callMainActivity();

        }


        txtUsername = (EditText) findViewById(R.id.etxt_fragmentLogin_username);
        txtPassword = (EditText) findViewById(R.id.etxt_fragmentLogin_password);

        loaderBar = (ProgressBar) findViewById(R.id.pgb_fragmentLogin_loader);

        panel = (LinearLayout) findViewById(R.id.ll_fragmentLogin_panel);

        btnLogin = (Button) findViewById(R.id.btn_fragmentLogin_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public ValidationMessage validationMessage;

            @Override
            public void onClick(View view) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (LoginValidation(username, password, validationMessage))
                    loginClicked(username, password);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loginClicked(String username, String password) {

        btnLogin.setVisibility(View.GONE);
        loaderBar.setVisibility(View.VISIBLE);

        Webservice.Login(this, username, password, new CallBack<String>() {
            @Override
            public void onSuccess(String token) {

                Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
                Account.getInstant(context).storeToken(token);
                callMainActivity();

            }

            @Override
            public void onError(String err) {
                //Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.view_not_valid);
                panel.startAnimation(animation);

                btnLogin.setVisibility(View.VISIBLE);
                loaderBar.setVisibility(View.GONE);
            }


        });

    }


    //-----------------------------------------------------Functions}


    //validations-----------------------------------------------------

    private boolean LoginValidation(String username, String password, ValidationMessage validationMessage) {

        return true;
    }

    private void callMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}

package com.pga.project1.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pga.project1.Intefaces.CallBack;
import com.pga.project1.MainActivity;
import com.pga.project1.R;
import com.pga.project1.Utilities.Account;
import com.pga.project1.Utilities.ErrorMessage;
import com.pga.project1.Utilities.ValidationMessage;
import com.pga.project1.Utilities.Webservice;

/**
 * Created by ashkan on 8/9/2014.
 */
public class FragmentLogin extends Fragment {


    // Constants-----------------------------------------------------

    //-----------------------------------------------------Constants

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentLogin() {

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,
                container, false);


        this.getActivity().getActionBar().show();

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (Account.getInstant(getActivity()).alreadyHaveToken()) {

            ((MainActivity) getActivity()).ShowTreeFragmnet("Login Fragment");

        }


        txtUsername = (EditText) getView().findViewById(R.id.etxt_fragmentLogin_username);
        txtPassword = (EditText) getView().findViewById(R.id.etxt_fragmentLogin_password);

        btnLogin = (Button) getView().findViewById(R.id.btn_fragmentLogin_login);
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


    //-----------------------------------------------------override functions}


    //{Functions-----------------------------------------------------

    private void loginClicked(String username, String password) {
        Webservice.Login(getActivity(), username, password, new CallBack<String>() {
            @Override
            public void onSuccess(String token) {

                Toast.makeText(getActivity(), token, Toast.LENGTH_SHORT).show();
                Account.getInstant(getActivity()).storeToken(token);
                ((MainActivity) getActivity()).ShowTreeFragmnet(-1, "Login Fragment");


            }

            @Override
            public void onError(ErrorMessage err) {
                //Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();

            }


        });

    }


    //-----------------------------------------------------Functions}


    //validations-----------------------------------------------------

    private boolean LoginValidation(String username, String password, ValidationMessage validationMessage) {

        return true;
    }

    //-----------------------------------------------------validations}


    //{static Functions-----------------------------------------------------

    //-----------------------------------------------------static Functions}

    //{Setter getters-----------------------------------------------------

    //-----------------------------------------------------Setter getters}

}

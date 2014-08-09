package com.pga.project1.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pga.project1.R;
import com.pga.project1.Utilities.ValidationMessage;

/**
 * Created by ashkan on 8/9/2014.
 */
public class FragmentLogin extends Fragment {


    //{Constants-----------------------------------------------------

    //-----------------------------------------------------Constants}

    //{static fields-----------------------------------------------------

    //-----------------------------------------------------static fields}

    //{Fields-----------------------------------------------------

    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;

    //-----------------------------------------------------Fields}

    //{Constructor-----------------------------------------------------


    public FragmentLogin(){

    }
    //-----------------------------------------------------Constructor}

    //{override functions---------------------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,
                container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtUsername = (EditText) getView().findViewById(R.id.etxt_fragmentLogin_username);
        txtPassword = (EditText) getView().findViewById(R.id.etxt_fragmentLogin_password);

        btnLogin = (Button) getView().findViewById(R.id.btn_fragmentLogin_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public ValidationMessage validationMessage;

            @Override
            public void onClick(View view) {

                String username = txtUsername.getText().toString();
                String password = txtUsername.getText().toString();

                LoginValidation(username, password, validationMessage);

                loginClicked();
            }
        });
    }




    //-----------------------------------------------------override functions}



    //{Functions-----------------------------------------------------

    private void loginClicked() {

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

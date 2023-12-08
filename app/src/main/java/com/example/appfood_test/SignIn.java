package com.example.appfood_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText email_login, password_login;
    CheckBox show_password;
    Button btn_login, btn_register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        email_login = findViewById(R.id.email_enter);
        password_login = findViewById(R.id.password_enter);
        show_password = findViewById(R.id.show_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login_email = email_login.getText().toString();
                String login_password = password_login.getText().toString();

                if(!TextUtils.isEmpty(login_email) || !TextUtils.isEmpty(login_password)) {

                    mAuth.signInWithEmailAndPassword(login_email, login_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                menuRedirection();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(SignIn.this, "" + error, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerRedirection();

            }
        });

        // Método responsável por tornar visível as senhas
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    password_login.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
    }

    public void registerRedirection() {

        Intent intent = new Intent(SignIn.this, Register.class);
        startActivity(intent);
        finish();

    }

    public void menuRedirection() {

        Intent intent = new Intent(SignIn.this, MainMenu.class);
        startActivity(intent);
        finish();

    }
}
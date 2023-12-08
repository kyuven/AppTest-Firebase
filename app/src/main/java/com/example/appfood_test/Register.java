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

import com.example.appfood_test.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText name_register, email_register, password_register, confirm_password_register;
    CheckBox show_password_register;
    Button btn_register, btn_signIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        name_register = findViewById(R.id.user_register_name);
        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        confirm_password_register = findViewById(R.id.password_confirm);
        show_password_register = findViewById(R.id.show_pass_register);

        btn_register = findViewById(R.id.btn_register);
        btn_signIn = findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel = new UserModel();

                userModel.setName(name_register.getText().toString());
                userModel.setEmail(email_register.getText().toString());

                String register_password = password_register.getText().toString();
                String register_password_confirm = confirm_password_register.getText().toString();

                // Verifica se não está vazio os campos
                if(!TextUtils.isEmpty(userModel.getName()) || !TextUtils.isEmpty(userModel.getEmail()) || !TextUtils.isEmpty(register_password) || !TextUtils.isEmpty(register_password_confirm)) {

                    // Verifica se ambas as senhas são iguais
                    if(register_password.equals(register_password_confirm)) {

                        // Cria o usuário passando o email e a senha; o email fica armazenado no model criado
                        mAuth.createUserWithEmailAndPassword(userModel.getEmail(), register_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    userModel.setId(mAuth.getUid());
                                    userModel.saveUser();

                                    // Se conseguir salvar o usuário o mesmo é enviado para a tela principal
                                    menuRedirection();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(Register.this, "" + error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Register.this, "Different passwords.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Register.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInRedirection();

            }
        });

        // Método responsável por tornar visível as senhas
        show_password_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    password_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirm_password_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirm_password_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

    }

    public void signInRedirection() {

        Intent intent = new Intent(Register.this, SignIn.class);
        startActivity(intent);
        finish();

    }

    public void menuRedirection() {

        Intent intent = new Intent(Register.this, MainMenu.class);
        startActivity(intent);
        finish();

    }
}
package com.example.finalproject.LOGIN;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Pass extends AppCompatActivity {

    private Button btnForgot;
    private EditText EditTextEmail;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);

        btnForgot = (Button) findViewById(R.id.btnForgot);
        EditTextEmail = (EditText) findViewById(R.id.EditTextEmail);

        mAuth = FirebaseAuth.getInstance();


        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditTextEmail.getText().toString();

                //validating inputs
                if (TextUtils.isEmpty(email)) {
                    EditTextEmail.setError("Please enter your email");
                    EditTextEmail.requestFocus();
                    return;
                }
                else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())    {
                                Toast.makeText(Forgot_Pass.this, "Verifique o seu email!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Forgot_Pass.this, Login.class));
                            }
                            else {
                                String error = task.getException().getMessage();
                                Toast.makeText(Forgot_Pass.this, "Error code: " + error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

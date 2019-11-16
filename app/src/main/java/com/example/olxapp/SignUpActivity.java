package com.example.olxapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    TextView textViewSignIn, textViewLogOut;
    EditText editTextEmail, editTextPassword, editTextRepPassword;
    String email, password, repeatPass;
    Button btnSignUp, btnValidate;
    private FirebaseAuth mAuth;
    private String TAG = "SignUp";
    FirebaseUser user;
    View textLayoutEmail,textLayoutPassword,textLayoutRepPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        editTextEmail = findViewById(R.id.editTextEmail);
        textLayoutEmail = findViewById(R.id.textLayoutEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textLayoutPassword = findViewById(R.id.textLayoutPassword);
        editTextRepPassword = findViewById(R.id.editTextRepPassword);
        textLayoutRepPassword=findViewById(R.id.textLayoutRepPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnValidate = findViewById(R.id.btnValidate);
        textViewLogOut = findViewById(R.id.textViewLogOut);


        btnSignUp.setOnClickListener(this);
        btnValidate.setOnClickListener(this);
        textViewLogOut.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null)
            refresh(user);
//        editTextPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editTextPassword.getText().toString().length() < 6)
//                    Toast.makeText(SignUpActivity.this, "password at least 6 character", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null)
            refresh(user);

    }

    private void refresh(final FirebaseUser user) {
        user.reload().addOnCompleteListener
                (new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("valid", String.valueOf(SignUpActivity.this.user.isEmailVerified()));
                        if (SignUpActivity.this.user != null) {
                            if (String.valueOf(SignUpActivity.this.user.isEmailVerified()).equalsIgnoreCase("false")) {
                                email=user.getEmail();
                                editTextEmail.setText(email);
                                editTextEmail.setEnabled(false);
                                editTextPassword.setVisibility(View.GONE);
                                editTextRepPassword.setVisibility(View.GONE);
                                textLayoutPassword.setVisibility(View.GONE);
                                textLayoutRepPassword.setVisibility(View.GONE);
                                btnSignUp.setVisibility(View.GONE);
                                btnValidate.setVisibility(View.VISIBLE);
                                textViewLogOut.setVisibility(View.VISIBLE);
                                textViewSignIn.setVisibility(View.GONE);
                            } else if (String.valueOf(SignUpActivity.this.user.isEmailVerified()).equalsIgnoreCase("true")) {
                                finish();
                                String email = SignUpActivity.this.user.getEmail();
                                String userId = SignUpActivity.this.user.getUid();
                                Log.d(TAG, email + userId);
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.putExtra("Id", userId);
                                intent.putExtra("Email", email);

                                startActivity(intent);

                            }

                        }

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if User is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null)
            refresh(user);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

            case R.id.btnSignUp:
                if (TextUtils.isEmpty(editTextEmail.getText().toString())
                        || TextUtils.isEmpty(editTextPassword.getText().toString())
                        || TextUtils.isEmpty(editTextRepPassword.getText().toString())) {
                    Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                } else if (editTextPassword.getText().toString().equals(editTextRepPassword.getText().toString()))
                    Registration();
                break;
            case R.id.btnValidate:
                sendEmailVerification();
                break;
            case R.id.textViewLogOut:
                logOut();
                break;
        }
    }

    public void logOut() {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();


    }

    private void sendEmailVerification() {
        user = mAuth.getCurrentUser();
        email = editTextEmail.getText().toString().trim();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Email sent.");
                    Toast.makeText(SignUpActivity.this,
                            "Email sent to " + user.getEmail(),
                            Toast.LENGTH_SHORT).show();

                } else {
                    Log.e(TAG, "sendEmailVerification", task.getException());
                    Toast.makeText(SignUpActivity.this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    private void Registration() {
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        repeatPass = editTextRepPassword.getText().toString().trim();
        if (email != null && password != null && repeatPass != null) {

            if (password.matches(repeatPass)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        editTextEmail.setEnabled(false);
                                        email=user.getEmail();
                                        editTextEmail.setText(email);
                                        textLayoutPassword.setVisibility(View.GONE);
                                        textLayoutRepPassword.setVisibility(View.GONE);
                                        btnSignUp.setVisibility(View.GONE);
                                        btnValidate.setVisibility(View.VISIBLE);
                                        textViewLogOut.setVisibility(View.VISIBLE);
                                        textViewSignIn.setVisibility(View.GONE);
                                    }
                                } else {
                                    Log.e(TAG, "createUserWithEmail:failure", task.getException());
                                    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                    Toast.makeText(SignUpActivity.this, "Registration failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            } else {
                Toast.makeText(this, "password and password repeat don't match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Check your connection");

    }
}

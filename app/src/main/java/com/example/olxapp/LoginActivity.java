package com.example.olxapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView textViewSignUp, textViewResetPass;
    EditText editTextEmail, editTextPassword;
    Button btnLogin;
    String email, password, TAG = "Login";
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewResetPass = findViewById(R.id.textViewResetPass);
        btnLogin.setOnClickListener(this);
        textViewResetPass.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        //userState();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Login();
                break;
            case R.id.textViewSignUp:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
            case R.id.textViewResetPass:
                ResetPassword();
                break;

        }
    }


    private void Login() {
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
//        if(email!=null&&password!=null){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Your Email and Password", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in User's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    final String email = user.getEmail();
                                    final String userId = user.getUid();
                                    Log.d(TAG, email + userId);
                                    if (String.valueOf(user.isEmailVerified()).equalsIgnoreCase("true")){

                                        Log.e("verified","true");


                                        Intent intent = new Intent(LoginActivity.this,
                                                MainActivity.class);
//                                        intent.putExtra("Id", userId);
//                                        intent.putExtra("Email", email);
                                        startActivity(intent);
                                        finish();

//                                        Constants.bandsRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                final FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                                                if (snapshot.hasChild(firebaseUser.getUid())) {
//                                                    goToBandHome(userId, email);
//
//                                                } else {
//                                                    Constants.restaurantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                        @Override
//                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                            if (firebaseUser != null) {
//                                                                if (snapshot.hasChild(firebaseUser.getUid())) {
//                                                                    goToRestaurantHome(userId, email);
//                                                                } else {
//                                                                    //not a band nor a restaurant!
//                                                                    Intent intent = new Intent(LoginActivity.this,
//                                                                            UserDataActivity.class);
//                                                                    intent.putExtra("Id", userId);
//                                                                    intent.putExtra("Email", email);
//                                                                    startActivity(intent);
//                                                                    finish();
//                                                                }
//                                                            }
//                                                        }
//
//                                                        @Override
//                                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                            Log.e(TAG, "Database error " + databaseError);
//                                                        }
//                                                    });
//                                                }
//                                            }
//
//                                            @Override
//                                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                Log.e(TAG, "Database error " + databaseError);
//                                            }
//                                        });
//
                                    }else{
                                        Intent intent = new Intent(LoginActivity.this,
                                                SignUpActivity.class);
//                                        intent.putExtra("Id", userId);
//                                        intent.putExtra("Email", email);
                                        startActivity(intent);
                                        finish();

                                    }
                                }
                            } else {
                                // If sign in fails, display a message to the User.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void ResetPassword() {
        email = editTextEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.sendPasswordResetEmail(email)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    public void goToBandHome(String userId, String email) {
        Intent intent = new Intent(LoginActivity.this,
                MainActivity.class);
        intent.putExtra("Id", userId);
        intent.putExtra("Email", email);
//        intent.putExtra("isBand", true);
        startActivity(intent);
        finish();
    }


    public void goToRestaurantHome(String userId, String email) {
        Intent intent = new Intent(LoginActivity.this,
                SignUpActivity.class);
        intent.putExtra("Id", userId);
        intent.putExtra("Email", email);
//        intent.putExtra("isBand", false);
        startActivity(intent);
        finish();
    }
    public void userState() {
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            goToBandHome(user.getUid(),user.getEmail());
        }else {
            Intent intent = new Intent(LoginActivity.this,
                    SignUpActivity.class);
            startActivity(intent);
            finish();

        }
    }
}

package com.example.quizapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapplication.callbacks.UserExistCallback;

public class SignIn extends AppCompatActivity {
    EditText editTextUsernameInSignIn, editTextPasswordInSignIn;
    DatabaseUtilities databaseUtilities;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextPasswordInSignIn = findViewById(R.id.editTextPasswordInSignIn);
        editTextUsernameInSignIn = findViewById(R.id.editTextUsernameInSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        databaseUtilities = new DatabaseUtilities(this);
        btnSignIn.setOnClickListener(v -> {
            String username = editTextUsernameInSignIn.getText().toString();
            String password = editTextPasswordInSignIn.getText().toString();
            databaseUtilities.checkIfUserExist(username, password, (userExist)->{
                if (userExist) {
                    Toast.makeText(SignIn.this, "Login Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignIn.this, "Check your username and password!", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
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

public class SignUp extends AppCompatActivity {
    EditText editTextUsernameInSignUp, editTextPasswordInSignUp, editTextConfirmPasswordInSignUp;
    Button btnSignUp;
    DatabaseUtilities databaseUtilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextPasswordInSignUp = findViewById(R.id.editTextPasswordInSignUp);
        editTextUsernameInSignUp = findViewById(R.id.editTextUsernameInSignUp);
        editTextConfirmPasswordInSignUp = findViewById(R.id.editTextConfirmPasswordInSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        databaseUtilities = new DatabaseUtilities(this);
        btnSignUp.setOnClickListener(v -> {
            String username = editTextUsernameInSignUp.getText().toString();
            String password = editTextPasswordInSignUp.getText().toString();
            String confirmPassword = editTextConfirmPasswordInSignUp.getText().toString();
            if(password.equals(confirmPassword)){
                databaseUtilities.checkIfTheUsernameIsRegistered(username, (userExist)->{
                    if (!userExist) {
                        databaseUtilities.insertNewUsers(username, password, (success)->{
                            if (!success) {
                                Toast.makeText(SignUp.this, "User creation failed", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
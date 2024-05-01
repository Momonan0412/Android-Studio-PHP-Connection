package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapplication.callbacks.UserExistCallback;

/**
 * DATABASE USAGE:
 * databaseUtilities.createTable();
 *         // THE CALLBACK RUN ASYNCHRONOUSLY
 *         databaseUtilities.insertNewUsers("xxxxxxx", "xxxxxxx", (insertSuccess)->{
 *             if(insertSuccess){
 *                 System.out.println("Insert Success! in the main!");
 *             }
 *         });
 *         // THE CALLBACK RUN ASYNCHRONOUSLY
 *         databaseUtilities.checkIfUserExist("xxxxxxx", "xxxxxxx", (userExists)->{
 *             if(userExists){
 *                 System.out.println("Checked Success! in the main!");
 *             }
 *         });
 * **/
public class MainActivity extends AppCompatActivity {
    DatabaseUtilities databaseUtilities;
    Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_design);
        databaseUtilities = new DatabaseUtilities(getApplicationContext());
        databaseUtilities.createTable();
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignIn.class));
        });
        btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SignUp.class));
        });
    }
}
package com.example.quizapplication;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapplication.callbacks.UserExistCallback;

/**
 * DATABASE USAGE:
 * databaseUtilities.createTable();
 * databaseUtilities.insertNewUsers("xxxxxx","xxxxxx");
 * databaseUtilities.readJapaneseKanjiData("Level 1");
 * **/
public class MainActivity extends AppCompatActivity {
    DatabaseUtilities databaseUtilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseUtilities = new DatabaseUtilities(getApplicationContext());

        // THE CALLBACK RUN ASYNCHRONOUSLY
        databaseUtilities.insertNewUsers("Momonan0412", "FurinaLablab", (insertSuccess)->{
            if(insertSuccess){
                System.out.println("Insert Success! in the main!");
            }
        });
        // THE CALLBACK RUN ASYNCHRONOUSLY
        databaseUtilities.checkIfUserExist("Momonan0412", "FurinaLablab", (userExists)->{
            if(userExists){
                System.out.println("Checked Success! in the main!");
            }
        });
    }
}
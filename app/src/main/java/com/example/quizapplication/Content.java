package com.example.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

public class Content extends AppCompatActivity {
    TextView textViewKanji, textViewChoiceOne, textViewChoiceTwo, textViewChoiceThree, textViewChoiceFour, textViewChoiceFive;
    TextView[] textViews;
    ArrayList<JapaneseData> data = new ArrayList<>();
    DatabaseUtilities databaseUtilities;
    Random random;
    JSONArray jsonArray;
    String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        random = new Random();
        textViews = new TextView[5];
        textViewKanji = findViewById(R.id.textViewKanji);
        textViews[0] = findViewById(R.id.textViewChoiceOne);
        textViews[1] = findViewById(R.id.textViewChoiceTwo);
        textViews[2] = findViewById(R.id.textViewChoiceThree);
        textViews[3] = findViewById(R.id.textViewChoiceFour);
        textViews[4] = findViewById(R.id.textViewChoiceFive);
        databaseUtilities = new DatabaseUtilities(this);
        // TODO: RANDOM A DATA AND GET THE INDEX IN A RANDOM METHOD
        // RANDOM DATA TO GUESS AND IN THE ONE IF THE CHOICES
        databaseUtilities.readJapaneseKanjiData("Level 1", (data)->{
            this.jsonArray = data;
            kanjiChanger();
        });
    }

    private void kanjiChanger() {
        int indexKanji = random.nextInt(this.jsonArray.length());
        int indexChoice = random.nextInt(5);
        try {
            textViewKanji.setText(jsonArray.getJSONObject(indexKanji).getString("kanji"));
            textViews[indexChoice].setText(jsonArray.getJSONObject(indexKanji).getString("furigana"));
            answer = jsonArray.getJSONObject(indexKanji).getString("furigana");
            for(int i = 0; i < 5; i++){
                if(i != indexChoice){
                    int index = random.nextInt(jsonArray.length());
                    textViews[i].setText(jsonArray.getJSONObject(index).getString("furigana"));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public void clickTextViewChoiceOne(View view) {
        System.out.println("CLICKED ONE");
        if(textViews[0].getText().toString().equals(answer)){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            kanjiChanger();
        }
    }

    public void clickTextViewChoiceTwo(View view) {
        System.out.println("CLICKED TWO");
        if (textViews[1].getText().toString().equals(answer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            kanjiChanger();
        }
    }
    public void clickTextViewChoiceThree(View view) {
        System.out.println("CLICKED THREE");
        if (textViews[2].getText().toString().equals(answer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            kanjiChanger();
        }
    }
    public void clickTextViewChoiceFour(View view) {
        System.out.println("CLICKED FOUR");
        if (textViews[3].getText().toString().equals(answer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            kanjiChanger();
        }
    }
    public void clickTextViewChoiceFive(View view) {
        System.out.println("CLICKED FIVE");
        if (textViews[4].getText().toString().equals(answer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            kanjiChanger();
        }
    }
}
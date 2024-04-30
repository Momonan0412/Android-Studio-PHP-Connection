package com.example.quizapplication;

import android.os.AsyncTask;

import com.example.quizapplication.record.DatabaseConfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ScrapperTask extends AsyncTask<Void, Void, Void> {
    private String url;
    private Document document;
    private String cssQueryForKanji = ".character-grid";
    private DatabaseUtilities databaseUtilities;

    public ScrapperTask(DatabaseUtilities databaseUtilities) {
        this.databaseUtilities = databaseUtilities;
        this.url = "https://www.wanikani.com/kanji?difficulty=pleasant";
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            document = Jsoup.connect(url).get();
            Elements kanjiData = document.select(cssQueryForKanji);
            for (Element k : kanjiData) {
                String level = k.select(".character-grid__header-text").text();
                System.out.println(level);
                Elements dataElements = k.select(".subject-character__content");
                for (Element data : dataElements) {
                    String kanji = data.select(".subject-character__characters").text();
                    String furigana = data.select(".subject-character__reading").text();
                    String english = data.select(".subject-character__meaning").text();
                    System.out.println("Kanji: " + kanji);
                    System.out.println("Furigana: " + furigana);
                    System.out.println("English: " + english);
                    databaseUtilities.insertJapaneseKanjiData(level, kanji, furigana, english);
                    System.out.println("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


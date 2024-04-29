package com.example.quizapplication.record;

public class DatabaseConfig {
    private String createTableURL;
    private String insertJapaneseKanjiDataURL;
    private String insertNewUsersURL;
    private String checkIfUserExistURL;

    private DatabaseConfig(String createTableURL, String insertJapaneseKanjiDataURL, String insertNewUsersURL, String checkIfUserExistURL) {
        this.createTableURL = createTableURL;
        this.insertJapaneseKanjiDataURL = insertJapaneseKanjiDataURL;
        this.insertNewUsersURL = insertNewUsersURL;
        this.checkIfUserExistURL = checkIfUserExistURL;
    }

    public static DatabaseConfig createWithDefaults() {
        return new DatabaseConfig(
                "http://192.168.254.101/Php-MySQL-Connector/create.table.php",
                "http://192.168.254.101/Php-MySQL-Connector/insert.JP.kanji.data.php",
                "http://192.168.254.101/Php-MySQL-Connector/register.user.php",
                "http://192.168.254.101/Php-MySQL-Connector/user.checker.php"
        );
    }

    public String getCreateTableURL() {
        return createTableURL;
    }

    public String getInsertJapaneseKanjiDataURL() {
        return insertJapaneseKanjiDataURL;
    }

    public String getInsertNewUsersURL() {
        return insertNewUsersURL;
    }

    public String getCheckIfUserExistURL() {
        return checkIfUserExistURL;
    }
}

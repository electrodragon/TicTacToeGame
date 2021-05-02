package com.example.ticktacktoe;

import android.database.sqlite.SQLiteDatabase;

public class HistoryManager {
    public static final String HISTORY_DATABASE = "hdb";
    private final String HISTORY_RECORD_TABLE = "record";

    private final String id = "id";
    private final String game_over_status = "game_over_status";
    private final String foot_steps = "foot_steps";

    private final SQLiteDatabase historyDatabase;

    private HistoryManager(SQLiteDatabase historyDatabase) {
        this.historyDatabase = historyDatabase;
    }

    private void createHistoryRecordTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + HISTORY_RECORD_TABLE + "(" +
                   id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                   game_over_status + " VARCHAR(5) NOT NULL," +
                   foot_steps + " VARCHAR(120) NOT NULL" +
                ")";
        historyDatabase.execSQL(query);
    }

    public void saveGameOverHistory(GameOver gameOver) {
        String query = "INSERT INTO " + HISTORY_RECORD_TABLE +
                "(" + game_over_status + "," + foot_steps + ")" +
                "VALUES ('"+gameOver.status+"', '"+gameOver.footSteps+"')";
        historyDatabase.execSQL(query);
    }

    public static HistoryManager buildWith(SQLiteDatabase historyDatabase) {
        HistoryManager historyManager = new HistoryManager(historyDatabase);
        historyManager.createHistoryRecordTable();
        return historyManager;
    }
}

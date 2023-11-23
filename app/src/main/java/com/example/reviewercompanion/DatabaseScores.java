package com.example.reviewercompanion;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseScores extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "ScoreHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_score";
    private static final String COLUMN_ID = "score_id";
    private static final String COLUMN_CATEGORY = "_category";
    private static final String COLUMN_SCORE = "_score";
    private static final String COLUMN_DATE = "_date";


    public DatabaseScores(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY + " TEXT, "
                + COLUMN_SCORE + " TEXT, "
                + COLUMN_DATE + " TEXT); ";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addScore(String score, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY, score);
        cv.put(COLUMN_SCORE, score);
        cv.put(COLUMN_DATE, date);

        db.insert(TABLE_NAME, null, cv);
    }
    @SuppressLint("Recycle")
    public ArrayList<DatabaseVariable> getAllScore() {
        SQLiteDatabase getDB = this.getReadableDatabase();

        Cursor cursor = getDB.rawQuery(" SELECT * FROM " + TABLE_NAME, null);

        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {

            DatabaseVariable _myDatabaseVariableHolder = new DatabaseVariable();
            _myDatabaseVariableHolder.category_score = cursor.getString(1);
            _myDatabaseVariableHolder.score = cursor.getString(2);
            _myDatabaseVariableHolder.date = cursor.getString(3);

            arrayList.add(_myDatabaseVariableHolder);
        }
        return arrayList;
    }
}
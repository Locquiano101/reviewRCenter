package com.example.reviewercompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseQuestions extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_quiz";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_SUBJECT = "subject";
    static final String COLUMN_QUESTION = "question";
    static final String COLUMN_CHOICE_A = "choice_a";
    static final String COLUMN_CHOICE_B = "choice_b";
    static final String COLUMN_CHOICE_C = "choice_c";
    static final String COLUMN_CHOICE_D = "choice_d";
    static final String COLUMN_ANSWERS = "answer";

    public DatabaseQuestions(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME
                        + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_SUBJECT + " TEXT, "
                        + COLUMN_QUESTION + " TEXT, "
                        + COLUMN_CHOICE_A + " TEXT, "
                        + COLUMN_CHOICE_B + " TEXT, "
                        + COLUMN_CHOICE_C + " TEXT, "
                        + COLUMN_CHOICE_D + " TEXT, "
                        + COLUMN_ANSWERS + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertQuestion(String subject, String question, String choiceA, String choiceB, String choiceC, String choiceD, String answersAns) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_QUESTION, question);
        cv.put(COLUMN_CHOICE_A, choiceA);
        cv.put(COLUMN_CHOICE_B, choiceB);
        cv.put(COLUMN_CHOICE_C, choiceC);
        cv.put(COLUMN_CHOICE_D, choiceD);
        cv.put(COLUMN_ANSWERS, answersAns);

        db.insert(TABLE_NAME, null, cv);
    }


    public void FetchQuestionsByCategory(String category, int limit) {
        SQLiteDatabase getDB = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE subject = ? LIMIT ?";

        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();
        String[] selectionArgs = new String[]{category, String.valueOf(limit)};

        Cursor cursor = getDB.rawQuery(query, selectionArgs);

        while (cursor.moveToNext()) {
            DatabaseVariable _myDatabaseVariableHolder = new DatabaseVariable();
            _myDatabaseVariableHolder.subject = cursor.getString(1);
            _myDatabaseVariableHolder.question = cursor.getString(2);
            _myDatabaseVariableHolder.choice_1 = cursor.getString(3);
            _myDatabaseVariableHolder.choice_2 = cursor.getString(4);
            _myDatabaseVariableHolder.choice_3 = cursor.getString(5);
            _myDatabaseVariableHolder.choice_4 = cursor.getString(6);
            _myDatabaseVariableHolder.answer = cursor.getString(7);

            arrayList.add(_myDatabaseVariableHolder);
        }

        // Check if arrayList is not empty before logging
        if (!arrayList.isEmpty()) {
            DatabaseVariable lastQuestion = arrayList.get(arrayList.size() - 1);
            for (DatabaseVariable question : arrayList) {
                Log.d("FetchQuestionsByCategory", "Added question: " + question.question);
            }
        }

        cursor.close(); // Close the cursor when finished using it
    }



    public ArrayList<DatabaseVariable> FetchAll() {
        SQLiteDatabase getDB = this.getReadableDatabase();
        Cursor cursor = getDB.rawQuery(" SELECT * FROM " + TABLE_NAME, null);

        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            DatabaseVariable databaseVariable = new DatabaseVariable();
            databaseVariable.subject = cursor.getString(1);
            databaseVariable.question = cursor.getString(2);
            databaseVariable.choice_1 = cursor.getString(3);
            databaseVariable.choice_2 = cursor.getString(4);
            databaseVariable.choice_3 = cursor.getString(5);
            databaseVariable.choice_4 = cursor.getString(6);
            databaseVariable.answer = cursor.getString(7);

            arrayList.add(databaseVariable);
        }
        cursor.close(); // Close the cursor when finished using it
        return arrayList;
    }

    public boolean isTableEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM " + TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count == 0;
        }
        return true;
    }
}

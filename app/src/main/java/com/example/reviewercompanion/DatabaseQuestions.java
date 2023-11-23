package com.example.reviewercompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseQuestions extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "QuizHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_quiz";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_SUBJECT = "subject";
    static final String COLUMN_SUB_TOPIC = "sub_topic";
    static final String COLUMN_QUESTION = "question";
    static final String COLUMN_CHOICE_A = "choice_a";
    static final String COLUMN_CHOICE_B = "choice_b";
    static final String COLUMN_CHOICE_C = "choice_c";
    static final String COLUMN_CHOICE_D = "choice_d";
    static final String COLUMN_ANSWERS = "answer";

    public DatabaseQuestions(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME
                        + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_SUBJECT + " TEXT, "
                        + COLUMN_SUB_TOPIC + " TEXT, "
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

    void insertQuestion(String subject, String subTopic, String question, String choiceA, String choiceB, String choiceC, String choiceD, String answersAns) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_SUB_TOPIC, subTopic);
        cv.put(COLUMN_QUESTION, question);
        cv.put(COLUMN_CHOICE_A, choiceA);
        cv.put(COLUMN_CHOICE_B, choiceB);
        cv.put(COLUMN_CHOICE_C, choiceC);
        cv.put(COLUMN_CHOICE_D, choiceD);
        cv.put(COLUMN_ANSWERS, answersAns);

        db.insert(TABLE_NAME, null, cv);

    }

    public ArrayList<DatabaseVariable> FetchQuestions() {
        SQLiteDatabase getDB = this.getReadableDatabase();
        Cursor cursor = getDB.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {

            DatabaseVariable _myDatabaseVariableHolder = new DatabaseVariable();
            _myDatabaseVariableHolder.subject = cursor.getString(1);
            _myDatabaseVariableHolder.sub_topic = cursor.getString(2);
            _myDatabaseVariableHolder.question = cursor.getString(3);
            _myDatabaseVariableHolder.choice_1 = cursor.getString(4);
            _myDatabaseVariableHolder.choice_2 = cursor.getString(5);
            _myDatabaseVariableHolder.choice_3 = cursor.getString(6);
            _myDatabaseVariableHolder.choice_4 = cursor.getString(7);
            _myDatabaseVariableHolder.answer = cursor.getString(8);

            arrayList.add(_myDatabaseVariableHolder);
        }
        return arrayList;
    }

    public ArrayList<DatabaseVariable> FetchQuestionsCategory(String category) {
        SQLiteDatabase getDB = this.getReadableDatabase();

        Cursor cursor = getDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE categoryQuiz = ?", new String[]{category});

        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            DatabaseVariable _databaseVariableHolder = new DatabaseVariable();
            _databaseVariableHolder.subject = cursor.getString(1);
            _databaseVariableHolder.sub_topic = cursor.getString(2);
            _databaseVariableHolder.question = cursor.getString(3);
            _databaseVariableHolder.choice_1 = cursor.getString(4);
            _databaseVariableHolder.choice_2 = cursor.getString(5);
            _databaseVariableHolder.choice_3 = cursor.getString(6);
            _databaseVariableHolder.choice_4 = cursor.getString(7);
            _databaseVariableHolder.answer = cursor.getString(8);
            arrayList.add(_databaseVariableHolder);
        }
        cursor.close();
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
   /* public boolean insertIfNotExists(String tableName, String columnToCheck, String valueToInsert, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if the value already exists in the table
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnToCheck + " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, valueToInsert);
        long count = statement.simpleQueryForLong();

        if (count == 0) {
            // Value doesn't exist, proceed with insertion
            long rowId = db.insert(tableName, null, values);
            if (rowId != -1) {
                // Insertion successful
                return true;
            } else {
                // Insertion failed
                // Handle failure scenario
                return false;
            }
        } else {
            // Value already exists, handle duplication scenario
            // You can show an error message or take appropriate action
            return false;
        }
    }*/
}

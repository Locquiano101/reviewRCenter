package com.example.reviewercompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseQuestions extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "QuizHolder.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_quiz";
    private static final String COLUMN_ID = "_id";
    static final String COLUMN_CATEGORY = "category";
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
                        + COLUMN_CATEGORY + " TEXT, "
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

    public ArrayList<DatabaseVariable> FetchQuestions() {
        SQLiteDatabase getDB = this.getReadableDatabase();
        Cursor cursor = getDB.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {

            DatabaseVariable _myDatabaseVariableHolder = new DatabaseVariable();
            _myDatabaseVariableHolder.categoryQuiz = cursor.getString(1);
            _myDatabaseVariableHolder.question = cursor.getString(2);
            _myDatabaseVariableHolder.choice_1 = cursor.getString(3);
            _myDatabaseVariableHolder.choice_2 = cursor.getString(4);
            _myDatabaseVariableHolder.choice_3 = cursor.getString(5);
            _myDatabaseVariableHolder.choice_4 = cursor.getString(6);
            _myDatabaseVariableHolder.answer = cursor.getString(7);

            arrayList.add(_myDatabaseVariableHolder);
        }
        return arrayList;
    }
    public ArrayList<DatabaseVariable> FetchQuestionsCategory(String category) {
        SQLiteDatabase getDB = this.getReadableDatabase();

        // Modify the query to fetch specific questions based on category
        Cursor cursor = getDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE categoryQuiz = ?", new String[]{category});

        ArrayList<DatabaseVariable> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            DatabaseVariable _myDatabaseVariableHolder = new DatabaseVariable();
            _myDatabaseVariableHolder.categoryQuiz = cursor.getString(1);
            _myDatabaseVariableHolder.question = cursor.getString(2);
            _myDatabaseVariableHolder.choice_1 = cursor.getString(3);
            _myDatabaseVariableHolder.choice_2 = cursor.getString(4);
            _myDatabaseVariableHolder.choice_3 = cursor.getString(5);
            _myDatabaseVariableHolder.choice_4 = cursor.getString(6);
            _myDatabaseVariableHolder.answer = cursor.getString(7);

            arrayList.add(_myDatabaseVariableHolder);
        }
        cursor.close(); // Close the cursor when done
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

        return true; // Assuming an error means the table is empty
    }
}

package lima.r0bertson.uwlgymlog;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r0bertson on 09/12/2015.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    //REFERENCE FOR THIS CLASS: http://www.techotopia.com/index.php/An_Android_Studio_SQLite_Database_Tutorial

    private static final String ROUTINE_TABLE = "routineExercises";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "uwlgym";
    private static final String TABLE_EXERCISES = "exercises";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOAD = "load";
    public static final String COLUMN_REPETITIONS = "repetitions";
    public static final String COLUMN_DATE= "date";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXERCISES_TABLE = "CREATE TABLE " + TABLE_EXERCISES + "("  + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_LOAD + " INTEGER,"+ COLUMN_REPETITIONS + " INTEGER," + COLUMN_DATE + " DATE)";
        db.execSQL(CREATE_EXERCISES_TABLE);
        String CREATE_ROUTINE_TABLE = "CREATE TABLE " + ROUTINE_TABLE + "(" + COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_ROUTINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        //THIS PIECE OF CODE IS DELETING OUR DATABASE ON EVERY UPGRADE
        //NEED TO TO SOMETHING ABOUT IT
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE);
        onCreate(db);
    }

    public void addExerciseOnRoutine( String ex_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ex_name);
        db.insert(ROUTINE_TABLE, null, values);
        db.close();
    }

    public void addExercise(Exercise ex) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ex.get_name());
        values.put(COLUMN_LOAD, ex.get_load());
        values.put(COLUMN_REPETITIONS, ex.get_repetitions());
        values.put(COLUMN_DATE, ex.get_date());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    public boolean deleteExercise(String ex_name) {

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_EXERCISES + " WHERE " + COLUMN_NAME + " = \'" + ex_name + "\'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Exercise ex = new Exercise();

        if (cursor.moveToFirst()) {
            ex.set_id(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_EXERCISES, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(ex.get_id()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean exerciseNameIsValid(String ex_name){
        String query = "SELECT * FROM " + ROUTINE_TABLE + " WHERE " + COLUMN_NAME + " = \'" + ex_name + "\'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0){

            System.out.println("exerciseNameIsValid: Returned true");
            return true;
        }
        else{
            System.out.println("exerciseNameIsValid: Returned false");
           // System.out.println(cursor.getString(1));
            return false;

        }
    }

    public List<String> getAllExercises(){
        String selectQuery = "SELECT  * FROM " + ROUTINE_TABLE;
        List<String> exercises = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                exercises.add(cursor.getString(0));
                System.out.println(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning exercises
        return exercises;
    }
}

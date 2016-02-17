package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import DAL.FeedElectroManContract.*;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by surfing on 2/16/2016.
 */
public class InitializeElectroMan {

    private SQLiteDatabase db;
    private ElectroManDbHelper dbHelper;



    public InitializeElectroMan(Context context) {
        dbHelper = new ElectroManDbHelper(context);
        db = dbHelper.getWritableDatabase();
        FeedProblems();
    }

    private void FeedProblems(){
        dbHelper.insertProblem("problem1", "title problem 1", "description problem 1");
        dbHelper.insertProblem("problem2", "title problem 2", "description problem 2");
        dbHelper.insertProblem("problem3", "title problem 3", "description problem 3");
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public ElectroManDbHelper getDbHelper() {
        return dbHelper;
    }
}

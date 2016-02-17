package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import DAL.FeedElectroManContract.*;
import model.Problem;

import java.util.ArrayList;

/**
 * Created by surfing on 2/16/2016.
 */
public class ElectroManDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ElectroMan.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_PROBLEMS =
            "CREATE TABLE " + FeedProblem.TABLE_PROBLEM + " (" +
                    FeedProblem._ID + " INTEGER PRIMARY KEY," +
                    FeedProblem.COLUMN_PROBLEM_ID + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_DESCRIPTION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_PROBLEMS = "DROP TABLE IF EXISTS " + FeedProblem.TABLE_PROBLEM;

    private ArrayList<Problem> problems;

    public ElectroManDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROBLEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_PROBLEMS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean insertProblem(String problemId, String problemTitle, String problemDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedProblem.COLUMN_PROBLEM_ID, problemId);
        values.put(FeedProblem.COLUMN_PROBLEM_TITLE, problemTitle);
        values.put(FeedProblem.COLUMN_PROBLEM_DESCRIPTION, problemDescription);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedProblem.TABLE_PROBLEM,
                FeedElectroManContract.COLUMN_NAME_NULLABLE,
                values);
        return true;
    }

    public void setProblems()
    {
        problems = new ArrayList<Problem>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedProblem.TABLE_PROBLEM, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            problems.add(new Problem(res.getString(res.getColumnIndex(FeedProblem._ID)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_ID)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_TITLE)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_DESCRIPTION))));
            res.moveToNext();
        }
    }
    public ArrayList<Problem> getAllProblems()
    {
        if (problems == null) setProblems();
        return problems;
    }

    public Problem findProblemNyId(int id){
        if (problems == null) setProblems();
        Problem p = null;
        for(Problem problem : problems)
        {
            if (Integer.parseInt(problem.getId()) == id) p = problem;
        }
        return p;
    }
}

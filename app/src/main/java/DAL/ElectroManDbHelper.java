package DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import DAL.FeedElectroManContract.*;
import android.widget.ArrayAdapter;
import model.Address;
import model.Client;
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
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_PROBLEMS =
            "CREATE TABLE " + FeedProblem.TABLE_PROBLEM + " (" +
                    FeedProblem._ID + " INTEGER PRIMARY KEY," +
                    FeedProblem.COLUMN_PROBLEM_ID + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_DEVICE + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_SOLUTION + TEXT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_SOLVED + INT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_CLIENT + INT_TYPE + COMMA_SEP +
                    FeedProblem.COLUMN_PROBLEM_CLIENT_ADDRESS + INT_TYPE + " )";

    private static final String SQL_CREATE_ADDRESSES =
            "CREATE TABLE " + FeedAddress.TABLE_ADDRESS + " (" +
                    FeedAddress._ID + " INTEGER PRIMARY KEY," +
                    FeedAddress.COLUMN_ADDRESS_ID + TEXT_TYPE + COMMA_SEP +
                    FeedAddress.COLUMN_ADDRESS_STREET + TEXT_TYPE + COMMA_SEP +
                    FeedAddress.COLUMN_ADDRESS_BOX + TEXT_TYPE + COMMA_SEP +
                    FeedAddress.COLUMN_ADDRESS_POST_CODE + TEXT_TYPE + COMMA_SEP +
                    FeedAddress.COLUMN_ADDRESS_CITY + TEXT_TYPE + COMMA_SEP +
                    FeedAddress.COLUMN_ADDRESS_COUNTRY + TEXT_TYPE + " )";

    private static final String SQL_CREATE_CLIENTS =
            "CREATE TABLE " + FeedClient.TABLE_CLIENT + " (" +
                    FeedClient._ID + " INTEGER PRIMARY KEY," +
                    FeedClient.COLUMN_CLIENT_ID + TEXT_TYPE + COMMA_SEP +
                    FeedClient.COLUMN_CLIENT_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedClient.COLUMN_CLIENT_FIRST_NAME + TEXT_TYPE + " )";

    private static final String SQL_CREATE_CLIENT_ADDRESS =
            "CREATE TABLE " + FeedClientAddress.TABLE_CLIENT_ADDRESS + " (" +
                    FeedClientAddress._ID + " INTEGER PRIMARY KEY, " +
                    FeedClientAddress.COLUMN_CLIENT_ADDRESS_CLIENT_ID + INT_TYPE + COMMA_SEP +
                    FeedClientAddress.COLUMN_CLIENT_ADDRESS_ADDRESS_ID + INT_TYPE + ")";


    private static final String SQL_DELETE_PROBLEMS = "DROP TABLE IF EXISTS " + FeedProblem.TABLE_PROBLEM;
    private static final String SQL_DELETE_ADDRESSES = "DROP TABLE IF EXISTS " + FeedAddress.TABLE_ADDRESS;
    private static final String SQL_DELETE_CLIENTS = "DROP TABLE IF EXISTS " + FeedClient.TABLE_CLIENT;
    private static final String SQL_DELETE_CLIENT_ADDRESS = "DROP TABLE IF EXISTS " + FeedClientAddress.TABLE_CLIENT_ADDRESS;

    private ArrayList<Problem> problems = null;
    private ArrayList<Address> addresses = null;
    private ArrayList<Client> clients = null;

    public ElectroManDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROBLEMS);
        db.execSQL(SQL_CREATE_ADDRESSES);
        db.execSQL(SQL_CREATE_CLIENTS);
        db.execSQL(SQL_CREATE_CLIENT_ADDRESS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_PROBLEMS);
        db.execSQL(SQL_DELETE_CLIENT_ADDRESS);
        db.execSQL(SQL_DELETE_ADDRESSES);
        db.execSQL(SQL_DELETE_CLIENTS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public long insertProblem(String problemId, String problemTitle, String problemDevice, String problemDescription,
                              String problemSolution, Boolean problemSolved, int clientId, int addressId){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedProblem.COLUMN_PROBLEM_ID, problemId);
        values.put(FeedProblem.COLUMN_PROBLEM_TITLE, problemTitle);
        values.put(FeedProblem.COLUMN_PROBLEM_DEVICE, problemDevice);
        values.put(FeedProblem.COLUMN_PROBLEM_DESCRIPTION, problemDescription);
        values.put(FeedProblem.COLUMN_PROBLEM_SOLUTION, problemSolution);
        values.put(FeedProblem.COLUMN_PROBLEM_SOLVED, problemSolved? "0" : "1");
        values.put(FeedProblem.COLUMN_PROBLEM_CLIENT, clientId);
        values.put(FeedProblem.COLUMN_PROBLEM_CLIENT_ADDRESS, addressId);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedProblem.TABLE_PROBLEM,
                FeedElectroManContract.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public long insertAddress(String addressId, String addressStreet, String addressBox, String addressPostCode, String addressCity, String addressContry){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedAddress.COLUMN_ADDRESS_ID, addressId);
        values.put(FeedAddress.COLUMN_ADDRESS_STREET, addressStreet);
        values.put(FeedAddress.COLUMN_ADDRESS_BOX, addressBox);
        values.put(FeedAddress.COLUMN_ADDRESS_POST_CODE, addressPostCode);
        values.put(FeedAddress.COLUMN_ADDRESS_CITY, addressCity);
        values.put(FeedAddress.COLUMN_ADDRESS_COUNTRY, addressContry);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedAddress.TABLE_ADDRESS,
                FeedElectroManContract.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public long insertClient(String clientId, String clientName, String clientFirstName){
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedClient.COLUMN_CLIENT_ID, clientId);
        values.put(FeedClient.COLUMN_CLIENT_NAME, clientName);
        values.put(FeedClient.COLUMN_CLIENT_FIRST_NAME, clientFirstName);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedClient.TABLE_CLIENT,
                FeedElectroManContract.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public void setProblems()
    {
        problems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedProblem.TABLE_PROBLEM, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            problems.add(new Problem(res.getString(res.getColumnIndex(FeedProblem._ID)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_ID)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_TITLE)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_DEVICE)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_DESCRIPTION)),
                    res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_SOLUTION)) == null? "" : res.getString(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_SOLUTION)),
                    res.getInt(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_SOLVED)) == 0? true :false,
                    this.findClientById(res.getInt(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_CLIENT))),
                    this.findAddressById(res.getInt(res.getColumnIndex(FeedProblem.COLUMN_PROBLEM_CLIENT_ADDRESS)))));
            res.moveToNext();
        }
    }

    public void setAddresses()
    {
        addresses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedAddress.TABLE_ADDRESS, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            addresses.add(new Address(res.getString(res.getColumnIndex(FeedAddress._ID)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_ID)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_STREET)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_BOX)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_POST_CODE)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_CITY)),
                    res.getString(res.getColumnIndex(FeedAddress.COLUMN_ADDRESS_COUNTRY))));
            res.moveToNext();
        }
    }

    public void setClients(){
        clients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedClient.TABLE_CLIENT, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            clients.add(new Client(res.getString(res.getColumnIndex(FeedClient._ID)),
                    res.getString(res.getColumnIndex(FeedClient.COLUMN_CLIENT_ID)),
                    res.getString(res.getColumnIndex(FeedClient.COLUMN_CLIENT_NAME)),
                    res.getString(res.getColumnIndex(FeedClient.COLUMN_CLIENT_FIRST_NAME))));
            res.moveToNext();
        }
    }

    public ArrayList<Problem> getAllProblems()
    {
        ArrayList<Problem> p = problems;
        if ((problems == null) || problems.isEmpty()) setProblems();
        setProblems();
        return problems;
    }

    public ArrayList<Address> getAllAddresses()
    {
        if ((addresses == null) || addresses.isEmpty()) setAddresses();
        return addresses;
    }

    public ArrayList<Client> getAllClients(){
        if((clients == null) || clients.isEmpty()) setClients();
        return clients;
    }

    public Problem findProblemById(long id){
        if ((problems == null) || problems.isEmpty()) setProblems();
        Problem p = null;
        for(Problem problem : problems)
        {
            if (Integer.parseInt(problem.getId()) == id) p = problem;
        }
        return p;
    }

    public Address findAddressById(long id){
        if((addresses == null) || addresses.isEmpty()) setAddresses();
        Address a = null;
        for(Address address : addresses){
            if(Integer.parseInt(address.getId()) == id) a = address;
        }
        return a;
    }

    public Client findClientById(long id){
        if((clients == null) || clients.isEmpty()) setClients();
        Client c = null;
        for(Client client : clients){
            if(Integer.parseInt(client.getId()) == id) c = client;
        }
        return c;
    }

    public boolean problemInitialized()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedProblem.TABLE_PROBLEM, null );
        return !(res.getCount() == 0);
    }

    public boolean clientInitialized()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedClient.TABLE_CLIENT, null );
        return !(res.getCount() == 0);
    }

    public boolean addressInitialized()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + FeedAddress.TABLE_ADDRESS, null );
        return !(res.getCount() == 0);
    }

    public void updateProblem(Problem problem, String problemSolution){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues data=new ContentValues();
        data.put(FeedProblem.COLUMN_PROBLEM_SOLUTION, problemSolution);
        data.put(FeedProblem.COLUMN_PROBLEM_SOLVED, "0");
        db.update(FeedProblem.TABLE_PROBLEM, data, FeedProblem._ID + "=" + problem.getId(), null);

//        String solutionSQL = "UPDATE " +FeedProblem.TABLE_PROBLEM +
//                " SET " + FeedProblem.COLUMN_PROBLEM_SOLUTION + " = " + problemSolution +
//                " WHERE " + FeedProblem._ID + " = "+ problem.getId();
//
//        String solvedSQL = "UPDATE " +FeedProblem.TABLE_PROBLEM +
//                " SET " + FeedProblem.COLUMN_PROBLEM_SOLVED + " = 1 " +
//                " WHERE " + FeedProblem._ID + " = "+ problem.getId();
//
//        db.execSQL(solutionSQL);
    }
}

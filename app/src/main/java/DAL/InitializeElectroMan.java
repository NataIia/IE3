package DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by surfing on 2/16/2016.
 */
public class InitializeElectroMan {

    private SQLiteDatabase db;
    private ElectroManDbHelper dbHelper;

    private int address1, address2, address3, client1, client2, client3;


    public InitializeElectroMan(Context context) {
        dbHelper = new ElectroManDbHelper(context);
        db = dbHelper.getWritableDatabase();

        FeedAddresses();
        FeedClients();
        FeedProblems();
    }

    private void FeedProblems(){
        dbHelper.insertProblem("problem1", "title problem 1", "device1", "description problem 1", null, false, client1, address1);
        dbHelper.insertProblem("problem2", "title problem 2", "device2", "description problem 2", null, false, client2, address2);
        dbHelper.insertProblem("problem3", "title problem 3", "device3", "description problem 3", null, false, client3, address3);
    }

    private void FeedAddresses(){
        address1 = (int) dbHelper.insertAddress("address1", "Brederodestraat", "16", "B-1000", "Brussel", "Belgium");
        address2 = (int) dbHelper.insertAddress("address2", "Minderbroedersstraat", "10", "3000", "Leuven", "Belgium");
        address3 = (int) dbHelper.insertAddress("address3", "Grote Markt", "1", "2000", "Antwerpen", "Belgium");
    }

    private void FeedClients(){
        client1 = (int) dbHelper.insertClient("client1", "clientName1", "clientFirstName1");
        client2 = (int) dbHelper.insertClient("client2", "clientName2", "clientFirstName2");
        client3 = (int) dbHelper.insertClient("client3", "clientName3", "clientFirstName3");
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public ElectroManDbHelper getDbHelper() {
        return dbHelper;
    }
}

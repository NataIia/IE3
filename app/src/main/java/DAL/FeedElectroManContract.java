package DAL;

import android.provider.BaseColumns;

/**
 * Created by surfing on 2/16/2016.
 */

//A contract class is a container for constants that define names for URIs, tables, and columns.
public final class FeedElectroManContract {

    public static final String COLUMN_NAME_NULLABLE = "null";

    /* To prevent someone from accidentally instantiating the contract class, give it an empty constructor.*/
    public FeedElectroManContract() {
    }

    /*  An inner class for each table that enumerates its columns. */
    public static abstract class FeedProblem implements BaseColumns {
        public static final String TABLE_PROBLEM = "tbl_problem";
        public static final String COLUMN_PROBLEM_ID = "problemId";
        public static final String COLUMN_PROBLEM_TITLE = "problemTitle";
        public static final String COLUMN_PROBLEM_DEVICE = "problemDevice";
        public static final String COLUMN_PROBLEM_DESCRIPTION = "problemDescription";
        public static final String COLUMN_PROBLEM_SOLVED = "problemIsSolved";
        public static final String COLUMN_PROBLEM_SOLUTION = "problemSolution";
        public static final String COLUMN_PROBLEM_CLIENT = "fk_cliendId";
        public static final String COLUMN_PROBLEM_CLIENT_ADDRESS = "fk_problemClientAddressId";
    }

    public static abstract class FeedClient implements BaseColumns{
        public static final String TABLE_CLIENT="tbl_client";
        public static final String COLUMN_CLIENT_ID = "clientId";
        public static final String COLUMN_CLIENT_NAME = "clientName";
        public static final String COLUMN_CLIENT_FIRST_NAME = "clientFirstName";
    }

    public static abstract class FeedAddress implements BaseColumns{
        public static final String TABLE_ADDRESS="tbl_address";
        public static final String COLUMN_ADDRESS_ID = "addressId";
        public static final String COLUMN_ADDRESS_STREET = "addressStreet";
        public static final String COLUMN_ADDRESS_BOX = "addressBox";
        public static final String COLUMN_ADDRESS_POST_CODE = "addressPostCode";
        public static final String COLUMN_ADDRESS_CITY = "addressCity";
        public static final String COLUMN_ADDRESS_COUNTRY = "addressCountry";

    }

    public static abstract class FeedClientAddress implements BaseColumns{
        public static final String TABLE_CLIENT_ADDRESS="tbl_client_address";
        public static final String COLUMN_CLIENT_ADDRESS_CLIENT_ID = "fk_cliendId";
        public static final String COLUMN_CLIENT_ADDRESS_ADDRESS_ID = "fk_addressId";
    }
}

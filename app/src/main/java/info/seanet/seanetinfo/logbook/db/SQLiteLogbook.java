package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 10/11/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLiteLogbook extends SQLiteOpenHelper {
    private static final String TAG = SQLiteLogbook.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "seanet_info_logbook";

    // Owner Table Columns names
    //
    private static final String TABLE_OWNER = "owner";
    private static final String KEY_OWNER_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    String CREATE_OWNER_TABLE = "CREATE TABLE " + TABLE_OWNER + "("
            + KEY_OWNER_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT UNIQUE," + KEY_ADDRESS +  ")";

    //Logbook Table
    private static final String TABLE_LOGBOOK = "logbook";
    private static final String KEY_LOGBOOK_ID = "id";
    private static final String KEY_DESC = "name";
    private static final String KEY_BOAT = "boat_name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_REG_NR = "reg_nr";
    private static final String KEY_FRA_NR = "fra_nr";
    private static final String KEY_INS_POL = "ins_pol";
    private static final String KEY_INS_CIE = "ins_cie";
    private static final String KEY_CAP_EMAIL = "email";
    private static final String KEY_HARBOUR = "harbour";
    private static final String KEY_RADIO_CALL = "callsign";
    private static final String KEY_LENGTH = "length";
    private static final String KEY_BEAM = "beam";
    private static final String KEY_DRAUGHT = "draught";
    private static final String KEY_TONNAGE = "tonnage";
    private static final String KEY_OWNER_BOAT = "owner";
    String CREATE_LOGBOOK_TABLE = "CREATE TABLE " + TABLE_LOGBOOK + "("
            + KEY_LOGBOOK_ID + " INTEGER PRIMARY KEY,"
            + KEY_DESC + " TEXT UNIQUE,"
            + KEY_BOAT + " TEXT,"
            + KEY_PHONE + " TEXT,"
            + KEY_REG_NR + " TEXT,"
            + KEY_FRA_NR + " TEXT,"
            + KEY_INS_POL + " TEXT,"
            + KEY_INS_CIE + " TEXT,"
            + KEY_CAP_EMAIL + " TEXT,"
            + KEY_HARBOUR + " TEXT,"
            + KEY_RADIO_CALL + " TEXT,"
            + KEY_LENGTH + " TEXT,"
            + KEY_BEAM + " TEXT,"
            + KEY_DRAUGHT  + " TEXT,"
            + KEY_TONNAGE + " TEXT,"
            + KEY_OWNER_BOAT + " INTEGER"
            +  ")";

    public SQLiteLogbook(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Owner table
        db.execSQL(CREATE_OWNER_TABLE);
        db.execSQL(CREATE_LOGBOOK_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older owner table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGBOOK);

        // Create tables again
        onCreate(db);
    }

    /***********************************************************************************************
     *                                    OWNER TABLE
     ******************************************************************************************** */
    /**
     * Adding owner data from database
     * */
    public void addOwner(String name, String email, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_ADDRESS, address); // Postal address

        // Inserting Row
        long id = db.insert(TABLE_OWNER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New owner inserted into sqlite: " + id);
    }

    /**
     * Getting owner data from database
     * */
    public String[] getOwnerDetails(String name) {
        ArrayList<String> owner= new ArrayList<String>();
        String selectQuery;
        switch  (name) {
            case "--all--":
                selectQuery = "SELECT * FROM " + TABLE_OWNER;
                break;
            default:
                selectQuery = "SELECT  * FROM " + TABLE_OWNER + " where " + KEY_NAME + " = " + name;
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            owner.add(cursor.getString(0));
            owner.add(cursor.getString(1));
            owner.add(cursor.getString(2));
            owner.add(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-Fetching owner from Sqlite: " + owner.toString());

        String [] ownerList = new String [owner.size()];
        ownerList=owner.toArray(ownerList);

        return ownerList;
    }
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteOwner () {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_OWNER, null, null);
        db.close();

        Log.d(TAG, "Deleted all owners info from sqlite");
    }


    /***********************************************************************************************
     *                                    LOGBOOK TABLE
     ******************************************************************************************** */
    /**
     * Storing Logbook details in database
     * */
    public void addLogbook(String nameLog, String boat, String phone, String reg_nr, String fra_nr,
                            String ins_pol, String ins_cie, String cap_email, String habour,
                            String callsign, String length, String beam, String draught, String tonnage,
                            Integer owner_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESC, nameLog);
        values.put(KEY_BOAT, boat);
        values.put(KEY_PHONE, phone);
        values.put(KEY_REG_NR, reg_nr);
        values.put(KEY_FRA_NR, fra_nr);
        values.put(KEY_INS_POL, ins_pol);
        values.put(KEY_INS_CIE, ins_cie);
        values.put(KEY_CAP_EMAIL, cap_email);
        values.put(KEY_HARBOUR, habour);
        values.put(KEY_RADIO_CALL, callsign);
        values.put(KEY_LENGTH, length);
        values.put(KEY_BEAM, beam);
        values.put(KEY_DRAUGHT, draught);
        values.put(KEY_TONNAGE, tonnage);
        values.put(KEY_OWNER_BOAT, owner_id);

        // Inserting Row
        long id = db.insert(TABLE_LOGBOOK, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Logbook inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public String[] getLogbookDetails(String name) {
        ArrayList<String> owner= new ArrayList<String>();
        String selectQuery;
        switch  (name) {
            case "--all--":
                selectQuery = "SELECT * FROM " + TABLE_OWNER;
                break;
            default:
                selectQuery = "SELECT  * FROM " + TABLE_OWNER + " where " + KEY_NAME + " = " + name;
                break;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            owner.add(cursor.getString(0));
            owner.add(cursor.getString(1));
            owner.add(cursor.getString(2));
            owner.add(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-Fetching owner from Sqlite: " + owner.toString());

        String [] ownerList = new String [owner.size()];
        ownerList=owner.toArray(ownerList);



        return ownerList;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteLogbook () {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGBOOK, null, null);
        db.close();

        Log.d(TAG, "Deleted all logbooks info from sqlite");
    }


}

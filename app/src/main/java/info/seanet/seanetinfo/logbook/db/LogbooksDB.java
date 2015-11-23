package info.seanet.seanetinfo.logbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 22/11/15.
 */
public class LogbooksDB {
    private static final String TAG = LogbooksDB.class.getSimpleName();

    // All Static variables
    /*********************************************************************************************
                    DATABASE SETTINGS
    ******************************************************************************************* */
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "seanet_info_logbook";

    private SQLiteDatabase db;

    private NewSQLiteLogbook newSQLiteLogbook;

    /* ********************************************************************************************
                    TABLES DEFINITION
    ******************************************************************************************** */
    // KEYS FOR TABLE OWNERS
    private static final String TABLE_OWNER="owner";
    private static final String KEY_OWNER_ID = "id";
    private static final int NUM_KEY_OWNER_ID=0;
    private static final String KEY_OWNER_NAME = "name";
    private static final int NUM_KEY_OWNER_NAME=1;
    private static final String KEY_OWNER_EMAIL = "email";
    private static final int NUM_KEY_OWNER_EMAIL=2;
    private static final String KEY_OWNER_ADDRESS = "address";
    private static final int NUM_KEY_OWNER_ADDRESS=3;

    // KEYS FOR TABLE LOGBOOK
    private static final String TABLE_LOGBOOK = "logbook";
    private static final String KEY_LOGBOOK_ID = "id";
    private static final int NUM_KEY_LOGBOOK_ID = 0;

    private static final String KEY_LOGBOOK_DESC = "name";
    private static final int NUM_KEY_LOGBOOK_DESC = 1;

    private static final String KEY_LOGBOOK_BOAT = "boat_name";
    private static final int NUM_KEY_LOGBOOK_BOAT = 2;

    private static final String KEY_LOGBOOK_PHONE = "phone";
    private static final int NUM_KEY_LOGBOOK_PHONE = 3;

    private static final String KEY_LOGBOOK_REG_NR = "reg_nr";
    private static final int NUM_KEY_LOGBOOK_REG_NR = 4;

    private static final String KEY_LOGBOOK_FRA_NR = "fra_nr";
    private static final int NUM_KEY_LOGBOOK_FRA_NR = 5;

    private static final String KEY_LOGBOOK_INS_POL = "ins_pol";
    private static final int NUM_KEY_LOGBOOK_INS_POL = 6;

    private static final String KEY_LOGBOOK_INS_CIE = "ins_cie";
    private static final int NUM_KEY_LOGBOOK_INS_CIE = 7;

    private static final String KEY_LOGBOOK_CAP_EMAIL = "email";
    private static final int NUM_KEY_LOGBOOK_CAP_EMAIL = 8;

    private static final String KEY_LOGBOOK_HARBOUR = "harbour";
    private static final int NUM_KEY_LOGBOOK_HARBOUR = 9;

    private static final String KEY_LOGBOOK_RADIO_CALL = "callsign";
    private static final int NUM_KEY_LOGBOOK_RADIO_CALL = 10;

    private static final String KEY_LOGBOOK_LENGTH = "length";
    private static final int NUM_KEY_LOGBOOK_LENGTH = 1;

    private static final String KEY_LOGBOOK_BEAM = "beam";
    private static final int NUM_KEY_LOGBOOK_BEAM = 12;

    private static final String KEY_LOGBOOK_DRAUGHT = "draught";
    private static final int NUM_KEY_LOGBOOK_DRAUGHT = 13;

    private static final int KEY_LOGBOOK_TONNAGE = "tonnage";
    private static final String NUM_KEY_LOGBOOK_TONNAGE = 14;

    private static final String KEY_LOGBOOK_OWNER_BOAT = "owner";
    private static final int NUM_KEY_LOGBOOK_OWNER_BOAT = 15;


    //---------------------------------------------------------------------------------------------
    public LogbooksDB(Context context) {
        newSQLiteLogbook=new NewSQLiteLogbook(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open() {
        db=newSQLiteLogbook.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDB(){
        return db;
    }

    /* ********************************************************************************************
                                FUNCTIONS OWNERS TABLE
    ******************************************************************************************* */
    /**
     * Adding owner data from database
     * */
    public void addOwner(String name, String email, String address) {

        ContentValues values = new ContentValues();
        values.put(KEY_OWNER_NAME, name); // Name
        values.put(KEY_OWNER_EMAIL, email); // Email
        values.put(KEY_OWNER_ADDRESS, address); // Postal address

        // Inserting Row
        long id = db.insert(TABLE_OWNER, null, values);

        Log.d(TAG, "New owner inserted into sqlite: " + id);
    }

    /**
     * Getting owner data from database
     * */
    public List<Owners> getOwnerDetails(String name) {
        List<Owners> owners= new ArrayList<>();
        String selectQuery;
        switch  (name) {
            case "--all--":
                selectQuery = "SELECT * FROM " + TABLE_OWNER;
                break;
            default:
                selectQuery = "SELECT  * FROM " + TABLE_OWNER + " where " + KEY_OWNER_NAME + " = " + name;
                break;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            Owners owner=cursorToOwners(cursor);
            owners.add(owner);
            cursor.moveToNext();
        }

        cursor.close();

        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-Fetching owner from Sqlite ");

        return owners;
    }

    private Owners cursorToOwners(Cursor cursor) {
        Owners owners=new Owners();
        owners.setId(cursor.getInt(0));
        owners.setName(cursor.getString(1));
        owners.setEmail(cursor.getString(2));
        owners.setAddress(cursor.getString(3));
        return owners;

    }
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteOwner () {
        // Delete All Rows
        db.delete(TABLE_OWNER, null, null);

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

        ContentValues values = new ContentValues();
        values.put(KEY_LOGBOOK_DESC, nameLog);
        values.put(KEY_LOGBOOK_BOAT, boat);
        values.put(KEY_LOGBOOK_PHONE, phone);
        values.put(KEY_LOGBOOK_REG_NR, reg_nr);
        values.put(KEY_LOGBOOK_FRA_NR, fra_nr);
        values.put(KEY_LOGBOOK_INS_POL, ins_pol);
        values.put(KEY_LOGBOOK_INS_CIE, ins_cie);
        values.put(KEY_LOGBOOK_CAP_EMAIL, cap_email);
        values.put(KEY_LOGBOOK_HARBOUR, habour);
        values.put(KEY_LOGBOOK_RADIO_CALL, callsign);
        values.put(KEY_LOGBOOK_LENGTH, length);
        values.put(KEY_LOGBOOK_BEAM, beam);
        values.put(KEY_LOGBOOK_DRAUGHT, draught);
        values.put(KEY_LOGBOOK_TONNAGE, tonnage);
        values.put(KEY_LOGBOOK_OWNER_BOAT, owner_id);

        // Inserting Row
        long id = db.insert(TABLE_LOGBOOK, null, values);

        Log.d(TAG, "New Logbook inserted into sqlite: " + id);
    }


}

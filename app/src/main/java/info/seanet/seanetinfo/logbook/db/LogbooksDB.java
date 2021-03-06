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
    private static final int DATABASE_VERSION = 12;

    // Database Name
    private static final String DATABASE_NAME = "seanet_info_logbook";

    private SQLiteDatabase db;

    private SQLiteLogbook SQLiteLogbook;

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
    private static final String KEY_OWNER_SAVED_ON_SERVER = "savedFlag";
    private static final int NUM_KEY_OWNER_SAVED_ON_SERVER = 4;
    private static final String KEY_OWNER_DATE_SAVED = "saved";
    private static final int NUM_KEY_OWNER_DATE_SAVED = 5;

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
    private static final int NUM_KEY_LOGBOOK_LENGTH = 11;

    private static final String KEY_LOGBOOK_BEAM = "beam";
    private static final int NUM_KEY_LOGBOOK_BEAM = 12;

    private static final String KEY_LOGBOOK_DRAUGHT = "draught";
    private static final int NUM_KEY_LOGBOOK_DRAUGHT = 13;

    private static final String KEY_LOGBOOK_TONNAGE = "tonnage";
    private static final int NUM_KEY_LOGBOOK_TONNAGE = 14;

    private static final String KEY_LOGBOOK_OWNER_BOAT = "owner";
    private static final int NUM_KEY_LOGBOOK_OWNER_BOAT = 15;

    private static final String KEY_LOGBOOK_CREATED = "created";
    private static final int NUM_KEY_LOGBOOK_CREATED = 16;

    private static final String KEY_LOGBOOK_CLOSED = "closed";
    private static final int NUM_KEY_LOGBOOK_CLOSED = 17;

    private static final String KEY_LOGBOOK_SAVED_ON_SERVER = "savedFlag";
    private static final int NUM_KEY_LOGBOOK_SAVED_ON_SERVER = 18;

    private static final String KEY_LOGBOOK_DATE_SAVED = "saved";
    private static final int NUM_KEY_LOGBOOK_DATE_SAVED = 19;

    // KEYS FOR TABLE CREWS
    private static final String TABLE_CREW="crew";
    private static final String KEY_CREW_ID = "id";
    private static final int NUM_KEY_CREW_ID=0;
    private static final String KEY_CREW_NAME = "name";
    private static final int NUM_KEY_CREW_NAME=1;
    private static final String KEY_CREW_LIST = "list";
    private static final int NUM_KEY_CREW_LIST=2;
    private static final String KEY_CREW_SAVED_ON_SERVER = "savedFlag";
    private static final int NUM_KEY_CREW_SAVED_ON_SERVER = 3;
    private static final String KEY_CREW_DATE_SAVED = "saved";
    private static final int NUM_KEY_CREW_DATE_SAVED = 4;

    // KEYS FOR TABLE CRUISES
    private static final String TABLE_CRUISE = "cruise";
    private static final String KEY_CRUISE_ID = "id";
    private static final int NUM_KEY_CRUISE_ID = 0;

    private static final String KEY_CRUISE_DESC = "name";
    private static final int NUM_KEY_CRUISE_DESC = 1;

    private static final String KEY_CRUISE_START_DATE = "start_date";
    private static final int NUM_KEY_CRUISE_START_DATE = 2;

    private static final String KEY_CRUISE_END_DATE = "endDate";
    private static final int NUM_KEY_CRUISE_END_DATE = 3;

    private static final String KEY_CRUISE_FROM = "from_area";
    private static final int NUM_KEY_CRUISE_FROM = 4;

    private static final String KEY_CRUISE_TO = "to_area";
    private static final int NUM_KEY_CRUISE_TO = 5;

    private static final String KEY_CRUISE_FROM_HARBOUR = "from_harbour";
    private static final int NUM_KEY_CRUISE_FROM_HARBOUR = 6;

    private static final String KEY_CRUISE_TO_HARBOUR = "to_habour";
    private static final int NUM_KEY_CRUISE_TO_HARBOUR = 7;

    private static final String KEY_CRUISE_CAPTAIN = "captain";
    private static final int NUM_KEY_CRUISE_CAPTAIN = 8;

    private static final String KEY_CRUISE_CREW_ID = "crew_id";
    private static final int NUM_KEY_CRUISE_CREW_ID = 9;

    private static final String KEY_CRUISE_EXPECTED_WEATHER = "expected_weather";
    private static final int NUM_KEY_CRUISE_EXPECTED_WEATHER = 10;

    private static final String KEY_CRUISE_WATER_LEVEL = "water_level";
    private static final int NUM_KEY_CRUISE_WATER_LEVEL = 11;

    private static final String KEY_CRUISE_FUEL = "fuel";
    private static final int NUM_KEY_CRUISE_FUEL = 12;

    private static final String KEY_CRUISE_AT_STOP = "at_stop";
    private static final int NUM_KEY_CRUISE_AT_STOP = 13;

    private static final String KEY_CRUISE_LOGBOOK_ID = "logbook_id";
    private static final int NUM_KEY_CRUISE_LOGBOOK_ID = 14;

    private static final String KEY_CRUISE_SAVED_ON_SERVER = "savedFlag";
    private static final int NUM_KEY_CRUISE_SAVED_ON_SERVER = 15;

    private static final String KEY_CRUISE_DATE_SAVED = "saved";
    private static final int NUM_KEY_CRUISE_DATE_SAVED = 16;

    //---------------------------------------------------------------------------------------------
    public LogbooksDB(Context context) {
        SQLiteLogbook =new SQLiteLogbook(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open() {
        db= SQLiteLogbook.getWritableDatabase();
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
    public void addOwner(Owners owner) {

        ContentValues values = new ContentValues();
        values.put(KEY_OWNER_NAME, owner.getName()); // Name
        values.put(KEY_OWNER_EMAIL, owner.getEmail()); // Email
        values.put(KEY_OWNER_ADDRESS, owner.getAddress()); // Postal address

        // Inserting Row
        long id = db.insert(TABLE_OWNER, null, values);

        Log.d(TAG, "New owner inserted into sqlite: " + id);
    }

    /**
     * Getting owner data from database
     * */
    public List<Owners> getOwnerDetails(int id) {
        List<Owners> owners= new ArrayList<>();
        String selectQuery;
        switch  (id) {
            case 0:
                selectQuery = "SELECT * FROM " + TABLE_OWNER;
                break;
            default:
                selectQuery = "SELECT  * FROM " + TABLE_OWNER + " where " + KEY_OWNER_ID + " = " + id;
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
    public void addLogbook(Logbooks logbook) {

        ContentValues values = new ContentValues();
        values.put(KEY_LOGBOOK_DESC, logbook.getNameLog());
        values.put(KEY_LOGBOOK_BOAT, logbook.getBoat());
        values.put(KEY_LOGBOOK_PHONE, logbook.getPhone());
        values.put(KEY_LOGBOOK_REG_NR, logbook.getRegNR());
        values.put(KEY_LOGBOOK_FRA_NR, logbook.getFraNR());
        values.put(KEY_LOGBOOK_INS_POL, logbook.getInsPol());
        values.put(KEY_LOGBOOK_INS_CIE, logbook.getInsCie());
        values.put(KEY_LOGBOOK_CAP_EMAIL, logbook.getCapEmail());
        values.put(KEY_LOGBOOK_HARBOUR, logbook.getHarbour());
        values.put(KEY_LOGBOOK_RADIO_CALL, logbook.getRadioCall());
        values.put(KEY_LOGBOOK_LENGTH, logbook.getLength());
        values.put(KEY_LOGBOOK_BEAM, logbook.getBeam());
        values.put(KEY_LOGBOOK_DRAUGHT, logbook.getDraught());
        values.put(KEY_LOGBOOK_TONNAGE, logbook.getTonnage());
        values.put(KEY_LOGBOOK_OWNER_BOAT, logbook.getOwnerId());
        values.put(KEY_LOGBOOK_CREATED, logbook.getCreated());
        values.put(KEY_LOGBOOK_CLOSED, logbook.getClosed());

        // Inserting Row
        long id = db.insert(TABLE_LOGBOOK, null, values);

        Log.d(TAG, "New Logbook inserted into sqlite: " + id);
    }

    /**
     * Getting Logbook data from database
     * */
    public List<Logbooks> getLogbookDetails(int id) {
        List<Logbooks> logbooks= new ArrayList<>();
        String selectQuery;
        if (id==0) {
            selectQuery = "SELECT * FROM " + TABLE_LOGBOOK;
        } else {
            selectQuery = "SELECT * FROM " + TABLE_LOGBOOK + " where " + KEY_LOGBOOK_ID + "=" + id;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_LOGBOOK + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            Logbooks logbook=cursorToLogbooks(cursor);
            logbooks.add(logbook);
            cursor.moveToNext();
        }

        cursor.close();

        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-Fetching owner from Sqlite ");

        return logbooks;
    }

    private Logbooks cursorToLogbooks(Cursor cursor) {
        Logbooks logbooks=new Logbooks();
        logbooks.setId(cursor.getInt(0));
        logbooks.setNameLog(cursor.getString(1));
        logbooks.setBoat(cursor.getString(2));
        logbooks.setPhone(cursor.getString(3));
        logbooks.setRegNR(cursor.getString(4));
        logbooks.setFraNR(cursor.getString(5));
        logbooks.setInsPol(cursor.getString(6));
        logbooks.setInsCie(cursor.getString(7));
        logbooks.setCapEmail(cursor.getString(8));
        logbooks.setHarbour(cursor.getString(9));
        logbooks.setRadioCall(cursor.getString(10));
        logbooks.setLength(cursor.getString(11));
        logbooks.setBeam(cursor.getString(12));
        logbooks.setDraught(cursor.getString(13));
        logbooks.setTonnage(cursor.getString(14));
        logbooks.setOwnerId(cursor.getInt(15));
        logbooks.setCreated(cursor.getString(16));
        logbooks.setClosed(cursor.getString(17));
        return logbooks;

    }

    public int updateLogbook(Logbooks logbook) {
        ContentValues values = new ContentValues();
        values.put(KEY_LOGBOOK_DESC, logbook.getNameLog());
        values.put(KEY_LOGBOOK_BOAT, logbook.getBoat());
        values.put(KEY_LOGBOOK_PHONE, logbook.getPhone());
        values.put(KEY_LOGBOOK_REG_NR, logbook.getRegNR());
        values.put(KEY_LOGBOOK_FRA_NR, logbook.getFraNR());
        values.put(KEY_LOGBOOK_INS_POL, logbook.getInsPol());
        values.put(KEY_LOGBOOK_INS_CIE, logbook.getInsCie());
        values.put(KEY_LOGBOOK_CAP_EMAIL, logbook.getCapEmail());
        values.put(KEY_LOGBOOK_HARBOUR, logbook.getHarbour());
        values.put(KEY_LOGBOOK_RADIO_CALL, logbook.getRadioCall());
        values.put(KEY_LOGBOOK_LENGTH, logbook.getLength());
        values.put(KEY_LOGBOOK_BEAM, logbook.getBeam());
        values.put(KEY_LOGBOOK_DRAUGHT, logbook.getDraught());
        values.put(KEY_LOGBOOK_TONNAGE, logbook.getTonnage());
        values.put(KEY_LOGBOOK_OWNER_BOAT, logbook.getOwnerId());
        values.put(KEY_LOGBOOK_CREATED, logbook.getCreated());
        values.put(KEY_LOGBOOK_CLOSED, logbook.getClosed());

        int rc = db.update(TABLE_LOGBOOK, //table
                values, // column-value
                KEY_LOGBOOK_ID + "="+logbook.getId(),
                null);

        return rc;
    }
   /* ********************************************************************************************
                                FUNCTIONS CREWS TABLE
    ******************************************************************************************* */
    /**
     * Adding crew data from database
     * */
    public void addCrew(Crews crew) {

        ContentValues values = new ContentValues();
        values.put(KEY_CREW_NAME, crew.getName()); // Name
        values.put(KEY_CREW_LIST, crew.getList()); // crew

        // Inserting Row
        long id = db.insert(TABLE_CREW, null, values);

        Log.d(TAG, "New crew inserted into sqlite: " + id);
    }

    /**
     * Getting crew data from database
     * */
    public List<Crews> getCrewDetails(int id) {
        List<Crews> crews= new ArrayList<>();
        String selectQuery;
        switch  (id) {
            case 0:
                selectQuery = "SELECT * FROM " + TABLE_CREW;
                break;
            default:
                selectQuery = "SELECT  * FROM " + TABLE_CREW + " where " + KEY_CREW_ID + " = " + id;
                break;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_CREW + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            Crews crew=cursorToCrews(cursor);
            crews.add(crew);
            cursor.moveToNext();
        }

        cursor.close();

        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_CREW + "-Fetching crew from Sqlite ");

        return crews;
    }

    private Crews cursorToCrews(Cursor cursor) {
        Crews crews=new Crews();
        crews.setId(cursor.getInt(0));
        crews.setName(cursor.getString(1));
        crews.setList(cursor.getString(3));
        return crews;

    }
    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteCrew () {
        // Delete All Rows
        db.delete(TABLE_CREW, null, null);

        Log.d(TAG, "Deleted all crews info from sqlite");
    }

    /***********************************************************************************************
     *                                    CRUISE TABLE
     ******************************************************************************************** */
    /**
     * Storing Cruise details in database
     * */
    public void addCruise(Cruises cruise) {

        ContentValues values = new ContentValues();
        values.put(KEY_CRUISE_DESC, cruise.getNameCruise());
        values.put(KEY_CRUISE_START_DATE, cruise.getStartDate());
        values.put(KEY_CRUISE_END_DATE, cruise.getEndDate());
        values.put(KEY_CRUISE_FROM, cruise.getFrom());
        values.put(KEY_CRUISE_TO, cruise.getTo());
        values.put(KEY_CRUISE_FROM_HARBOUR, cruise.getFromHarbour());
        values.put(KEY_CRUISE_TO_HARBOUR, cruise.getToHarbour());
        values.put(KEY_CRUISE_CAPTAIN, cruise.getCaptain());
        values.put(KEY_CRUISE_CREW_ID, cruise.getCrew_id());
        values.put(KEY_CRUISE_EXPECTED_WEATHER, cruise.getExpectedWeather());
        values.put(KEY_CRUISE_WATER_LEVEL, cruise.getWaterLevels());
        values.put(KEY_CRUISE_FUEL, cruise.getFuel());
        values.put(KEY_CRUISE_AT_STOP, cruise.getAtStop());
        values.put(KEY_CRUISE_LOGBOOK_ID, cruise.getLogbook_id());

        // Inserting Row
        long id = db.insert(TABLE_CRUISE, null, values);

        Log.d(TAG, "New Cruise inserted into sqlite: " + id);
    }

    /**
     * Getting Cruise data from database
     * */
    public List<Cruises> getCruiseDetails(int id) {
        List<Cruises> cruises= new ArrayList<>();
        String selectQuery;
        if (id==0) {
            selectQuery = "SELECT * FROM " + TABLE_CRUISE;
        } else {
            selectQuery = "SELECT * FROM " + TABLE_CRUISE + " where " + KEY_CRUISE_ID + "=" + id;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_CRUISE + "-cursor return a count of " + cursor.getCount());
        while (!cursor.isAfterLast()) {
            Cruises cruise=cursorToCruises(cursor);
            cruises.add(cruise);
            cursor.moveToNext();
        }

        cursor.close();

        // return user
        Log.d(TAG, DATABASE_NAME + "-" + TABLE_OWNER + "-Fetching owner from Sqlite ");

        return cruises;
    }

    private Cruises cursorToCruises(Cursor cursor) {
        Cruises cruises=new Cruises();
        cruises.setId(cursor.getInt(0));
        cruises.setNameCruise(cursor.getString(1));
        cruises.setStartDate(cursor.getString(3));
        cruises.setEndDate(cursor.getString(4));
        cruises.setTo(cursor.getString(5));
        cruises.setFromHarbour(cursor.getString(6));
        cruises.setToHarbour(cursor.getString(7));
        cruises.setCaptain(cursor.getString(8));
        cruises.setCrew_id(cursor.getInt(9));
        cruises.setExpectedWeather(cursor.getString(10));
        cruises.setWaterLevels(cursor.getString(11));
        cruises.setFuel(cursor.getString(12));
        cruises.setAtStop(cursor.getString(13));
        cruises.setLogbook_id(cursor.getInt(14));

        return cruises;

    }

    public int updateCruise(Cruises cruise) {
        ContentValues values = new ContentValues();
        values.put(KEY_CRUISE_DESC, cruise.getNameCruise());
        values.put(KEY_CRUISE_START_DATE, cruise.getStartDate());
        values.put(KEY_CRUISE_END_DATE, cruise.getEndDate());
        values.put(KEY_CRUISE_FROM, cruise.getFrom());
        values.put(KEY_CRUISE_TO, cruise.getTo());
        values.put(KEY_CRUISE_FROM_HARBOUR, cruise.getFromHarbour());
        values.put(KEY_CRUISE_TO_HARBOUR, cruise.getToHarbour());
        values.put(KEY_CRUISE_CAPTAIN, cruise.getCaptain());
        values.put(KEY_CRUISE_CREW_ID, cruise.getCrew_id());
        values.put(KEY_CRUISE_EXPECTED_WEATHER, cruise.getExpectedWeather());
        values.put(KEY_CRUISE_WATER_LEVEL, cruise.getWaterLevels());
        values.put(KEY_CRUISE_FUEL, cruise.getFuel());
        values.put(KEY_CRUISE_AT_STOP, cruise.getAtStop());
        values.put(KEY_CRUISE_LOGBOOK_ID, cruise.getLogbook_id());

        int rc = db.update(TABLE_CRUISE, //table
                values, // column-value
                KEY_CRUISE_ID + "="+cruise.getId(),
                null);

        return rc;
    }


}

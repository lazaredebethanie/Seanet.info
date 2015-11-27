package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 10/11/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteLogbook extends SQLiteOpenHelper {
    private static final String TAG = SQLiteLogbook.class.getSimpleName();

    // Owner Table Columns names
    //
    private static final String TABLE_OWNER = "owner";
    private static final String KEY_OWNER_ID = "id";
    private static final String KEY_OWNER_NAME = "name";
    private static final String KEY_OWNER_EMAIL = "email";
    private static final String KEY_OWNER_ADDRESS = "address";
    private static final String KEY_OWNER_SAVED_ON_SERVER = "savedFlag";
    private static final String KEY_OWNER_DATE_SAVED = "saved";

    String CREATE_OWNER_TABLE = "CREATE TABLE " + TABLE_OWNER + "("
            + KEY_OWNER_ID + " INTEGER PRIMARY KEY,"
            + KEY_OWNER_NAME + " TEXT,"
            + KEY_OWNER_EMAIL + " TEXT,"
            + KEY_OWNER_ADDRESS + " TEXT,"
            + KEY_OWNER_SAVED_ON_SERVER + " INTEGER,"
            + KEY_OWNER_DATE_SAVED + " TEXT"
            +  ")";

    //Logbook Table
    private static final String TABLE_LOGBOOK = "logbook";
    private static final String KEY_LOGBOOK_ID = "id";
    private static final String KEY_LOGBOOK_DESC = "name";
    private static final String KEY_LOGBOOK_BOAT = "boat_name";
    private static final String KEY_LOGBOOK_PHONE = "phone";
    private static final String KEY_LOGBOOK_REG_NR = "reg_nr";
    private static final String KEY_LOGBOOK_FRA_NR = "fra_nr";
    private static final String KEY_LOGBOOK_INS_POL = "ins_pol";
    private static final String KEY_LOGBOOK_INS_CIE = "ins_cie";
    private static final String KEY_LOGBOOK_CAP_EMAIL = "email";
    private static final String KEY_LOGBOOK_HARBOUR = "harbour";
    private static final String KEY_LOGBOOK_RADIO_CALL = "callsign";
    private static final String KEY_LOGBOOK_LENGTH = "length";
    private static final String KEY_LOGBOOK_BEAM = "beam";
    private static final String KEY_LOGBOOK_DRAUGHT = "draught";
    private static final String KEY_LOGBOOK_TONNAGE = "tonnage";
    private static final String KEY_LOGBOOK_OWNER_BOAT = "owner";
    private static final String KEY_LOGBOOK_CREATED = "created";
    private static final String KEY_LOGBOOK_CLOSED = "closed";
    private static final String KEY_LOGBOOK_SAVED_ON_SERVER = "savedFlag";
    private static final String KEY_LOGBOOK_DATE_SAVED = "saved";

    String CREATE_LOGBOOK_TABLE = "CREATE TABLE " + TABLE_LOGBOOK + "("
            + KEY_LOGBOOK_ID + " INTEGER PRIMARY KEY,"
            + KEY_LOGBOOK_DESC + " TEXT UNIQUE,"
            + KEY_LOGBOOK_BOAT + " TEXT,"
            + KEY_LOGBOOK_PHONE + " TEXT,"
            + KEY_LOGBOOK_REG_NR + " TEXT,"
            + KEY_LOGBOOK_FRA_NR + " TEXT,"
            + KEY_LOGBOOK_INS_POL + " TEXT,"
            + KEY_LOGBOOK_INS_CIE + " TEXT,"
            + KEY_LOGBOOK_CAP_EMAIL + " TEXT,"
            + KEY_LOGBOOK_HARBOUR + " TEXT,"
            + KEY_LOGBOOK_RADIO_CALL + " TEXT,"
            + KEY_LOGBOOK_LENGTH + " TEXT,"
            + KEY_LOGBOOK_BEAM + " TEXT,"
            + KEY_LOGBOOK_DRAUGHT  + " TEXT,"
            + KEY_LOGBOOK_TONNAGE + " TEXT,"
            + KEY_LOGBOOK_OWNER_BOAT + " INTEGER,"
            + KEY_LOGBOOK_CREATED + " TEXT,"
            + KEY_LOGBOOK_CLOSED + " TEXT,"
            + KEY_LOGBOOK_SAVED_ON_SERVER + " INTEGER,"
            + KEY_LOGBOOK_DATE_SAVED + " TEXT"
            +  ")";

    // Crew Table Columns names
    //
    private static final String TABLE_CREW = "crew";
    private static final String KEY_CREW_ID = "id";
    private static final String KEY_CREW_NAME = "name";
    private static final String KEY_CREW_ADDRESS = "list";
    private static final String KEY_CREW_SAVED_ON_SERVER = "savedFlag";
    private static final String KEY_CREW_DATE_SAVED = "saved";

    String CREATE_CREW_TABLE = "CREATE TABLE " + TABLE_CREW + "("
            + KEY_CREW_ID + " INTEGER PRIMARY KEY,"
            + KEY_CREW_NAME + " TEXT,"
            + KEY_CREW_ADDRESS + " TEXT,"
            + KEY_CREW_SAVED_ON_SERVER + " INTEGER,"
            + KEY_CREW_DATE_SAVED + " TEXT"
            +  ")";
    
    public SQLiteLogbook(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Owner table
        //db.execSQL(CREATE_OWNER_TABLE);
        //db.execSQL(CREATE_LOGBOOK_TABLE);
        db.execSQL(CREATE_CREW_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older owner table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_OWNER);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGBOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREW);

        // Create tables again
        onCreate(db);
    }


}

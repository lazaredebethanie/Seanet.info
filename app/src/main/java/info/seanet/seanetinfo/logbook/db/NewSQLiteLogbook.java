package info.seanet.seanetinfo.logbook.db;

/**
 * Created by andre on 10/11/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NewSQLiteLogbook extends SQLiteOpenHelper {
    private static final String TAG = NewSQLiteLogbook.class.getSimpleName();

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

    public NewSQLiteLogbook(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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


}

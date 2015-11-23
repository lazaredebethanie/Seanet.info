package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.SQLiteLogbook;

public class NewOwner extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etEmail;
    private SQLiteLogbook db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_new_owner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName=(EditText) findViewById(R.id.etName);
        etAddress=(EditText) findViewById(R.id.etAddress);
        etEmail=(EditText) findViewById(R.id.etEmail);
        // SQLite database handler

        db = new SQLiteLogbook(getApplicationContext());

    }

    public void onExit(View v) {
        finish();
    }

    public void onSaveOwner(View v) {
        String name=etName.getText().toString();
        String email=etEmail.getText().toString();
        String address=etAddress.getText().toString();

        db.addOwner(name,email,address);
        finish();
    }


}

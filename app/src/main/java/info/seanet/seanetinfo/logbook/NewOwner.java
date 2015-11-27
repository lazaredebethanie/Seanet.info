package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;
import info.seanet.seanetinfo.logbook.db.Owners;

public class NewOwner extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etEmail;
    private ImageButton save;
    private LogbooksDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_new_owner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName=(EditText) findViewById(R.id.etName);
        etAddress=(EditText) findViewById(R.id.etAddress);
        etEmail=(EditText) findViewById(R.id.etEmail);

        save=(ImageButton) findViewById(R.id.iBtnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveOwner();
            }
        });

    }

    public void onExit(View v) {
        finish();
    }

    public void onSaveOwner() {
        db=new LogbooksDB(this);

        db.open();

        Owners owner = new Owners();
        owner.setName(etName.getText().toString());
        owner.setEmail(etEmail.getText().toString());
        owner.setAddress(etAddress.getText().toString());

        db.addOwner(owner);

        db.close();
        finish();
    }


}

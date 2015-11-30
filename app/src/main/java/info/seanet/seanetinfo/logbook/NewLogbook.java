package info.seanet.seanetinfo.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.Logbooks;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;
import info.seanet.seanetinfo.logbook.db.Owners;

public class NewLogbook extends AppCompatActivity {
    private static final String TAG = NewLogbook.class.getSimpleName();

    private ImageButton back;
    private ImageButton save;
    private ImageButton addOwner;
    private EditText etNamelog;
    private EditText etBoatName;
    private EditText etPhone;
    private EditText etRegistrationNR;
    private EditText etFrancisationNR;
    private EditText etInsurrancePolicy;
    private EditText etInsurranceCie;
    private EditText etMailSkipper;
    private EditText etHarbour;
    private EditText etRadioCall;
    private EditText etLength;
    private EditText etBeam;
    private EditText etDraught;
    private EditText etTonnage;
    private Spinner spOwner;
    private TextView tOwnerEmail;
    private TextView tOwnerAddress;
    private LogbooksDB db;
    static final int OWNER_REQUEST = 1;

    private List<Owners> owners;
    private static SimpleDateFormat format_sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_new_logbook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNamelog=(EditText) findViewById(R.id.etNameLog);
        etBoatName=(EditText) findViewById(R.id.etBoatName);
        etPhone=(EditText) findViewById(R.id.etPhone);
        etRegistrationNR=(EditText) findViewById(R.id.etRegistrationNR);
        etFrancisationNR=(EditText) findViewById(R.id.etFrancisationNR);
        etInsurrancePolicy=(EditText) findViewById(R.id.etInsurrancePolicy);
        etInsurranceCie=(EditText) findViewById(R.id.etInsurranceCie);
        etMailSkipper=(EditText) findViewById(R.id.etMailSkipper);
        etHarbour=(EditText) findViewById(R.id.etHarbour);
        etRadioCall=(EditText) findViewById(R.id.etRadioCall);
        etLength=(EditText) findViewById(R.id.etLength);
        etBeam=(EditText) findViewById(R.id.etBeam);
        etDraught=(EditText) findViewById(R.id.etDraught);
        etTonnage=(EditText) findViewById(R.id.etTonnage);

        tOwnerEmail =(TextView) findViewById(R.id.tEmailOwner);
        tOwnerAddress = (TextView) findViewById(R.id.tAddress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new LogbooksDB(this);

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save = (ImageButton) findViewById(R.id.iBtnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveLogbook();
            }
        });

        addOwner = (ImageButton) findViewById(R.id.iBtnAddOwner);
        addOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewOwner();
            }
        });

        spOwner=(Spinner) findViewById(R.id.spOwner);

        spOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                tOwnerEmail.setText(owners.get(spOwner.getSelectedItemPosition()).getEmail());
                tOwnerAddress.setText(owners.get(spOwner.getSelectedItemPosition()).getAddress());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("item not selected");
            }
        });

        loadOwnerSpinner();
    }

    public void createNewOwner () {

        Intent newOwnerAct = new Intent (this,NewOwner.class);
        startActivityForResult(newOwnerAct, OWNER_REQUEST);

    }

    private void loadOwnerSpinner (){
        db.open();
        owners=db.getOwnerDetails(0); // return notifyAll();
        db.close();
        List<String> listName=new ArrayList<String>();
        for (int i=0;i<owners.size();i++) {
            listName.add(owners.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOwner.setAdapter(dataAdapter);
        spOwner.setSelection(spOwner.getCount() - 1);

        Log.d(TAG, "Spinner loaded");

    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch  (requestCode) {
            case OWNER_REQUEST:
                loadOwnerSpinner();
                return;
        }
    }


    public void onSaveLogbook () {

        if (spOwner.getCount()>0) {
            if (! etNamelog.equals("")) {

                db = new LogbooksDB(this);

                db.open();

                Logbooks logbook = new Logbooks();
                logbook.setNameLog(etNamelog.getText().toString());
                logbook.setBoat(etBoatName.getText().toString());

                logbook.setPhone(etPhone.getText().toString());
                logbook.setRegNR(etRegistrationNR.getText().toString());
                logbook.setFraNR(etFrancisationNR.getText().toString());
                logbook.setInsPol(etInsurrancePolicy.getText().toString());
                logbook.setInsCie(etInsurranceCie.getText().toString());
                logbook.setCapEmail(etMailSkipper.getText().toString());
                logbook.setHarbour(etHarbour.getText().toString());
                logbook.setRadioCall(etRadioCall.getText().toString());
                logbook.setLength(etLength.getText().toString());
                logbook.setBeam(etBeam.getText().toString());
                logbook.setDraught(etDraught.getText().toString());
                logbook.setTonnage(etTonnage.getText().toString());
                logbook.setOwnerId(owners.get(spOwner.getSelectedItemPosition()).getId());
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                String timestamp=(String) df.format("yyyy-MM-dd hh:mm", new java.util.Date());
                logbook.setCreated(timestamp);
                logbook.setClosed(null);

                db.addLogbook(logbook);

                db.close();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Not saved, give a name to your logbook", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Not saved, create a owner...", Toast.LENGTH_SHORT).show();
        }
    }

    public void onExit(View v) {
        finish();
    }

}

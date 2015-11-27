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

import java.util.ArrayList;
import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.Logbooks;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;
import info.seanet.seanetinfo.logbook.db.Owners;

public class ModifyLogbook extends AppCompatActivity {
    private static final String TAG = ModifyLogbook.class.getSimpleName();

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
    private String created;
    private String closed;
    private Spinner spOwner;
    private TextView tOwnerEmail;
    private TextView tOwnerAddress;
    private LogbooksDB db;
    private List<Logbooks> logbooks;
    private List<Owners> owners;
    static final int OWNER_REQUEST = 1;


    static final String EXTRA_ID="logbook_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_modify_logbook);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent callingActivity = getIntent();
        final int logbookId=callingActivity.getIntExtra(EXTRA_ID, 0);

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
        spOwner=(Spinner) findViewById(R.id.spOwner);
        tOwnerEmail =(TextView) findViewById(R.id.tEmailOwner);
        tOwnerAddress = (TextView) findViewById(R.id.tAddress);

        db = new LogbooksDB(this);

        db.open();
        // load logbook with id
        logbooks=db.getLogbookDetails(logbookId);
        db.close();

        etNamelog.setText(logbooks.get(0).getNameLog());
        etBoatName.setText(logbooks.get(0).getBoat());
        etPhone.setText(logbooks.get(0).getPhone());
        etRegistrationNR.setText(logbooks.get(0).getRegNR());
        etFrancisationNR.setText(logbooks.get(0).getFraNR());
        etInsurrancePolicy.setText(logbooks.get(0).getInsPol());
        etInsurranceCie.setText(logbooks.get(0).getInsCie());
        etMailSkipper.setText(logbooks.get(0).getCapEmail());
        etHarbour.setText(logbooks.get(0).getHarbour());
        etRadioCall.setText(logbooks.get(0).getRadioCall());
        etLength.setText(logbooks.get(0).getLength());
        etBeam.setText(logbooks.get(0).getBeam());
        etDraught.setText(logbooks.get(0).getDraught());
        etTonnage.setText(logbooks.get(0).getTonnage());
        created=logbooks.get(0).getCreated();
        closed=logbooks.get(0).getClosed();


        loadOwnerSpinner(logbooks.get(0).getOwnerId());

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
                onSaveLogbook(logbookId);
            }
        });

        addOwner = (ImageButton) findViewById(R.id.iBtnAddOwner);
        addOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewOwner();
            }
        });
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

    }
    public void createNewOwner () {

        Intent newOwnerAct = new Intent (this,NewOwner.class);
        startActivityForResult(newOwnerAct, OWNER_REQUEST);

    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch  (requestCode) {
            case OWNER_REQUEST:
                loadOwnerSpinner(-1);
                return;
        }
    }

    private void loadOwnerSpinner (int ownerId){
        db.open();
        owners=db.getOwnerDetails(0); // return notifyAll();
        db.close();

        int idxSp=-1;

        List<String> listName=new ArrayList<String>();
        for (int i=0;i<owners.size();i++) {
            listName.add(owners.get(i).getName());
            System.out.println(owners.get(i).getId());
            if (owners.get(i).getId()==ownerId) {
                idxSp=i;
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOwner.setAdapter(dataAdapter);
        if (ownerId==-1) {idxSp=spOwner.getCount() - 1;}
        spOwner.setSelection(idxSp);

        Log.d(TAG, "Spinner loaded");

    }

    public void onSaveLogbook (int logbookId) {

        if (spOwner.getCount()>0) {
            if (! etNamelog.equals("")) {

                db = new LogbooksDB(this);


                Logbooks logbook = new Logbooks();
                logbook.setId(logbookId);
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
                logbook.setCreated(created);
                logbook.setClosed(closed);

                db.open();

                db.updateLogbook(logbook);

                db.close();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Not saved, give a to your logbook", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Not saved, create a owner...", Toast.LENGTH_SHORT).show();
        }
    }
}

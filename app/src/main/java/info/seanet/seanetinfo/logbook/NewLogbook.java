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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.SQLiteLogbook;

public class NewLogbook extends AppCompatActivity {
    private static final String TAG = NewLogbook.class.getSimpleName();

    private ImageButton back;
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
    private TextView ownerEmail;
    private TextView ownerAddress;
    private SQLiteLogbook db;
    static final int OWNER_REQUEST = 1;

    private HashMap <Integer, String> ownersDetail= new HashMap<>();

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

        ownerEmail=(TextView) findViewById(R.id.tEmailOwner);
        ownerAddress= (TextView) findViewById(R.id.tAddress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new SQLiteLogbook(getApplicationContext());

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spOwner=(Spinner) findViewById(R.id.spOwner);

        spOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("item selected");
                String [] detailsOwner=ownersDetail.get(spOwner.getSelectedItemPosition()).split(";");

                ownerEmail.setText(detailsOwner[2]);
                ownerAddress.setText(detailsOwner[3]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("item not selected");
            }
        });

        loadOwnerSpinner();
    }



    public void createNewOwner (View view) {

        Intent newOwnerAct = new Intent (this,NewOwner.class);
        startActivityForResult(newOwnerAct, OWNER_REQUEST);

    }

    private void loadOwnerSpinner (){
        String[] owners;
        owners=db.getOwnerDetails("--all--");
        List<String> list=new ArrayList<String>();
        int indexSpinner=0;
        for (int i=1;i<owners.length;i=i+4) {
            ownersDetail.put(indexSpinner++,owners[i-1]+";"+owners[i]+";"+owners[i+1]+";"+owners[i+2]);
            list.add(owners[i]);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOwner.setAdapter(dataAdapter);
        spOwner.setSelection(spOwner.getCount() - 1);

        if (spOwner.getCount()>0) {
            String[] detailsOwner = ownersDetail.get(spOwner.getCount() - 1).split(";");

            ownerEmail.setText(detailsOwner[2]);
            ownerAddress.setText(detailsOwner[3]);
        }

        Log.d(TAG, "Spinner loaded");

    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch  (requestCode) {
            case OWNER_REQUEST:
                loadOwnerSpinner();
                return;
        }
    }


    public void onSaveLogbook (View v) {
        String nameLog=etNamelog.getText().toString();
        String boat=etBoatName.getText().toString();
        String phone=etPhone.getText().toString();
        String reg_nr=etRegistrationNR.getText().toString();
        String fra_nr=etFrancisationNR.getText().toString();
        String ins_pol=etInsurrancePolicy.getText().toString();
        String ins_cie=etInsurranceCie.getText().toString();
        String cap_email=etMailSkipper.getText().toString();
        String harbour=etHarbour.getText().toString();
        String callsign=etRadioCall.getText().toString();
        String length=etLength.getText().toString();
        String beam=etBeam.getText().toString();
        String draught=etDraught.getText().toString();
        String tonnage=etTonnage.getText().toString();

        String[] detailsOwner = ownersDetail.get(spOwner.getCount() - 1).split(";");
        Integer owner_id=Integer.parseInt(detailsOwner[0]);

        db.addLogbook(nameLog, boat, phone, reg_nr, fra_nr, ins_pol, ins_cie, cap_email, harbour, callsign,
                length, beam, draught, tonnage, owner_id);
        finish();


    }

    public void onExit(View v) {
        finish();
    }

}

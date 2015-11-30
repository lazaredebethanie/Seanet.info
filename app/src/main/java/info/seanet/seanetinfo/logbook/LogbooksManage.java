package info.seanet.seanetinfo.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class LogbooksManage extends AppCompatActivity {
    private static final String TAG = LogbooksManage.class.getSimpleName();

    private ImageButton back;
    private Spinner spLogbook;
    private TextView tBoatName;
    private TextView tPhone;
    private TextView tRegistrationNR;
    private TextView tFrancisationNR;
    private TextView tInsurrancePolicy;
    private TextView tInsurranceCie;
    private TextView tMailSkipper;
    private TextView tHarbour;
    private TextView tRadioCall;
    private TextView tLength;
    private TextView tBeam;
    private TextView tDraught;
    private TextView tTonnage;
    private TextView tOwner;
    private TextView tCreated;
    private TextView tClosed;
    static final int NEW_LOGBOOK_REQUEST = 1;
    static final int MODIFY_LOGBOOK_REQUEST = 2;
    private LogbooksDB db;
    private List<Logbooks> logbooks;
    private List<Owners> owners;

    static final String EXTRA_ID="logbook_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_logbook_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new LogbooksDB(this);

        tBoatName =(TextView) findViewById(R.id.tBoatName);
        tPhone =(TextView) findViewById(R.id.tPhone);
        tRegistrationNR =(TextView) findViewById(R.id.tRegNr);
        tFrancisationNR =(TextView) findViewById(R.id.tFraNr);
        tInsurrancePolicy =(TextView) findViewById(R.id.tInsPol);
        tInsurranceCie =(TextView) findViewById(R.id.tInsCie);
        tMailSkipper =(TextView) findViewById(R.id.tEmail);
        tHarbour =(TextView) findViewById(R.id.tHarbour);
        tRadioCall =(TextView) findViewById(R.id.tCallsign);
        tLength =(TextView) findViewById(R.id.tLength);
        tBeam =(TextView) findViewById(R.id.tBeam);
        tDraught =(TextView) findViewById(R.id.tDraught);
        tTonnage =(TextView) findViewById(R.id.tTonnage);
        tOwner =(TextView) findViewById(R.id.tOwner);
        tCreated =(TextView) findViewById(R.id.tCreated);
        tClosed =(TextView) findViewById(R.id.tClosed);

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spLogbook=(Spinner) findViewById(R.id.spLogbook);

        spLogbook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                tBoatName.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getBoat());
                tPhone.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getPhone());
                tRegistrationNR.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getRegNR());
                tFrancisationNR.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getFraNR());
                tInsurrancePolicy.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getInsPol());
                tInsurranceCie.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getInsCie());
                tMailSkipper.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getCapEmail());
                tHarbour.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getHarbour());
                tRadioCall.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getRadioCall());
                tLength.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getLength());
                tBeam.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getBeam());
                tDraught.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getDraught());
                tTonnage.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getTonnage());
                db.open();
                owners=db.getOwnerDetails(logbooks.get(spLogbook.getSelectedItemPosition()).getOwnerId());
                db.close();
                tOwner.setText(owners.get(0).getName());
                tCreated.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getCreated());
                tClosed.setText(logbooks.get(spLogbook.getSelectedItemPosition()).getClosed());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("item not selected");
            }
        });

        loadLogbookSpinner();

    }

    public void addLogbook(View view) {
        Intent newLogbook = new Intent (this,NewLogbook.class);
        startActivityForResult(newLogbook, NEW_LOGBOOK_REQUEST);

    }

    public void modifyLogbook(View view) {
        if (logbooks.size()>0) {
            Intent modifyLogbook = new Intent(this, ModifyLogbook.class);
            modifyLogbook.putExtra(EXTRA_ID, logbooks.get(spLogbook.getSelectedItemPosition()).getId());
            startActivityForResult(modifyLogbook, MODIFY_LOGBOOK_REQUEST);
        }
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch  (requestCode) {
            case NEW_LOGBOOK_REQUEST:
                loadLogbookSpinner();
                return;
            case MODIFY_LOGBOOK_REQUEST:
                loadLogbookSpinner();
                return;
        }
    }

    public void InfoOwner (View view) {
        Toast.makeText(getApplicationContext(),owners.get(0).getName()+"\n"+owners.get(0).getAddress()+"\n"+owners.get(0).getEmail(),Toast.LENGTH_LONG).show();
    }

    private void loadLogbookSpinner (){
        db.open();
        logbooks=db.getLogbookDetails(0); //0 return all
        db.close();
        List<String> listName= new ArrayList<>();
        for (int i=0;i<logbooks.size();i++) {
            listName.add(logbooks.get(i).getNameLog());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int itemSelected=-1;

        try {

            if (spLogbook.getSelectedItemPosition() >= 0) {
                itemSelected = spLogbook.getSelectedItemPosition();
            }
        } catch (Exception e){}
        spLogbook.setAdapter(dataAdapter);
        if (itemSelected==-1) {
            itemSelected=spLogbook.getCount() - 1;
        }
        spLogbook.setSelection(itemSelected);

        Log.d(TAG, "Spinner loaded");

    }
}

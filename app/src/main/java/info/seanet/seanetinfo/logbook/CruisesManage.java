package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.Crews;
import info.seanet.seanetinfo.logbook.db.Logbooks;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;

public class CruisesManage extends AppCompatActivity {
    private static final String TAG = LogbooksManage.class.getSimpleName();

    private ImageButton back;
    private ImageButton save;
    private String nameCruise;
    private Spinner spLogbook;
    private EditText eStartDate;
    private EditText eFrom;
    private EditText eTo;
    private EditText eFromHarbour;
    private EditText eToHarbour;
    private EditText eCaptain;
    private Spinner spCrew;
    private EditText eExpectedWeather;
    private EditText eTide1;
    private EditText eHeight1;
    private EditText eTide2;
    private EditText eHeight2;
    private EditText eTide3;
    private EditText eHeight3;
    private TextView tvHeights;
    private EditText eFuel;
    private EditText eAtStop;
    private LogbooksDB db;
    private List<Logbooks> logbooks;
    private List<Crews> crews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_cruises_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new LogbooksDB(this);

        spLogbook=(Spinner) findViewById(R.id.spLogbook);
        eStartDate=(EditText) findViewById(R.id.eStartDate);
        eFrom=(EditText) findViewById(R.id.eFrom);
        eTo=(EditText) findViewById(R.id.eTo);
        eFromHarbour=(EditText) findViewById(R.id.eFromHarbour);
        eToHarbour=(EditText) findViewById(R.id.eToHarbour);
        eCaptain=(EditText) findViewById(R.id.eCaptain);
        spCrew=(Spinner) findViewById(R.id.spCrew);
        eExpectedWeather=(EditText) findViewById(R.id.eExpectedWeather);
        eTide1=(EditText) findViewById(R.id.eTide1);
        eHeight1=(EditText) findViewById(R.id.eHeight2);
        eTide2=(EditText) findViewById(R.id.eTide1);
        eHeight2=(EditText) findViewById(R.id.eHeight2);
        eTide3=(EditText) findViewById(R.id.eTide3);
        eHeight3=(EditText) findViewById(R.id.eHeight3);
        tvHeights=(TextView) findViewById(R.id.tvHeights);
        eFuel=(EditText) findViewById(R.id.eFuel);
        eAtStop=(EditText) findViewById(R.id.eAtStop);

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadLogbookSpinner();
        loadCrewSpinner();
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

        Log.d(TAG, "Spinner logbook loaded");

    }

    private void loadCrewSpinner (){
        db.open();
        crews=db.getCrewDetails(0); //0 return all
        db.close();
        List<String> listName= new ArrayList<>();
        for (int i=0;i<crews.size();i++) {
            listName.add(crews.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        int itemSelected=-1;

        try {

            if (spCrew.getSelectedItemPosition() >= 0) {
                itemSelected = spCrew.getSelectedItemPosition();
            }
        } catch (Exception e){}
        spCrew.setAdapter(dataAdapter);
        if (itemSelected==-1) {
            itemSelected=spCrew.getCount() - 1;
        }
        spCrew.setSelection(itemSelected);

        Log.d(TAG, "Spinner loaded");

    }

}

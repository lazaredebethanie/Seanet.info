package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.Crews;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;

public class CrewsManage extends AppCompatActivity {

    private ImageButton back;
    private ImageButton save;
    private ImageButton addPerson;
    private EditText eNameCrew;
    private ListView lvPersonsList;
    private ArrayAdapter<String> adapter;
    private List<String> arrayPersonsList;
    private EditText eNameOfPerson;
    private LogbooksDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_crews_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayPersonsList=new ArrayList<>() ;

        eNameCrew=(EditText) findViewById(R.id.etNameCrew);
        eNameOfPerson=(EditText) findViewById(R.id.etNameOfPerson);
        lvPersonsList =(ListView) findViewById(R.id.lvLPersonsList);

        addPerson=(ImageButton) findViewById(R.id.iBtnAddInList);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    arrayPersonsList.add(eNameOfPerson.getText().toString());
                    adapter = new ArrayAdapter<>(CrewsManage.this,
                            android.R.layout.simple_list_item_1, arrayPersonsList);
                    lvPersonsList.setAdapter(adapter);
                    eNameOfPerson.setText("");
                } catch (Exception e) {}
            }
        });

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
                onSaveCrew();
            }
        });
    }

    public void onSaveCrew () {

            if (! eNameCrew.equals("")) {

                db = new LogbooksDB(this);

                db.open();

                Crews crew = new Crews();
                crew.setName(eNameCrew.getText().toString());
                String crewStr = new String();
                for (int i=0;i<arrayPersonsList.size();i++) {
                    crewStr=arrayPersonsList.get(i)+"\n";
                }
                crew.setList(crewStr);
                android.text.format.DateFormat df = new android.text.format.DateFormat();
                String timestamp=(String) df.format("yyyy-MM-dd hh:mm", new java.util.Date());
                db.addCrew(crew);

                db.close();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Not saved, give a name to your crew", Toast.LENGTH_SHORT).show();
            }
    }

}

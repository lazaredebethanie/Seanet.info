package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.seanet.seanetinfo.R;

public class CrewsManage extends AppCompatActivity {

    private ImageButton back;
    private ImageButton addPerson;
    private ListView personsList;
    private ArrayAdapter<String> adapter;
    private List<String> arrayPersonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_crews_manage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayPersonsList=new ArrayList<>() ;

        arrayPersonsList.add("maman");
        arrayPersonsList.add("papa");

        personsList=(ListView) findViewById(R.id.lvLPersonsList);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayPersonsList);
        personsList.setAdapter(adapter);


        addPerson=(ImageButton) findViewById(R.id.iBtnAddInList);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}

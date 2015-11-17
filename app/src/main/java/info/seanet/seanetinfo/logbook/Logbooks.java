package info.seanet.seanetinfo.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import info.seanet.seanetinfo.R;

public class Logbooks extends AppCompatActivity {

    private ImageButton back;
    private ImageButton newLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logbook_activity_logbooks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        back = (ImageButton) findViewById(R.id.iBtnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newLog = (ImageButton) findViewById(R.id.iBtnAddLog);
        newLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newLogbook = new Intent (this,NewLogbook.class);
                startActivity(newLogbook);
            }
        });

    }

}

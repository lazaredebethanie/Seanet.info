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
    static final int NEW_LOGBOOK_REQUEST = 1;


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
    }

    public void Add (View view) {
        Intent newLogbook = new Intent (this,NewLogbook.class);
        startActivityForResult(newLogbook,NEW_LOGBOOK_REQUEST);

    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        switch  (requestCode) {
            case NEW_LOGBOOK_REQUEST:
                return;
        }
    }
}

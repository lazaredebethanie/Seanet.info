package info.seanet.seanetinfo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.HashMap;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.harbors.Harbors;
import info.seanet.seanetinfo.logbook.TestOwners;
import info.seanet.seanetinfo.loginandregistration.helper.SQLiteSession;
import info.seanet.seanetinfo.loginandregistration.helper.SessionManager;
import info.seanet.seanetinfo.logbook.Logbook;
import info.seanet.seanetinfo.loginandregistration.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnLogout;
    private ImageButton btnLogbook;
    private ImageButton btnMarinas;
    private LinearLayout lAlertNotConnected;

    private SQLiteSession db;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (ImageButton) findViewById(R.id.iBtnLogout);
        btnLogbook = (ImageButton) findViewById(R.id.iBtnLogBook);
        btnMarinas = (ImageButton) findViewById(R.id.iBtnMarinas);
        lAlertNotConnected= (LinearLayout) findViewById(R.id.lAlertNotConnected);

        // SqLite database handler
        db = new SQLiteSession(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            lAlertNotConnected.setVisibility(View.VISIBLE);
        } else {
            // Fetching user details from SQLite
            HashMap<String, String> user = db.getUserDetails();

            String name = user.get("name");
            String email = user.get("email");
            // Logout button click event
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        btnLogbook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent logbook = new Intent(MainActivity.this, Logbook.class);
                startActivity(logbook);
            }
        });

        btnMarinas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent marinas = new Intent(MainActivity.this, Harbors.class);
                startActivity(marinas);
            }
        });

    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSpecial(View v) {
        Intent act=new Intent(this, TestOwners.class);
        startActivity(act);
    }

}

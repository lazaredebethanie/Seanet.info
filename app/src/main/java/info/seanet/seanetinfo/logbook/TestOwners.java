package info.seanet.seanetinfo.logbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import info.seanet.seanetinfo.R;
import info.seanet.seanetinfo.logbook.db.LogbooksDB;
import info.seanet.seanetinfo.logbook.db.Owners;

public class TestOwners extends AppCompatActivity {

    private LogbooksDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_owners);

        db=new LogbooksDB(this);

        db.open();

        List<Owners> owners = db.getOwnerDetails(0);

        for (int i=0;i<owners.size();i++) {
            System.out.println(owners.get(i).toString());
            System.out.println(owners.get(i).getName());
        }

        db.close();

    }
}

package be.groept;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by surfing on 2/16/2016.
 */
public class DisplayProblemActivity extends AppCompatActivity {

    private Bundle extras;
    private String repairInfo = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_problem);

        extras = getIntent().getExtras();

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_display_problem);
        TextView tv = (TextView) findViewById(R.id.tv_display_problem);
        TextView tv_address = (TextView) findViewById(R.id.tv_display_full_address);
        Button btn = (Button) findViewById(R.id.btn_display_problem_back);

        setMapFragment();

        if (extras != null) {
            tv.setText(extras.getString("problemDescription"));
            tv_address.setText("Address: " + extras.getString("problemAddress"));
            repairInfo = extras.getString("problemSolution");
        }

        if(repairInfo != null)
        {
            TextView tv_repair = new TextView(this);
            tv_repair.setText("ElectroDoctor repair information: " + repairInfo);
            ll.addView(tv_repair);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayProblemActivity.this.onBackPressed();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_problem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_phone_client) {
            //nummer in formaat "tel: 123456789")
            Uri uri =  Uri.parse("tel:" + extras.get("phone"));
            // Create a intent for viewing a URL
            Intent dialIntent = new Intent(Intent.ACTION_CALL, uri);
            //Create a chooser intent, for choosing which Activity will carry out the intent
            Intent chooserIntent = Intent.createChooser(dialIntent, "Call " +  uri + " using:");
            chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Start the chooser Activity, using the chooser intent
            startActivity(chooserIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setMapFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mapFragment = new MapFragment();
        Bundle data = new Bundle();
        data.putString("problemAddress", extras.getString("problemAddress"));
        mapFragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, mapFragment).commit();

    }
}

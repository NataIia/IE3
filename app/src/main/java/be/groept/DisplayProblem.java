package be.groept;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by surfing on 2/16/2016.
 */
public class DisplayProblem extends AppCompatActivity {

    private Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_problem);

        extras = getIntent().getExtras();

        TextView tv = (TextView) findViewById(R.id.tv_display_problem);
        TextView tv_address = (TextView) findViewById(R.id.tv_display_full_address);
        Button btn = (Button) findViewById(R.id.btn_display_problem_back);
        setMapFragment();

        if (extras != null) {
            tv.setText(extras.getString("problemDescription"));
            tv_address.setText("Address: " + extras.getString("problemAddress"));

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayProblem.this.onBackPressed();
            }
        });
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

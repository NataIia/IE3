package be.groept;

import DAL.InitializeElectroMan;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import model.Problem;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private TableLayout tableHeader, tableLayout;
    private InitializeElectroMan electroManInitilizer;
    private String[] headers = {"City", "Device", "Problem code", "Name", "Processed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Appcompat-v7 automatically disables setDisplayShowHomeEnabled, must set it to true to show an icon.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.wrench_icon);

        tableHeader = (TableLayout) findViewById(R.id.tbl_table_header);
        tableLayout = (TableLayout) findViewById(R.id.tbl_problem_table);

        electroManInitilizer = new InitializeElectroMan(this);

        ArrayList<Problem> problems = electroManInitilizer.getDbHelper().getAllProblems();

        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        for(String header : headers) //set table header always on the top
        {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f)); //cells with the same width and height
            tv.setBackgroundResource(R.drawable.cell_shape);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(5, 5, 5, 5);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setText(header);
            headerRow.addView(tv);
        }
        tableHeader.addView(headerRow);
        TableRow dataRow = null;

        for (Problem problem : problems) //set problems list as scrolable table
        {
            dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            dataRow.addView(setCellContent(problem.getId()));
            dataRow.addView(setCellContent(problem.getProblemId()));
            dataRow.addView(setCellContent(problem.getProblemTitle()));
            dataRow.addView(setCellContent(problem.getProblemDescription()));
            dataRow.setId(Integer.parseInt(problem.getId())); //pass problem ID via row ID
            dataRow.setOnClickListener(this); // set TableRow onClickListner
            tableLayout.addView(dataRow);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("problemId", v.getId());
        dataBundle.putString("problemDescription", electroManInitilizer.getDbHelper().findProblemNyId(v.getId()).getProblemDescription());
        dataBundle.putString("problemAddress", "address " + electroManInitilizer.getDbHelper().findProblemNyId(v.getId()).getProblemDescription());

        Intent intent = new Intent(getApplicationContext(),DisplayProblem.class);

        intent.putExtras(dataBundle);
        startActivity(intent);
    }

    private TextView setCellContent(String content){
        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f)); //cells with the same width and height
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(5, 5, 5, 5);
        tv.setText(content);
        return tv;
    }

}

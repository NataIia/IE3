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

    private static final int REQUEST_CODE = 1234;

    private TableLayout tableHeader, tableLayout;
    private InitializeElectroMan electroManInitilizer;
    private String[] headers = {"City", "Device", "Problem code", "Name", "Processed"};

    private TableRow dataRow = null;

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

//        ArrayList<Problem>  problems = electroManInitilizer.getDbHelper().getAllProblems();

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

        setProblemsTable();
    }

    public  void setProblemsTable()
    {
        tableLayout.removeAllViews();

        ArrayList<Problem> problems = electroManInitilizer.getDbHelper().getAllProblems();

        for (Problem problem : problems) //set problems list as scrolable table
        {
            dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            dataRow.addView(setCellTextContent(problem.getAddress().getCity()));
            dataRow.addView(setCellTextContent(problem.getProblemDevice()));
            dataRow.addView(setCellTextContent(problem.getProblemId()));
            dataRow.addView(setCellTextContent(problem.getClient().getName()));
            if(problem.isProblemSolved()) dataRow.addView(setCellTextContent("YES"));
            else{
                Button btn = new Button(this);
//            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
                btn.setText("NO");
                btn.setOnClickListener(new RepairButtonOnClickListener(problem));
                dataRow.addView(btn);
            }
            dataRow.setId(Integer.parseInt(problem.getId())); //pass problem ID via row ID
            dataRow.setOnClickListener(this); // set TableRow onClickListner
            tableLayout.addView(dataRow);
        }
    }


    @Override
    public void onClick(View v) {

        Bundle dataBundle = new Bundle();
        dataBundle.putInt("problemId", v.getId());
        dataBundle.putString("problemDescription", electroManInitilizer.getDbHelper().findProblemById(v.getId()).getProblemDescription());
        dataBundle.putString("problemAddress", electroManInitilizer.getDbHelper().findProblemById(v.getId()).getAddress().toString());
        dataBundle.putString("phone", " +32494863885"); //need to add column to client table with phone number, for testing one number is hardcoded

        if (electroManInitilizer.getDbHelper().findProblemById(v.getId()).isProblemSolved())
            dataBundle.putString("problemSolution", electroManInitilizer.getDbHelper().findProblemById(v.getId()).getProblemSolutionNotes());

        Intent intent = new Intent(getApplicationContext(),DisplayProblemActivity.class);

        intent.putExtras(dataBundle);
        startActivity(intent);
    }

    private TextView setCellTextContent(String content){
        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f)); //cells with the same width and height
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(5, 5, 5, 5);
        tv.setText(content);
        return tv;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK && requestCode == REQUEST_CODE )
        {
            Problem problem = electroManInitilizer.getDbHelper().findProblemById(Integer.parseInt(data.getStringExtra("problemId")));
            electroManInitilizer.getDbHelper().updateProblem(problem, data.getStringExtra("solution"));
            Toast toast = Toast.makeText(this, "The problem " + problem.getId() + " is solved.", Toast.LENGTH_LONG);
            toast.show();
            setProblemsTable();
        }
    }

    class RepairButtonOnClickListener implements View.OnClickListener{

        private Problem problem;

        public RepairButtonOnClickListener(Problem problem) {
            this.problem = problem;
        }

        @Override
        public void onClick(View v) {

            Bundle dataBundle = new Bundle();
            dataBundle.putString("problemId", problem.getId());
            Intent intent = new Intent(getApplicationContext(),RepairActivity.class);
            intent.putExtras(dataBundle);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}

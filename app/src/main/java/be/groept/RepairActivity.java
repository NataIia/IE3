package be.groept;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by surfing on 2/23/2016.
 */
public class RepairActivity extends AppCompatActivity {

    private EditText et_repair;
    private String problemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        et_repair = (EditText) findViewById(R.id.et_repair);

        problemId = getIntent().getExtras().getString("problemId");
    }

    public void backOnClick(View v){
        RepairActivity.this.onBackPressed();

    }

    public void doneOnClick(View v){
        Intent intent = new Intent();
        intent.putExtra("solution", et_repair.getText().toString());
        intent.putExtra("problemId", problemId);

        setResult(RESULT_OK, intent);

        finish();
    }
}

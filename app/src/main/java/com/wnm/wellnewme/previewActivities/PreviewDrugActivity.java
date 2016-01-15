package com.wnm.wellnewme.previewActivities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.Utility.FetchAndCheck;
import com.wnm.wellnewme.Utility.PerformTask;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.information.databaseInformation;
import com.wnm.wellnewme.informationActivities.TobaccoDrugsActivity;

import java.util.ArrayList;

public class PreviewDrugActivity extends ActionBarActivity implements View.OnClickListener{

    TextView ans1,ans2,ans3,ans4,ans5;
    Button submit,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_drug);

        ans1 = (TextView)findViewById(R.id.answer1_drug);
        ans2 = (TextView)findViewById(R.id.answer2_drug);
        ans3 = (TextView)findViewById(R.id.answer3_drug);
        ans4 = (TextView)findViewById(R.id.answer4_drug);
        ans5 = (TextView)findViewById(R.id.answer5_drug);
        submit = (Button)findViewById(R.id.submit);
        cancel = (Button)findViewById(R.id.cancel);

        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        ans1.setText(bundle.getString("drug1"));
        ans2.setText(bundle.getString("drug2"));
        ans3.setText(bundle.getString("drug3"));
        ans4.setText(bundle.getString("drug4"));
        ans5.setText(bundle.getString("drug5"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview_drug, menu);
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
        switch (v.getId()){
            case R.id.submit:
                submitDrug();
                break;
            case R.id.cancel:
                startActivity(new Intent(PreviewDrugActivity.this, TobaccoDrugsActivity.class));
                break;
        }
    }

    private void submitDrug() {
        String answer1 = ans1.getText().toString();
        String answer2 = ans2.getText().toString();
        String answer3 = ans3.getText().toString();
        String answer4 = ans4.getText().toString();
        String answer5 = ans4.getText().toString();
        String done = FetchAndCheck.returnPercentValue();
        boolean chk = FetchAndCheck.checkDB();

        if(chk){
            Toast.makeText(PreviewDrugActivity.this, "Please fill your age and gender first", Toast.LENGTH_LONG).show();
            startActivity(new Intent(PreviewDrugActivity.this, StartActivity.class));
            finish();
        }else {

            String[] current = {answer1, answer2, answer3, answer4, answer5, "done3",done};
            MyApplication.getWriteAbleDb().updateDrug(current);

            new PerformTask(PreviewDrugActivity.this).Perform();
        }

    }
}

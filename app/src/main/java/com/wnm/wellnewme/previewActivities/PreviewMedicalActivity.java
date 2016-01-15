package com.wnm.wellnewme.previewActivities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.Utility.FetchAndCheck;
import com.wnm.wellnewme.Utility.PerformTask;
import com.wnm.wellnewme.activities.DashboardActivity;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.informationActivities.MedicalHistoryActivity;

public class PreviewMedicalActivity extends ActionBarActivity implements View.OnClickListener{

    TextView ans1,ans2,ans3,ans4;
    Button submit,cancel;
    UserLocalStorage userLocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_medical);

        ans1 = (TextView)findViewById(R.id.answer1_medical);
        ans2 = (TextView)findViewById(R.id.answer2_medical);
        ans3 = (TextView)findViewById(R.id.answer3_medical);
        ans4 = (TextView)findViewById(R.id.answer4_medical);
        submit = (Button)findViewById(R.id.submit);
        cancel = (Button)findViewById(R.id.cancel);

        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        userLocalStorage = new UserLocalStorage(PreviewMedicalActivity.this);

        Bundle bundle = getIntent().getExtras();
        ans1.setText(bundle.getString("medical1"));
        ans2.setText(bundle.getString("medical2"));
        ans3.setText(bundle.getString("medical3"));
        ans4.setText(bundle.getString("medical4"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview_medical, menu);
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
                submitMedical();
                break;
            case R.id.cancel:
                startActivity(new Intent(PreviewMedicalActivity.this, MedicalHistoryActivity.class));
                break;
        }
    }

    private void submitMedical() {
        String answer1 = ans1.getText().toString();
        String answer2 = ans2.getText().toString();
        String answer3 = ans3.getText().toString();
        String answer4 = ans4.getText().toString();
        String done = FetchAndCheck.returnPercentValue();
        boolean chk = FetchAndCheck.checkDB();

        if(chk){
            Toast.makeText(PreviewMedicalActivity.this, "Please fill your age and gender first", Toast.LENGTH_LONG).show();
            startActivity(new Intent(PreviewMedicalActivity.this, StartActivity.class));
            finish();
        }else {
            String[] current = {answer1, answer2, answer3, answer4, "done5",done};
            MyApplication.getWriteAbleDb().updateMedical(current);

            new PerformTask(PreviewMedicalActivity.this).Perform();

        }
    }
}

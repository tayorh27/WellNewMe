package com.wnm.wellnewme.informationActivities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;

import com.wnm.wellnewme.R;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.previewActivities.PreviewMedicalActivity;

public class MedicalHistoryActivity extends ActionBarActivity implements View.OnClickListener{

    Spinner q1,q2,q3,q4;
    Button preview,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        q1 = (Spinner)findViewById(R.id.spinner1_medical);
        q2 = (Spinner)findViewById(R.id.spinner2_medical);
        q3 = (Spinner)findViewById(R.id.spinner3_medical);
        q4 = (Spinner)findViewById(R.id.spinner4_medical);
        preview = (Button)findViewById(R.id.preview);
        cancel = (Button)findViewById(R.id.cancel);

        preview.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medical_history, menu);
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

    private int ReturnIndex(String value){
        int index = 0;
        String[] yes_no = getResources().getStringArray(R.array.yes_no);
        for(int i = 0; i < yes_no.length; i++){
            if(yes_no[i].contentEquals(value)){
                index = i;
            }
        }

        return index;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String question1 = q1.getSelectedItem().toString();
        String question2 = q2.getSelectedItem().toString();
        String question3 = q3.getSelectedItem().toString();
        String question4 = q4.getSelectedItem().toString();

        outState.putString("medical",question1);
        outState.putString("medical2",question2);
        outState.putString("medical3",question3);
        outState.putString("medical4",question4);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            String question1 = savedInstanceState.getString("medical1");
            String question2 = savedInstanceState.getString("medical2");
            String question3 = savedInstanceState.getString("medical3");
            String question4 = savedInstanceState.getString("medical4");

            q1.setSelection(ReturnIndex(question1),true);
            q2.setSelection(ReturnIndex(question2),true);
            q3.setSelection(ReturnIndex(question3),true);
            q4.setSelection(ReturnIndex(question4),true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.preview:
                previewDetails();
                break;
            case R.id.cancel:
                startActivity(new Intent(MedicalHistoryActivity.this, StartActivity.class));
                break;
        }
    }

    private void previewDetails() {

        String question1 = q1.getSelectedItem().toString();
        String question2 = q2.getSelectedItem().toString();
        String question3 = q3.getSelectedItem().toString();
        String question4 = q4.getSelectedItem().toString();

        Bundle bundle = new Bundle();
        bundle.putString("medical1",question1);
        bundle.putString("medical2",question2);
        bundle.putString("medical3",question3);
        bundle.putString("medical4",question4);

        Intent intent = new Intent(MedicalHistoryActivity.this, PreviewMedicalActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

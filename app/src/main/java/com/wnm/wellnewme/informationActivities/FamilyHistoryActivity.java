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
import com.wnm.wellnewme.previewActivities.PreviewFamilyActivity;

public class FamilyHistoryActivity extends ActionBarActivity implements View.OnClickListener{

    Spinner q1,q2,q3;
    Button preview,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_history);

        q1 = (Spinner)findViewById(R.id.spinner1_family);
        q2 = (Spinner)findViewById(R.id.spinner2_family);
        q3 = (Spinner)findViewById(R.id.spinner3_family);
        preview = (Button)findViewById(R.id.preview);
        cancel = (Button)findViewById(R.id.cancel);

        preview.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_family_history, menu);
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
            case R.id.preview:
                previewDetails();
                break;
            case R.id.cancel:
                startActivity(new Intent(FamilyHistoryActivity.this, StartActivity.class));
                break;
        }
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

        outState.putString("family1",question1);
        outState.putString("family2",question2);
        outState.putString("family3",question3);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            String question1 = savedInstanceState.getString("family1");
            String question2 = savedInstanceState.getString("family2");
            String question3 = savedInstanceState.getString("family3");

            q1.setSelection(ReturnIndex(question1),true);
            q2.setSelection(ReturnIndex(question2),true);
            q3.setSelection(ReturnIndex(question3),true);
        }
    }

    private void previewDetails() {

        String question1 = q1.getSelectedItem().toString();
        String question2 = q2.getSelectedItem().toString();
        String question3 = q3.getSelectedItem().toString();

        Bundle bundle = new Bundle();
        bundle.putString("family1",question1);
        bundle.putString("family2",question2);
        bundle.putString("family3",question3);

        Intent intent = new Intent(FamilyHistoryActivity.this, PreviewFamilyActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

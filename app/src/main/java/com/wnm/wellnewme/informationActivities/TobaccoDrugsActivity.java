package com.wnm.wellnewme.informationActivities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wnm.wellnewme.R;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.previewActivities.PreviewDrugActivity;

public class TobaccoDrugsActivity extends ActionBarActivity implements View.OnClickListener{

    EditText e1;
    Spinner q1,q2,q3,q4;
    Button preview,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobacco_drugs);

        q1 = (Spinner)findViewById(R.id.spinner1_drug);
        q2 = (Spinner)findViewById(R.id.spinner2_drug);
        q3 = (Spinner)findViewById(R.id.spinner4_drug);
        q4 = (Spinner)findViewById(R.id.spinner5_drug);
        e1 = (EditText)findViewById(R.id.editText_drug);
        preview = (Button)findViewById(R.id.preview1);
        cancel = (Button)findViewById(R.id.cancel1);

        preview.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tobacco_drugs, menu);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String question1 = q1.getSelectedItem().toString();
        String question2 = q2.getSelectedItem().toString();
        String question3 = q3.getSelectedItem().toString();
        String question4 = q4.getSelectedItem().toString();
        String question5 = e1.getText().toString();

        outState.putString("drug1",question1);
        outState.putString("drug2",question2);
        outState.putString("drug3",question3);
        outState.putString("drug4",question4);
        outState.putString("drug5",question5);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            String question1 = savedInstanceState.getString("drug1");
            String question2 = savedInstanceState.getString("drug2");
            String question3 = savedInstanceState.getString("drug3");
            String question4 = savedInstanceState.getString("drug4");
            String question5 = savedInstanceState.getString("drug5");

            e1.setText(question5);
            q1.setSelection(ReturnIndexSMK(question1),true);
            q2.setSelection(ReturnIndexFM(question2),true);
            q3.setSelection(ReturnIndex(question3),true);
            q4.setSelection(ReturnIndex(question4),true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.preview1:
                previewDrugsDetails();
                break;
            case R.id.cancel1:
                startActivity(new Intent(TobaccoDrugsActivity.this, StartActivity.class));
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

    private int ReturnIndexSMK(String value){
        int index = 0;
        String[] diet = getResources().getStringArray(R.array.smoke);
        for(int i = 0; i < diet.length; i++){
            if(diet[i].contentEquals(value)){
                index = i;
            }
        }

        return index;
    }

    private int ReturnIndexFM(String value){
        int index = 0;
        String[] diet = getResources().getStringArray(R.array.forms);
        for(int i = 0; i < diet.length; i++){
            if(diet[i].contentEquals(value)){
                index = i;
            }
        }

        return index;
    }

    private void previewDrugsDetails() {
        String question1 = q1.getSelectedItem().toString();
        String question2 = q2.getSelectedItem().toString();
        String question3 = q3.getSelectedItem().toString();
        String question4 = q4.getSelectedItem().toString();
        String question5 = e1.getText().toString();
        if(question5.contentEquals("")){
            question5 = "0";
        }

        Bundle bundle = new Bundle();
        bundle.putString("drug1",question1);
        bundle.putString("drug2",question2);
        bundle.putString("drug3",question3);
        bundle.putString("drug4",question4);
        bundle.putString("drug5",question5);

        Intent intent = new Intent(TobaccoDrugsActivity.this, PreviewDrugActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

package com.wnm.wellnewme.informationActivities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.information.databaseInformation;

import java.util.ArrayList;
import java.util.Calendar;

public class AgeGenderActivity extends ActionBarActivity implements View.OnClickListener{

    Button setdate,add,cancel;
    Spinner gender;

    String date_of_birth = "";

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_gender);

        setdate = (Button)findViewById(R.id.setDate);
        add = (Button)findViewById(R.id.add);
        cancel = (Button)findViewById(R.id.cancel);
        gender = (Spinner)findViewById(R.id.select_gender);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        setdate.setOnClickListener(this);
        add.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void showDate(int year, int month, int day) {
        date_of_birth = String.valueOf(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

        setdate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_age_gender, menu);
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
        switch(v.getId()){
            case R.id.cancel:
                startActivity(new Intent(AgeGenderActivity.this, StartActivity.class));
                break;
            case R.id.setDate:
                popupDate();
                break;
            case R.id.add:
                addtodatabase();
                break;
        }
    }

    private void addtodatabase() {
        String get_gender = gender.getSelectedItem().toString();
        final ArrayList<databaseInformation> customData = new ArrayList<>();
        databaseInformation current = new databaseInformation(date_of_birth,get_gender,"done1","20","","","","","",
                "","","","","","",
                "","","","",
                "","","","","");
        customData.add(current);
        MyApplication.getWriteAbleDb().insertDB(customData,false);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                   MyApplication.getWriteAbleDb().insertDB(customData,false);
                }
            }
        };
        thread.start();
        //ArrayList<databaseInformation> customList = MyApplication.getWriteAbleDb().getAllData();
        //Toast.makeText(AgeGenderActivity.this, customList.get(0).percent, Toast.LENGTH_LONG).show();

        startActivity(new Intent(AgeGenderActivity.this, DietAndExerciseActivity.class));
        finish();
    }

    private void popupDate() {
         showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == 999){
            return new DatePickerDialog(this,mDate,year,month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            showDate(year,monthOfYear,dayOfMonth);
        }
    };
}

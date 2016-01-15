package com.wnm.wellnewme.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wnm.wellnewme.LocalDatabase.User;
import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.adapters.ListAdapter;
import com.wnm.wellnewme.information.databaseInformation;
import com.wnm.wellnewme.informationActivities.AgeGenderActivity;
import com.wnm.wellnewme.informationActivities.DietAndExerciseActivity;
import com.wnm.wellnewme.informationActivities.FamilyHistoryActivity;
import com.wnm.wellnewme.informationActivities.MedicalHistoryActivity;
import com.wnm.wellnewme.informationActivities.TobaccoDrugsActivity;

import java.util.ArrayList;

public class StartActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    TextView percent;
    UserLocalStorage userLocalStorage;
    ListAdapter adapter;
    ArrayList<databaseInformation> customList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        userLocalStorage = new UserLocalStorage(StartActivity.this);
        User user = userLocalStorage.getLoggedInUser();
        //getSupportActionBar().setTitle(user.firstname +" "+ user.lastname);
        adapter = new ListAdapter(StartActivity.this);

        listView = (ListView)findViewById(R.id.lists);
        percent = (TextView)findViewById(R.id.percent);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        customList = MyApplication.getWriteAbleDb().getAllData();
        if(!customList.isEmpty()){
            databaseInformation current = customList.get(0);
            percent.setText(current.percent+"%");
            //Toast.makeText(StartActivity.this, "start percent",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if(userLocalStorage.getCompleted() == true){
//            startActivity(new Intent(StartActivity.this, DashboardActivity.class));
//        }
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(StartActivity.this, AgeGenderActivity.class));
                break;
            case 1:
                startActivity(new Intent(StartActivity.this, DietAndExerciseActivity.class));
                break;
            case 2:
                startActivity(new Intent(StartActivity.this, TobaccoDrugsActivity.class));
                break;
            case 3:
                startActivity(new Intent(StartActivity.this, FamilyHistoryActivity.class));
                break;
            case 4:
                startActivity(new Intent(StartActivity.this, MedicalHistoryActivity.class));
                break;
        }
    }
}

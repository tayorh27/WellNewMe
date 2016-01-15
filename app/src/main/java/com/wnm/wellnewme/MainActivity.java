package com.wnm.wellnewme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.activities.DashboardActivity;
import com.wnm.wellnewme.activities.LoginActivity;
import com.wnm.wellnewme.activities.SignupActivity;
import com.wnm.wellnewme.activities.StartActivity;
import com.wnm.wellnewme.informationActivities.DietAndExerciseActivity;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button signup2;
    UserLocalStorage userLocalStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStorage = new UserLocalStorage(MainActivity.this);

        signup2 = (Button)findViewById(R.id.btn_signup2);

        signup2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signup2:
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(userLocalStorage.getUserLoggedIn() == true){
            startActivity(new Intent(MainActivity.this, StartActivity.class));
        }else if(userLocalStorage.getCompleted() == "true"){
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
        }
    }
}

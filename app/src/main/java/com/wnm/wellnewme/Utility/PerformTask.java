package com.wnm.wellnewme.Utility;

import android.content.Context;
import android.content.Intent;

import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.activities.DashboardActivity;
import com.wnm.wellnewme.activities.StartActivity;

/**
 * Created by tayo on 11/12/2015.
 */
public class PerformTask {

    private Context context;
    private UserLocalStorage userLocalStorage;
    public PerformTask(Context context){
        this.context = context;
        userLocalStorage = new UserLocalStorage(context);
    }

    public void Perform(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    String checkPercent = FetchAndCheck.returnCurrentPercentValue();
                    if(checkPercent.contentEquals("100")){
                        userLocalStorage.setCompleted(true);
                        context.startActivity(new Intent(context, DashboardActivity.class));
                        //this.finish();
                    }else{
                        context.startActivity(new Intent(context, StartActivity.class));
                        //context.finish();
                    }
                }
            }
        };
        thread.start();
    }
}

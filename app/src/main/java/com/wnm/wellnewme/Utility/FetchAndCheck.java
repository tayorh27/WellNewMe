package com.wnm.wellnewme.Utility;

import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.information.databaseInformation;

import java.util.ArrayList;

/**
 * Created by tayo on 11/12/2015.
 */
public class FetchAndCheck {

    public static String returnPercentValue(){

        ArrayList<databaseInformation> customList = new ArrayList<>();
        customList = MyApplication.getWriteAbleDb().getAllData();
        int id = customList.size() - 1;
        String value = "";

        if(customList != null && !customList.isEmpty()){
            String percent = customList.get(id).percent;

            if(percent.contentEquals("20")){
                value = "40";
            }
            if(percent.contentEquals("40")){
                value = "60";
            }
            if(percent.contentEquals("60")){
                value = "80";
            }
            if(percent.contentEquals("80")){
                value = "100";
            }
        }
        return value;
    }

    public static String returnCurrentPercentValue(){

        ArrayList<databaseInformation> customList = new ArrayList<>();
        customList = MyApplication.getWriteAbleDb().getAllData();
        int id = customList.size() - 1;
        String value = "";

        if(customList != null && !customList.isEmpty()){
            String percent = customList.get(id).percent;

            value = percent;
        }
        return value;
    }

    public static boolean checkDB(){
        boolean avail;
            ArrayList<databaseInformation> customList = new ArrayList<>();
            customList = MyApplication.getWriteAbleDb().getAllData();

            if (customList.isEmpty()) {
                avail = true;//database not created
            } else {
                avail = false;
            }
        return avail;
    }
}

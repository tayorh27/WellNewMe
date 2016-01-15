package com.wnm.wellnewme.SqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.wnm.wellnewme.information.databaseInformation;

import java.util.ArrayList;

/**
 * Created by tayo on 11/10/2015.
 */
public class WellNewMeLocalDb {

    private Helper myHelper;
    private SQLiteDatabase mDatabase;
    private Context mcontext;

    public WellNewMeLocalDb(Context context) {
        this.mcontext = context;
        myHelper = new Helper(context);
        mDatabase = myHelper.getWritableDatabase();
    }

    public ArrayList<databaseInformation> getAllData(){

        ArrayList<databaseInformation> customData = new ArrayList<>();
        String[] columns = {
                Helper.COLUMN_ID,
                Helper.COLUMN_DOB,
                Helper.COLUMN_GENDER,
                Helper.COLUMN_DONE1,
                Helper.COLUMN_DONE2,
                Helper.COLUMN_DONE3,
                Helper.COLUMN_DONE4,
                Helper.COLUMN_DONE5,
                Helper.COLUMN_PERCENT,
                Helper.COLUMN_DIET1,
                Helper.COLUMN_DIET2,
                Helper.COLUMN_DIET3,
                Helper.COLUMN_DIET4,
                Helper.COLUMN_DRUG1,
                Helper.COLUMN_DRUG2,
                Helper.COLUMN_DRUG3,
                Helper.COLUMN_DRUG4,
                Helper.COLUMN_DRUG5,Helper.COLUMN_FAMILY1,Helper.COLUMN_FAMILY2,Helper.COLUMN_FAMILY3,
                Helper.COLUMN_MEDICAL1,Helper.COLUMN_MEDICAL2,Helper.COLUMN_MEDICAL3,Helper.COLUMN_MEDICAL4
        };

        Cursor cursor = mDatabase.query(Helper.TABLE_NAME_WELL,columns,null,null,null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            while (cursor.moveToNext()){
                databaseInformation current = new databaseInformation();
                current.date_of_birth = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DOB));
                current.gender = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_GENDER));
                current.done1 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DONE1));
                current.done2 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DONE2));
                current.done3 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DONE3));
                current.done4 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DONE4));
                current.done5 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DONE5));
                current.percent = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_PERCENT));
                current.diet1 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DIET1));
                current.diet2 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DIET2));
                current.diet3 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DIET3));
                current.diet4 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DIET4));
                current.drug1 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DRUG1));
                current.drug2 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DRUG2));
                current.drug3 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DRUG3));
                current.drug4 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DRUG4));
                current.drug5 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_DRUG5));
                current.family1 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_FAMILY1));
                current.family2 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_FAMILY2));
                current.family3 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_FAMILY3));
                current.medical1 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_MEDICAL1));
                current.medical2 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_MEDICAL2));
                current.medical3 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_MEDICAL3));
                current.medical4 = cursor.getString(cursor.getColumnIndex(Helper.COLUMN_MEDICAL4));


                customData.add(current);

            }
            cursor.close();
        }


        return customData;
    }

    public int getLastID(){
        int id = 0;
        String[] columns = {
                Helper.COLUMN_ID, Helper.COLUMN_DOB,Helper.COLUMN_GENDER,Helper.COLUMN_DONE1,Helper.COLUMN_DONE2,Helper.COLUMN_DONE3,Helper.COLUMN_DONE4,Helper.COLUMN_DONE5,Helper.COLUMN_PERCENT,
                Helper.COLUMN_DIET1,Helper.COLUMN_DIET2,Helper.COLUMN_DIET3,Helper.COLUMN_DIET4,
                Helper.COLUMN_DRUG1, Helper.COLUMN_DRUG2,Helper.COLUMN_DRUG3,Helper.COLUMN_DRUG4,Helper.COLUMN_DRUG5,
                Helper.COLUMN_FAMILY1,Helper.COLUMN_FAMILY2,Helper.COLUMN_FAMILY3,
                Helper.COLUMN_MEDICAL1,Helper.COLUMN_MEDICAL2,Helper.COLUMN_MEDICAL3,Helper.COLUMN_MEDICAL4
        };
        Cursor cursor = mDatabase.query(Helper.TABLE_NAME_WELL,columns,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToLast();
            id = cursor.getInt(0);
        }

        return id;
    }

    public void insertDB(ArrayList<databaseInformation> list, boolean clearPrevious) {
        if (clearPrevious) {
            deleteDB();
        }
        String sql = "INSERT INTO " + Helper.TABLE_NAME_WELL + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        //compile statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            databaseInformation current = list.get(i);
            statement.clearBindings();

            statement.bindString(2, current.date_of_birth);
            statement.bindString(3, current.gender);
            statement.bindString(4, current.done1);
            statement.bindString(5, current.done2);
            statement.bindString(6, current.done3);
            statement.bindString(7, current.done4);
            statement.bindString(8, current.done5);
            statement.bindString(9, current.percent);

            statement.bindString(10, current.diet1);
            statement.bindString(11, current.diet2);
            statement.bindString(12, current.diet3);
            statement.bindString(13, current.diet4);

            statement.bindString(14,current.drug1);
            statement.bindString(15,current.drug2);
            statement.bindString(16,current.drug3);
            statement.bindString(17,current.drug4);
            statement.bindString(18,current.drug5);

            statement.bindString(19,current.family1);
            statement.bindString(20,current.family2);
            statement.bindString(21,current.family3);

            statement.bindString(22,current.medical1);
            statement.bindString(23,current.medical2);
            statement.bindString(24,current.medical3);
            statement.bindString(25,current.medical4);


            statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();

        Log.d("DB", "Inserted");
        //Toast.makeText(mcontext, "Inserted", Toast.LENGTH_LONG).show();
    }

    public void updateDiet(String[] getDiet){

        int id = getLastID();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.COLUMN_DIET1,getDiet[0]);
        contentValues.put(Helper.COLUMN_DIET2,getDiet[1]);
        contentValues.put(Helper.COLUMN_DIET3,getDiet[2]);
        contentValues.put(Helper.COLUMN_DIET4,getDiet[3]);
        contentValues.put(Helper.COLUMN_DONE2,getDiet[4]);
        contentValues.put(Helper.COLUMN_PERCENT,getDiet[5]);


        mDatabase.update(Helper.TABLE_NAME_WELL,contentValues,Helper.COLUMN_ID+"="+id,null);
        Log.d("updateDiet", "Diet updated"+getDiet[3]);
        //Toast.makeText(mcontext,"Diet updated"+getDiet[3],Toast.LENGTH_LONG).show();
    }

    public void updateDrug(String[] getDrug){

        int id = getLastID();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.COLUMN_DRUG1,getDrug[0]);
        contentValues.put(Helper.COLUMN_DRUG2,getDrug[1]);
        contentValues.put(Helper.COLUMN_DRUG3,getDrug[2]);
        contentValues.put(Helper.COLUMN_DRUG4,getDrug[3]);
        contentValues.put(Helper.COLUMN_DRUG5,getDrug[4]);
        contentValues.put(Helper.COLUMN_DONE3,getDrug[5]);
        contentValues.put(Helper.COLUMN_PERCENT,getDrug[6]);

        mDatabase.update(Helper.TABLE_NAME_WELL,contentValues,Helper.COLUMN_ID+"="+id,null);
        Log.d("updateDrug", "Diet updated"+getDrug[5]);
        //Toast.makeText(mcontext,"Drug updated"+getDrug[5],Toast.LENGTH_LONG).show();
    }

    public void updateFamily(String[] getFamily){

        int id = getLastID();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.COLUMN_FAMILY1,getFamily[0]);
        contentValues.put(Helper.COLUMN_FAMILY2,getFamily[1]);
        contentValues.put(Helper.COLUMN_FAMILY3,getFamily[2]);
        contentValues.put(Helper.COLUMN_DONE4,getFamily[3]);
        contentValues.put(Helper.COLUMN_PERCENT,getFamily[4]);


        mDatabase.update(Helper.TABLE_NAME_WELL,contentValues,Helper.COLUMN_ID+"="+id,null);
        Log.d("updateDiet", "Diet updated"+getFamily[3]);
        //Toast.makeText(mcontext,"Diet updated"+getDiet[3],Toast.LENGTH_LONG).show();
    }

    public void updateMedical(String[] getMedical){

        int id = getLastID();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.COLUMN_MEDICAL1,getMedical[0]);
        contentValues.put(Helper.COLUMN_MEDICAL2,getMedical[1]);
        contentValues.put(Helper.COLUMN_MEDICAL3,getMedical[2]);
        contentValues.put(Helper.COLUMN_MEDICAL4,getMedical[3]);
        contentValues.put(Helper.COLUMN_DONE5,getMedical[4]);
        contentValues.put(Helper.COLUMN_PERCENT,getMedical[5]);


        mDatabase.update(Helper.TABLE_NAME_WELL,contentValues,Helper.COLUMN_ID+"="+id,null);
        Log.d("updateDiet", "Diet updated"+getMedical[3]);
        //Toast.makeText(mcontext,"Diet updated"+getDiet[3],Toast.LENGTH_LONG).show();
    }

    private void deleteDB() {
        mDatabase.delete(Helper.TABLE_NAME_WELL,null,null);
    }


    public class Helper extends SQLiteOpenHelper {

        private Context mcontext;
        private static final String DB_NAME = "wellnewme_db";
        private static final int DB_VERSION = 1;

        public static final String TABLE_NAME_WELL = "welldb";
        public static final String COLUMN_ID = "id";

        public static final String COLUMN_DOB = "date_of_birth";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_DONE1 = "done1";
        public static final String COLUMN_DONE2 = "done2";
        public static final String COLUMN_DONE3 = "done3";
        public static final String COLUMN_DONE4 = "done4";
        public static final String COLUMN_DONE5 = "done5";
        public static final String COLUMN_PERCENT = "percent";
        public static final String COLUMN_DIET1 = "diet_1";
        public static final String COLUMN_DIET2 = "diet_2";
        public static final String COLUMN_DIET3 = "diet_3";
        public static final String COLUMN_DIET4 = "diet_4";
        public static final String COLUMN_DRUG1 = "drug_1";
        public static final String COLUMN_DRUG2 = "drug_2";
        public static final String COLUMN_DRUG3 = "drug_3";
        public static final String COLUMN_DRUG4 = "drug_4";
        public static final String COLUMN_DRUG5 = "drug_5";
        public static final String COLUMN_FAMILY1 = "family1";
        public static final String COLUMN_FAMILY2 = "family2";
        public static final String COLUMN_FAMILY3 = "family3";
        public static final String COLUMN_MEDICAL1 = "medical1";
        public static final String COLUMN_MEDICAL2 = "medical2";
        public static final String COLUMN_MEDICAL3 = "medical3";
        public static final String COLUMN_MEDICAL4 = "medical4";


        private static final String CREATE_TABLE_WELL = "CREATE TABLE " + TABLE_NAME_WELL + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DOB + " TEXT," +
                COLUMN_GENDER + " TEXT," +
                COLUMN_DONE1 + " TEXT," +
                COLUMN_DONE2 + " TEXT," +
                COLUMN_DONE3 + " TEXT," +
                COLUMN_DONE4 + " TEXT," +
                COLUMN_DONE5 + " TEXT," +
                COLUMN_PERCENT + " TEXT," +
                COLUMN_DIET1 + " TEXT," +
                COLUMN_DIET2 + " TEXT," +
                COLUMN_DIET3 + " TEXT," +
                COLUMN_DIET4 + " TEXT," +
                COLUMN_DRUG1 + " TEXT," +
                COLUMN_DRUG2 + " TEXT," +
                COLUMN_DRUG3 + " TEXT," +
                COLUMN_DRUG4 + " TEXT," +
                COLUMN_DRUG5 + " TEXT," +
                COLUMN_FAMILY1 + " TEXT," +
                COLUMN_FAMILY2 + " TEXT," +
                COLUMN_FAMILY3 + " TEXT," +
                COLUMN_MEDICAL1 + " TEXT," +
                COLUMN_MEDICAL2 + " TEXT," +
                COLUMN_MEDICAL3 + " TEXT," +
                COLUMN_MEDICAL4 + " TEXT" +
                ");";

        public Helper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_WELL);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(" DROP TABLE " + TABLE_NAME_WELL + " IF EXISTS;");
                onCreate(db);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }
}

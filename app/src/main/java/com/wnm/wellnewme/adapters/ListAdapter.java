package com.wnm.wellnewme.adapters;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wnm.wellnewme.MyApplication;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.information.databaseInformation;

import java.util.ArrayList;

/**
 * Created by tayo on 11/9/2015.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    String[] options;
    int[] drawables;
    ArrayList<databaseInformation> customList;

    public ListAdapter(Context context){
        this.context = context;
        options = context.getResources().getStringArray(R.array.options);
        drawables = context.getResources().getIntArray(R.array.done);
        customList = MyApplication.getWriteAbleDb().getAllData();
    }
    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return options[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row =  null;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_list,parent,false);
        }else{
            row = convertView;
        }
        TextView textView = (TextView)row.findViewById(R.id.textView1);
        ImageView imageView = (ImageView)row.findViewById(R.id.imageView1);
        textView.setText(options[position]);

        int id = customList.size() - 1;
        if(id >= 0) {
            databaseInformation current = customList.get(id);
            switch (position) {
                case 0:
                    if (!customList.isEmpty()) {
                        if (current.done1.contentEquals("done1")) {
                            imageView.setImageResource(drawables[0]);
                        }
                    }
                    break;
                case 1:
                    if (!customList.isEmpty()) {
                        if (current.done1.contentEquals("done2")) {
                            imageView.setImageResource(drawables[0]);
                        }
                    }
                    break;
                case 2:
                    if (!customList.isEmpty()) {
                        if (current.done1.contentEquals("done3")) {
                            imageView.setImageResource(drawables[0]);
                        }
                    }
                    break;
                case 3:
                    if (!customList.isEmpty()) {
                        if (current.done1.contentEquals("done4")) {
                            imageView.setImageResource(drawables[0]);
                        }
                    }
                    break;
                case 4:
                    if (!customList.isEmpty()) {
                        if (current.done1.contentEquals("done5")) {
                            imageView.setImageResource(drawables[0]);
                        }
                    }
                    break;
            }
        }

        return row;
    }
}

package com.example.firstassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.number.IntegerWidth;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.firstassignment.Constants.*;

public class    Fragment_Top10 extends Fragment {

    private ListView top10_LSTVEW_theList;
    private CallBack_Top10 callBack_top10;

    public void setCallBack_top10(CallBack_Top10 _callBack_top10) {
        this.callBack_top10 = _callBack_top10;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top10, container, false);
        findViews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        String topTen = MySP.getInstance().getString("topTenJson", "{}");
        Gson gson = new Gson();
        TopTen topTenFromJson = gson.fromJson(topTen, TopTen.class);
        ArrayList<Record> theArray = topTenFromJson.getRecords();
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        int topTenSize = theArray.size() > 10 ? 10 : theArray.size();
        for (int i=0 ; i<topTenSize; i++) {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("name", theArray.get(i).getName());
            if (theArray.get(i).getScore() == -1) {
                tempMap.put("score", "");
            }
            else {
                tempMap.put("score", theArray.get(i).getScore() + "");
            }
            data.add(tempMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), data,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "score"},
                new int[]{android.R.id.text1,
                        android.R.id.text2});
        top10_LSTVEW_theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if (callBack_top10 != null) {
                    callBack_top10.displayLocation(theArray.get(position).getName(), theArray.get(position).getScore(), theArray.get(position).getLat(), theArray.get(position).getLon());
                }
            }
        });
        top10_LSTVEW_theList.setAdapter(adapter);

    }

    private void findViews(View view) {
        top10_LSTVEW_theList = view.findViewById(R.id.top10_LSTVEW_theList);
    }
}
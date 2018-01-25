package com.sheygam.masa_g2_25_01_18;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by gregorysheygam on 25/01/2018.
 */

public class MyPage extends Fragment {
    private static final String TAG = "PAGE";
    private String title;
    private int color;

    public static MyPage newInstance(String title){
        MyPage page = new MyPage();
        page.title = title;
        return page;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach: " + title);
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random rnd = new Random();
        color = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: " + title);
        View view = inflater.inflate(R.layout.my_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(color);
        TextView titleTxt = view.findViewById(R.id.title_txt);
        titleTxt.setText(title);
        
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: " + title);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: " + title);
        super.onDetach();
    }
}

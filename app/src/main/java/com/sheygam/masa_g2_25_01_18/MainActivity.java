package com.sheygam.masa_g2_25_01_18;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager myPager;
    private MyAdapter adapter;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            names.add("Name " + i);
        }
        myPager = findViewById(R.id.my_pager);
        adapter = new MyAdapter(getSupportFragmentManager());
        myPager.setAdapter(adapter);
        myPager.setOffscreenPageLimit(3);

        findViewById(R.id.next_btn)
                .setOnClickListener(this);
        findViewById(R.id.prev_btn)
                .setOnClickListener(this);
        findViewById(R.id.add_btn)
                .setOnClickListener(this);

        myPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("SCROLLED", "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("SELECTED", "onPageSelected() called with: position = [" + position + "]");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.d("SELECTED", "onPageScrollStateChanged: IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log.d("SELECTED", "onPageScrollStateChanged: DRAGGING");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        Log.d("SELECTED", "onPageScrollStateChanged: SETTLING");
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.next_btn) {
            int currentPosition = myPager.getCurrentItem();
            if (currentPosition < adapter.getCount() - 1) {
//                myPager.setCurrentItem(currentPosition+1);
                myPager.setCurrentItem(10, true);
            }
        } else if (v.getId() == R.id.prev_btn) {
            int currentPosition = myPager.getCurrentItem();
            if (currentPosition > 0) {
//                myPager.setCurrentItem(currentPosition-1);
                myPager.setCurrentItem(1, false);
            }
        } else if (v.getId() == R.id.add_btn) {
            names.add("New name");
            adapter.notifyDataSetChanged();
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return MyPage.newInstance(names.get(position));
        }

        @Override
        public int getCount() {
            return names.size();
        }
    }
}

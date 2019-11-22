package com.example.quack;

import android.content.Context;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private int currectFragment = 0;
    private Context mContext;
    private String tabTitles[] = new String[] { "Earthquakes", "Tsunamis" };
    // VIEW PAGER.................
    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            return new EarthquakeFragment();
        }
        else if(position == 1)
        {
            return new TsunamiFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        currectFragment = position;
        return tabTitles[position];
    }
    public int getCurrectFragment(){
        return currectFragment;
    }

}

package com.venkata.sai.pavan.phonecop;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by KVR on 1/10/2017.
 */

public class mainScreenPagerAdapter extends FragmentStatePagerAdapter{

    public static int NUM_PAGES = 2;
    private static String[] page_headers = {"IMAGES","AUDIO"};

    public mainScreenPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case  0: // The First Fragment and default fragment shown on the screen i.e. Images fragment.
                return ImagesFragmentOfViewPager.newInstance("dummy data1","dummy data2");

            case  1: // The second Fragment i.e. Audio fragment.
                return AudioFragmentOfViewPager.newInstance("data dummy3","data dummy4");

            default:
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return page_headers[position];
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

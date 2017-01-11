package com.venkata.sai.pavan.phonecop;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by KVR on 1/10/2017.
 */

public class mainScreenPagerAdapter extends FragmentPagerAdapter {

    public static int NUM_PAGES = 2;

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

        if (position == 0)
            return "IMAGES";
        else if (position == 1)
            return "AUDIO";

        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}

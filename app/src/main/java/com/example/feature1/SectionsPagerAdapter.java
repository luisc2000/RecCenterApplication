package com.example.feature1;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.feature1.Fragment1;
import com.example.feature1.Fragment2;
import com.example.feature1.Fragment3;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    // todo: get dates for TAB_TITLES
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.day_1, R.string.day_2, R.string.day_3};
    private final Context mContext;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(position + 1);

        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new Fragment1();

                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
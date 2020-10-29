package cg.mokano.bzv.covid.services.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOKANO on 31/07/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragList = new ArrayList<>();
    private final List<String> mFragTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragList.get(position);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragList.add(fragment);
        mFragTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragTitleList.get(position);
    }
}

package ambe.com.vn.bachkhoaxanh.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by AMBE on 19/03/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.arrayList=arrayList;
    }


    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}

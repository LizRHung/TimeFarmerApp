package project.tthree;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import layout.RankFragment1;
import layout.RankFragment2;
import layout.TabFragment2;
import layout.TabFragment3;
import layout.TabFragment4;
import layout.TabFragment1;


public class PagerAdapter2 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter2(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RankFragment1 rank1 = new RankFragment1();
                return rank1;
            case 1:
                RankFragment2 rank2 = new RankFragment2();
                return rank2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

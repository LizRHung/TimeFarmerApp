package project.tthree;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TutorialActivity extends AppCompatActivity {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));
    }
    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SampleFragment();
                case 1:
                    return  new SampleFragmentOne();
                case 2:
                    return new SampleFragmentTwo();
                case 3:
                    return new SampleFragmentThree();
                case 4:
                    return new SampleFragmentFour();
                case 5:
                    return new SampleFragmentFive();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}

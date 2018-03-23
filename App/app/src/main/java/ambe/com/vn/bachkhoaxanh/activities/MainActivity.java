package ambe.com.vn.bachkhoaxanh.activities;

import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.adapters.ViewPagerAdapter;
import ambe.com.vn.bachkhoaxanh.fragments.CaNhanFragment;
import ambe.com.vn.bachkhoaxanh.fragments.HomeFragment;
import ambe.com.vn.bachkhoaxanh.fragments.LichSuFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private ViewPager viewPager;
    private BottomNavigationView navigationView;
    private ArrayList<Fragment> arrFragment;
    private MenuItem prevMenuItem;
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;
    public static int widthScreen;
    public static int heightScreen;

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (prevMenuItem != null){
                prevMenuItem.setChecked(false);
            } else {
                navigationView.getMenu().getItem(0).setChecked(false);
            }

            navigationView.getMenu().getItem(position).setChecked(true);
            prevMenuItem=navigationView.getMenu().getItem(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_lich_su:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_ca_nhan:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvens();

       }

    private void addEvens() {
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);

    }

    private void addControls() {
        navigationView = findViewById(R.id.navigation);
        viewPager=findViewById(R.id.view_pager);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        arrFragment = new ArrayList<>();
        HomeFragment homeFragment=HomeFragment.newInstance();
        LichSuFragment lichSuFragment=LichSuFragment.newInstance();
        CaNhanFragment caNhanFragment=CaNhanFragment.newInstance();

        arrFragment.add(homeFragment);
        arrFragment.add(lichSuFragment);
        arrFragment.add(caNhanFragment);

        adapter=new ViewPagerAdapter(getSupportFragmentManager(),arrFragment);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        Display screensize = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        screensize.getSize(size);
        widthScreen = size.x;
        heightScreen = size.y;



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_loc){
            Toast.makeText(this, "Ấn vào đây", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}

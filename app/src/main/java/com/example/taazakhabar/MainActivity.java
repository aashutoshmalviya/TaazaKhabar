package com.example.taazakhabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mhome,mhealth,msports,mscience,mentertainment,mtechnology;
    PageAdapter pageAdapter;
    Toolbar mtoolbar;

    String apikey="c12f878cb81d4b918a7d1969aa9df2ff";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        mhome=findViewById(R.id.Home);
        mhealth=findViewById(R.id.Health);
        msports=findViewById(R.id.Sports);
        mscience=findViewById(R.id.Science);
        mentertainment=findViewById(R.id.Entertainment);
        mtechnology=findViewById(R.id.Technology);
        ViewPager viewPager=findViewById(R.id.fragment_container);
        tabLayout=findViewById(R.id.include);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if((tab.getPosition()>=0)&&(tab.getPosition()<=5))
                {
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
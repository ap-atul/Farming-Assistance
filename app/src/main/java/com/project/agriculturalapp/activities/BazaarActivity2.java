package com.project.agriculturalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.project.agriculturalapp.R;
import com.project.agriculturalapp.adapters.ViewPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class BazaarActivity2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    String state,district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazaar_information2);
        Intent i = getIntent();

        if(i!=null){
            state=i.getStringExtra(SurveyActivity.STATE);
            district=i.getStringExtra(SurveyActivity.DISTRICT);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),state,district);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        final TabLayout.Tab home = tabLayout.newTab();
        final TabLayout.Tab inbox = tabLayout.newTab();
        final TabLayout.Tab star = tabLayout.newTab();

        tabLayout.addTab(home, 0);
        tabLayout.addTab(inbox, 1);
        tabLayout.addTab(star, 2);

        tabLayout.setupWithViewPager(viewPager);

        Calendar cal2 = Calendar.getInstance();

        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(cal2.getTimeInMillis()));

        cal2.add(Calendar.DATE, -1);
        Date dt2 = new Date(cal2.getTimeInMillis());
        String date_pre = new SimpleDateFormat("dd/MM/yyyy").format(dt2);

        cal2.add(Calendar.DATE,-1);
        Date dt3 = new Date(cal2.getTimeInMillis());
        String date_pre_pre = new SimpleDateFormat("dd/MM/yyyy").format(dt3);

        tabLayout.getTabAt(0).setText(date_pre_pre);
        tabLayout.getTabAt(1).setText(date);
        tabLayout.getTabAt(2).setText(date_pre);

        tabLayout.getTabAt(1).select();

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.drawable.selector_colors));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

    }

}

package com.example.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

import Adapter.PageAdapter;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CheckAppointment extends AppCompatActivity {

    public String selectedDateStr;
    Calendar selected_date;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_appointment);

        getSupportActionBar().hide();

        //Calendar Code

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 2);
        final Calendar defaultSelectedDate = Calendar.getInstance();

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate,endDate)
                .datesNumberOnScreen(5)
                .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY,Color.BLACK)
                .selectorColor(Color.parseColor("#0999ED"))
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#0999ED"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                //if(selected_date.getTimeInMillis() != date.getTimeInMillis()){
                selected_date = date;
                selectedDateStr = selected_date.toString();
                selectedDateStr = DateFormat.format("dd_MM_yyyy", date).toString();
                Intent intent = new Intent("my-event");
                // You can also include some extra data.
                intent.putExtra("data", selectedDateStr);
                LocalBroadcastManager.getInstance(CheckAppointment.this).sendBroadcast(intent);
                //   }
/*
                selectedDateStr = DateFormat.format("EEE, d MMM yyyy", date).toString();
                apt_date.setText("Date : "+selectedDateStr);

                Log.d("abc", "selecteddatre " + selectedDateStr);
                Intent intent = new Intent("my-event");
                // You can also include some extra data.
                intent.putExtra("data", selectedDateStr);
                LocalBroadcastManager.getInstance(Appointment.this).sendBroadcast(intent);

 */
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });


        //Fragment code
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Morning"));
        tabLayout.addTab(tabLayout.newTab().setText("Evening"));

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, true);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

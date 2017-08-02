package com.example.martin.kanadiasuitetabs;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5;

    Timer timer = new Timer();
    TimerTask tt_c;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    BA_Kanadia BA_K_C = new BA_Kanadia();
    Bas_Can_Kanadia Bas_Can = new Bas_Can_Kanadia();
    BA_get_Var BgV = new BA_get_Var();

    Bas_Color BC = new Bas_Color();
    Bas_Sound BS = new Bas_Sound();
    Bas_gettextview Bgtv = new Bas_gettextview(this);

    android.os.Handler handler = new android.os.Handler();
    int tone_rast=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        BA_K_C.post_ba();
        Bas_Can.init_bas_can(getApplicationContext());
    }


    public void do_timer_task(){
        tt_c = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run () {

                        BgV.update_data();


                        Bgtv.get_textview_alt().setText(Float.toString(BgV.get_altitude()));
                        Bgtv.get_textview_speed().setText(Float.toString(BgV.get_speed()));
                        Bgtv.get_textview_poslat().setText(Float.toString(BgV.get_pos_Lat()));
                        Bgtv.get_textview_poslong().setText(Float.toString(BgV.get_pos_Long()));
                        Bgtv.get_textview_heading().setText(Float.toString(BgV.get_Heading()));
                        Bgtv.get_textview_gpsspeed().setText(Float.toString(BgV.get_gps_speed()));
                        Bgtv.get_textview_bodypitchr().setText(Float.toString(BgV.get_body_pitch_rate()));
                        Bgtv.get_textview_bodyrollr().setText(Float.toString(BgV.get_body_roll_rate()));
                        Bgtv.get_textview_bodyrolla().setText(Float.toString(BgV.get_body_roll_angle()));


                        BC.change_color();
                        if(tone_rast<10) {

                            tone_rast++;
                        }
                        else
                        {
                            BS.tone_warning();
                            tone_rast=0;
                        }



                    }
                });
            }
        };
        timer.schedule(tt_c,500,100);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab_2_Config_S tab2 = new Tab_2_Config_S();
                    return tab2;
                case 1:
                    Tab_1_Main tab1 = new Tab_1_Main();
                    return tab1;
                case 2:
                    Tab_3_Config_T tab3 = new Tab_3_Config_T();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Sound Konfiguration Warnung";
                case 1:
                    return "Main";
                case 2:
                    return "Text Konfiguration Warnung";
            }
            return null;
        }
    }

    public void on(View v)throws IOException{
        int ret_sta = BA_K_C.start_ba();
        if (ret_sta==1) {

            Toast.makeText(getApplicationContext(), "BT eingeschaltet",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "BT eingeschaltet", Toast.LENGTH_LONG).show();
        }

    }

    public void off(View v) throws Exception {
        try {
            BA_K_C.stop_ba();
            Toast.makeText(getApplicationContext(), "Stop Bluetooth ",Toast.LENGTH_LONG).show();
        }
        catch (IOException | NullPointerException poff){
            Toast.makeText(getApplicationContext(), "Bluetooth deaktiviert",Toast.LENGTH_LONG).show();
        }
    }

    public void start (View v) throws Exception{
        try {
            BA_K_C.paired_dev();
            Toast.makeText(getApplicationContext(), "Start Bluetooth ",Toast.LENGTH_LONG).show();
        }
        catch (IOException | NullPointerException p){
            Toast.makeText(getApplicationContext(), "Bluetooth nicht gefunden",Toast.LENGTH_LONG).show();
        }

    }
    public void show(View v) throws NullPointerException{
        do_timer_task();
        Toast.makeText(getApplicationContext(), "Start Update ",Toast.LENGTH_LONG).show();
    }
    public void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }
    public void stop(View v){
        tt_c.cancel();
        Toast.makeText(getApplicationContext(), "Stop Update ",Toast.LENGTH_LONG).show();
    }
    public Context getcont(){
        return getApplicationContext();
    }
}

package com.ftninformatika.zavrsniispit.Actvty;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.ftninformatika.zavrsniispit.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsFrag frag = new SettingsFrag();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, frag);
        transaction.commit();
    }



    public static class SettingsFrag extends PreferenceFragmentCompat {


        @Override
        public void onCreatePreferences(Bundle bundle, String s) {

        }
    }

}

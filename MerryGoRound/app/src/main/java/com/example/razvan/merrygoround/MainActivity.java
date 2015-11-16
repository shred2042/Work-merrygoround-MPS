package com.example.razvan.merrygoround;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread splashTimer = new Thread(){

            public void run(){

                try{
                    sleep(2000);
                    Intent menuIntent = new Intent("com.example.razvan.merrygoround.MENU");
                    startActivity(menuIntent);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    finish();
                }

            }

        };

        splashTimer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}

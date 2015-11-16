package com.example.razvan.merrygoround;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends ActionBarActivity {


    Button btn1;
    Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        final MediaPlayer sound = MediaPlayer.create(MainMenu.this,R.raw.button_sound);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn1.setText("pressed");
                sound.start();
                startActivity(new Intent("com.example.razvan.merrygoround.GAME"));


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn2.setText("pressed");
                sound.start();
                startActivity(new Intent("com.example.razvan.merrygoround.STATS"));

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

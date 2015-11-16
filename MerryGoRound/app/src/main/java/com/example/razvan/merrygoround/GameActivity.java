package com.example.razvan.merrygoround;

import android.app.Activity;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.HashMap;
import java.util.Timer;

/**
 * Created by Razvan on 11/12/2015.
 */
public class GameActivity extends Activity {

    private Resources resources;

    ImageButton ib1,ib2,ib3,ib4,ib5,ib6,ib7,ib8,ib9;
    ImageButton ibs[] = new ImageButton[9];
    TextView word;
    TextView timer;
    TextView text;
    Button ok_btn;
    Button del;

    Dice dices[] = new Dice[9];
    Letter letters[] = new Letter[9];

    String wordsGuessed[] = new String[30]; //maximum number of words that you can guess in 45 secconds = 30 words
    int words_idx=0;

    String gameStage=new String();
    Integer currentPressed[] = new Integer[90]; //ids of the ImageButtons that form the current word
    int currentPressed_idx = 0;

    int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        word = (TextView)findViewById(R.id.word);

        text = (TextView)findViewById(R.id.text);

        final MediaPlayer soundd = MediaPlayer.create(GameActivity.this,R.raw.button_sound);

        final String old_text = new String(text.getText().toString());

        String search=new String();

        gameStage = "ready"; // ready,started,ended

        timer = (TextView)findViewById(R.id.timer);

        final CountDownTimer time = new CountDownTimer(46000, 1000) {

            public void onTick(long millisUntilFinished) {

                timer.setText("" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                timer.setText("Time is up");
                gameStage = "ended";
                ok_btn.callOnClick();
                this.cancel();
            }
        };


        text.setText(old_text+"\n press ok to start");


        ib1 = (ImageButton)findViewById(R.id.btn1);
        ib2 = (ImageButton)findViewById(R.id.btn2);
        ib3 = (ImageButton)findViewById(R.id.btn3);
        ib4 = (ImageButton)findViewById(R.id.btn4);
        ib5 = (ImageButton)findViewById(R.id.btn5);
        ib6 = (ImageButton)findViewById(R.id.btn6);
        ib7 = (ImageButton)findViewById(R.id.btn7);
        ib8 = (ImageButton)findViewById(R.id.btn8);
        ib9 = (ImageButton)findViewById(R.id.btn9);

        ibs[0] = ib1;ibs[1]=ib2;ibs[2]=ib3;
        ibs[3] = ib4;ibs[4]=ib5;ibs[5]=ib6;
        ibs[6] = ib7;ibs[7]=ib8;ibs[8]=ib9;


        for(int i=0; i<9; ++i){

            dices[i] = new Dice("dice_"+i+".txt");

        }


        for(int i=0; i<9; ++i){

            final String pickedLetter = new String(dices[i].pickOne());
            letters[i] = new Letter(ibs[i],pickedLetter);
            letters[i].setImage(pickedLetter);

            letters[i].ib.setVisibility(View.INVISIBLE);

            letters[i].ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    view.setClickable(false);
                    view.setVisibility(View.INVISIBLE);
                    currentPressed[currentPressed_idx] = view.getId();
                    currentPressed_idx++;

                    if(gameStage.equals("started")) {
                        String old_word = word.getText().toString();
                        word.setText(old_word + pickedLetter);
                    }

                }
            });

        }


        InputStream iS;
        resources = getResources();

        //get the resource id from the file name
        int rID = resources.getIdentifier("words_1","raw",GameActivity.this.getPackageName());
        int rID2 = resources.getIdentifier("words_2","raw",GameActivity.this.getPackageName());
        int rID3 = resources.getIdentifier("words_3","raw",GameActivity.this.getPackageName());


        String words_1[] = new String[100000];
        String words_2[] = new String[75000];
        String words_3[] = new String[100000];

        //get the file as a stream
        iS = resources.openRawResource(rID);


        //create a buffer that has the same size as the InputStream
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[iS.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the text file as a stream, into the buffer
        try {
            iS.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //create a output stream to write the buffer into
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        try {
            oS.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        words_1 = oS.toString().split("\n");

        iS = resources.openRawResource(rID2);
        buffer = new byte[0];
        try {
            buffer = new byte[iS.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the text file as a stream, into the buffer
        try {
            iS.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create a output stream to write the buffer into
        oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        try {
            oS.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        words_2 = oS.toString().split("\n");


        iS = resources.openRawResource(rID3);
        buffer = new byte[0];
        try {
            buffer = new byte[iS.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the text file as a stream, into the buffer
        try {
            iS.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //create a output stream to write the buffer into
        oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        try {
            oS.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        words_3 = oS.toString().split("\n");


        //Close the Input and Output streams
        try {
            iS.close();
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        HashMap<String,Integer> hmap = new HashMap<>();


        for(String str:words_1){
            hmap.put(str,str.hashCode());
        }

        for(String str:words_2){
            hmap.put(str,str.hashCode());
        }

        for(String str:words_3){
            hmap.put(str,str.hashCode());
        }

        final HashMap<String,Integer> hm = new HashMap<>(hmap);



        ok_btn = (Button)findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {

            boolean first = true;
            boolean fraud = false;

            @Override
            public void onClick(View view) {
                //ok button pressed

                soundd.start();

                if(gameStage.equals("ready")){
                    //the games is just starting now
                    text.setText(old_text);
                    gameStage = "started";
                    wordsGuessed = new String[30];
                    words_idx=0;
                    ok_btn.setText("OK");
                    word.setText("");
                    score=0;

                    for(int i=0; i<letters.length;++i){
                        letters[i].ib.setVisibility(View.VISIBLE);
                    }


                    time.start();

                }

                if(gameStage.equals("started")){
                    //the game is ongoing , have to search for a word

                    fraud = false;

                    for(int i=0; i<words_idx; ++i) {
                        if (word.getText().toString().equals(wordsGuessed[i])){
                            fraud = true;
                            break;
                        }
                    }

                    if(hm.get(word.getText().toString()+"\r")!=null && !fraud){
                        //word found
                        wordsGuessed[words_idx] = word.getText().toString();
                        words_idx++;
                        switch(word.getText().toString().length()){

                            case 4: score+=4;break;
                            case 5: score+=4;break;
                            case 6: score+=7;break;
                            case 7: score+=7;break;
                            case 8: score+=15;break;
                            case 9: score+=17;break;

                        }

                        text.setText("You just guessed " + word.getText());
                        word.setText("");


                        for(int i=0; i<letters.length;++i){
                            letters[i].ib.setClickable(true);
                            letters[i].ib.setVisibility(View.VISIBLE);
                        }
                        currentPressed_idx=0;

                    }
                    else{

                        if(first){
                            first = false;
                        }
                        else{

                            if(!fraud) {
                                text.setText("word " + word.getText().toString() + " does not exist ! Try again");
                            }
                            else{
                                text.setText("Already guessed that word. Try another one");
                            }
                            word.setText("");

                            for(int i=0; i<letters.length;++i){
                                letters[i].ib.setClickable(true);
                                letters[i].ib.setVisibility(View.VISIBLE);
                            }
                            currentPressed_idx=0;

                        }


                    }


                }

                if(gameStage.equals("ended")){
                    //the game just ended, show score etc


                    String res = new String();
                    for(int i=0; i<words_idx-1; ++i){
                        res+=wordsGuessed[i];
                        res+=", ";
                    }
                    if(words_idx>0) {
                        res += wordsGuessed[words_idx - 1]; //don't use comma after last word
                    }

                    text.setText("Game ended ! Your score is : " + score + "\nyou guessed the words:\n" + res);

                    for(int i=0; i<letters.length;++i){
                        letters[i].ib.setVisibility(View.INVISIBLE);
                    }

                    ok_btn.setText("One more !");

                    gameStage = "replay";

                    currentPressed_idx = 0;

                }

                if(gameStage.equals("replay")){
                    //user clicked again to replay

                    for(int i=0; i<9; ++i) {
                        //get new letters
                        final String pickedLetter = new String(dices[i].pickOne());
                        letters[i] = new Letter(ibs[i], pickedLetter);
                        letters[i].setImage(pickedLetter);

                        letters[i].ib.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                view.setClickable(false);
                                view.setVisibility(View.INVISIBLE);
                                currentPressed[currentPressed_idx] = view.getId();
                                currentPressed_idx++;

                                if(gameStage.equals("started")) {
                                    String old_word = word.getText().toString();
                                    word.setText(old_word + pickedLetter);
                                }

                            }
                        });

                    }

                    gameStage = "ready";
                    first = true;


                }


            }
        });


        del = (Button)findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soundd.start();
                if(word.getText().toString().length()>0){

                    for(int i=0; i<letters.length;++i){
                        if(letters[i].ib.getId() == currentPressed[currentPressed_idx-1]){
                            letters[i].ib.setClickable(true);
                            letters[i].ib.setVisibility(View.VISIBLE);
                            currentPressed[currentPressed_idx--] = -1;
                            break;
                        }
                    }
                    word.setText(word.getText().toString().substring(0,word.getText().toString().length()-1));

                }
            }
        });


    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}

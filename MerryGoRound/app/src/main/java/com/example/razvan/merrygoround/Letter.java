package com.example.razvan.merrygoround;

import android.media.Image;
import android.widget.ImageButton;

/**
 * Created by Razvan on 11/12/2015.
 */
public class Letter {


    ImageButton ib;

    public Letter(ImageButton ib, String str) {
        this.ib = ib;
        this.str = str;
    }

    String str;

    int resid=0;

    public void setImage(String str){

        switch (str.charAt(0)){



            case 'a': resid = R.drawable.a;break;
            case 'b': resid = R.drawable.b;break;
            case 'c': resid = R.drawable.c;break;
            case 'd': resid = R.drawable.d;break;
            case 'e': resid = R.drawable.e;break;
            case 'f': resid = R.drawable.f;break;
            case 'g': resid = R.drawable.g;break;
            case 'h': resid = R.drawable.h;break;
            case 'i': resid = R.drawable.i;break;
            case 'j': resid = R.drawable.j;break;
            case 'k': resid = R.drawable.k;break;
            case 'l': resid = R.drawable.l;break;
            case 'm': resid = R.drawable.m;break;
            case 'n': resid = R.drawable.n;break;
            case 'o': resid = R.drawable.o;break;
            case 'p': resid = R.drawable.p;break;
            case 'q': resid = R.drawable.q;break;
            case 'r': resid = R.drawable.r;break;
            case 's': resid = R.drawable.s;break;
            case 't': resid = R.drawable.t;break;
            case 'u': resid = R.drawable.u;break;
            case 'v': resid = R.drawable.v;break;
            case 'w': resid = R.drawable.w;break;
            case 'x': resid = R.drawable.x;break;
            case 'y': resid = R.drawable.y;break;
            case 'z': resid = R.drawable.z;break;


        }

        ib.setImageResource(resid);

    }

    public ImageButton getIb() {
        return ib;
    }

    public String getStr() {
        return str;
    }

    public void setIb(ImageButton ib) {
        this.ib = ib;
    }

    public void setStr(String str) {
        this.str = str;
    }


}



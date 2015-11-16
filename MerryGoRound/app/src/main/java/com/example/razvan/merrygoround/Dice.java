package com.example.razvan.merrygoround;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by Razvan on 11/12/2015.
 */
public class Dice {

    String letters[] = new String[6];

    Random rn = new Random();

    public String pickOne(){
        return letters[rn.nextInt(120)%6];
    }

    public Dice(String filename) {
        this.load(filename);
    }

    public void load(String filename)  {


        if(filename.equals("dice_0.txt")){ //A A U I H J
            letters[0]="a";letters[1]="a";letters[2]="u";
            letters[3]="i";letters[4]="h";letters[5]="j";
        }

        if(filename.equals("dice_1.txt")){ // A A R C D M
            letters[0]="a";letters[1]="a";letters[2]="r";
            letters[3]="c";letters[4]="d";letters[5]="m";
        }

        if(filename.equals("dice_2.txt")){ //T R N S M B
            letters[0]="t";letters[1]="r";letters[2]="n";
            letters[3]="s";letters[4]="m";letters[5]="b";
        }

        if(filename.equals("dice_3.txt")){ //E E I O D F
            letters[0]="e";letters[1]="e";letters[2]="i";
            letters[3]="o";letters[4]="d";letters[5]="f";
        }

        if(filename.equals("dice_4.txt")){ //A E U S F V
            letters[0]="a";letters[1]="e";letters[2]="u";
            letters[3]="s";letters[4]="f";letters[5]="v";
        }

        if(filename.equals("dice_5.txt")){ //T L N P G C
            letters[0]="t";letters[1]="l";letters[2]="n";
            letters[3]="p";letters[4]="g";letters[5]="c";
        }

        if(filename.equals("dice_6.txt")){ //A I O E X Z
            letters[0]="a";letters[1]="i";letters[2]="o";
            letters[3]="e";letters[4]="x";letters[5]="z";
        }

        if(filename.equals("dice_7.txt")){ //N S T R G B
            letters[0]="n";letters[1]="s";letters[2]="t";
            letters[3]="r";letters[4]="g";letters[5]="b";
        }

        if(filename.equals("dice_8.txt")){ //I I U E L P
            letters[0]="i";letters[1]="i";letters[2]="u";
            letters[3]="e";letters[4]="l";letters[5]="p";
        }


    }

}

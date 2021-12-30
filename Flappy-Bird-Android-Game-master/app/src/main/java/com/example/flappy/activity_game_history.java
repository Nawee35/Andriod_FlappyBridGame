package com.example.flappy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class activity_game_history extends AppCompatActivity {
    private int n ,round,num,r ,loop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("History");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        String history = "score_history.txt";
        ArrayList<String> score = new ArrayList <String>();
        String inputString;
        String history1 = "dif_level.txt";
        ArrayList<String> score1 = new ArrayList <String>();
        String inputString1;
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(history)));
            BufferedReader inputReader1 = new BufferedReader(new InputStreamReader(openFileInput(history1)));
            while ((inputString = inputReader.readLine()) != null) {
                score.add(inputString);
                inputString1 = inputReader1.readLine();
                score1.add(inputString1);
            }
            loop = (score.size()/10)+1;
            StringBuffer stringBuffer[] = new StringBuffer[loop];
            StringBuffer stringBuffer1[] = new StringBuffer[loop];
            for(int i=0;i<loop;i++){
                stringBuffer[i] = new StringBuffer();
                stringBuffer1[i] = new StringBuffer();
            }
            num = score.size();
            round = num/10;
            r = 0;
            if(round == 0){
                round = 1;
            }
            else if(num%10 !=0){
                round ++;
            }
            n = 0;
            r = 1;
            if(num <= 10){
                for(int i=score.size()-1;i>=0;i--) {
                    stringBuffer[0].append(score.get(i) + "\n");
                    stringBuffer1[0].append(score1.get(i) + "\n");
                }
                n++;
            }
            else {
                for(int j=0;j<=round;j++) {
                    int first = (j*10);
                    if(j ==0) {
                        for (int i = num - 1; i >= num - 10; i--) {
                            stringBuffer[j].append(score.get(i) + "\n");
                            stringBuffer1[j].append(score1.get(i) + "\n");
                        }
                    }
                    else if((j == round) && ((num%10) != 0)){
                        for (int i=(num-first);i>=0;i--){
                            stringBuffer[j].append(score.get(i) + "\n");
                            stringBuffer1[j].append(score1.get(i) + "\n");
                        }
                    }
                    else{
                        if(num-(first+10) >= 0) {
                            for (int i = (num - first); i > (num - (first + 10)); i--) {
                                stringBuffer[j].append(score.get(i) + "\n");
                                stringBuffer1[j].append(score1.get(i) + "\n");
                            }
                        }
                        else{
                            for (int i = (num - first); i >= 0; i--) {
                                stringBuffer[j].append(score.get(i) + "\n");
                                stringBuffer1[j].append(score1.get(i) + "\n");
                            }
                        }
                    }

                }
            }
            final TextView page =(TextView)findViewById(R.id.textView6);
            page.setText( Integer.toString(n));
            final TextView text=(TextView)findViewById(R.id.score1);
            text.setText(stringBuffer[0].toString());
            final TextView text1=(TextView)findViewById(R.id.difficultylevel);
            text1.setText(stringBuffer1[0].toString());
            Button back = (Button) findViewById(R.id.button5);
            Button fornt = (Button) findViewById(R.id.button11);
            fornt.setOnClickListener(v ->{
                back.setEnabled(true);
                final TextView page1 =(TextView)findViewById(R.id.textView6);
                page1.setText( Integer.toString(r+1));
//                final TextView text2=(TextView)findViewById(R.id.score1);
                text.setText(stringBuffer[r].toString());
//                final TextView text3=(TextView)findViewById(R.id.difficultylevel);
                text1.setText(stringBuffer1[r].toString());
                r++;
                if(r == loop){
                    fornt.setEnabled(false);
                }
            });
            back.setOnClickListener(v ->{
                fornt.setEnabled(true);
                r--;
                final TextView page1 =(TextView)findViewById(R.id.textView6);
                page1.setText( Integer.toString(r+1));
//                final TextView text2=(TextView)findViewById(R.id.score1);
                text.setText(stringBuffer[r].toString());
//                final TextView text3=(TextView)findViewById(R.id.difficultylevel);
                text1.setText(stringBuffer1[r].toString());
                if(r == 0){
                    back.setEnabled(false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView play = (ImageView) findViewById(R.id.retry2);
        play.setClickable(true);
        play.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
            finish();
        });

    }




}
package com.example.flappy;




import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class game_over extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String filename = "score_history.txt";
        String filename2 = "dif_level.txt";
        FileOutputStream outputStream;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent intent = getIntent();
        String score = intent.getStringExtra(GameView.MSG);
        String dif_level = intent.getStringExtra(GameView.MSG2);
        TextView textView= (TextView) findViewById(R.id.score);
        textView.setText(score);
        try {
            outputStream = openFileOutput(filename, MODE_APPEND);
            outputStream.write((score+"\n").getBytes());
            outputStream.close();
            outputStream = openFileOutput(filename2, MODE_APPEND);
            outputStream.write((dif_level+"\n").getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView play = (ImageView) findViewById(R.id.retry);
        play.setClickable(true);
        play.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
            finish();
        });

    }
    @Override
    public void onBackPressed() {

        Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent1);
        finish();
    }
}


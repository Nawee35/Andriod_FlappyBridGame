package com.example.flappy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class game extends Activity {
    Switch aSwitch;
    boolean shown_dialog=false;
    protected long pressedTime;
    String different_level;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        different_level = getIntent().getStringExtra("extra");
        if(different_level.equals("Hard")){
            GameView2 gameView = new GameView2(this,different_level);
            setContentView(gameView);
        }
        else {
            GameView gameView = new GameView(this, different_level);
            setContentView(gameView);
        }


    }
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to go to home screen", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

//    private void show() {
//        if (!shown_dialog) {
//            shown_dialog = true;
//            final AlertDialog.Builder viewDialog = new AlertDialog.Builder(game.this);
//            View view = LayoutInflater.from(game.this).inflate(R.layout.activity_popup_up, findViewById(R.id.popup_menu), false);
//            viewDialog.setView(view);
//            viewDialog.setCancelable(false);
//            view.findViewById(R.id.imageButton2);
//            final AlertDialog alert = viewDialog.create();
//            view.findViewById(R.id.imageButton2).setOnClickListener(view1 -> {
//                shown_dialog = false;
//                alert.dismiss();
//            });
//            alert.show();
//        }
//    }

}



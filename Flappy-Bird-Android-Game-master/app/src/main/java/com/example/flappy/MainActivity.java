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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected long pressedTime;
    Switch aSwitch;
    boolean shown_dialog=false;
    private AudioManager audioManager;
    String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView btn = (ImageView) findViewById(R.id.play);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        btn.setOnClickListener(v -> {
            show();
//            Intent intent = new Intent(getApplicationContext(),game.class);
//            startActivity(intent);
//            finish();
        });
        Button btn2 = (Button) findViewById(R.id.button4);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),activity_game_history.class);
            startActivity(intent);
            finish();
        });
        aSwitch=findViewById(R.id.switch3);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(aSwitch.isChecked()){
                    audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
                }
                else{
                    audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_PLAY_SOUND);
                }
            }
        });


    }
    private void show() {

        if (!shown_dialog) {
            shown_dialog = true;
            final AlertDialog.Builder viewDialog = new AlertDialog.Builder(this,R.style.MyDialogTheme);
            View view = LayoutInflater.from(this).inflate(R.layout.activity_popup_up, null);
            viewDialog.setView(view);
            viewDialog.setCancelable(false);
            viewDialog.setMessage("Difficulty Level");
            final AlertDialog alert = viewDialog.create();
            view.findViewById(R.id.button).setOnClickListener(view1 -> {
                Intent intent = new Intent(getApplicationContext(),game.class);
                level = "Easy";
                intent.putExtra("extra", level);
                startActivity(intent);
                finish();
                shown_dialog = false;
                alert.dismiss();
            });
            view.findViewById(R.id.button2).setOnClickListener(view1 -> {
                Intent intent = new Intent(getApplicationContext(),game.class);
                level = "Normal";
                intent.putExtra("extra", level);
                startActivity(intent);
                finish();
                shown_dialog = false;
                alert.dismiss();
            });
            view.findViewById(R.id.button3).setOnClickListener(view1 -> {
                Intent intent = new Intent(getApplicationContext(),game.class);
                level = "Hard";
                intent.putExtra("extra", level);
                startActivity(intent);
                finish();
                shown_dialog = false;
                alert.dismiss();
            });
            view.findViewById(R.id.button_close).setOnClickListener(view1 -> {
                shown_dialog = false;
                alert.dismiss();
            });
            alert.show();
        }
    }
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}
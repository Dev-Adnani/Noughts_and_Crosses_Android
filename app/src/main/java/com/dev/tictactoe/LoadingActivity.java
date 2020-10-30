package com.dev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class LoadingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        setContentView(R.layout.activity_load);

        initiateHandler();

    }
    private void initiateHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {                                             // Using This For Lottie Animation

                Intent launchIntent = new Intent();// Taking Value From Playername.java & passing it to main activity

                launchIntent.setClass(LoadingActivity.this, MainActivity.class);

                launchIntent.putExtra("playerOne", getIntent().getStringExtra("playerOne") );

                launchIntent.putExtra("playerTwo", getIntent().getStringExtra("playerTwo") );

                launchIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                startActivity(launchIntent);

                finish();
            }

        }, 3600);
    }
}
package com.dev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PlayerNameActivity extends AppCompatActivity {

    private String playerOne, playerTwo;
    private EditText etPlayerNameOne, etPlayerNameTwo;
    private Button etStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen
        setContentView(R.layout.activity_playername);
        findId();
    }


    public void findId()
    {
        etPlayerNameOne = findViewById(R.id.playerNameOne);
        etPlayerNameTwo = findViewById(R.id.playerNameTwo);
        etStartGame = findViewById(R.id.startGame);

        etStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPlayerNameOne.getText().toString().trim())) // Checking If User Has Entered Name Or Not
                {
                    etPlayerNameOne.setError("Please " + getString(R.string.enter_name_of_first_player));
                }
                else if (TextUtils.isEmpty(etPlayerNameTwo.getText().toString().trim()))
                {
                    etPlayerNameTwo.setError("Please " + getString(R.string.enter_name_of_sec_player));
                }
                else
                {
                    passValues();
                }
            }
        });

        logo();
    }

    private void passValues()
    {
        Intent i = new Intent(PlayerNameActivity.this, LoadingActivity.class); // Taking Values Of Player Name & Passing It To Another Segment / Screen
        playerOne = etPlayerNameOne.getText().toString();
        playerTwo = etPlayerNameTwo.getText().toString();

        i.putExtra("playerOne", playerOne);
        i.putExtra("playerTwo", playerTwo);
        startActivity(i);
        finish();
    }


    private void logo() // For Logo
    {
        ImageView logoImageView = (ImageView) findViewById(R.id.imageView10);
        logoImageView.setY(-2000);
        logoImageView.animate().translationYBy(2000).alpha(1).setDuration(600);
    }
}
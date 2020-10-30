package com.dev.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // Empty Pos

    int[][] winningPostions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; // Winning Pos

    int activePlayer = 0;  // yellow = 0 , red = 1 , 2 = empty

    boolean gameStatus = true;     //Game Status

    String playerWon;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        logo();
        mainMenu();

    }

    private void mainMenu()
    {

        Button brainMenu = (Button) findViewById(R.id.bmainMenu);

        brainMenu.setTranslationY(-1500);

        brainMenu.animate().translationYBy(1500).setDuration(500);

        brainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerNameActivity.class);
                startActivity(intent);

                androidx.gridlayout.widget.GridLayout Layout = findViewById(R.id.gridLayout);

                for (int i = 0; i < Layout.getChildCount(); i++) {
                    ((ImageView) Layout.getChildAt(i)).setImageResource(0);
                }

                for (int a = 0; a < gameState.length; a++) {
                    gameState[a] = 2;

                }

                activePlayer = 0;  // yellow = 0 , red = 1 , 2 = empty
                gameStatus = true;

                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                playAgainButton.setVisibility(View.INVISIBLE);
                winnerTextView.setVisibility(View.INVISIBLE);

            }
        });
    }

    private void logo()                                             // For Logo & Animation
    {
        ImageView logoImageView = (ImageView) findViewById(R.id.logo);
        logoImageView.setY(-2000);
        logoImageView.animate().translationYBy(2000).alpha(1).setDuration(1000);
    }

    public void playAgain(View view)                            // Play Again Button
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout Layout = findViewById(R.id.gridLayout);

        for (int i = 0; i < Layout.getChildCount(); i++) {
            ((ImageView) Layout.getChildAt(i)).setImageResource(0);
        }

        for (int a = 0; a < gameState.length; a++) {
            gameState[a] = 2;

        }

        activePlayer = 0;  // yellow = 0 , red = 1 , 2 = empty
        gameStatus = true;

    }

    public void dropIn(View view)                                                   //Whole Function For Working Of Game
    {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);                                         //Animation Of Drop X & O
        counter.animate().translationYBy(1500).setDuration(250);                //Animation Of Drop X & O

        int tappedCounter = Integer.parseInt(counter.getTag().toString());      //If Any Counter Is Tapped

        if (gameState[tappedCounter] == 2 && gameStatus) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;                            //Changing Player When One's Turns Is Done
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;                           //Changing Player When One's Turns Is Done
            }

            for (int[] winningPosition : winningPostions)
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)                // Someone Wins
                {
                 Winning();
                }

                else                                            // For Game Draw
                {
                    int j = 0;

                    for (int i = 0; i < gameState.length; i++)          //  Checking gameState has empty pos or not
                    {
                        if (gameState[i] != 2)
                        {
                            j = j + 1;
                        }

                    }

                    if (j == 9 && gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)  //  Checking gameState has empty pos or not if yes & it does include winning Pos This Will Execute{
                    {
                        Winning();
                    }

                    else if (j == 9)
                    {
                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        playAgainButton.setVisibility(View.VISIBLE);
                    }

                }

            }

        }
    }

    void Winning()
    {
        gameStatus = false;                                                             //If Postions Of Winning Matches & The Game Stops It Working

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        if (activePlayer == 1)
        {
            playerWon = getIntent().getStringExtra("playerOne");                //Getting Player Name From Last Activity
        }
        else
        {
            playerWon = getIntent().getStringExtra("playerTwo");                //Getting Player Name From Last Activity
        }

        winnerTextView.setText(playerWon + " Has Won!");

        playAgainButton.setVisibility(View.VISIBLE);
        winnerTextView.setVisibility(View.VISIBLE);
    }

}
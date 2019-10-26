package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttons[][]=new Button[3][3];
    Button resetBtn;
    int player1score;
    int player2score;
    int roundCount=0;

    TextView player1ScoreTextView;
    TextView player2ScoreTextView;

    boolean player1turn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1ScoreTextView=findViewById(R.id.player1score);
        player2ScoreTextView=findViewById(R.id.player2score);

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                String btnId="btn_"+i+j;
                Log.e("buttons id:",btnId);
                int resId=getResources().getIdentifier(btnId,"id",getPackageName());

                buttons[i][j]=findViewById(resId);
                Log.e("buttons:","button"+i+j+"clicked");
                buttons[i][j].setOnClickListener(this);
            }
        }

        resetBtn=findViewById(R.id.button_reset);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals(""))
        {
            return;
        }

        if (player1turn)
        {
            ((Button) v).setText("X");
        }

        else
        {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin())
        {
            if (player1turn)
            {
                player1Wins();
            }

            else
            {
                player2Wins();
            }
        }

        else if (roundCount==9)
        {
            Draw();
        }

        else
        {
            player1turn=!player1turn;
        }
    }


    private boolean checkForWin()
    {

        String result[][]=new String[3][3];

        for (int row=0;row<3;row++)
        {
            for (int col=0;col<3;col++)
            {
                result[row][col]=buttons[row][col].getText().toString();
            }
        }

        for (int row=0; row<3; row++)
        {
            if ((result[row][0].equals(result[row][1]))
            && result[row][0].equals(result[row][2])
            && !result[row][0].equals(""))
            {
               return true;
            }
        }

        for (int col=0; col<3; col++)
        {
            if ((result[0][col].equals(result[1][col]))
            && result[0][col].equals(result[2][col])
            && !result[0][col].equals(""))
            {
                return true;
            }
        }

        if ((result[0][0].equals(result[1][1]))
                && result[0][0].equals(result[2][2])
                && !result[0][0].equals(""))
        {
            return true;
        }


        if ((result[2][0].equals(result[1][1]))
                && result[2][0].equals(result[0][2])
                && !result[2][0].equals(""))
        {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1score++;

        Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player2Wins() {

        player2score++;

        Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void Draw() {

        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints() {
        player1ScoreTextView.setText("Player 1: "+player1score);
        player2ScoreTextView.setText("Player 2: "+player2score);
    }

    private void resetBoard() {

        for (int row=0; row<3; row++)
        {
            for (int col=0;col<3;col++)
            {
                buttons[row][col].setText("");
            }
        }

        roundCount=0;
        player1turn=true;
    }

    private void resetGame() {
        player1score=0;
        player2score=0;

        updatePoints();
        resetBoard();
    }

}

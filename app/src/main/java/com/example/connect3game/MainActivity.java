package com.example.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int[][] win_ways = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] board = {2,2,2,2,2,2,2,2,2};
    //0:yellow,1:red
    int player = 0;
    boolean gameover = false;

    public void drop(View view){
        ImageView counter = (ImageView) view;
        int position = Integer.parseInt(counter.getTag().toString());
        if(board[position] == 2 && !gameover){
            counter.setTranslationY(-1500);
            if(player == 0){
                counter.setImageResource(R.drawable.yellow);
                board[position] = 0;
                player = 1;
            }
            else{
                counter.setImageResource(R.drawable.red);
                board[position] = 1;
                player = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            //someone wins
            for(int[] way : win_ways){
                if(board[way[0]] != 2 && board[way[0]] == board[way[1]] && board[way[1]] == board[way[2]]){
                    gameover = true;
                    Button button = (Button) findViewById(R.id.button);
                    TextView report = (TextView) findViewById(R.id.winner_report);
                    if(player == 0){
                        report.setText("Red has won!");
                    }
                    else{
                        report.setText("Yellow has won!");
                    }
                    button.setVisibility(View.VISIBLE);
                    report.setVisibility(View.VISIBLE);
                    break;
                }
            }

            //full board
            boolean full = true;
            for(int i=0; i<board.length; i++){
                if(board[i] == 2){
                    full = false;
                    break;
                }
            }
            if(full){
                gameover = true;
                Button button = (Button) findViewById(R.id.button);
                TextView report = (TextView) findViewById(R.id.winner_report);
                report.setText("Tie!");
                button.setVisibility(View.VISIBLE);
                report.setVisibility(View.VISIBLE);
            }
        }
    }

    public void play_again(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView report = (TextView) findViewById(R.id.winner_report);
        button.setVisibility(View.INVISIBLE);
        report.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.grid_layout);
        for(int i=0; i<grid.getChildCount(); i++){
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<board.length; i++){
            board[i] = 2;
        }

        player = 0;
        gameover = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
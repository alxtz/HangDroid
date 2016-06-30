package com.example.bj.handdroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // second is default value
        int points = getIntent().getIntExtra("POINTS_IDENTIFIER" , 0);

        TextView pointView = (TextView) findViewById(R.id.textViewPoints);
        pointView.setText(String.valueOf(points));
    }
}

package com.example.bj.handdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View v)
    {
        // make an intent when you want to start a new activity
        Intent myIntent = new Intent(this , GameActivity.class);

        startActivity(myIntent);
    }

    public void startMultiGame(View v)
    {
        Intent myIntent = new Intent(this , GameActivity.class);

        startActivity(myIntent);
    }
}

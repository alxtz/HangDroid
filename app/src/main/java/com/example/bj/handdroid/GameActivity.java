package com.example.bj.handdroid;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

import javax.xml.parsers.SAXParser;

public class GameActivity extends Activity
{
    //其實有一個BUG，那就是如果你重複猜已經猜中的數字還是會加分

    String mWord;

    int failCounter = 0;

    String wrongString = "";

    int correctLetters = 0;

    int getPoints = 0;

    String wordList = "jazz buzz fuzz fizz hajj juju quiz razz jeez jeux jinx jock jack jump jamb juku joky jivy jiff junk jimp jibb jauk phiz zouk zonk juke chez cozy zyme mazy jouk qoph jink whiz fozy joke jake zebu java fuji jowl puja jerk jaup jive jagg zeks jupe fuze putz hazy koji zinc futz juba zerk juco jube quip waxy jehu bozo mozo jugs jows jeep dozy lazy jefe flux maze czar faze pixy meze john boxy jibe juga jibs bize jury jobs prez jabs friz jape poxy zeps jams quay zany yutz zaps quey zarf mojo quag hadj";
    String[] wordListArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        wordListArray = wordList.split(" ");
        Log.d("MyLog" , "The length of array is : " + wordListArray.length);
        setRandomWord();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void introduceLetter(View v)
    {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MyLog" , "The letter is : " + letter);

        if( letter.length() == 1 )
        {
            checkLetter(letter.toLowerCase());
        }
        else
        {
            //Toase is the messageBox in android
            Toast.makeText(this , "Plz introduce a letter" , Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLetter(String introducedLetter)
    {
        boolean failed = true;

        char charIntroduced = introducedLetter.charAt(0);

        for(int i = 0; i < mWord.length(); ++i)
        {
            char charFromWord = mWord.charAt(i);

            if( charFromWord == charIntroduced )
            {
                //show the letter
                Log.d("MyLog" , "There was a match");

                failed = false;
                showLettersAtIndex(i , charIntroduced);
            }
            else
            {
                //do something later

            }
        }
        if(failed==true)
        {
            letterFailed();
            addWrongLetter(introducedLetter.charAt(0));
        }
    }

    public void letterFailed()
    {
        failCounter++;
        ImageView imageView = (ImageView)  findViewById(R.id.imageView);
        if(failCounter==1)
        {
            imageView.setImageResource(R.drawable.hang5);
        }
        else if(failCounter==2)
        {
            imageView.setImageResource(R.drawable.hang4);
        }
        else if(failCounter==3)
        {
            imageView.setImageResource(R.drawable.hang3);
        }
        else if(failCounter==4)
        {
            imageView.setImageResource(R.drawable.hang2);
        }
        else if(failCounter==5)
        {
            imageView.setImageResource(R.drawable.hang1);
        }
        else if(failCounter==6)
        {
            imageView.setImageResource(R.drawable.hang0);
            //TODO lose the game
            Intent gameOverIntent = new Intent(this , GameOverActivity.class);

            gameOverIntent.putExtra("POINTS_IDENTIFIER" , getPoints);

            startActivity(gameOverIntent);
        }
    }

    public void addWrongLetter(char wrongLetter)
    {
        wrongString += Character.toString(wrongLetter);
        TextView wrongGuessTextView = (TextView)findViewById(R.id.wrongGuess);
        wrongGuessTextView.setText(wrongString);
    }

    public void showLettersAtIndex(int position , char letterGuessed)
    {
        correctLetters++;

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));

        if(correctLetters == mWord.length())
        {
            //TODO win the game
            getPoints++;
            clearScreen();
            setRandomWord();
            //Intent gameOverIntent = new Intent(this , GameOverActivity.class);
            //startActivity(gameOverIntent);
        }
    }

    public void clearScreen()
    {
        TextView textViewFailed = (TextView) findViewById(R.id.wrongGuess);
        textViewFailed.setText("");
        failCounter = 0;
        correctLetters = 0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < layoutLetters.getChildCount(); ++i) {
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hang6);
    }

    public void setRandomWord()
    {
        int randomNumber = (int) (Math.random() * wordListArray.length);

        String randomWord = wordListArray[randomNumber];

        mWord = randomWord;
    }
}


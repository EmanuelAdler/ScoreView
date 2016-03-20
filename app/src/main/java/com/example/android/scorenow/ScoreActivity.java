package com.example.android.scorenow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends MainActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);
        Intent i = getIntent();
        setPlayer1(i.getStringExtra("name1"));
        setTeamA(i.getStringExtra("team1"));
        setPlayer2(i.getStringExtra("name2"));
        setTeamB(i.getStringExtra("team2"));
    }

    public void setPlayer1(String text){
        TextView p1 = (TextView) findViewById(R.id.player1Text);
        p1.setText(text);
    }

    public void setTeamA(String text){
        TextView p1 = (TextView) findViewById(R.id.player1Team);
        p1.setText(text);
    }

    public void setPlayer2(String text){
        TextView p2 = (TextView) findViewById(R.id.player2Text);
        p2.setText(text);
    }

    public void setTeamB(String text){
        TextView p2 = (TextView) findViewById(R.id.player2Team);
        p2.setText(text);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void goalTeamA(View view){
        ++scoreTeamA;
        displayForTeamA(scoreTeamA);
    }

    public void goalTeamB(View view){
        ++scoreTeamB;
        displayForTeamB(scoreTeamB);
    }

    public void reset(View view){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    public String assembleShare(){
        TextView p1 = (TextView) findViewById(R.id.player1Text);
        TextView t1 = (TextView) findViewById(R.id.player1Team);
        TextView p2 = (TextView) findViewById(R.id.player2Text);
        TextView t2 = (TextView) findViewById(R.id.player2Team);
        String msg= "We've just finished a match!\n\n";
        msg+="Score:\n\n";
        msg+= p1.getText().toString() + " (" + t1.getText().toString() + "): ";
        msg+= scoreTeamA + "\n";
        msg+= p2.getText().toString() + " (" + t2.getText().toString() + "): ";
        msg+= scoreTeamB + "\n";
        msg+="\nSent via ScoreNow, an app made for Android Study Jams";
        return msg;
    }

    public void shareMessenger(View view){
        String message = assembleShare();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.facebook.orca");
        try
        {
            startActivity(sendIntent);
        }
        catch (android.content.ActivityNotFoundException e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Facebook Messenger not found", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void shareWhatsapp(View view){
        String message = assembleShare();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        try
        {
            startActivity(sendIntent);
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "WhatsApp not found", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}

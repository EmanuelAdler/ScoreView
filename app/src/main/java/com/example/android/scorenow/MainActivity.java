package com.example.android.scorenow;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.scorenow.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Players players = new Players();
    public boolean saveName1(){
        EditText n1 = (EditText) findViewById(R.id.p1Name);
        players.setPlayer1Name(n1.getText().toString());
        if(players.getPlayer1Name().isEmpty())
            return false;
        return true;
    }

    public boolean saveTeam1(){
        EditText n1 = (EditText) findViewById(R.id.p1Team);
        players.setPlayer1Team(n1.getText().toString());
        if(players.getPlayer1Team().isEmpty())
            return false;
        return true;
    }

    public boolean saveName2(){
        EditText n1 = (EditText) findViewById(R.id.p2Name);
        players.setPlayer2Name(n1.getText().toString());
        if(players.getPlayer2Name().isEmpty())
            return false;
        return true;
    }

    public boolean saveTeam2(){
        EditText n1 = (EditText) findViewById(R.id.p2Team);
        players.setPlayer2Team(n1.getText().toString());
        if(players.getPlayer2Team().isEmpty())
            return false;
        return true;
    }

    public void save(View view){
        if(saveName1() && saveTeam1() && saveTeam2() && saveName2()){
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("name1", players.getPlayer1Name());
            intent.putExtra("name2", players.getPlayer2Name());
            intent.putExtra("team1", players.getPlayer1Team());
            intent.putExtra("team2", players.getPlayer2Team());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else{
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}


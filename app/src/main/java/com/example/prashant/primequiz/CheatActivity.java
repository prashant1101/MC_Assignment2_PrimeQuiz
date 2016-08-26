package com.example.prashant.primequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    public static int cheat_pressed=0;
    static final String STATUS = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        cheat_pressed = 0;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ic_prime);
        if(savedInstanceState != null)
        {
            cheat_pressed = savedInstanceState.getInt(STATUS);
            if(cheat_pressed == 1)
            {
                Button show_cheat = (Button) findViewById(R.id.ShowCheat);
                show_cheat.setEnabled(false);
                Bundle bundle = getIntent().getExtras();
                int number = bundle.getInt("current_prime");
                TextView ne = (TextView) findViewById(R.id.ShowCheatView);
                Boolean check = factors(number);
                if(check == true)
                    ne.setText("Yes, " + number+" is a Prime number.");
                else
                    ne.setText("No, " + number+" is not a Prime number.");
            }
        }

    }

    public void onBackClicked(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("cheat_flag",cheat_pressed);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("cheat_flag",cheat_pressed);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(STATUS,cheat_pressed);
        super.onSaveInstanceState(savedInstanceState);
    }

    public Boolean factors(int number)
    {
        int flag = 0;
        for (int i=2;i<=Math.sqrt(number);i++)
        {
            if(number%i == 0)
            {
                flag = 1;
                break;
            }
        }
        if(flag==1)
            return false;
        else
            return true;
    }

    public void ShowCheatButton(View view)
    {
        cheat_pressed = 1;
        Button show_cheat = (Button) findViewById(R.id.ShowCheat);
        show_cheat.setEnabled(false);
        Bundle bundle = getIntent().getExtras();
        int number = bundle.getInt("current_prime");
        TextView ne = (TextView) findViewById(R.id.ShowCheatView);
        Boolean check = factors(number);
        if(check == true)
            ne.setText("Yes, " + number+" is a Prime number.");
        else
            ne.setText("No, " + number+" is not a Prime number.");
    }
}

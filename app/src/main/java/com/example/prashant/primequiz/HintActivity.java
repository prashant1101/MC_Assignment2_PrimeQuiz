package com.example.prashant.primequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    static final String STATUS = "status";
   // static final String CHECK_STATUS = "check_status";

    public static int hint_pressed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hint_pressed=0;
        setContentView(R.layout.activity_hint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ic_prime);
        if(savedInstanceState != null)
        {
            hint_pressed = savedInstanceState.getInt(STATUS);
            if(hint_pressed == 1)
            {
                Button show_hint = (Button) findViewById(R.id.ShowHint);
                show_hint.setEnabled(false);
                TextView ne = (TextView) findViewById(R.id.ShowHintView);
                ne.setText("Prime number is a number which has only two factors, i.e. 1 and itself.");
            }

        }

    }
    public void onBackClicked(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("hint_flag",hint_pressed);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("hint_flag",hint_pressed);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(STATUS,hint_pressed);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void ShowHintButton(View view)
    {
        hint_pressed = 1;
        TextView ne = (TextView) findViewById(R.id.ShowHintView);
        Button show_hint = (Button) findViewById(R.id.ShowHint);
        show_hint.setEnabled(false);
        ne.setText("Prime number is a number which has only two factors, i.e. 1 and itself.");
    }
}

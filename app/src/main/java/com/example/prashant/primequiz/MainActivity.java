package com.example.prashant.primequiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int[] histogram = new int[1100];
    public static int number;
    public static int count;
    public static String count_str=new String();
    public static String num_str = new String();
    public static String correct_str=new String();
    public static String incorrect_str=new String();
    public static String hint_str=new String();
    public static String cheat_str=new String();
    public static String cheat_hint_str = new String();
    static final String HINT_CURRENT = "current_hint";
    static final String CHEAT_CURRENT = "current_cheat";

    public static boolean check_status;
    public static int check_button = 0;
    static final String CURRENT_NUMBER = "current_prime";
    static final String CORRECT_ANSWER = "correct_answer";
    static final String INCORRECT_ANSWER = "incorrect_answer";
    static final String STATUS = "status";
    static final String SELECTED = "selected";
    static final String STATUS_HINT = "status_hint";
    static final String STATUS_CHEAT = "status_cheat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ic_prime);
        TextView num = (TextView) findViewById(R.id.textView);

        for (int i=0;i<=1080;i++)
            histogram[i]=0;
        int[] array = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,
                71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,
                151,157,163,167,173,179,181,191,193,197,199,211,223,227,229
                ,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,
                317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,
                419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,
                503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,
                607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,
                701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,
                811,821,823,827,829,839,853,857,859,863,877,881,883,887,907,
                911,919,929,937,941,947,953,967,971,977,983,991,997};

        for (int i=0;i<168;i++)
            histogram[array[i]]++;


        if (savedInstanceState != null)
        {
            TextView correct = (TextView) findViewById(R.id.Correct_textid);
            TextView incorrect = (TextView) findViewById(R.id.Incorrect_textid);
            Button true_button = (Button) findViewById(R.id.True);
            Button false_button = (Button) findViewById(R.id.False);
            Button hint_button = (Button) findViewById(R.id.Hint);
            Button cheat_button = (Button) findViewById(R.id.Cheat);
            TextView hint = (TextView) findViewById(R.id.Hint_textid);
            TextView cheat = (TextView) findViewById(R.id.Cheat_textid);
            Boolean hint_stat=false;
            Boolean cheat_stat = false;

            hint_str = savedInstanceState.getString(HINT_CURRENT);
            cheat_str = savedInstanceState.getString(CHEAT_CURRENT);
            number = savedInstanceState.getInt(CURRENT_NUMBER);
            correct_str = savedInstanceState.getString(CORRECT_ANSWER);
            incorrect_str = savedInstanceState.getString(INCORRECT_ANSWER);
            check_status = savedInstanceState.getBoolean(STATUS);
            hint_stat = savedInstanceState.getBoolean(STATUS_HINT);
            cheat_stat = savedInstanceState.getBoolean(STATUS_CHEAT);
            hint.setText(hint_str);
            cheat.setText(cheat_str);

            if(hint_stat == false)
                hint_button.setEnabled(false);
            else
                hint_button.setEnabled(true);
            if(cheat_stat == false)
                cheat_button.setEnabled(false);
            else
                cheat_button.setEnabled(true);
            check_button = savedInstanceState.getInt(SELECTED);
            correct.setText(correct_str);
            incorrect.setText(incorrect_str);
            if(!check_status)
            {
                true_button.setEnabled(false);
                false_button.setEnabled(false);
                if(check_button==1)
                {
                    if(histogram[number]==1)
                        true_button.setBackgroundColor(Color.GREEN);
                    else
                        true_button.setBackgroundColor(Color.RED);
                    false_button.setBackgroundColor(Color.TRANSPARENT);
                }
                else
                {
                    if(histogram[number]==0)
                        false_button.setBackgroundColor(Color.GREEN);
                    else
                        false_button.setBackgroundColor(Color.RED);
                    true_button.setBackgroundColor(Color.TRANSPARENT);
                }
            }


        }
        else
        {
            Random rand = new Random();
            number = rand.nextInt(1000)+1;
        }

        num_str = "" + String.valueOf(number) + " is a Prime Number.";
        num.setText(num_str);

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView correct = (TextView) findViewById(R.id.Correct_textid);
        TextView incorrect = (TextView) findViewById(R.id.Incorrect_textid);
        Button true_button = (Button) findViewById(R.id.True);
        Button hint_button = (Button) findViewById(R.id.Hint);
        Button cheat_button = (Button) findViewById(R.id.Cheat);
        TextView hint = (TextView) findViewById(R.id.Hint_textid);
        TextView cheat = (TextView) findViewById(R.id.Cheat_textid);

        savedInstanceState.putInt(CURRENT_NUMBER, number);
        savedInstanceState.putString(CORRECT_ANSWER,correct.getText().toString());
        savedInstanceState.putString(HINT_CURRENT,hint.getText().toString());
        savedInstanceState.putString(CHEAT_CURRENT,cheat.getText().toString());
        savedInstanceState.putString(INCORRECT_ANSWER,incorrect.getText().toString());
        savedInstanceState.putBoolean(STATUS,true_button.isEnabled());
        savedInstanceState.putBoolean(STATUS_HINT,hint_button.isEnabled());
        savedInstanceState.putBoolean(STATUS_CHEAT,cheat_button.isEnabled());
        savedInstanceState.putInt(SELECTED,check_button);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume()
    {

        super.onResume();
    }

    public void increament(boolean flag)
    {
        TextView correct = (TextView) findViewById(R.id.Correct_textid);
        TextView incorrect = (TextView) findViewById(R.id.Incorrect_textid);
        if(flag)
        {
            count_str = correct.getText().toString();
            count = Integer.parseInt(count_str);
            count++;
            count_str = String.valueOf(count);
            correct.setText(count_str);
        }
        else
        {
            count_str = incorrect.getText().toString();
            count = Integer.parseInt(count_str);
            count++;
            count_str = String.valueOf(count);
            incorrect.setText(count_str);
        }
    }

    public void increament_hint()
    {
        TextView hint_text = (TextView) findViewById(R.id.Hint_textid);
        cheat_hint_str = hint_text.getText().toString();

        int counti = Integer.parseInt(cheat_hint_str);
        counti++;
        cheat_hint_str = String.valueOf(counti);
        hint_text.setText(cheat_hint_str);
    }
    public void increament_cheat()
    {
        TextView cheat_text = (TextView) findViewById(R.id.Cheat_textid);
        cheat_hint_str = cheat_text.getText().toString();
        int counti = Integer.parseInt(cheat_hint_str);
        counti++;
        cheat_hint_str = String.valueOf(counti);
        cheat_text.setText(cheat_hint_str);
    }

    @Override
    protected void onActivityResult(int request_code,int result_code,Intent intent)
    {
        Log.d("yes","aaa");
        if(request_code == 1 && result_code == RESULT_OK && intent != null)
        {

            int flag = intent.getIntExtra("hint_flag",0);
            if(flag == 1)
            {
                Toast.makeText(this,"Hind has been used.", Toast.LENGTH_SHORT).show();
                Button hint_button = (Button) findViewById(R.id.Hint);
                //TextView hint_text = (TextView) findViewById(R.id.Hint_textid);
                hint_button.setEnabled(false);
                increament_hint();
            }
        }

        if(request_code == 2 && result_code == RESULT_OK && intent != null)
        {
            int flag = intent.getIntExtra("cheat_flag",0);
            if(flag == 1)
            {
                Toast.makeText(this,"Cheat has been used.", Toast.LENGTH_SHORT).show();
                Button cheat_button = (Button) findViewById(R.id.Cheat);
                cheat_button.setEnabled(false);
                Button hint_button = (Button) findViewById(R.id.Hint);
                hint_button.setEnabled(false);
                increament_cheat();
            }
        }
    }

    public void CheatButton(View view)
    {
        Intent intent = new Intent(MainActivity.this, CheatActivity.class);
        Bundle bund = new Bundle();
        bund.putInt("current_prime",number);
        intent.putExtras(bund);
        startActivityForResult(intent,2);
    }

    public void HintButton(View view)
    {
        Intent intent = new Intent(MainActivity.this, HintActivity.class);
        startActivityForResult(intent,1);
    }


    public void TrueButton(View view)
    {
        Button hint_button = (Button) findViewById(R.id.Hint);
        Button cheat_button = (Button) findViewById(R.id.Cheat);
        cheat_button.setEnabled(false);
        hint_button.setEnabled(false);
        if(histogram[number] == 1)
        {
            Button true_button = (Button) findViewById(R.id.True);
            Button false_button = (Button) findViewById(R.id.False);
            true_button.setBackgroundColor(Color.GREEN);
            true_button.setEnabled(false);
            false_button.setEnabled(false);
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show();
            increament(true);
        }
        else
        {
            Button true_button = (Button) findViewById(R.id.True);
            Button false_button = (Button) findViewById(R.id.False);
            true_button.setBackgroundColor(Color.RED);
            true_button.setEnabled(false);
            false_button.setEnabled(false);
            Toast.makeText(this,"InCorrect", Toast.LENGTH_SHORT).show();
            increament(false);

        }
        check_button = 1;
    }

    public void FalseButton(View view)
    {
        Button hint_button = (Button) findViewById(R.id.Hint);
        Button cheat_button = (Button) findViewById(R.id.Cheat);
        cheat_button.setEnabled(false);
        hint_button.setEnabled(false);
        if(histogram[number] == 0)
        {
            Button true_button = (Button) findViewById(R.id.True);
            Button false_button = (Button) findViewById(R.id.False);
            false_button.setBackgroundColor(Color.GREEN);
            true_button.setEnabled(false);
            false_button.setEnabled(false);
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show();
            increament(true);
        }
        else
        {
            Button true_button = (Button) findViewById(R.id.True);
            Button false_button = (Button) findViewById(R.id.False);
            false_button.setBackgroundColor(Color.RED);
            true_button.setEnabled(false);
            false_button.setEnabled(false);
            Toast.makeText(this,"InCorrect", Toast.LENGTH_SHORT).show();
            increament(false);
        }
        check_button = 2;
    }

    public void NextButton(View view)
    {
        Button true_button = (Button) findViewById(R.id.True);
        Button hint_button = (Button) findViewById(R.id.Hint);
        Button cheat_button = (Button) findViewById(R.id.Cheat);
        Button false_button = (Button) findViewById(R.id.False);
        TextView num = (TextView) findViewById(R.id.textView);

        if(!true_button.isEnabled())
        {
            true_button.setEnabled(true);
            hint_button.setEnabled(true);
            cheat_button.setEnabled(true);
            false_button.setEnabled(true);
            true_button.setBackgroundColor(Color.TRANSPARENT);
            false_button.setBackgroundColor(Color.TRANSPARENT);

            Random rand = new Random();
            number = rand.nextInt(1000)+1;
            num_str = "" + String.valueOf(number) + " is a Prime Number.";
            num.setText(num_str);

        }
        else
            Toast.makeText(this,"First, Answer the question.", Toast.LENGTH_SHORT).show();

        check_button = 0;
    }
}

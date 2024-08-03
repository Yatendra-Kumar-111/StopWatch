package com.example.stopwatch;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int seconds = 0;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textView = findViewById(R.id.timerView);
        startTimer();
    }


    public void onClickStartBtn (View v) {
        isRunning = true;
    }
    public void onClickStopBtn (View v) {
        isRunning = false;
    }
    public void onClickResetBtn (View v) {
        isRunning = false;
        seconds = 0;    //
    }

    public void startTimer () {
        Handler myHandlerObj = new Handler();
        Runnable myRunnableObj = new Runnable() {
            @Override
            public void run() {
                int sec = seconds % 60;
                int min = seconds / 60;
                int hr = min / 60;
                if(isRunning) {
                    seconds++;
                }
                String myTime = String.format(Locale.getDefault(),"%02d:%02d:%02d",hr, min, sec);
                textView.setText(myTime);
                myHandlerObj.postDelayed(this, 1000);
            }
        };
        myHandlerObj.post(myRunnableObj);
    }
}
package com.example.xypherdon.metronome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.constraint.ConstraintLayout;
import android.graphics.Color;

import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        active=false;

        setContentView(R.layout.activity_main);
        Button beatButton=(Button)findViewById(R.id.beatButton);
        Button stopButton=(Button)findViewById(R.id.stopButton);
        ScheduledExecutorService turn_red=Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService turn_white=Executors.newSingleThreadScheduledExecutor();

        class BPM_red extends TimerTask{
            @Override
            public void run(){
                ConstraintLayout constraintLayout =findViewById(R.id.constraintLayout);
                constraintLayout.setBackgroundColor(Color.RED);
            }
        }

        class BPM_white extends TimerTask{
            @Override
            public void run(){
                ConstraintLayout constraintLayout =findViewById(R.id.constraintLayout);
                constraintLayout.setBackgroundColor(Color.WHITE);
            }
        }

        beatButton.setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    if(active){
                        active=false;
                    }
                    else
                    {
                        active=true;
                    }
                    //EditText tempoEditText = (EditText) findViewById(R.id.tempoEditText);
                    //int bpm = Integer.parseInt(tempoEditText.getText().toString());
                    //int beat = 60000 / bpm;
                    Timer timer = new Timer();
                    if(active){

                        timer.scheduleAtFixedRate(new BPM_red(), 0, 2500);
                        //timer.scheduleAtFixedRate(new BPM_white(), 50, 2500);
                    }
                    else{
                        timer.cancel();
                    }
                }
            }
        );
    }
}

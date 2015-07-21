package com.dreamer.testgit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = (CircleView) findViewById(R.id.circle);
        circleView.beginAnimator();
    }

}

package com.dreamer.testgit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView1,circleView2,circleView3,circleView4;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView1 = (CircleView) findViewById(R.id.circle1);
        circleView1.beginAnimator(1000,100);

        circleView2 = (CircleView) findViewById(R.id.circle2);
        circleView2.beginAnimator(1000,200);

        circleView3 = (CircleView) findViewById(R.id.circle3);
        circleView3.beginAnimator(2000,300);

        circleView4 = (CircleView) findViewById(R.id.circle4);
        circleView4.beginAnimator(1500,300);
    }

}

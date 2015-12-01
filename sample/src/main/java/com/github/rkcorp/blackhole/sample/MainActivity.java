package com.github.rkcorp.blackhole.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.rkcorp.blackhole.BlackHole;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BlackHole blackHole = (BlackHole) findViewById(R.id.black_hole);
        blackHole.setFocusView(findViewById(R.id.textview));

    }
}

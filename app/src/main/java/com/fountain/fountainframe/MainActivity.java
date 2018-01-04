package com.fountain.fountainframe;

import android.os.Bundle;

import com.fountain.bases.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        getStatusBarManager().setStatusBarIntegration(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
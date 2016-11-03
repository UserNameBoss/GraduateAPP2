package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.yangxiaolong.graduateapp.R;

import butterknife.ButterKnife;

public class SubjectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subject);
        ButterKnife.bind(this);
    }
}

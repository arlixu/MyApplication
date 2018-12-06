package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RunCollectActivity extends AppCompatActivity {
    private View.OnClickListener rOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            //
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_collect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Button 123处理的办法。
        //进入时只有1能点。2，3置灰。
        Button collectSpeed1 = findViewById(R.id.bt_collect_speed_1);
        Button collectSpeed2 = findViewById(R.id.bt_collect_speed_2);
        Button collectSpeed3 = findViewById(R.id.bt_collect_speed_3);
        collectSpeed1.setBackgroundResource(R.drawable.clr_normal);
        collectSpeed2.setBackgroundResource(R.drawable.clr_pressed);
        collectSpeed2.setEnabled(false);
        collectSpeed3.setBackgroundResource(R.drawable.clr_pressed);
        collectSpeed3.setEnabled(false);
        //点x采集后。将x置为采集中。enable false ,颜色标黄。采集的百分比写在后面。
        collectSpeed1.setOnClickListener();
        //当采集值达到100%后，颜色标灰，将2变绿，enable=true

        //TODO 点击图标时开始分析问题。并跳转到问题分析页。
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

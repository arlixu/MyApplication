package com.example.alex.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alex.myapplication.constants.ButtonConst;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RunCollectActivity extends AppCompatActivity {
    //使用map来存按钮状态。
    private Map buttonStatus=new HashMap<Integer,String>();
    private void initButton(){
        buttonStatus.put(R.id.bt_collect_speed_1,ButtonConst.UNCLICK);
        buttonStatus.put(R.id.bt_collect_speed_2,ButtonConst.UNCLICK);
        buttonStatus.put(R.id.bt_collect_speed_3,ButtonConst.UNCLICK);
        //Button 123处理的办法。
        //进入时只有1能点。2，3置灰。
        Button collectSpeed1 = findViewById(R.id.bt_collect_speed_1);
        Button collectSpeed2 = findViewById(R.id.bt_collect_speed_2);
        Button collectSpeed3 = findViewById(R.id.bt_collect_speed_3);
        collectSpeed1.setBackgroundResource(R.drawable.clr_normal);
        collectSpeed2.setBackgroundResource(R.drawable.clr_normal);
        collectSpeed3.setBackgroundResource(R.drawable.clr_normal);

        collectSpeed1.setOnClickListener(rOnClickListener);
        collectSpeed2.setOnClickListener(rOnClickListener);
        collectSpeed3.setOnClickListener(rOnClickListener);
    }

    private View.OnClickListener rOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            //如果按钮状态为UNCLICK
            Object status = buttonStatus.get(id);
            if(status.equals(ButtonConst.UNCLICK))
            {
                ////点采集后。置为采集中。enable true不变， ,颜色标黄。采集的百分比写在后面。
                buttonStatus.put(id,ButtonConst.COLLECTING);
                ((Button)v).setText(R.string.collecting);
                ((Button)v).setBackgroundResource(R.drawable.clr_collecting);
                //暂时不允许点击另外两个
                Set buttons = buttonStatus.keySet();
                for (Object button : buttons) {
                    if((Integer)button!=id)
                    {
                        findViewById((Integer)button).setEnabled(false);
                    }
                }
                //TODO 向硬件发送USB串口命令，启动收集。这里应该是启动一个服务。

            }else if(status.equals(ButtonConst.COLLECTING))
            {
                buttonStatus.put(id,ButtonConst.COLLECT_FINISHED);
                ((Button)v).setText(R.string.collect_finished);
                ((Button)v).setBackgroundResource(R.drawable.clr_finished);
                //TODO 断开和usb的链接。
                //其他的要是true
                Set buttons = buttonStatus.keySet();
                for (Object button : buttons) {
                    if((Integer)button!=id)
                    {
                        findViewById((Integer) button).setEnabled(true);
                    }
                }
            }else
            {
                Toast.makeText(RunCollectActivity.this,R.string.collect_finished_toast,Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_collect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //初始化按钮逻辑
        initButton();
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

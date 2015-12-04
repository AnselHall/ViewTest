package com.qyz.soufuntest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
* 点击任务列表进入
*   如果点击的任务还未完成，则进入的页面显示为 任务签到页
*   如果点击的任务已完成，则进入的页面显示为 任务明细页
*
* */
public class EB_Sale_TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_eb_sale_task_detail);
        setTitle("任务签到");
    }

}

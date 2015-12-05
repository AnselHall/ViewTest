package com.qyz.soufuntest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class EB_Sale_MyAttendanceActivity extends AppCompatActivity {

    private TextView tv_week;
    private TextView tv_date;
    private ArcView arcView;
    private LinearLayout ll_arc;
    private LinearLayout include_work;
    private LinearLayout include_workoff;
    private TextView tv_addtask;
    private ListView lv_tasklist;

    private LinearLayout work_ll_item_root;
    private ImageView work_iv_item_icon;
    private TextView work_tv_item_work;
    private TextView work_tv_item_time;
    private Button work_btn_item_registrition;
    private TextView work_tv_item_state;

    private LinearLayout workoff_ll_item_root;
    private ImageView workoff_iv_item_icon;
    private TextView workoff_tv_item_work;
    private TextView workoff_tv_item_time;
    private Button workoff_btn_item_registrition;
    private TextView workoff_tv_item_state;
    private List<Task> taskList;
    private LinearLayout ll_task_list;
    private ImageView work_iv_item_reason;
    private ImageView workoff_iv_item_reason;

    private static String STATE = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_eb_sale_myattendance);

        setTitle("我的考勤");

        initView();
        registerComponent();
        initData();

        addView();

        unitData();

        fillData();
    }

    private void registerComponent() {

    }

    private void initData() {
        taskList = new ArrayList<Task>();

        taskList.add(new Task("带看", "9:00 - 10:00   北京市丰台区新村街道万达广场"));
        taskList.add(new Task("与业主谈独家", "9:00 - 10:00   北京市丰台区新村街道万达广场"));
        taskList.add(new Task("空看", "9:00 - 10:00   北京市丰台区新村街道万达广场"));
        taskList.add(new Task("实勘", "9:00 - 10:00   北京市丰台区新村街道万达广场"));
        taskList.add(new Task("培训开会", "9:00 - 10:00   北京市丰台区新村街道万达广场"));
    }

    private void fillData() {

    }

    private void unitData() {

        workoff_btn_item_registrition.setVisibility(View.INVISIBLE);
        workoff_tv_item_state.setVisibility(View.VISIBLE);
        workoff_tv_item_work.setText("下班");
        workoff_tv_item_state.setText("已签到 15:00");
        workoff_tv_item_time.setText("18:00");
        workoff_iv_item_icon.setImageResource(R.drawable.about_me_icon);
        workoff_iv_item_reason.setVisibility(View.VISIBLE);

        for (int i = 0; i < taskList.size(); i++) {
            View inflater = View.inflate(this, R.layout.lv_task_item, null);
            ImageView iv_task_icon = (ImageView) inflater.findViewById(R.id.iv_task_icon);
            TextView tv_task_name = (TextView) inflater.findViewById(R.id.tv_task_name);
            TextView tv_task_detail = (TextView) inflater.findViewById(R.id.tv_task_detail);

            Task task = taskList.get(i);
            if (task != null) {
                tv_task_name.setText(task.taskName);
                tv_task_detail.setText(task.taskDetail);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(80.0f));
           /* params.gravity = Gravity.CENTER_VERTICAL;
            inflater.setLayoutParams(params);*/

            ll_task_list.addView(inflater,params);

            inflater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EB_Sale_MyAttendanceActivity.this,EB_Sale_TaskDetailActivity.class);
                    intent.putExtra("state", STATE);
                    startActivity(intent);

                }
            });
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void addView() {

        arcView = new ArcView(this);
        arcView.setNumerator(30);
        arcView.setDenominator(31);

        ll_arc.addView(arcView);
    }

    private void initView() {

        //activity_eb_sale_myattendance.xml
        tv_week = (TextView) findViewById(R.id.tv_week);
        tv_date = (TextView) findViewById(R.id.tv_date);
        ll_arc = (LinearLayout) findViewById(R.id.ll_arc);
        include_work = (LinearLayout) findViewById(R.id.include_work);

        include_workoff = (LinearLayout) findViewById(R.id.include_workoff);
        tv_addtask = (TextView) findViewById(R.id.tv_addtask);
        //lv_tasklist = (ListView) findViewById(R.id.lv_tasklist);
        ll_task_list = (LinearLayout) findViewById(R.id.ll_task_list);

        //registrition_item.xml  上班签到对应的布局
        work_iv_item_icon = (ImageView) include_work.findViewById(R.id.iv_item_icon);
        work_tv_item_work = (TextView) include_work.findViewById(R.id.tv_item_work);
        work_tv_item_time = (TextView) include_work.findViewById(R.id.tv_item_time);
        work_btn_item_registrition = (Button) include_work.findViewById(R.id.btn_item_registrition);
        work_tv_item_state = (TextView) include_work.findViewById(R.id.tv_item_state);
        work_ll_item_root = (LinearLayout) include_work.findViewById(R.id.ll_itemroot);
        work_iv_item_reason = (ImageView) include_work.findViewById(R.id.iv_item_reason);

        //registrition_item.xml  下班签到对应的布局
        workoff_iv_item_icon = (ImageView) include_workoff.findViewById(R.id.iv_item_icon);
        workoff_tv_item_work = (TextView) include_workoff.findViewById(R.id.tv_item_work);
        workoff_tv_item_time = (TextView) include_workoff.findViewById(R.id.tv_item_time);
        workoff_btn_item_registrition = (Button) include_workoff.findViewById(R.id.btn_item_registrition);
        workoff_tv_item_state = (TextView) include_workoff.findViewById(R.id.tv_item_state);
        workoff_ll_item_root = (LinearLayout) include_workoff.findViewById(R.id.ll_itemroot);
        workoff_iv_item_reason = (ImageView) include_workoff.findViewById(R.id.iv_item_reason);
    }


    class Task{
        public Task(String taskName, String taskDetail) {
            this.taskName = taskName;
            this.taskDetail = taskDetail;
        }
        public String taskName;
        public String taskDetail;
    }
}

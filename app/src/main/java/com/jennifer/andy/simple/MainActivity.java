package com.jennifer.andy.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jennifer.andy.simple.widget.AdjustLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private Button mBtnNext;
    private Button mBtnLast;
    private int position = 0;
    private List<String> mStringList;
    private AdjustLinearLayoutManager mLayoutManager;
    private final int mScrollType = LinearSmoothScroller.SNAP_TO_ANY;
    private final static int COUNT = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new AdjustLinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnNext = findViewById(R.id.btn_next);
        mBtnLast = findViewById(R.id.btn_last);

        initData();
        config();
    }

    private void config() {

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = position + 1 > COUNT - 1? 0: position + 1;
                mRecyclerView.smoothScrollToPosition(position);
            }
        });

        mBtnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = position - 1 < 0? COUNT - 1: position - 1;
                mRecyclerView.smoothScrollToPosition(position);
            }
        });
    }


    private void initData() {
        mStringList = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            mStringList.add("data " + i);
        }
        mLayoutManager.setScrollType(mScrollType);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new SimpleTextAdapter(this, mStringList));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); // 添加分割线
    }

}

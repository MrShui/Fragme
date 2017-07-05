package com.iuicity.xinjr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iuicity.xinjr.adapter.TestAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_test)
    RecyclerView mRvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> data = Arrays.asList("呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼", "呵呵", "哈哈", "嘻嘻", "呼呼");
        TestAdapter adapter = new TestAdapter(data);
        mRvTest.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvTest.setLayoutManager(layoutManager);
    }
}

package com.dhn.pullzoomrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.dhn.library.PullZoomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PullZoomRecyclerView zoomRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("" + i);
        }

        zoomRecyclerView = (PullZoomRecyclerView) findViewById(R.id.rv);


        zoomRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyAdapter adapter = new MyAdapter(datas, this);
        adapter.setImageResource(R.drawable.pic);

        zoomRecyclerView.setAdapter(adapter);
    }
}

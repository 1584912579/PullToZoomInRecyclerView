package com.dhn.pullzoomrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dhn.library.PullZoomRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PullZoomRecyclerView zoomRecyclerView;
    Toolbar toolbar;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    boolean isLinear = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("" + i);
        }


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2);

        zoomRecyclerView = (PullZoomRecyclerView) findViewById(R.id.rv);
        zoomRecyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter adapter = new MyAdapter(datas, this);
        adapter.setImageResource(R.drawable.pic);
        zoomRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_main) {
            if (isLinear) {
                zoomRecyclerView.setLayoutManager(gridLayoutManager);
                isLinear = false;
            } else {
                zoomRecyclerView.setLayoutManager(linearLayoutManager);
                isLinear = true;
            }
        }
        return true;
    }
}

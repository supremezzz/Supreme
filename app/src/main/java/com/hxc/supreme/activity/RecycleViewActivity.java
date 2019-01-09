package com.hxc.supreme.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hxc.supreme.R;
import com.hxc.supreme.adapter.RecycleAdapter;
import com.hxc.supreme.adapter.VerRecycleAdapter;
import com.hxc.supreme.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.SCREEN_STATE_ON;

/**
 * created by huxc  on 2017/9/28.
 * func： recycleView
 * email: hxc242313@qq.com
 */

public class RecycleViewActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView horRecyclerView;
    private RecyclerView verRecyclerView;
    private List<String> data;
    private RecycleAdapter adapter;
    private VerRecycleAdapter verRecycleAdapter;
    private Button btnInsert, btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        initView();
        initListener();
        initData();
    }

    protected void initView() {
        horRecyclerView = (RecyclerView) findViewById(R.id.hor_recycleView);
        verRecyclerView = (RecyclerView) findViewById(R.id.ver_recycleview);
        btnInsert = (Button) findViewById(R.id.insert_item);
        btnDelete = (Button) findViewById(R.id.delete_item);
    }

    protected void initListener() {
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    protected void initData() {
        adapter = new RecycleAdapter();
        verRecycleAdapter = new VerRecycleAdapter();
        getData();
        horRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horRecyclerView.setItemAnimator(new DefaultItemAnimator());
        horRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 3);
            }
        });
        horRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
                getVerData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                adapter.removeData(position);
            }
        });
        verRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        verRecyclerView.setItemAnimator(new DefaultItemAnimator());
        verRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 3);
            }
        });
        verRecyclerView.setAdapter(verRecycleAdapter);
        verRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("recycleViewActivity",newState+"");
                if(newState == SCROLL_STATE_IDLE){
                    findViewById(R.id.btn_all).setVisibility(View.VISIBLE);
                }else{
                    findViewById(R.id.btn_all).setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        verRecycleAdapter.setOnItemClickListener(new VerRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
//                adapter.removeData(position);
            }
        });

    }

    protected void getData() {
        data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        adapter.setData(data);
//        verRecycleAdapter.setData(data);
    }

    private void getVerData(int number) {
        List<String> verData = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            verData.add(i + "");
        }
        verRecycleAdapter.setData(verData);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_item:
                adapter.addData(1);
                break;
            case R.id.delete_item:
                adapter.removeData(1);
                break;
        }

    }

}

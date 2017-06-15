package prj.blog.joker.recyclerview.collection.new_base_adapter;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import prj.blog.joker.recyclerview.collection.R;
import prj.blog.joker.recyclerview.collection.activity.LinearActivity;

/**
 * Created by liuxiaoyu on 17/6/15.
 */

public class RecycleViewActivity1 extends Activity {

    private RecyclerView mRecycleView;
    private ArrayList<ItemBean> mList;
    private RecycleAdater1 mAdater1;
    private final String TAG = "RecycleViewActivity1.CLASS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setTvShow("第" + i + "个item");
            mList.add(itemBean);
        }
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdater1 = new RecycleAdater1(this, mList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                    RecyclerView.State state) {
                outRect.set(0, 8, 0, 0);
            }
        });
        mRecycleView.setAdapter(mAdater1);
    }

    private void initListener() {
        mAdater1.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d(TAG, "点击-------" + pos);
            }
        });

        mAdater1.setOnItemLongClickListener(new BaseAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                Log.d(TAG, "长按-------" + pos);
            }
        });
    }
}

package prj.blog.joker.recyclerview.collection.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import prj.blog.joker.recyclerview.collection.EditClearAdapter;
import prj.blog.joker.recyclerview.collection.ItemBean;
import prj.blog.joker.recyclerview.collection.R;

/**
 * Created by XiaoYuLiu on 17/3/10.
 */

public class EditClearActivity extends Activity implements EditClearAdapter.OnItemClickListener {

    private List<ItemBean> mDatas;
    private Button mDeleteBtn, editBtn, cancelBtn, selectAllBtn,unSelectAllBtn;
    private RecyclerView mRecyclerviewEditClear;
    private EditClearAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clear);
        initListData();
        initAdapter();
        initViews();
    }

    private void initViews() {
        mDeleteBtn = (Button) findViewById(R.id.delete_btn);
        editBtn = (Button) findViewById(R.id.edit_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        selectAllBtn = (Button) findViewById(R.id.select_all_btn);
        unSelectAllBtn = (Button) findViewById(R.id.unselect_all_btn);
        mRecyclerviewEditClear = (RecyclerView) findViewById(R.id.recyclerview_edit_clear);
    }

    private void initAdapter() {
        mAdapter = new EditClearAdapter(this, mDatas);
        mAdapter.setOnItemClickListener(this);
        RecyclerView recyclerviewEditClear = (RecyclerView) findViewById(R.id.recyclerview_edit_clear);
        recyclerviewEditClear.setAdapter(mAdapter);
        recyclerviewEditClear.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerviewEditClear.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(8, 8, 0, 0);
            }
        });
    }

    private void initListData() {
        mDatas = new ArrayList<ItemBean>();
        for (int i = 0; i < 100; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.setContent(String.valueOf(i));
            itemBean.setSelected(false);
            mDatas.add(itemBean);
        }
    }

    public void edit(View view) {
        editBtn.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.VISIBLE);
        selectAllBtn.setVisibility(View.VISIBLE);

        mAdapter.setType(1);
        mAdapter.notifyDataSetChanged();

    }

    public void cancel(View view) {
        editBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.GONE);
        selectAllBtn.setVisibility(View.GONE);
        unSelectAllBtn.setVisibility(View.GONE);

        mAdapter.setType(2);
        mAdapter.notifyDataSetChanged();
        mAdapter.reset();

    }

    public void selectAll(View view) {
        editBtn.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.VISIBLE);
        selectAllBtn.setVisibility(View.VISIBLE);
        selectAllBtn.setVisibility(View.GONE);
        unSelectAllBtn.setVisibility(View.VISIBLE);

        mAdapter.setType(3);
        mAdapter.notifyDataSetChanged();
    }

    public void unSelectAll(View view) {
        unSelectAllBtn.setVisibility(View.GONE);
        selectAllBtn.setVisibility(View.VISIBLE);

        mAdapter.setType(4);
        mAdapter.notifyDataSetChanged();
    }

    public void delete(View view) {
        mAdapter.setType(5);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        mAdapter.dealSelectedBean(position);
        mAdapter.notifyDataSetChanged();
    }
}

package prj.blog.joker.recyclerview.collection;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import prj.blog.joker.recyclerview.collection.databinding.ActivityRecyclerviewBinding;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<ItemBean> mDatas;
    private ActivityRecyclerviewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        RecyclerAdapter adapter = new RecyclerAdapter(this, mDatas);
        mBinding.recyclerview.setAdapter(adapter);
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,8,0,0);
            }
        });
    }

    private void initListData() {
        mDatas = new ArrayList<ItemBean>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add(new ItemBean("" + (char) i));
        }
    }
}

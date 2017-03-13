package prj.blog.joker.recyclerview.collection.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import prj.blog.joker.recyclerview.collection.Decoration.DividerGridItemDecoration;
import prj.blog.joker.recyclerview.collection.R;
import prj.blog.joker.recyclerview.collection.RecyclerAdapter;
import prj.blog.joker.recyclerview.collection.ViewModel.ItemBean;
import prj.blog.joker.recyclerview.collection.databinding.ActivityRecyclerviewBinding;

/**
 * Created by XiaoYuLiu on 17/3/10.
 */

public class GridActivity extends AppCompatActivity {

    private List<ItemBean> mDatas;
    private ActivityRecyclerviewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        RecyclerAdapter adapter = new RecyclerAdapter(this, mDatas);
        mBinding.recyclerview.setAdapter(adapter);
        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this,4));

        /**
         * 布局的方向和4的方向是垂直关系
         * GridLayoutManager构造的第二个参数传一个orientation，如果传入的是GridLayoutManager.VERTICAL代表有多少列；
         * 那么传入的如果是GridLayoutManager.HORIZONTAL就代表有多少行
         */
//        mBinding.recyclerview.setLayoutManager(new GridLayoutManager(this,4, LinearLayoutManager.VERTICAL,false));
//        mBinding.recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
//                                       RecyclerView.State state) {
//                outRect.set(8, 0, 8, 8);
//            }
//        });
        mBinding.recyclerview.addItemDecoration(new DividerGridItemDecoration(this));
    }

    private void initListData() {
        mDatas = new ArrayList<ItemBean>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add(new ItemBean("" + (char) i));
        }
    }
}
package prj.blog.joker.recyclerview.collection.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

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

public class StaggeredGridActivity extends AppCompatActivity{

    private List<ItemBean> mDatas;
    private ActivityRecyclerviewBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListData();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview);
        RecyclerAdapter adapter = new RecyclerAdapter(this, mDatas);
        mBinding.recyclerview.setAdapter(adapter);
        mBinding.recyclerview.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));

//        mBinding.recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                outRect.set(0,8,0,0);
//            }
//        });


        mBinding.recyclerview.addItemDecoration(new DividerGridItemDecoration(this));

//        mBinding.recyclerview.addItemDecoration(new LinearLayoutColorDivider(this
//                ,R.color.red,10,StaggeredGridLayoutManager.VERTICAL));
//        mBinding.recyclerview.addItemDecoration(new VerticalDividerDecoration());

        mBinding.recyclerview.setItemAnimator(new SimpleItemAnimator() {
            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateAdd(RecyclerView.ViewHolder holder) {
                return false;
            }

            @Override
            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                return false;
            }

            @Override
            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                return false;
            }

            @Override
            public void runPendingAnimations() {

            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {

            }

            @Override
            public void endAnimations() {

            }

            @Override
            public boolean isRunning() {
                return false;
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
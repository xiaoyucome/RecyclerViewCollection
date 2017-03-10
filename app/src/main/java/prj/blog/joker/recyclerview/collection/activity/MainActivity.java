package prj.blog.joker.recyclerview.collection.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import prj.blog.joker.recyclerview.collection.R;
import prj.blog.joker.recyclerview.collection.ViewModel.MainActivityVM;
import prj.blog.joker.recyclerview.collection.databinding.ActivityMainBinding;

/**
 * Created by XiaoYuLiu on 17/3/10.
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setActivityModel(new MainActivityVM());
    }

}

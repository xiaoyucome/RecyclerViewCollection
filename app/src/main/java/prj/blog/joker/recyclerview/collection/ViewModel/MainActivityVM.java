package prj.blog.joker.recyclerview.collection.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import prj.blog.joker.recyclerview.collection.activity.GridActivity;
import prj.blog.joker.recyclerview.collection.activity.LinearActivity;
import prj.blog.joker.recyclerview.collection.activity.StaggeredGridActivity;

/**
 * Created by XiaoYuLiu on 17/3/10.
 */

public class MainActivityVM {

    public void clickLinear(View view) {
        Context context = view.getContext();
        context.startActivity(new Intent(context, LinearActivity.class));
    }

    public void clickGrid(View view) {
        Context context = view.getContext();
        context.startActivity(new Intent(context, GridActivity.class));
    }

    public void clickWaterFall(View view) {
        Context context = view.getContext();
        context.startActivity(new Intent(context, StaggeredGridActivity.class));
    }

}

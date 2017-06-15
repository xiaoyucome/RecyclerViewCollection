package prj.blog.joker.recyclerview.collection.new_base_adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by liuxiaoyu on 17/6/15.
 */

public class RecycleAdater1 extends BaseAdapter<ItemBean, ItemView> {

    public RecycleAdater1(Context context, List<ItemBean> data) {
        super(context, data);
    }

    @Override
    protected ItemView createView(Context context) {
        return new ItemView(context);
    }
}

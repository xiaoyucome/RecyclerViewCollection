package prj.blog.joker.recyclerview.collection.new_base_adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import prj.blog.joker.recyclerview.collection.R;

/**
 * Created by liuxiaoyu on 17/6/15.
 */

public class ItemView extends RelativeLayout implements IAdapterView<ItemBean> {

    private final TextView mTvShow;

    public ItemView(Context context) {
        super(context);
        View inflate = View.inflate(context, R.layout.item1, this);
        mTvShow = (TextView) inflate.findViewById(R.id.item1_tv);
    }

    @Override
    public void bind(ItemBean view, int position) {
        mTvShow.setText(view.getTvShow());
    }
}

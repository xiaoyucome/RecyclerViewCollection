package prj.blog.joker.recyclerview.collection;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import prj.blog.joker.recyclerview.collection.ViewModel.ItemBean;
import prj.blog.joker.recyclerview.collection.databinding.ItemBinding;

/**
 * Created by XiaoYuLiu on 17/3/9.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<ItemBean> mList;
    private List<Integer> mHeights;
    private final LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context, List<ItemBean> datas) {
        this.mList = datas;
        mLayoutInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mList.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding binding = DataBindingUtil
                .inflate(mLayoutInflater, R.layout.item, parent, false);
        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.mBinding.itemTv.getLayoutParams();
        if (position != 0 && position < mList.size()) {
            params.height = mHeights.get(position);
            holder.mBinding.itemTv.setLayoutParams(params);
        }

        holder.getBinding().setVariable(BR.itemBean, mList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding mBinding;

        public RecyclerViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public ItemBinding getBinding() {
            return mBinding;
        }
    }
}

package prj.blog.joker.recyclerview.collection;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by XiaoYuLiu on 17/3/9.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<ItemBean> mList;
    private final LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context, List<ItemBean> datas) {
        this.mContext = context;
        this.mList = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item, parent,
                false);
        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.itemBean, mList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public RecyclerViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }
}

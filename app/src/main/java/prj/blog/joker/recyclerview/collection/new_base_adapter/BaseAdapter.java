package prj.blog.joker.recyclerview.collection.new_base_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liuxiaoyu on 17/6/15.
 * T：传进来的集合的数据类型
 * V：条目item数据类型
 */

public abstract class BaseAdapter<T, V extends IAdapterView> extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<T> mData;
    private int mLastItemClickPosition = RecyclerView.NO_POSITION;

    public BaseAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
    }

    protected abstract V createView(Context context);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = (View) createView(mContext);
        final RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(itemView) {
        };

        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        mLastItemClickPosition = adapterPosition;
                        mOnItemClickListener.onItemClick(adapterPosition);
                    }
                }
            });
        }

        if (mItemLongClickListener != null) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        mItemLongClickListener.onItemLongClick(adapterPosition);
                    }
                    return false;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IAdapterView itemView = (IAdapterView) holder.itemView;
        itemView.bind(getItem(position), position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public T getItem(int pos) {
        return mData.get(pos);
    }

    public void setData(List<T> data) {
        mData = data;
    }

    public void addData(List<T> data) {
        if (mData == null) {
            mData = data;
        } else {
            mData.addAll(data);
        }
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
    }

    /**
     * 点击监听
     */
    private onItemClickListener mOnItemClickListener;

    public interface onItemClickListener {
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public onItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    /**
     * 长按监听
     */
    private onItemLongClickListener mItemLongClickListener;

    public interface onItemLongClickListener {
        void onItemLongClick(int pos);
    }

    public void setOnItemLongClickListener(onItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public onItemLongClickListener getItemLongClickListener() {
        return mItemLongClickListener;
    }

    /**
     * 获取上次点击的位置
     */
    public int getLastItemClickPosition() {
        return mLastItemClickPosition;
    }

}

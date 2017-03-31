package prj.blog.joker.recyclerview.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XiaoYuLiu on 17/3/9.
 */

/**
 * 编辑：显示空圈
 * 取消：隐藏空圈／石圈
 * 全选：全部选中石圈
 * 删除：删除石圈数据
 */
public class EditClearAdapter extends RecyclerView.Adapter<EditClearAdapter.RecyclerViewHolder> {
    private Context mContext;
    private List<ItemBean> mList;
    private List<ItemBean> deleteList;
    private final LayoutInflater mLayoutInflater;
    private boolean isSelected;
    private OnItemClickListener mOnItemClickListener;
    private boolean[] isSelectArr;
    private final int EDIT = 1;
    private final int CANCEL = 2;
    private final int SELECTALL = 3;
    private final int UNSELECTALL = 4;
    private final int DELETE = 5;
    private int mType;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public EditClearAdapter(Context context, List<ItemBean> datas) {
        this.mList = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        setTotalCount(mList.size());
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mLayoutInflater.inflate(R.layout.edit_clear_item, parent, false);
        return new RecyclerViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        ItemBean itemBean = mList.get(position);
        holder.difSelectIv.setVisibility(View.GONE);
        if (mType == EDIT) {
            holder.difSelectIv.setVisibility(View.VISIBLE);
            if (isSelectArr[position]) {
                holder.difSelectIv.setImageResource(R.mipmap.mc_select_pressed);
            } else {
                holder.difSelectIv.setImageResource(R.mipmap.mc_select_normal);
            }
        } else if (mType == CANCEL) {
            holder.difSelectIv.setVisibility(View.GONE);
        } else if (mType == SELECTALL) {
            holder.difSelectIv.setVisibility(View.VISIBLE);
            holder.difSelectIv.setImageResource(R.mipmap.mc_select_pressed);
        } else if (mType == UNSELECTALL) {
            holder.difSelectIv.setVisibility(View.GONE);
        }

        holder.mTextView.setText(itemBean.getContent());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.mView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setTotalCount(int count) {
        isSelectArr = new boolean[count];
    }

    public void dealSelectedBean(int pos) {
        isSelectArr[pos] = !isSelectArr[pos];
    }

    public void setType(int type) {
        mType = type;
    }

    public void reset() {
        mType = 0;
        for (int i = 0; i < isSelectArr.length; i++) {
            isSelectArr[i] = false;
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView mTextView;
        private ImageView difSelectIv;

        public RecyclerViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.item_tv);
            difSelectIv = (ImageView) view.findViewById(R.id.dif_select_iv);
            this.mView = view;
        }
    }
}

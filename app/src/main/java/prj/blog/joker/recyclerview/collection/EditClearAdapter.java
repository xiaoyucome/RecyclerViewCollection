package prj.blog.joker.recyclerview.collection;

import android.content.Context;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private boolean isSelectedMode;// 是否是编辑状态
    private OnItemClickListener mOnItemClickListener;
    private deleteBtnVisibilityListener mDeleteBtnVisibilityListener;
    public boolean[] isSelectArr;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface deleteBtnVisibilityListener {
        void deleteBtnIsVisible(boolean isShow);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setDeleteBtnVisibilityListener(deleteBtnVisibilityListener listener) {
        this.mDeleteBtnVisibilityListener = listener;
    }

    public EditClearAdapter(Context context, List<ItemBean> datas) {
        this.mList = datas;
        deleteList = new ArrayList<>();
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
        // 先看是否看见
        if (isSelectedMode) {
            holder.difSelectIv.setVisibility(View.VISIBLE);
        } else {
            holder.difSelectIv.setVisibility(View.GONE);
        }

        // 在设置状态
        ItemBean itemBean = mList.get(position);
        if (isSelectArr[position]) {
            holder.difSelectIv.setImageResource(R.mipmap.mc_select_pressed);
        } else {
            holder.difSelectIv.setImageResource(R.mipmap.mc_select_normal);
        }

        holder.mTextView.setText(itemBean.getContent());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelectedMode) {
                    mOnItemClickListener.onItemClick(holder.mView, position);
                }
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

    // 设置是否是选择模式
    public void setSelectedMode(boolean selectedMode) {
        isSelectedMode = selectedMode;
        notifyDataSetChanged();
    }

    // 全选
    public void setSelectAll() {
        for (int i = 0; i < isSelectArr.length; i++) {
            isSelectArr[i] = true;
        }
        notifyDataSetChanged();
    }

    // 取消全选
    public void setUnSelectAll() {
        for (int i = 0; i < isSelectArr.length; i++) {
            isSelectArr[i] = false;
        }
        notifyDataSetChanged();
    }

    // 重置
    public void reset() {
        for (int i = 0; i < isSelectArr.length; i++) {
            isSelectArr[i] = false;
        }
        notifyDataSetChanged();
    }

    // 处理点击的条目状态
    public void dealSelectedBean(int pos) {
        isSelectArr[pos] = !isSelectArr[pos];
        checkDeteleBtnVisibility();
    }

    public void deleteBean() {
        for (int i = 0; i < isSelectArr.length; i++) {
            if (isSelectArr[i]) {
                deleteList.add(mList.get(i));
            }
        }

        for (int i = 0; i < deleteList.size(); i++) {
            mList.remove(deleteList.get(i));
        }

        isSelectArr = new boolean[mList.size()];
        notifyDataSetChanged();
    }

    // 检查删除按钮是否应该显示，只要有一个按钮被选中，就该显示！！！～
    public void checkDeteleBtnVisibility() {
        for (int i = 0; i < isSelectArr.length; i++) {
            if (isSelectArr[i]) {
                mDeleteBtnVisibilityListener.deleteBtnIsVisible(true);
                return;
            }
        }
        mDeleteBtnVisibilityListener.deleteBtnIsVisible(false);
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

            String[] a = new String[3];
            List<String> lista = Arrays.asList(a);
            a = (String[]) lista.toArray(new String[lista.size()]);

        }
    }
}

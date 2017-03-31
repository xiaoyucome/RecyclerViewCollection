package prj.blog.joker.recyclerview.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by XiaoYuLiu on 17/3/9.
 */

public class EditClearAdapter extends RecyclerView.Adapter<EditClearAdapter.RecyclerViewHolder> {
    private List<String> mList;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    public EditClearAdapter(Context context, List<String> datas) {
        this.mList = datas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mLayoutInflater.inflate(R.layout.edit_clear_item, parent, false);
        return new RecyclerViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String str = mList.get(position);
        holder.mTextView.setText(str);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public RecyclerViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.item_tv);
        }
    }
}

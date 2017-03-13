package prj.blog.joker.recyclerview.collection.Decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * 瀑布流的itemDecoration
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 默认R.attr.listDivider使用的是默认的
     * 如果想自定义，在style.xml文件的AppTheme中添加listDivider属性，并指定资源divider_bg
     */
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context) {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDivider = array.getDrawable(0);
        array.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 测试child的ondraw和decoration的ondraw谁先draw
     * @param c
     * @param
     *        经过验证：decoration的ondraw是后画上去的，child的ondraw先画
     */
    // public void drawHorizontal(Canvas c, RecyclerView parent) {
    // int childCount = parent.getChildCount();
    // for (int i = 0; i < childCount; i++) {
    // final View child = parent.getChildAt(i);
    // int left = child.getLeft();
    // int top = child.getTop();
    // int right = child.getRight() + mDivider.getIntrinsicWidth();
    // int bottom = child.getBottom() + mDivider.getIntrinsicHeight();
    // mDivider.setBounds(left, top, right, bottom);
    // mDivider.draw(c);
    // }
    // }

    /**
     * 自己实现
     * @param c
     * @param parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int left = child.getRight();
            int top = child.getTop();
            int right = left + mDivider.getIntrinsicWidth();
            int bottom = top + child.getHeight() + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 自己实现
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int left = child.getLeft();
            int top = child.getBottom();
            int right = left + child.getWidth();
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 鸿洋实现
     * @param c
     * @param parent
     */
    // public void drawHorizontal(Canvas c, RecyclerView parent) {
    // int childCount = parent.getChildCount();
    // for (int i = 0; i < childCount; i++) {
    // final View child = parent.getChildAt(i);
    // final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
    // .getLayoutParams();
    // final int left = child.getLeft() - params.leftMargin;
    // final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
    // final int top = child.getBottom() + params.bottomMargin;
    // final int bottom = top + mDivider.getIntrinsicHeight();
    // mDivider.setBounds(left, top, right, bottom);
    // mDivider.draw(c);
    // }
    // }

    /**
     * 鸿洋实现
     * @param c
     * @param parent
     */
    // public void drawVertical(Canvas c, RecyclerView parent) {
    // final int childCount = parent.getChildCount();
    // for (int i = 0; i < childCount; i++) {
    // final View child = parent.getChildAt(i);
    //
    // final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
    // .getLayoutParams();
    // final int top = child.getTop() - params.topMargin;
    // final int bottom = child.getBottom() + params.bottomMargin;
    // final int left = child.getRight() + params.rightMargin;
    // final int right = left + mDivider.getIntrinsicWidth();
    //
    // mDivider.setBounds(left, top, right, bottom);
    // mDivider.draw(c);
    // }
    // }

    /**
     * 鸿洋实现
     * @param c
     * @param parent
     */
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // @Override
    // public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
    // RecyclerView.State state) {
    // int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams())
    // .getViewAdapterPosition();
    // int totalItemCount = parent.getAdapter().getItemCount();
    // if (itemPosition == totalItemCount - 1) {
    // outRect.set(0, 0, 0, 0);
    // } else {
    // outRect.set(0, 0, 5, 5);
    // }
    // }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int totalCount = parent.getAdapter().getItemCount();

//        int hang = totalCount / spanCount;
//        Log.d("sdsdsdsdsdsd","totalCount:"+totalCount+"---spanCount:"+spanCount+"----hang:"+hang);
//        if ((spanCount * hang) < itemPosition) {// 是最后一行
//            Log.d("sdsdsdsdsdsd","0000----行---"+totalCount+"--itemPosition----"+itemPosition);
//            outRect.set(0, 0, 0, 0);
//        } else if (itemPosition % spanCount == 0) {
//            Log.d("sdsdsdsdsdsd","0000----列---");
//            outRect.set(0, 0, 0, 0);
//        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//        }

//         if (isLastRaw(parent, itemPosition, spanCount, totalCount))// 如果是最后一行，则不需要绘制底部
//         {
//         outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
//         } else if (isLastColum(parent, itemPosition, spanCount, totalCount))// 如果是最后一列，则不需要绘制右边
//         {
//         outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//         } else {
//         outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
//         }
    }
}

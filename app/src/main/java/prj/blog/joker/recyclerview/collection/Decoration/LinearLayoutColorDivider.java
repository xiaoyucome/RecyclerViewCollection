package prj.blog.joker.recyclerview.collection.Decoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * https://blog.piasy.com/2016/03/26/Insight-Android-RecyclerView-ItemDecoration/
 * getItemOffsets：可以通过outRect.set()为每个Item设置一定的偏移量，主要用于绘制Decorator   --->   insters值变化   --->   childv view 的padding发生变化
 * Decoration的ondraw   --->   限制divider的范围，并ondraw上去，onDraw方法先于drawChildren
 * onDrawOver   在drawChildren之后，一般我们选择复写其中一个即可。是绘制在最上层的，所以它的绘制位置并不受限制
 */
public class LinearLayoutColorDivider extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private final int mSize;
    private final int mOrientation;

    // public LinearLayoutColorDivider(Resources resources, @ColorRes int color,
    // @DimenRes int size, int orientation) {
    // mDivider = new ColorDrawable(resources.getColor(color));
    // mSize = resources.getDimensionPixelSize(100);
    // mOrientation = orientation;
    // }

    public LinearLayoutColorDivider(Context context, @ColorRes int color, @DimenRes int size,
            int orientation) {
        Resources resources = context.getResources();
        mDivider = new ColorDrawable(resources.getColor(color));
        mSize = 10;
        mOrientation = orientation;
    }

    // 画ItemDecoration
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left;
        int right;
        int top;
        int bottom;
        // if (mOrientation == LinearLayoutManager.HORIZONTAL) {
        // top = parent.getPaddingTop();
        // bottom = parent.getHeight() - parent.getPaddingBottom();
        // final int childCount = parent.getChildCount();
        // for (int i = 0; i < childCount - 1; i++) {
        // final View child = parent.getChildAt(i);
        // final RecyclerView.LayoutParams params =
        // (RecyclerView.LayoutParams) child.getLayoutParams();
        // left = child.getRight() + params.rightMargin;
        // right = left + mSize;
        // mDivider.setBounds(left, top, right, bottom);
        // mDivider.draw(c);
        // }
        // } else {
        // left = parent.getPaddingLeft();
        // right = parent.getWidth() - parent.getPaddingRight();
        // final int childCount = parent.getChildCount();
        // for (int i = 0; i < childCount - 1; i++) {
        // final View child = parent.getChildAt(i);
        // final RecyclerView.LayoutParams params =
        // (RecyclerView.LayoutParams) child.getLayoutParams();
        // top = child.getBottom() + params.bottomMargin;
        // bottom = top + mSize;
        // mDivider.setBounds(left, top, right, bottom);
        // mDivider.draw(c);
        //
        // }
        // }

        /**
         * test
         */
        // left = parent.getPaddingLeft();
        // right = parent.getWidth() - parent.getPaddingRight();
        // final int childCount = parent.getChildCount();
        // for (int i = 0; i < childCount - 1; i++) {
        // final View child = parent.getChildAt(i);
        // final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
        // .getLayoutParams();
        // top = child.getBottom() + params.bottomMargin;
        // bottom = top + 10;
        // mDivider.setBounds(left, top, right, bottom);
        // mDivider.draw(c);
        // }

        left = parent.getPaddingLeft();
        right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            top = child.getBottom() + params.bottomMargin - 25;
            bottom = top + 50;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

        // left = parent.getPaddingLeft() - 100;
        // right = parent.getWidth() - parent.getPaddingRight() - 100;
        // final int childCount = parent.getChildCount();
        // for (int i = 0; i < childCount - 1; i++) {
        // final View child = parent.getChildAt(i);
        // final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
        // .getLayoutParams();
        // top = child.getBottom() + params.bottomMargin - 25;
        // bottom = top + 50;
        // mDivider.setBounds(left, top, right, bottom);
        // mDivider.draw(c);
        // }
    }

    /**
     * 限定范围
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        // if (mOrientation == LinearLayoutManager.HORIZONTAL) {
        // outRect.set(0, 0, mSize, 0);
        // } else {
        // outRect.set(0, 0, 0, mSize);
        // }
        outRect.set(0, 0, 0, 50);

    }
}
package me.zsj.imageslider.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.zsj.imageslider.MainActivity;
import me.zsj.imageslider.R;

/**
 * Created by zsj on 2015/8/16 0016.
 *
 *<p>
 *     设置图片导航点
 *</p>
 */
public class PagerIndicator extends LinearLayout {


    private GradientDrawable mUnSelectedShapeDrawable;
    private GradientDrawable mSelectedShapeDrawable;

    private Drawable mSelectedDrawable;
    private Drawable mUnSelectedDrawable;

    private int mSelectedColor;
    private int mUnSelectedColor;

    private int mIndicatorPaddingLeft;
    private int mIndicatorPaddingRight;
    private int mIndicatorPaddingTop;
    private int mIndicatorPaddingButtom;

    private int mUnSelectedIndicatorSize;
    private int mSelectedIndicatorSize;

    private List<ImageView> mPageImageViews = new ArrayList<>();
    private ImageView mImageView;

    public PagerIndicator(Context context) {
        this(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicator, 0, 0);

        mUnSelectedShapeDrawable = new GradientDrawable();
        mSelectedDrawable = ta.getDrawable(R.styleable.PagerIndicator_selected_drawable);

        mSelectedColor = ta.getColor(R.styleable.PagerIndicator_selected_color, Color.rgb(255, 255, 255));
        mUnSelectedColor = ta.getColor(R.styleable.PagerIndicator_unSelected_color, Color.argb(33, 255, 255, 255));

        mIndicatorPaddingLeft = pxTodp(0);
        mIndicatorPaddingTop = pxTodp(6);
        mIndicatorPaddingRight = pxTodp(6);
        mIndicatorPaddingButtom = pxTodp(6);

        mUnSelectedIndicatorSize = pxTodp(8);

        setIndicatorShape();
        setUnSelectedIndicatorSize(mUnSelectedIndicatorSize);
        setIndicatorRes(mUnSelectedShapeDrawable);

        ta.recycle();
    }


    private void setIndicatorShape() {
        mUnSelectedShapeDrawable.setShape(GradientDrawable.OVAL);
        mUnSelectedShapeDrawable.setColor(mUnSelectedColor);
    }


    private void setIndicatorRes(GradientDrawable unSelectedDrawable) {
        mUnSelectedDrawable = unSelectedDrawable;
        resetDrawable();
    }


    private void setUnSelectedIndicatorSize(int size) {
        int width = size;
        int height = size;

        mUnSelectedShapeDrawable.setSize(width, height);
    }

    private void resetDrawable() {

        for (int i = 0; i < MainActivity.mImageSource.length; i++) {
            mImageView = new ImageView(getContext());
            mImageView.setPadding(
                    mIndicatorPaddingLeft,
                    mIndicatorPaddingTop,
                    mIndicatorPaddingRight,
                    mIndicatorPaddingButtom
            );
            addView(mImageView);
            mPageImageViews.add(mImageView);
        }
    }

    public void setItemAsSelected(int item) {

        for (int i = 0; i < mPageImageViews.size(); i++) {
            mPageImageViews.get(i).setImageDrawable(mUnSelectedDrawable);
        }
        mPageImageViews.get(item).setImageDrawable(mSelectedDrawable);
    }

    private int pxTodp(int dp) {
        float size = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp, getContext().getResources().getDisplayMetrics());
        return (int) size;
    }

}

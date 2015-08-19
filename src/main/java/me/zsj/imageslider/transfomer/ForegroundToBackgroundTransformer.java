package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class ForegroundToBackgroundTransformer implements ViewPager.PageTransformer {


    /**
     * 代码参考 damajia 的 AndroidImageSlider 开源库源码
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {

        final float height = view.getHeight();
        final float width = view.getWidth();
        final float scale = min(position > 0 ? 1f : Math.abs(1f + position), 0.5f);

        ViewHelper.setScaleX(view,scale);
        ViewHelper.setScaleY(view,scale);
        ViewHelper.setPivotX(view, width * 0.5f);
        ViewHelper.setPivotY(view,height * 0.5f);
        ViewHelper.setTranslationX(view,position > 0 ? width * position : -width * position * 0.25f);
    }

    private static final float min(float val, float min) {
        return val < min ? min : val;
    }
}

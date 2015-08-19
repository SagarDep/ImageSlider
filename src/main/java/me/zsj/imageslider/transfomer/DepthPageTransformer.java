package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;


    /**
     * 代码参考 damajia 的 AndroidImageSlider 开源库源码
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {
        if (position <= 0f) {
            ViewHelper.setTranslationX(view, 0f);
            ViewHelper.setScaleX(view,1f);
            ViewHelper.setScaleY(view, 1f);
        } else if (position <= 1f) {  // position [0, 1]之间 变化
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            ViewHelper.setAlpha(view,1-position);
            ViewHelper.setPivotY(view, 0.5f * view.getHeight());
            ViewHelper.setTranslationX(view,view.getWidth() * - position);
            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);
        }
    }
}
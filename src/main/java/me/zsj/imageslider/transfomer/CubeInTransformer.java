package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class CubeInTransformer implements ViewPager.PageTransformer {

    /**
     * 代码参考 damajia 的 AndroidImageSlider 开源库源码
     * @param view Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {

        if (position < -1) {
            ViewHelper.setPivotX(view, 0);
        }else if (position <= 0) {
            ViewHelper.setPivotX(view, view.getWidth());
        }else if (position <= 1) {
            ViewHelper.setPivotY(view,0);
            ViewHelper.setRotation(view, -90f * position);
        }
    }
}

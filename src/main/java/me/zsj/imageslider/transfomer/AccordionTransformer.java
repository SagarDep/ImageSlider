package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class AccordionTransformer implements ViewPager.PageTransformer {

    /**
     * @param view Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {

        if (position < -1) {
            ViewHelper.setScaleX(view, 1f);
        }else if (position <= 0.0f) {
            ViewHelper.setPivotX(view, view.getWidth());
            ViewHelper.setScaleX(view, 1.0f + position);
        }else if (position <= 1.0f) {
            ViewHelper.setPivotX(view, 0);
            ViewHelper.setScaleX(view, 1.0f - position);
        }
    }
}

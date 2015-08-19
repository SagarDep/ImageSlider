package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class FlipHorizontalTransformer implements ViewPager.PageTransformer {

    private static final float ROTATION = 180.0f;
    /**
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {
        if (position <= 0.0f) {
            ViewHelper.setTranslationX(view, -view.getWidth() * position);
            float rotation = (ROTATION * position);
            ViewHelper.setRotationY(view, rotation);

            if (position > -0.5) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }else if (position <= 1.0f) {
            ViewHelper.setTranslationX(view, -view.getWidth() * position);
            float rotation = (ROTATION * position);
            ViewHelper.setRotationY(view, rotation);

            if (position < 0.5) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}

package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;


/**
 * Created by zsj on 2015/8/18 0018.
 */
public class FlipPageViewTransformer implements ViewPager.PageTransformer {


    /**
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {

        float percentage = 1 - Math.abs(position);

        if (position <= 0.0f) {

            if (position < 0.5 && position > -0.5) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }

            ViewHelper.setScaleX(view, 1 - Math.abs(position));
            ViewHelper.setScaleY(view, 1 - Math.abs(position));

            ViewHelper.setRotationY(view, 180 * (percentage + 1));
        }else if (position <= 1.0f) {
            ViewHelper.setScaleX(view, 1 - Math.abs(position));
            ViewHelper.setScaleY(view, 1 - Math.abs(position));

            ViewHelper.setRotationY(view, -180 * (percentage + 1));
        }
    }

}

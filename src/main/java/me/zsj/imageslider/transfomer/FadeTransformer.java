package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class FadeTransformer implements ViewPager.PageTransformer {

    /**
     * 代码参考 damajia 的 AndroidImageSlider 开源库源码
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {
        // Page is not an immediate sibling, just make transparent
        if(position < -1 || position > 1) {
            ViewHelper.setAlpha(view,0.6f);
        }
        // Page is sibling to left or right
        else if (position <= 0 || position <= 1) {

            // Calculate alpha.  Position is decimal in [-1,0] or [0,1]
            float alpha = (position <= 0) ? position + 1 : 1 - position;
            ViewHelper.setAlpha(view,alpha);

        }
        // Page is active, make fully visible
        else if (position == 0) {
            ViewHelper.setAlpha(view, 1);
        }
    }
}

package me.zsj.imageslider.transfomer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import me.zsj.imageslider.indicator.ViewPager;

/**
 * Created by zsj on 2015/8/18 0018.
 */
public class ZoomOutSlideTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;


    /**
     * 代码参考 damajia 的 AndroidImageSlider 开源库源码
     * @param view
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     */
    @Override
    public void transformPage(View view, float position) {

        if (position >= -1 || position <= 1) {
            // Modify the default slide transition to shrink the page as well
            final float height = view.getHeight();
            final float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            final float vertMargin = height * (1 - scaleFactor) / 2;
            final float horzMargin = view.getWidth() * (1 - scaleFactor) / 2;

            // Center vertically
            ViewHelper.setPivotY(view,0.5f * height);


            if (position < 0) {
                ViewHelper.setTranslationX(view,horzMargin - vertMargin / 2);
            } else {
                ViewHelper.setTranslationX(view,-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);

            // Fade the page relative to its size.
            ViewHelper.setAlpha(view, MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}

package me.zsj.imageslider.indicator;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zsj on 2015/8/17 0017.
 * 代码参考github 开源项目 CycleViewPager
 */
public class BannerViewPager extends ViewPager {

    private InnerPagerAdapter mAdapter;
    private PagerIndicator mPagerIndicator;

    public BannerViewPager(Context context) {
        super(context);
        setOnPageChangeListener(null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener(null);
    }


    @Override
    public void setAdapter(PagerAdapter adapter) {
        mAdapter = new InnerPagerAdapter(adapter);
        super.setAdapter(mAdapter);
        setCurrentItem(1);
    }

    public void setPagerIndicator(PagerIndicator pagerIndicator) {
        mPagerIndicator = pagerIndicator;
    }


    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        super.setOnPageChangeListener(new InnerOnPageChangeListener(listener));
    }

    private class InnerOnPageChangeListener implements OnPageChangeListener {

        private OnPageChangeListener listener;
        private int mPagePosition;

        public InnerOnPageChangeListener(OnPageChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (null != listener) {
                listener.onPageScrollStateChanged(state);
            }
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (mPagePosition == mAdapter.getCount() - 1) {
                    setCurrentItem(1, false);
                } else if (mPagePosition == 0) {
                    setCurrentItem(mAdapter.getCount() - 2, false);
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels2) {
            if (null != listener) {
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels2);
            }
        }

        @Override
        public void onPageSelected(int position) {
            mPagePosition = position;

            if (null != listener) {
                listener.onPageSelected(position);
            }
            if (position >= 1 && position <= 3) {
                mPagerIndicator.setItemAsSelected(position - 1);
            } else if (position == 4) {
                position--;
                mPagerIndicator.setItemAsSelected(position);
            } else if (position == 0) {
                mPagerIndicator.setItemAsSelected(3);
            }

        }
    }

    private class InnerPagerAdapter extends PagerAdapter {

        private PagerAdapter adapter;

        public InnerPagerAdapter(PagerAdapter adapter) {
            this.adapter = adapter;
            adapter.registerDataSetObserver(new DataSetObserver() {

                @Override
                public void onChanged() {
                    notifyDataSetChanged();
                }

                @Override
                public void onInvalidated() {
                    notifyDataSetChanged();
                }

            });
        }

        @Override
        public int getCount() {
            return adapter.getCount() + 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return adapter.isViewFromObject(view, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int mPagePosition) {
            if (mPagePosition == 0) {
                mPagePosition = adapter.getCount() - 1;
            } else if (mPagePosition == adapter.getCount() + 1) {
                mPagePosition = 0;
            } else {
                mPagePosition -= 1;
            }
            return adapter.instantiateItem(container, mPagePosition);
        }

        @Override
        public void destroyItem(ViewGroup container, int mPagePosition, Object object) {
            adapter.destroyItem(container, mPagePosition, object);
        }

    }
}

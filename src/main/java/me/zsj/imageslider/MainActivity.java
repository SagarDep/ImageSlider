package me.zsj.imageslider;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.zsj.imageslider.indicator.BannerViewPager;
import me.zsj.imageslider.indicator.PagerIndicator;
import me.zsj.imageslider.transfomer.AccordionTransformer;
import me.zsj.imageslider.transfomer.BackgroundToForegroundTransformer;
import me.zsj.imageslider.transfomer.CubeInTransformer;
import me.zsj.imageslider.transfomer.DefaultTransformer;
import me.zsj.imageslider.transfomer.DepthPageTransformer;
import me.zsj.imageslider.transfomer.FlipHorizontalTransformer;
import me.zsj.imageslider.transfomer.FlipPageViewTransformer;
import me.zsj.imageslider.transfomer.ForegroundToBackgroundTransformer;
import me.zsj.imageslider.transfomer.RotateDownTransformer;
import me.zsj.imageslider.transfomer.RotateUpTransformer;
import me.zsj.imageslider.transfomer.StackTransformer;
import me.zsj.imageslider.transfomer.TabletTransformer;
import me.zsj.imageslider.transfomer.ZoomOutSlideTransformer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private BannerViewPager mViewPager;
    private PagerIndicator mPagerIndicator;
    private BannerAdapter mBannerAdapter;
    private ListView mTranslationList;
    private TransformerAdapter mTransformerAdapter;

    private List<String> mTransLists;
    private List<ImageView> mImageList = new ArrayList<>();

    private int mPagePosition = 1;

    public static final int mImageSource[] = new int[]{
            R.mipmap.hannibal,
            R.mipmap.house,
            R.mipmap.game_of_thrones,
            R.mipmap.bigbang
    };

    /**
     * 自动轮播的代码
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mPagePosition++;
            if (mPagePosition == 5) {
                mPagePosition = 1;
                mViewPager.setCurrentItem(mPagePosition, false);
            }
            if (mPagePosition == 5) {
                mPagePosition = 1;
            } else if (mPagePosition == 0) {
                mPagePosition = 4;
            }
            mViewPager.setCurrentItem(mPagePosition);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (BannerViewPager) findViewById(R.id.sliderPager);
        mPagerIndicator = (PagerIndicator) findViewById(R.id.pageIndicator);
        mTranslationList = (ListView) findViewById(R.id.translationList);
        initLists();

        mBannerAdapter = new BannerAdapter();

        mViewPager.setPagerIndicator(mPagerIndicator);
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.setPageTransformer(true, new AccordionTransformer());

        mViewPager.setOnPageChangeListener(mBannerAdapter);

        /**
         * 监听ViewPager 触摸事件，Down, Move 时 Handler 都移除消息发送，
         * 避免图片在按下或者拖动时继续发送消息导致图片自动轮播
         */

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeMessages(0);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mHandler.removeMessages(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

        mTransformerAdapter = new TransformerAdapter(this, mTransLists);
        mTranslationList.setAdapter(mTransformerAdapter);
        mTranslationList.setOnItemClickListener(this);

    }

    private void initLists() {
        mTransLists = new ArrayList<>();
        mTransLists.add("Default");
        mTransLists.add("Accordion");
        mTransLists.add("Background2Foreground");
        mTransLists.add("CubeIn");
        mTransLists.add("DepthPage");
        mTransLists.add("FlipHorizontal");
        mTransLists.add("FlipPage");
        mTransLists.add("Foreground2Background");
        mTransLists.add("RotateDown");
        mTransLists.add("RotateUp");
        mTransLists.add("Stack");
        mTransLists.add("Tablet");
        mTransLists.add("ZoomOutSlide");
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessageDelayed(0, 3000);
            }
        }, 3000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String index = mTransLists.get(position);
        setPageTranfomer(index);
        Toast.makeText(MainActivity.this, index, Toast.LENGTH_SHORT).show();

        mHandler.removeMessages(0);
        mHandler.sendEmptyMessageDelayed(0, 4000);
    }


    /**
     * 设置 ViewPager 页面切换动画
     * @param tranfomer
     */
    private void setPageTranfomer(String tranfomer) {

        switch (tranfomer) {
            case "Default":
                mViewPager.setPageTransformer(true, new DefaultTransformer());
                break;
            case "Accordion":
                mViewPager.setPageTransformer(true, new AccordionTransformer());
                break;
            case "Background2Foreground":
                mViewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
                break;
            case "CubeIn":
                mViewPager.setPageTransformer(true, new CubeInTransformer());
                break;
            case "DepthPage":
                mViewPager.setPageTransformer(true, new DepthPageTransformer());
                break;
            case "FlipHorizontal":
                mViewPager.setPageTransformer(true, new FlipHorizontalTransformer());
                break;
            case "FlipPage":
                mViewPager.setPageTransformer(true, new FlipPageViewTransformer());
                break;
            case "Foreground2Background":
                mViewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                break;
            case "RotateDown":
                mViewPager.setPageTransformer(true, new RotateDownTransformer());
                break;
            case "RotateUp":
                mViewPager.setPageTransformer(true, new RotateUpTransformer());
                break;
            case "Stack":
                mViewPager.setPageTransformer(true, new StackTransformer());
                break;
            case "Tablet":
                mViewPager.setPageTransformer(true, new TabletTransformer());
                break;
            case "ZoomOutSlide":
                mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
                break;
        }
    }


    class BannerAdapter extends PagerAdapter implements BannerViewPager.OnPageChangeListener {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.with(MainActivity.this).load(mImageSource[position]).fit().into(imageView);
            container.addView(imageView);
            mImageList.add(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return mImageSource.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageList.get(position));
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mPagePosition = position;
            mHandler.sendEmptyMessageDelayed(0, 6000);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeMessages(0);
    }
}

package com.dhn.library;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * Created by DHN on 2016/8/20.
 */
public class PullZoomRecyclerView extends RecyclerView {

    public static final String TAG = PullZoomRecyclerView.class.getSimpleName();


    private FrameLayout mHeaderContainer;

    private int mHeaderHeight;
    private int mScreenWidth;
    private int duration = 1000;
    ValueAnimator valueAnimator;

    private float mLastMotionY = -1.0F;

    public PullZoomRecyclerView(Context context) {
        super(context);
        init();
    }

    public PullZoomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullZoomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }



    public void setHeaderContainer(FrameLayout headerContainer) {
        mHeaderContainer = headerContainer;
    }

    private void init() {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay()
                .getMetrics(localDisplayMetrics);
        //获取屏幕宽
        mScreenWidth = localDisplayMetrics.widthPixels;
        //固定的header高度
        mHeaderHeight = (int) (9.0F * (mScreenWidth / 16.0F));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    valueAnimator.cancel();
                }
                //记录触点Y轴坐标
                mLastMotionY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算deltY
                float Y = e.getY();
                float deltY = Y - mLastMotionY;
                //暂时只能LinearLayout使用,不可见时，交给RecyclerView处理
                int firstItemPosition = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
                if (firstItemPosition > 0) {
                    return super.onTouchEvent(e);
                }

                //处于未放大状态且向上滑，则将事件交给RecyclerView处理
                if (mHeaderContainer.getBottom() == mHeaderHeight && deltY < 0) {
                    return super.onTouchEvent(e);
                }



                if (mHeaderContainer.getBottom() >= mHeaderHeight) {

                    ViewGroup.LayoutParams layoutParams = mHeaderContainer.getLayoutParams();
                    layoutParams.height += deltY;
                    //防止下次收到move事件，getBottom() < mHeaderHeight,导致无法下拉
                    if (layoutParams.height < mHeaderHeight) {
                        layoutParams.height = mHeaderHeight;
                    }

                    mHeaderContainer.setLayoutParams(layoutParams);
                    //更新y值
                    mLastMotionY = Y;
                    //直接返回true，防止RecyclerView对它进行处理，导致整体上滑
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastMotionY = -1;
                //自动收起
                endScaling();
                break;
        }
        return super.onTouchEvent(e);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void endScaling() {
        valueAnimator = ValueAnimator.ofInt(mHeaderContainer.getHeight(), mHeaderHeight);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curHeight = (Integer) animation.getAnimatedValue();
                mHeaderContainer.getLayoutParams().height = curHeight;
                mHeaderContainer.requestLayout();
            }
        });
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

}
package com.salili.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;

/**
 * Created by alili on 26/10/14.
 */
public class HScrollView extends HorizontalScrollView {

    private ArrayList<VScrollView.OnScrollChangedListener> hScrollListener = new ArrayList<VScrollView.OnScrollChangedListener>();
    private int paddingLeft = 0;
    private int paddingRight = 0;

    public HScrollView(Context context) {
        super(context);
    }

    public HScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //check attributes you need, for example all paddings
        int[] attributes = new int[]{android.R.attr.paddingLeft, android.R.attr.paddingRight};

        //then obtain typed array
        TypedArray arr = context.obtainStyledAttributes(attrs, attributes);

        //and get values you need by indexes from your array attributes defined above
        paddingLeft = arr.getDimensionPixelOffset(0, 0);
        paddingRight = arr.getDimensionPixelOffset(1, 0);

        for (int i = 0; i < 2; i++) {
            Log.e("PADDING", "i=" + i + " Value = " + arr.getDimensionPixelOffset(i, -1));
        }



    }

    public HScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //check attributes you need, for example all paddings
        int[] attributes = new int[]{android.R.attr.paddingLeft, android.R.attr.paddingTop, android.R.attr.paddingBottom, android.R.attr.paddingRight};

        //then obtain typed array
        TypedArray arr = context.obtainStyledAttributes(attrs, attributes);

        //and get values you need by indexes from your array attributes defined above
        paddingLeft = arr.getDimensionPixelOffset(0, -1);
        paddingRight = arr.getDimensionPixelOffset(3, -1);

        for (int i = 0; i < 4; i++) {
            Log.e("PADDING", "i=" + i + " Value = " + arr.getDimensionPixelOffset(i, -1));
        }
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (VScrollView.OnScrollChangedListener listner : hScrollListener) {
            if (listner != null) {
                listner.onHorizontalScrollChanged(this, t);
                listner.onScrollChanged(this, l, t, oldl, oldt);
            }

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() {

            @Override
            public void run() {
                for (VScrollView.OnScrollChangedListener listner : hScrollListener) {
                    if (listner != null) {
                        listner.onHorizontalScrollChanged(HScrollView.this, getScrollX());
                    }

                }
                invalidate();
            }
        });
    }


    public void addOnScrollListener(VScrollView.OnScrollChangedListener onScrollListener) {
        this.hScrollListener.add(onScrollListener);
    }


    @Override
    public int getPaddingLeft() {
        return paddingLeft;
    }

    @Override
    public int getPaddingRight() {
        return paddingRight;
    }
}

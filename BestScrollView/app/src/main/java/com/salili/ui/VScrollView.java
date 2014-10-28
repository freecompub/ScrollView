package com.salili.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by SALILI on 22/10/2014.
 */
public class VScrollView extends ScrollView {

    private ArrayList<OnScrollChangedListener> vScrollListener = new ArrayList<OnScrollChangedListener>();

    public VScrollView(final Context context) {
        super(context);
    }

    public VScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public VScrollView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollChangedListener listner : vScrollListener) {
            if (listner != null) {
                listner.onVerticalScrollChanged(this, t);
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
                for (OnScrollChangedListener listner : vScrollListener) {
                    if (listner != null) {
                        listner.onVerticalScrollChanged(VScrollView.this, getScrollY());
                    }

                }
                invalidate();
            }
        });
    }


    public void addOnScrollListener(OnScrollChangedListener onScrollListener) {
        this.vScrollListener.add(onScrollListener);
    }

    public static interface OnScrollChangedListener {
        public void onVerticalScrollChanged(ScrollView view, int offsetY);

        public void onHorizontalScrollChanged(HorizontalScrollView view, int offsetX);

        public void onScrollChanged(ScrollView view, int x, int y, int oldx, int oldy);

        public void onScrollChanged(HorizontalScrollView view, int x, int y, int oldx, int oldy);
    }


}

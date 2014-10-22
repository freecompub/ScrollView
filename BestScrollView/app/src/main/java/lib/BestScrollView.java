package lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by SALILI on 22/10/2014.
 */
public class BestScrollView extends ScrollView {

    private ArrayList<OnScrollChangedListener> hScrollListener = new ArrayList<OnScrollChangedListener>();

    public BestScrollView(final Context context) {
        super(context);
    }

    public BestScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public BestScrollView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollChangedListener listner : hScrollListener) {
            listner.onVerticalScrollChanged(t);
            listner.onScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() {

            @Override
            public void run() {
                for (OnScrollChangedListener oscl : hScrollListener) {
                    oscl.onVerticalScrollChanged(getScrollY());
                }
                invalidate();
            }
        });
    }


    public void addOnScrollListener(OnScrollChangedListener onScrollListener) {
        this.hScrollListener.add(onScrollListener);
    }

    public static interface OnScrollChangedListener {
        public void onVerticalScrollChanged(int offsetY);

        public void onScrollChanged(int x, int y, int oldx, int oldy);
    }


}

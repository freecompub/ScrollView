package lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;

/**
 * Created by alili on 26/10/14.
 */
public class HScrollView extends HorizontalScrollView {

    private ArrayList<VScrollView.OnScrollChangedListener> hScrollListener = new ArrayList<VScrollView.OnScrollChangedListener>();

    public HScrollView(Context context) {
        super(context);
    }

    public HScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

}

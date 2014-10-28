package com.salili.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alili on 22/10/14.
 */
public class SynchroLayout extends LinearLayout implements VScrollView.OnScrollChangedListener {

    private ArrayList<OnViewsVisibilityListner> mListners = new ArrayList<OnViewsVisibilityListner>();
    private ArrayList<View> verticalVisibleViews = new ArrayList<View>();
    private ArrayList<View> horizontalVisibleViews = new ArrayList<View>();


    public SynchroLayout(Context context) {
        super(context);
    }

    public SynchroLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SynchroLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onVerticalScrollChanged(ScrollView view, int offsetY) {

    }

    @Override
    public void onHorizontalScrollChanged(HorizontalScrollView view, int offsetX) {

    }

    @Override
    public void onScrollChanged(ScrollView view, int x, int y, int oldx, int oldy) {
        verticalVisibleViews = getVisibleViewsForVerticalScroll(view);
        for (OnViewsVisibilityListner listner : mListners) {
            if (listner != null) {
                listner.onChildViewVible(verticalVisibleViews);
            }
        }
    }

    @Override
    public void onScrollChanged(HorizontalScrollView view, int x, int y, int oldx, int oldy) {
        horizontalVisibleViews = getVisibleViewsForHorizontalScroll(view);
        for (OnViewsVisibilityListner listner : mListners) {
            if (listner != null) {
                listner.onChildViewVible(horizontalVisibleViews);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findScrollView(getParent());
    }

    private void findScrollView(ViewParent parent) {
        if (parent == null) {
            throw new IllegalArgumentException("A SynchroLayout must be inside BestScrollView, directly.");
        }

        if (parent instanceof VScrollView) {
            ((VScrollView) parent).addOnScrollListener(this);
        }

        if (parent instanceof HScrollView) {
            ((HScrollView) parent).addOnScrollListener(this);
        }
    }

    public void addListner(OnViewsVisibilityListner mListner) {
        mListners.add(mListner);
    }

    public ArrayList<View> getVisibleViewsForVerticalScroll(ScrollView scrollView) {
        ArrayList<View> tmp = new ArrayList<View>();
        int[] position = {0, 0};

        int paddingTop = ((VScrollView) scrollView).getPaddingTop();
        int paddingBottom = ((VScrollView) scrollView).getPaddingBottom();
        int scrollViewHeight = scrollView.getHeight() - paddingBottom - paddingTop;

        scrollView.getLocationOnScreen(position);
        position[1] = position[1] + paddingTop;
        for (int i = 0; i < this.getChildCount(); i++) {
            int[] location = {0, 0};
            View view = this.getChildAt(i);
            view.getLocationOnScreen(location);

            if ((location[1] >= position[1] && location[1] < (scrollViewHeight + position[1])) ||
                    (location[1] < position[1] && (location[1] + view.getHeight()) > position[1])
                    ) {
                tmp.add(view);
            }
        }
        return tmp;
    }


    public ArrayList<View> getVisibleViewsForHorizontalScroll(HorizontalScrollView scrollView) {
        ArrayList<View> tmp = new ArrayList<View>();
        int[] position = {0, 0};

        int paddingLeft = scrollView.getPaddingLeft();
        int paddingRight = scrollView.getPaddingRight();
        int scrollViewWidth = scrollView.getWidth() - paddingRight - paddingLeft;
        scrollView.getLocationOnScreen(position);
        position[0] = position[0] + paddingLeft;
        for (int i = 0; i < this.getChildCount(); i++) {
            int[] location = {0, 0};
            View view = this.getChildAt(i);
            view.getLocationOnScreen(location);

            if ((location[0] >= position[0] && location[0] < (scrollViewWidth + position[0])) ||
                    (location[0] < position[0] && (location[0] + view.getWidth()) > position[0])
                    ) {
                tmp.add(view);
            }
        }
        return tmp;
    }


    public boolean isViewVisible(int id) {
        View view = this.findViewById(id);
        if (view == null)
            return false;

        return verticalVisibleViews.contains(view);
    }

    public enum ViewStatus {
        GO_IN,
        IN,
        GO_OUT,
        OUT;
    }


    public interface OnViewsVisibilityListner {
        public void onChildViewVible(List<View> visibleChild);
    }

    ;
}

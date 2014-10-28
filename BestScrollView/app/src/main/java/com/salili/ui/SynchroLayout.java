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
    private ArrayList<View> VerticalVisibleViews = new ArrayList<View>();
    private ArrayList<View> VerticalHorizontalViews = new ArrayList<View>();


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
        VerticalVisibleViews = getVisibleViewsForVerticalScroll(view);
        for (OnViewsVisibilityListner listner : mListners) {
            if (listner != null) {
                listner.onChildViewVible(VerticalVisibleViews);
            }
        }
    }

    @Override
    public void onScrollChanged(HorizontalScrollView view, int x, int y, int oldx, int oldy) {

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
        scrollView.getLocationOnScreen(position);
        for (int i = 0; i < this.getChildCount(); i++) {
            int[] location = {0, 0};
            View view = this.getChildAt(i);
            view.getLocationOnScreen(location);

            if ((location[1] >= position[1] && location[1] < (scrollView.getHeight() + position[1])) ||
                    (location[1] < position[1] && (location[1] + view.getHeight()) > position[1])
                    ) {
                tmp.add(view);
            }
        }
        return tmp;
    }


    public ArrayList<View> getVisibleViewsForHorizontalScroll(ScrollView scrollView) {
        ArrayList<View> tmp = new ArrayList<View>();
        int[] position = {0, 0};
        scrollView.getLocationOnScreen(position);
        for (int i = 0; i < this.getChildCount(); i++) {
            int[] location = {0, 0};
            View view = this.getChildAt(i);
            view.getLocationOnScreen(location);

            if ((location[0] >= position[0] && location[0] < (scrollView.getHeight() + position[0])) ||
                    (location[0] < position[0] && (location[0] + view.getHeight()) > position[0])
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

        return VerticalVisibleViews.contains(view);
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

package lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alili on 22/10/14.
 */
public class SynchroLayout extends LinearLayout implements BestScrollView.OnScrollChangedListener {

    private ArrayList<OnViewsVisibilityListner> mListners = new ArrayList<OnViewsVisibilityListner>();
    private ArrayList<View> visibleViews;

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
    public void onScrollChanged(ScrollView view, int x, int y, int oldx, int oldy) {
        visibleViews = getVisibleViews(view);
        for (OnViewsVisibilityListner listner : mListners) {
            if (listner != null) {
                listner.onChildViewVible(visibleViews);
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

        if (parent instanceof BestScrollView) {
            ((BestScrollView) parent).addOnScrollListener(this);
        }
    }

    public interface OnViewsVisibilityListner {
        public void onChildViewVible(List<View> visibleChild);
    }

    public void addListner(OnViewsVisibilityListner mListner) {
        mListners.add(mListner);
    }

    public ArrayList<View> getVisibleViews(ScrollView scrollView) {
        ArrayList<View> tmp = new ArrayList<View>();
        for (int i = 0; i < this.getChildCount(); i++) {
            int[] location = {0, 0};
            View view = this.getChildAt(i);
            view.getLocationOnScreen(location);

            if ((location[1] >= 0 && location[1] < scrollView.getHeight()) ||
                    (location[1] < 0 && (location[1] + view.getHeight()) > 0)
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

        return visibleViews.contains(view);
    }
}

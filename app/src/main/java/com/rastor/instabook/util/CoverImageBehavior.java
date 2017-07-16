package com.rastor.instabook.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Krishna Chaitanya Kandula on 7/9/17.
 */

public class CoverImageBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    public CoverImageBehavior() {
    }

    public CoverImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        modifyChildView(child, dependency);
        return true;
    }

    private void modifyChildView(CircleImageView childView, View dependency) {
        childView.getLayoutParams().width *= .9;
        childView.getLayoutParams().height *= .9;
    }
}

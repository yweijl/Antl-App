package com.avansprojects.antl.helpers;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BottomOffsetDecoration extends RecyclerView.ItemDecoration {
    private int mBottomOffset;
    private int mRecyclerViewSize;

    public BottomOffsetDecoration(int bottomOffset, int recyclerViewSize) {
        mBottomOffset = bottomOffset;
        mRecyclerViewSize = recyclerViewSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int dataSize = state.getItemCount();
        int position = parent.getChildAdapterPosition(view);
        if (dataSize > mRecyclerViewSize && position == dataSize - 1) {
            outRect.set(0, 0, 0, mBottomOffset);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }
}
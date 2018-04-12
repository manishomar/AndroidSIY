package com.siy.SlideBar;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by Manish Omar on 3/15/2018.
 */

public class swipeHelperAdapter extends ItemTouchHelper.SimpleCallback {
    ChatRecyclerAdapter chatRecyclerAdapter;
    public swipeHelperAdapter(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public swipeHelperAdapter(ChatRecyclerAdapter chatRecyclerAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
        this.chatRecyclerAdapter = chatRecyclerAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((ChatRecyclerAdapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
      chatRecyclerAdapter.dismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((ChatRecyclerAdapter.MyViewHolder) viewHolder).viewForeground;

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }
}

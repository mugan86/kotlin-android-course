package com.mugan86.drag.drop.list.ui.listener

import android.support.v7.widget.RecyclerView

/**
 * Created by anartzmugika on 26/9/17.
 */
interface OnStartDragListener {
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}
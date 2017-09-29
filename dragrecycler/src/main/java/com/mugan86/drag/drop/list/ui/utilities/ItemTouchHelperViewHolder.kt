package com.mugan86.drag.drop.list.ui.utilities

/**
 * Created by anartzmugika on 26/9/17.
 */

interface ItemTouchHelperViewHolder {
    /**
     * Implementations should update the item view to indicate it's active state.
     */
    fun onItemSelected()

    /**
     * state should be cleared.
     */
    fun onItemClear()
}

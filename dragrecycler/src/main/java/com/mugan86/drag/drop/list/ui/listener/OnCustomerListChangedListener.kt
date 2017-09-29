package com.mugan86.drag.drop.list.ui.listener

import com.mugan86.drag.drop.list.domain.model.Customer

/**
 * Created by anartzmugika on 26/9/17.
 */

interface OnCustomerListChangedListener {
    fun onNoteListChanged(customers: List<Customer>)
}

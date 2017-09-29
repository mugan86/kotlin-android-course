package com.mugan86.drag.drop.list.ui.adapters

/**
 * Created by anartzmugika on 26/9/17.
 */

import android.content.Context
import android.graphics.Color
import android.support.v4.view.MotionEventCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.mugan86.drag.drop.list.R
import com.mugan86.drag.drop.list.domain.model.Customer
import com.mugan86.drag.drop.list.ui.listener.OnCustomerListChangedListener
import com.mugan86.drag.drop.list.ui.listener.OnStartDragListener
import com.mugan86.drag.drop.list.ui.utilities.ItemTouchHelperAdapter
import com.mugan86.drag.drop.list.ui.utilities.ItemTouchHelperViewHolder
import com.mugan86.drag.drop.list.ui.utilities.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_customer_list.view.*

import java.util.Collections

class CustomerListAdapter(private val mCustomers: List<Customer>,
                          private val mDragStartListener: OnStartDragListener,
                          private val mListChangedListener: OnCustomerListChangedListener) : RecyclerView.Adapter<CustomerListAdapter.ItemViewHolder>(), ItemTouchHelperAdapter {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.row_customer_list, parent, false)
        return ItemViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //Bind custom item (define in row_customer_list.xml)
        holder.bindItem(mCustomers[position], mDragStartListener, holder)
    }

    override fun getItemCount(): Int = mCustomers.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(mCustomers, fromPosition, toPosition)
        mListChangedListener.onNoteListChanged(mCustomers)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

        fun bindItem(item: Customer, mDragStartListener: OnStartDragListener, holder: ItemViewHolder) {
            with(item) {
                Picasso.with(itemView.ctx).load(imagePath).into(itemView.image_view_customer_head_shot)
                itemView.customerName.text = name
                itemView.customerEmail.text = emailAddress
                itemView.row_Linear.setOnTouchListener { v, event ->
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        mDragStartListener.onStartDrag(holder)
                    }
                    false
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }
}
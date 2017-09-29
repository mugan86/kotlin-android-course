package com.mugan86.drag.drop.list.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mugan86.drag.drop.list.R
import com.mugan86.drag.drop.list.data.SampleData
import com.mugan86.drag.drop.list.domain.model.Customer
import com.mugan86.drag.drop.list.ui.adapters.CustomerListAdapter
import com.mugan86.drag.drop.list.ui.listener.OnCustomerListChangedListener
import com.mugan86.drag.drop.list.ui.listener.OnStartDragListener
import com.mugan86.drag.drop.list.ui.utilities.SimpleItemTouchHelperCallback
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import java.util.*
import kotlinx.android.synthetic.main.activity_main_drag.*

class MainActivity : AppCompatActivity(), OnCustomerListChangedListener, OnStartDragListener {

    private var mAdapter: CustomerListAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mItemTouchHelper: ItemTouchHelper? = null
    private var mCustomers: List<Customer>? = null

    private var mSharedPreferences: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    //Get the sample data
    //create an empty array to hold the list of sorted Customers
    //get the JSON array of the ordered of sorted customers
    //check for null
    //convert JSON array into a List<Long>
    //build sorted list
    //if there are still customers that were not in the sorted list
    //maybe they were added after the last drag and drop
    //add them to the sorted list
    private val sampleData: List<Customer>
        get() {
            val customerList = SampleData.addSampleCustomers()
            val sortedCustomers = ArrayList<Customer>()
            val jsonListOfSortedCustomerId = mSharedPreferences!!.getString(LIST_OF_SORTED_DATA_ID, "")
            if (!jsonListOfSortedCustomerId!!.isEmpty()) {
                val gson = Gson()
                val listOfSortedCustomersId = gson.fromJson<List<Long>>(jsonListOfSortedCustomerId, object : TypeToken<List<Long>>() {

                }.type)
                if (listOfSortedCustomersId != null && listOfSortedCustomersId.size > 0) {
                    for (id in listOfSortedCustomersId) {
                        for (customer in customerList) {
                            if (customer.id == id) {
                                sortedCustomers.add(customer)
                                customerList.remove(customer)
                                break
                            }
                        }
                    }
                }
                if (customerList.size > 0) {
                    sortedCustomers.addAll(customerList)
                }

                return sortedCustomers
            } else {
                return customerList
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drag)

        mSharedPreferences = this.applicationContext
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences!!.edit()


        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mCustomers = sampleData

        //setup the adapter with empty list
        mAdapter = CustomerListAdapter(mCustomers!!, this, this)
        val callback = SimpleItemTouchHelperCallback(mAdapter!!)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(mRecyclerView)
        mRecyclerView!!.addItemDecoration(HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.colorPrimaryDark)
                .size(2)
                .build())
        mRecyclerView!!.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean =// Inflate the menu; this adds items to the action bar if it is present.
            // getMenuInflater().inflate(R.menu.menu_main, menu);
            true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item)
    }

    override fun onNoteListChanged(customers: List<Customer>) {
        //after drag and drop operation, the new list of Customers is passed in here

        //create a List of Long to hold the Ids of the
        //Customers in the List
        val listOfSortedCustomerId = ArrayList<Long>()

        for (customer in customers) {
            println(customer.id)
            customer.id?.let { listOfSortedCustomerId.add(it) }
        }

        //convert the List of Longs to a JSON string
        val gson = Gson()
        val jsonListOfSortedCustomerIds = gson.toJson(listOfSortedCustomerId)


        //save to SharedPreference
        mEditor!!.putString(LIST_OF_SORTED_DATA_ID, jsonListOfSortedCustomerIds).commit()
        mEditor!!.commit()


    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper!!.startDrag(viewHolder)

    }

    companion object {
        val LIST_OF_SORTED_DATA_ID = "json_list_sorted_data_id"
        val PREFERENCE_FILE = "preference_file"
    }


}
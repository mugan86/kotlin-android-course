package com.mugan86.drag.drop.list.data

import com.mugan86.drag.drop.list.domain.model.Customer

import java.util.ArrayList

/**
 * Created by anartzmugika on 26/9/17.
 */

object SampleData {

    private val AVATAR_IMG = "https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/profle-128.png"
    fun addSampleCustomers(): ArrayList<Customer> {
        val customers = ArrayList<Customer>()

        val customer1 = Customer()
        customer1.id = 1.toLong()
        customer1.name = "Anartz Mugika"
        customer1.emailAddress = "mugan86@gmail.com"
        customer1.imagePath = "https://avatars1.githubusercontent.com/u/5081970?v=4&s=460"
        customers.add(customer1)


        val customer2 = Customer()
        customer2.id = 2.toLong()
        customer2.name = "Fernando Gabriel"
        customer2.emailAddress = "fer@fer.com"
        customer2.imagePath = AVATAR_IMG
        customers.add(customer2)


        val customer3 = Customer()
        customer3.id = 3.toLong()
        customer3.name = "Antonio Leiva"
        customer3.emailAddress = "antonio@leiva.com"
        customer3.imagePath = "https://pbs.twimg.com/profile_images/585807991724765185/x5BTF6MQ_400x400.jpg"
        customers.add(customer3)


        val customer4 = Customer()
        customer4.id = 4.toLong()
        customer4.name = "Homer Simpson"
        customer4.emailAddress = "mosquis@homersimpson.com"
        customer4.imagePath = "https://i.pinimg.com/736x/f4/b0/97/f4b0971b151931d9e547b0cdce0b2e9d--les-simpson-design-pop.jpg"
        customers.add(customer4)

        return customers

    }
}
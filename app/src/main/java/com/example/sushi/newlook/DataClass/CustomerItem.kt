package com.examples.newlook.data

import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.persistence.DataQueryBuilder


class CustomerItem {
    val ownerId: String? = null
    val updated: java.util.Date? = null
    val objectId: String? = null
    var lastName: String? = null
    var discription: String? = null
    var serialVersionUID: Double? = null
    var phone: String? = null
    var birthday: String? = null
    var firstName: String? = null
    var index: String? = null
    val created: java.util.Date? = null


    fun save(): CustomerItem {
        return Backendless.Data.of(CustomerItem::class.java).save(this)
    }

    fun saveAsync(callback: AsyncCallback<CustomerItem>) {
        Backendless.Data.of(CustomerItem::class.java).save(this, callback)
    }

    fun remove(): Long? {
        return Backendless.Data.of(CustomerItem::class.java).remove(this)
    }

    fun removeAsync(callback: AsyncCallback<Long>) {
        Backendless.Data.of(CustomerItem::class.java).remove(this, callback)
    }

    companion object {

        fun findById(id: String): CustomerItem {
            return Backendless.Data.of(CustomerItem::class.java).findById(id)
        }

        fun findByIdAsync(id: String, callback: AsyncCallback<CustomerItem>) {
            Backendless.Data.of(CustomerItem::class.java).findById(id, callback)
        }

        fun findFirst(): CustomerItem {
            return Backendless.Data.of(CustomerItem::class.java).findFirst()
        }

        fun findFirstAsync(callback: AsyncCallback<CustomerItem>) {
            Backendless.Data.of(CustomerItem::class.java).findFirst(callback)
        }

        fun findLast(): CustomerItem {
            return Backendless.Data.of(CustomerItem::class.java).findLast()
        }

        fun findLastAsync(callback: AsyncCallback<CustomerItem>) {
            Backendless.Data.of(CustomerItem::class.java).findLast(callback)
        }

        fun find(queryBuilder: DataQueryBuilder): List<CustomerItem> {
            return Backendless.Data.of(CustomerItem::class.java).find(queryBuilder)
        }

        fun findAsync(queryBuilder: DataQueryBuilder, callback: AsyncCallback<List<CustomerItem>>) {
            Backendless.Data.of(CustomerItem::class.java).find(queryBuilder, callback)
        }
    }
}
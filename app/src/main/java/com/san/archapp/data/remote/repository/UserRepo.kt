package com.san.archapp.data.remote.repository

import com.san.archapp.data.local.UserDao
import com.san.archapp.data.remote.api.BaseApi
import com.san.archapp.data.remote.utils.networkBoundResource
import com.san.archapp.data.remote.utils.networkOnlyResource
import javax.inject.Inject

class UserRepo @Inject constructor(private val api: BaseApi, private val userDao: UserDao) {


     fun getUsers() = networkBoundResource(

        // Query to return the list of all cars
        query = {
            userDao.getUsersList()
        },

        // Just for testing purpose,
        // a delay of 2 second is set.
        fetch = {
            //delay(2000)
            api.getUsers()
        },

        // Save the results in the table.
        // If data exists, then delete it
        // and then store.
        saveFetchResult = { userList ->
            userList.data?.let { userDao.insertUser(it) }
        },

        shouldFetch = {
            it.isEmpty()
        }

    )

    fun getUsers2() = networkOnlyResource { api.getUsers() }


    fun getUsers3(){

    }


    fun getComment(id: Int) = userDao.getComment(id)

    suspend fun insertComment(id: Int, comment: String) = userDao.insertComment(id, comment)
}
package com.san.archapp.data.remote.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class CustomNetworkCall<T : Any> {

    private var result: MutableStateFlow<Resource<T>?> = MutableStateFlow(null)

   /* fun makeCall(call: suspend () -> T): MutableStateFlow<Resource<T>?> {
        CoroutineScope(Dispatchers.IO).launch {
            result.emit(Resource.Loading(null))
            try {
                result.emit(Resource.Success(call()))

            } catch (e: Exception) {
                e.printStackTrace()
                result.emit(
                    Resource.Error(e.toString())
                )
            }
        }
        return result
    }*/

    fun fetchFromLocal() {

    }


}
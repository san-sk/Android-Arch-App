package com.san.archapp.data.remote.utils


import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

   /* fun asFlow() = flow {
        emit(Resource.Loading(null))

        val dbValue = loadFromDb().first()

        if (shouldFetch(dbValue)) {
            emit(Resource.Loading(dbValue))
            when (val apiResponse = fetchFromNetwork()) {
                is Resource.Error -> {
                    onFetchFailed()
                    emitAll(loadFromDb().map { Resource.Error(apiResponse.message, it) })
                }
                is Resource.Loading -> TODO()
                is Resource.Success -> {
                    saveNetworkResult(processResponse(apiResponse))
                    emitAll(loadFromDb().map { Resource.Success(it) })
                }
            }
        } else {
            emitAll(loadFromDb().map { Resource.Success(it) })
        }
    }*/

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected open fun processResponse(response: Resource<RequestType?>) = response.data

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType?>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Resource<RequestType?>
}

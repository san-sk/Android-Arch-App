package com.san.archapp.data.remote.utils

import kotlinx.coroutines.flow.*

 fun <ResultType, RequestType> networkBoundResource(
     query: () -> Flow<ResultType>,
     fetch: suspend () -> RequestType,
     saveFetchResult: suspend (RequestType) -> Unit,
     shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
       emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}


fun <ResultType> networkOnlyResource(
    fetch: suspend () -> ResultType
) = flow {
    val flow = flow {
        emit(Resource.Loading(null))
        try {
            emit(Resource.Success(fetch()))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, null))
        }
    }
    emitAll(flow)
}
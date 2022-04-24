package com.san.archapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.archapp.data.entity.Data
import com.san.archapp.data.remote.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {

    var usersLiveData = MutableLiveData<List<Data>?>(null)

    init {
        viewModelScope.launch {
            getUsers().collect {
                usersLiveData.value = it.data
            }
        }
    }

    private suspend fun getUsers() = repo.getUsers()

    suspend fun insertComment(id: Int, comment: String) = repo.insertComment(id, comment)

}
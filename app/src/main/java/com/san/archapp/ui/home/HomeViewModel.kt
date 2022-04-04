package com.san.archapp.ui.home

import androidx.lifecycle.ViewModel
import com.san.archapp.data.remote.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {

    suspend fun getUsers() = repo.getUsers()

    suspend fun insertComment(id: Int, comment: String) = repo.insertComment(id, comment)

}
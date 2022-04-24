package com.san.archapp.ui.home.comment

import androidx.lifecycle.ViewModel
import com.san.archapp.data.remote.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val repo: UserRepo) : ViewModel() {

    fun getComment(id:Int) = repo.getComment(id)

}
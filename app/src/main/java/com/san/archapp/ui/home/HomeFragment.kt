package com.san.archapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.san.archapp.R
import com.san.archapp.data.remote.utils.Resource
import com.san.archapp.databinding.FragmentUsersBinding
import com.san.archapp.ui.adapters.UserListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_users) {

    private lateinit var binding: FragmentUsersBinding

    private val viewModel by viewModels<HomeViewModel>()

    private val userListAdapter by lazy {
        UserListAdapter { user ->
            user.comments?.let {
                lifecycleScope.launch { user.id?.let { it1 -> viewModel.insertComment(it1, it) } }
            }
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCommentFragment(
                    user.id ?: -1
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsers.adapter = userListAdapter

        fetchUsers()
    }

    private fun fetchUsers() {
        lifecycleScope.launch {
            viewModel.getUsers().collect {
                when (it) {
                    is Resource.Error -> {
                        showSnackBar(it.error?.message.toString())
                    }
                    is Resource.Loading -> {
                        userListAdapter.submitList(it.data)
                    }
                    is Resource.Success -> {
                        userListAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
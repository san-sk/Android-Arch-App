package com.san.archapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.san.archapp.R
import com.san.archapp.databinding.FragmentUsersBinding
import com.san.archapp.ui.adapters.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
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
        viewModel.usersLiveData.observe(viewLifecycleOwner) {
            it?.let {
                userListAdapter.submitList(it)
            } ?: kotlin.run { showSnackBar("Users not found") }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
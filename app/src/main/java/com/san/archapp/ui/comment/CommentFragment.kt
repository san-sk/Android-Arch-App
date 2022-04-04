package com.san.archapp.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.san.archapp.R
import com.san.archapp.databinding.FragmentCommentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentFragment : Fragment(R.layout.fragment_comment) {

    private lateinit var binding: FragmentCommentBinding

    private val viewModel by viewModels<CommentViewModel>()

    private val args by navArgs<CommentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchComment()
    }

    private fun fetchComment() {
        lifecycleScope.launch {
            viewModel.getComment(args.id).collect {
                binding.tvInfo.text = it ?: "Comment Not Found"
            }
        }
    }

}
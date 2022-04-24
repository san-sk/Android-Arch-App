package com.san.archapp.ui.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.san.archapp.R
import com.san.archapp.data.entity.CommonItem
import com.san.archapp.databinding.FragmentExamplesBinding
import com.san.archapp.ui.adapters.CommonListAdapter


class ExamplesFragment : Fragment(R.layout.fragment_examples) {

    private lateinit var binding: FragmentExamplesBinding

    private val listAdapter by lazy {
        CommonListAdapter {
            it.title?.let { it1 -> navigate(it1) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExamplesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvList.adapter = listAdapter
        listAdapter.submitList(listOf(CommonItem(0, "Service")))
    }

    private fun navigate(title: String) {
        when (title) {
            "Service" -> findNavController().navigate(ExamplesFragmentDirections.actionExampleFragmentToServiceFragment())
        }
    }
}
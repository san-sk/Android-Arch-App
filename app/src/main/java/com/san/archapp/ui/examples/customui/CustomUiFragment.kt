package com.san.archapp.ui.examples.customui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.san.archapp.R
import com.san.archapp.databinding.FragmentCustomUiBinding

class CustomUiFragment : Fragment(R.layout.fragment_custom_ui) {

    private lateinit var binding: FragmentCustomUiBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCustomUiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sv1.adapter =
            ArrayAdapter(requireContext(), R.layout.item_user, arrayOf(1, 2, 3, 4))

    }

}
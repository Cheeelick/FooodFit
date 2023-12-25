package com.example.search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import com.example.search.R
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.presentation.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchView
            .getEditText()
            .setOnEditorActionListener { v, actionId, event ->
                binding.searchBar.setText(binding.searchView.getText())
                binding.searchView.hide()
                Toast.makeText(context, binding.searchView.text.toString(), LENGTH_SHORT).show()
                false
            }




        return binding.root
    }



}
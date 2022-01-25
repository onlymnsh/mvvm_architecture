package com.example.architecture.features.datastore.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.architecture.databinding.FragmentDataStoreBinding
import com.example.architecture.features.datastore.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class FragmentDataStore : Fragment() {

    private var _binding: FragmentDataStoreBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DataStoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataStoreBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launchWhenStarted {
            viewModel.mainUser.collect {
                if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
                    it?.let {
                        Toast.makeText(
                            requireContext(),
                            "FirstName: ${it.firstName} and LastName:${it.lastName}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
        }
    }

}
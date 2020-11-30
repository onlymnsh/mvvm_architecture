package com.example.ta.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ta.R
import com.example.ta.databinding.RestaurantFragmentBinding
import com.example.ta.utils.Sorting
import com.example.ta.utils.SortingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantsFragment : Fragment(),
    RestaurantsAdapter.RestaurantFavListener, SortingItemListener {

    private lateinit var binding: RestaurantFragmentBinding
    private val viewModel: RestaurantsViewModel by viewModels()
    private lateinit var adapter: RestaurantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RestaurantFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        setupSortingView()
    }

    private fun setupSortingView() {

        binding.sorting.setOnClickListener {
            val dialogFragment:DialogFragment = SortingDialogFragment(this,ArrayList(R.array.sorting_types))
            dialogFragment.isCancelable = true
            val fm = parentFragmentManager
            fm.let {dialogFragment.show(fm, "Sorting")}
        }
    }

    private fun setupRecyclerView() {
        adapter = RestaurantsAdapter(this)
        binding.restaurantsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.restaurantsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty().not()) {
                binding.progressBar.visibility = View.GONE
                adapter.setItems(ArrayList(it))
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onRestaurantFav(restaurantId: Int, isFav: Boolean) {
        when (isFav) {
            true -> {
                GlobalScope.launch {
                    Dispatchers.IO
                    viewModel.saveFavouriteRestaurant(restaurantId)
                }
            }
            false -> {
                GlobalScope.launch {
                    Dispatchers.IO
                    viewModel.deleteFavouriteRestaurant(restaurantId)
                }
            }
        }
    }

    override fun onClickedSorting(position: Int) {
        SortingUtility.SortRestaurant(viewModel.restaurants, Sorting.values()[position])
    }
}

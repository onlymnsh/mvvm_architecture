package com.example.ta.ui.restaurants

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ta.databinding.ItemSortingBinding
import com.example.ta.databinding.SortingDialogFragmentBinding


class SortingDialogFragment(val fragment: RestaurantsFragment, var listItems: ArrayList<String>) : DialogFragment(), SortingItemListener {

    private lateinit var binding: SortingDialogFragmentBinding
    private lateinit var adapter: SortingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SortingDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupList()
    }

    private fun setupList() {
        if(listItems.isNullOrEmpty().not()){
            adapter.setItems(listItems)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        adapter = SortingListAdapter(this)
        binding.sortingList.layoutManager = LinearLayoutManager(requireContext())
        binding.sortingList.adapter = adapter
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    class SortingListAdapter(private val listener: SortingItemListener) :
        RecyclerView.Adapter<SortingViewHolder>() {
        private val items = ArrayList<String>()

        fun setItems(items: ArrayList<String>) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortingViewHolder {
            val binding: ItemSortingBinding =
                ItemSortingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SortingViewHolder(binding, listener)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: SortingViewHolder, position: Int) =
            holder.bind(items[position])
    }

    class SortingViewHolder(
        private val itemBinding: ItemSortingBinding,
        private val listener: SortingItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        private lateinit var item: String

        init {
            itemBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: String) {
            this.item = item
            itemBinding.textView.text = item
        }

        override fun onClick(v: View?) {
            listener.onClickedSorting(adapterPosition)
        }
    }

    override fun onClickedSorting(position: Int) {
        (parentFragment as RestaurantsFragment).onClickedSorting(position)
    }
}

interface SortingItemListener {
    fun onClickedSorting(position: Int)
}
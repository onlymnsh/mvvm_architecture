package com.example.ta.ui.restaurants

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ta.data.entities.Restaurant
import com.example.ta.databinding.ItemRestaurantBinding
import com.jackandphantom.androidlikebutton.AndroidLikeButton

class RestaurantsAdapter(private val likeClickedListener:RestaurantFavListener) : RecyclerView.Adapter<RestaurantViewHolder>() {

    interface RestaurantFavListener {
        fun onRestaurantFav(restaurantId: Int, isFav:Boolean)
    }

    private val items = ArrayList<Restaurant>()

    fun setItems(items: ArrayList<Restaurant>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding: ItemRestaurantBinding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding, likeClickedListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) = holder.bind(items[position])
}

class RestaurantViewHolder(private val itemBinding: ItemRestaurantBinding, private val favClicked:RestaurantsAdapter.RestaurantFavListener ) : RecyclerView.ViewHolder(itemBinding.root),
    AndroidLikeButton.OnLikeEventListener {

    private lateinit var restaurant: Restaurant

    init {
        itemBinding.favourite.setOnLikeEventListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Restaurant) {
        this.restaurant = item
        itemBinding.name.text = item.name
        itemBinding.status.text = item.status
    }
    override fun onLikeClicked(androidLikeButton: AndroidLikeButton?) {
        androidLikeButton?.setCurrentlyLiked(true)
        favClicked.onRestaurantFav(restaurant.id,true)
    }

    override fun onUnlikeClicked(androidLikeButton: AndroidLikeButton?) {
        androidLikeButton?.setCurrentlyLiked(false)
        favClicked.onRestaurantFav(restaurant.id,false)
    }
}
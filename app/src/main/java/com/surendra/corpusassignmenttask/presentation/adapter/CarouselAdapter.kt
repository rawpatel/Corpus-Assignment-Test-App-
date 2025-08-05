package com.surendra.corpusassignmenttask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.data.model.ContentItem
import com.surendra.corpusassignmenttask.databinding.ItemCarouselImageBinding

class CarouselAdapter : ListAdapter<ContentItem, CarouselAdapter.CarouselViewHolder>(CarouselDiffCallback()) {
    
    init {
        // Set horizontal layout manager
        setHasStableIds(true)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        // Set horizontal scrolling
        if (recyclerView.layoutManager == null) {
            recyclerView.layoutManager = LinearLayoutManager(
                recyclerView.context, 
                LinearLayoutManager.HORIZONTAL, 
                false
            )
        }
    }
    
    inner class CarouselViewHolder(
        private val binding: ItemCarouselImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ContentItem) {
            // Load image using Glide
            val imageUrl = item.mobileCarouselImage ?: item.otherDeviceCarouselImage ?: item.stbCarouselImage
            
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(binding.imageViewCarousel.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imageViewCarousel)
                
                binding.progressBar.visibility = View.GONE
                binding.imageViewCarousel.visibility = View.VISIBLE
            } else {
                binding.imageViewCarousel.setImageResource(R.drawable.placeholder_image)
                binding.progressBar.visibility = View.GONE
            }
            
            // Handle click events
            binding.root.setOnClickListener {
                item.externalLink?.let { link ->
                    if (link.isNotEmpty() && link != "-1") {
                        // Handle external link click
                        // You can implement custom click handling here
                    }
                }
            }
            
            // Set title if available
            if (!item.title.isNullOrEmpty()) {
                binding.textViewTitle.text = item.title
                binding.textViewTitle.visibility = View.VISIBLE
            } else {
                binding.textViewTitle.visibility = View.GONE
            }
        }
    }
}

class CarouselDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
    override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem == newItem
    }
}
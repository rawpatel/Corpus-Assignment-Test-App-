package com.surendra.corpusassignmenttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surendra.corpusassignmenttask.data.model.ContentItem
import com.surendra.corpusassignmenttask.data.model.IconType
import com.surendra.corpusassignmenttask.databinding.ItemContentLandscapeBinding
import com.surendra.corpusassignmenttask.databinding.ItemContentPortraitBinding
import com.surendra.corpusassignmenttask.databinding.ItemContentTextBinding

class ContentItemAdapter(
    private val contentType: String,
    private val iconType: String?
) : ListAdapter<ContentItem, RecyclerView.ViewHolder>(ContentItemDiffCallback()) {
    
    companion object {
        private const val VIEW_TYPE_TEXT = 0
        private const val VIEW_TYPE_LANDSCAPE = 1
        private const val VIEW_TYPE_PORTRAIT = 2
    }
    
    override fun getItemViewType(position: Int): Int {
        return when {
            iconType == IconType.PORTRAIT_ICON_3 -> VIEW_TYPE_PORTRAIT
            iconType == IconType.LANDSCAPE_ICON || iconType == IconType.LANDSCAPE_ICON_2 -> VIEW_TYPE_LANDSCAPE
            else -> VIEW_TYPE_TEXT
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PORTRAIT -> {
                val binding = ItemContentPortraitBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PortraitViewHolder(binding)
            }
            VIEW_TYPE_LANDSCAPE -> {
                val binding = ItemContentLandscapeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                LandscapeViewHolder(binding)
            }
            else -> {
                val binding = ItemContentTextBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                TextViewHolder(binding)
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PortraitViewHolder -> holder.bind(item)
            is LandscapeViewHolder -> holder.bind(item)
            is TextViewHolder -> holder.bind(item)
        }
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
    
    inner class TextViewHolder(
        private val binding: ItemContentTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ContentItem) {
            binding.textViewId.text = item.id.toString()
            binding.textViewTitle.text = item.title ?: "Content ${item.id}"
            
            // Handle click
            binding.root.setOnClickListener {
                // Handle item click
            }
        }
    }
    
    inner class LandscapeViewHolder(
        private val binding: ItemContentLandscapeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ContentItem) {
            binding.textViewId.text = item.id.toString()
            binding.textViewTitle.text = item.title ?: "Content ${item.id}"
            
            // You can load images here if needed for landscape items
            // For now, showing ID as per requirement
            
            binding.root.setOnClickListener {
                // Handle item click
            }
        }
    }
    
    inner class PortraitViewHolder(
        private val binding: ItemContentPortraitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ContentItem) {
            binding.textViewId.text = item.id.toString()
            binding.textViewTitle.text = item.title ?: "Content ${item.id}"
            
            // You can load images here if needed for portrait items
            // For now, showing ID as per requirement
            
            binding.root.setOnClickListener {
                // Handle item click
            }
        }
    }
}

class ContentItemDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
    override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
        return oldItem == newItem
    }
}
package com.surendra.corpusassignmenttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.data.model.ContentSection
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class ContentSectionAdapter : ListAdapter<ContentSection, ContentSectionAdapter.SectionViewHolder>(SectionDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = ItemContentSectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SectionViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class SectionViewHolder(private val binding: ItemContentSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        private var contentAdapter: ContentItemAdapter? = null
        
        fun bind(section: ContentSection) {
            // Set section title
            binding.tvSectionTitle.text = section.title
            
            when (section.contentType) {
                "CAROUSEL_AD" -> {
                    setupCarouselSection(section)
                }
                "LTV", "VOD", "APPSTORE" -> {
                    setupHorizontalSection(section)
                }
                else -> {
                    setupDefaultSection(section)
                }
            }
        }
        
        private fun setupCarouselSection(section: ContentSection) {
            binding.singleImageView.isVisible = false
            binding.horizontalRecyclerView.isVisible = true
            
            // Check if it's a single banner or carousel
            if (section.content.size == 1 && section.adImageType == "MidRowSmallBanner") {
                // Single banner ad
                binding.singleImageView.isVisible = true
                binding.horizontalRecyclerView.isVisible = false
                
                val item = section.content.first()
                item.mobileCarouselImage?.let { imageUrl ->
                    com.bumptech.glide.Glide.with(binding.root.context)
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(binding.singleImageView)
                }
            } else {
                // Carousel with multiple items
                setupHorizontalRecyclerView(section, "CAROUSEL")
            }
        }
        
        private fun setupHorizontalSection(section: ContentSection) {
            binding.singleImageView.isVisible = false
            binding.horizontalRecyclerView.isVisible = true
            
            val layoutType = when (section.contentType) {
                "VOD" -> "PORTRAIT"  // Movies - portrait layout
                "LTV" -> "LANDSCAPE" // TV Channels - landscape layout
                "APPSTORE" -> "SQUARE" // Apps - square layout
                else -> "LANDSCAPE"
            }
            
            setupHorizontalRecyclerView(section, layoutType)
        }
        
        private fun setupDefaultSection(section: ContentSection) {
            binding.singleImageView.isVisible = false
            binding.horizontalRecyclerView.isVisible = true
            setupHorizontalRecyclerView(section, "LANDSCAPE")
        }
        
        private fun setupHorizontalRecyclerView(section: ContentSection, layoutType: String) {
            if (contentAdapter == null) {
                contentAdapter = ContentItemAdapter(layoutType)
                binding.horizontalRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = contentAdapter
                }
            } else {
                contentAdapter?.updateLayoutType(layoutType)
            }
            contentAdapter?.submitList(section.content)
        }
    }
    
    class SectionDiffCallback : DiffUtil.ItemCallback<ContentSection>() {
        override fun areItemsTheSame(oldItem: ContentSection, newItem: ContentSection): Boolean {
            return oldItem.title == newItem.title && oldItem.displayOrder == newItem.displayOrder
        }
        
        override fun areContentsTheSame(oldItem: ContentSection, newItem: ContentSection): Boolean {
            return oldItem == newItem
        }
    }
}
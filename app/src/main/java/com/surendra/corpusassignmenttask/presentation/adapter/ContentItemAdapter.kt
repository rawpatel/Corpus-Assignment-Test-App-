package com.surendra.corpusassignmenttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.data.model.ContentItem
import com.surendra.corpusassignmenttask.databinding.ItemContentItemBinding
import com.surendra.corpusassignmenttask.utils.Constants
import com.surendra.corpusassignmenttask.utils.ExtensionsUtils.openUrl
import com.surendra.corpusassignmenttask.utils.ValidationUtils

class ContentItemAdapter(
    private var layoutType: String,
    private val onItemClick: ((ContentItem) -> Unit)? = null
) : ListAdapter<ContentItem, ContentItemAdapter.ContentItemViewHolder>(ContentItemDiffCallback()) {

    fun updateLayoutType(newLayoutType: String) {
        this.layoutType = newLayoutType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val binding = ItemContentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContentItemViewHolder(binding, layoutType, onItemClick)
    }

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContentItemViewHolder(
        private val binding: ItemContentItemBinding,
        private val layoutType: String,
        private val onItemClick: ((ContentItem) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContentItem) {
            // Reset visibility
            binding.itemImage.isVisible = false
            binding.itemTitle.isVisible = false
            binding.itemId.isVisible = false
            binding.actionButton.isVisible = false

            when (layoutType) {
                Constants.LAYOUT_TYPE_CAROUSEL -> setupCarouselItem(item)
                Constants.LAYOUT_TYPE_PORTRAIT,
                Constants.LAYOUT_TYPE_LANDSCAPE,
                Constants.LAYOUT_TYPE_SQUARE -> setupContentItem(item)
                else -> setupDefaultItem(item)
            }

            // Set click listeners
            setupClickListeners(item)
        }

        private fun setupCarouselItem(item: ContentItem) {
            binding.itemImage.isVisible = true

            // Adjust layout params for carousel
            adjustLayoutForCarousel()

            item.mobileCarouselImage?.let { imageUrl ->
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .centerCrop()
                    .into(binding.itemImage)
            }

            // Show title if available
            if (!item.title.isNullOrEmpty() && item.title != "GFGNL-Bigbannerad1") {
                binding.itemTitle.isVisible = true
                binding.itemTitle.text = item.title
            }

            // Show action button if action exists
            if (!item.onScreenAction.isNullOrEmpty() && item.onScreenAction != Constants.ACTION_NONE) {
                binding.actionButton.isVisible = true
                binding.actionButton.text = item.onScreenAction
            }
        }

        private fun setupContentItem(item: ContentItem) {
            adjustLayoutForContentType()

            // Show ID for content items
            binding.itemId.isVisible = true
            binding.itemId.text = "ID: ${item.id}"

            // Show title or generate one
            binding.itemTitle.isVisible = true
            binding.itemTitle.text = item.title ?: "Content ${item.id}"

            // Show appropriate placeholder
            binding.itemImage.isVisible = true
            loadPlaceholderImage()
        }

        private fun setupDefaultItem(item: ContentItem) {
            binding.itemId.isVisible = true
            binding.itemId.text = "ID: ${item.id}"

            if (!item.title.isNullOrEmpty()) {
                binding.itemTitle.isVisible = true
                binding.itemTitle.text = item.title
            }
        }

        private fun setupClickListeners(item: ContentItem) {
            // Action button click
            binding.actionButton.setOnClickListener {
                handleActionClick(item)
            }

            // Item click
            binding.root.setOnClickListener {
                onItemClick?.invoke(item) ?: handleDefaultClick(item)
            }
        }

        private fun handleActionClick(item: ContentItem) {
            when (item.onScreenAction) {
                Constants.ACTION_LEARN_MORE, Constants.ACTION_PURCHASE -> {
                    item.externalLink?.let { url ->
                        if (ValidationUtils.isValidUrl(url)) {
                            binding.root.context.openUrl(url)
                        }
                    }
                }
            }
        }

        private fun handleDefaultClick(item: ContentItem) {
            // Handle share URL or external link
            val urlToOpen = item.shareUrl?.takeIf { ValidationUtils.isValidUrl(it) }
                ?: item.externalLink?.takeIf { ValidationUtils.isValidUrl(it) }

            urlToOpen?.let { url ->
                binding.root.context.openUrl(url)
            }
        }

        private fun adjustLayoutForCarousel() {
            val context = binding.root.context
            val layoutParams = binding.itemImage.layoutParams
            layoutParams.width = context.resources.getDimensionPixelSize(R.dimen.carousel_item_width_large)
            layoutParams.height = context.resources.getDimensionPixelSize(R.dimen.carousel_item_height)
            binding.itemImage.layoutParams = layoutParams
        }

        private fun adjustLayoutForContentType() {
            val context = binding.root.context
            val layoutParams = binding.itemImage.layoutParams

            when (layoutType) {
                Constants.LAYOUT_TYPE_PORTRAIT -> {
                    layoutParams.width = context.resources.getDimensionPixelSize(R.dimen.portrait_item_width)
                    layoutParams.height = context.resources.getDimensionPixelSize(R.dimen.portrait_item_height)
                }
                Constants.LAYOUT_TYPE_SQUARE -> {
                    val size = context.resources.getDimensionPixelSize(R.dimen.square_item_size)
                    layoutParams.width = size
                    layoutParams.height = size
                }
                Constants.LAYOUT_TYPE_LANDSCAPE -> {
                    layoutParams.width = context.resources.getDimensionPixelSize(R.dimen.landscape_item_width)
                    layoutParams.height = context.resources.getDimensionPixelSize(R.dimen.landscape_item_height)
                }
            }
            binding.itemImage.layoutParams = layoutParams
        }

        private fun loadPlaceholderImage() {
            val placeholderRes = when (layoutType) {
                Constants.LAYOUT_TYPE_PORTRAIT -> R.drawable.placeholder_portrait
                Constants.LAYOUT_TYPE_SQUARE -> R.drawable.placeholder_square
                else -> R.drawable.placeholder_landscape
            }

            Glide.with(binding.root.context)
                .load(placeholderRes)
                .into(binding.itemImage)
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
}
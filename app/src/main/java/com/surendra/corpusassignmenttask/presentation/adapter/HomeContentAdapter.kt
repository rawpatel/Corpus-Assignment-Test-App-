package com.surendra.corpusassignmenttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surendra.corpusassignmenttask.data.model.HomeContent
import com.surendra.corpusassignmenttask.databinding.ItemCarouselAdBinding
import com.surendra.corpusassignmenttask.databinding.ItemContentSectionBinding
import com.surendra.corpusassignmenttask.utils.Constants
import com.surendra.corpusassignmenttask.utils.Constants.CAROUSEL_AD

class HomeContentAdapter : ListAdapter<HomeContent, RecyclerView.ViewHolder>(HomeDiffCallback()) {


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).contentType) {
            CAROUSEL_AD -> Constants.VIEW_TYPE_CAROUSEL_AD
            else -> Constants.VIEW_TYPE_CONTENT_SECTION
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.VIEW_TYPE_CAROUSEL_AD -> {
                val binding = ItemCarouselAdBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                CarouselAdViewHolder(binding)
            }
            else -> {
                val binding = ItemContentSectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ContentSectionViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CarouselAdViewHolder -> holder.bind(item)
            is ContentSectionViewHolder -> holder.bind(item)
        }
    }

    inner class CarouselAdViewHolder(
        private val binding: ItemCarouselAdBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homeContent: HomeContent) {
            binding.textViewTitle.text = homeContent.title

            val carouselAdapter = CarouselAdapter()
            binding.recyclerViewCarousel.apply {
                adapter = carouselAdapter
                setHasFixedSize(true)
            }
            carouselAdapter.submitList(homeContent.content)
        }
    }

    inner class ContentSectionViewHolder(
        private val binding: ItemContentSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(homeContent: HomeContent) {


            binding.textViewSectionTitle.text = homeContent.title

            val contentAdapter = ContentItemAdapter(homeContent.contentType, homeContent.iconType)
            binding.recyclerViewContent.apply {
                adapter = contentAdapter
                setHasFixedSize(true)
            }
            contentAdapter.submitList(homeContent.content)
        }
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<HomeContent>() {
    override fun areItemsTheSame(oldItem: HomeContent, newItem: HomeContent): Boolean {
        return oldItem.displayOrder == newItem.displayOrder
    }

    override fun areContentsTheSame(oldItem: HomeContent, newItem: HomeContent): Boolean {
        return oldItem == newItem
    }
}
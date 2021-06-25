package com.dicoding.chillyshow.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chillyshow.core.R
import com.dicoding.chillyshow.core.databinding.ItemGridBinding
import com.dicoding.chillyshow.core.domain.model.TvShow

class TvShowsGridAdapter : RecyclerView.Adapter<TvShowsGridAdapter.ViewHolder>() {

    private val listItems = ArrayList<TvShow>()

    fun setItems(items: List<TvShow>?) {
        if (items != null) {
            listItems.clear()
            listItems.addAll(items)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(private val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TvShow) {
            binding.apply {
                textTitle.text = item.title

                Glide.with(itemView.context)
                    .load(item.posterUrl)
                    .error(R.drawable.ic_placeholder_poster)
                    .into(imgPoster)

                root.setOnClickListener {
                    callback?.onItemClick(item)
                }
            }
        }
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun onItemClick(item: TvShow)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

}
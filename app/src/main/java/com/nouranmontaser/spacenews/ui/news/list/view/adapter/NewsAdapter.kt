package com.nouranmontaser.spacenews.ui.news.list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.databinding.ItemNewsBinding

class NewsAdapter(private val delegate: Delegate): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private val diffCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var newsList: List<News?>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun getItemCount(): Int = newsList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                news = newsList[position]

                root.setOnClickListener {
                    newsList[position]?.apply { delegate.newsItemSelected(this) }
                }
            }
        }
    }

    interface Delegate {
        fun newsItemSelected(news: News)
    }
}
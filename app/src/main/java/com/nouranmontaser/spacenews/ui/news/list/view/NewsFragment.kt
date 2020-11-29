package com.nouranmontaser.spacenews.ui.news.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nouranmontaser.spacenews.utils.Resource
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.databinding.FragmentNewsBinding
import com.nouranmontaser.spacenews.ui.news.list.view.adapter.NewsAdapter
import com.nouranmontaser.spacenews.ui.news.list.viewmodel.NewsViewModel
import com.nouranmontaser.spacenews.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), NewsAdapter.Delegate {

    private lateinit var binding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        initUi()
        subscribeUi()
        return binding.root
    }

    private fun initUi() {
        binding.lifecycleOwner = this
        binding.hasNews = false
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        adapter = NewsAdapter(this)
        binding.newsRecyclerView.adapter = adapter
    }

    fun subscribeUi() {
        observe(viewModel.news, ::handelNews)
    }

    private fun handelNews(news: Resource<List<News?>>) {
        when(news) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Success -> {
                if (news.data.isNullOrEmpty()) {
                    binding.hasNews = false
                } else {
                    adapter.newsList = news.data
                    binding.hasNews = true
                }
                binding.isLoading = false
            }
            is Resource.Error -> {
                adapter.newsList = news.data ?: ArrayList()
                news.errorMsg.let {
                    Toast.makeText(requireActivity(), getString(it), Toast.LENGTH_SHORT).show()
                }
                binding.isLoading = false
                binding.hasNews = false
            }
        }
    }

    override fun newsItemSelected(news: News) {
        findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment())
    }
}
package com.digimaster.kotlin2.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.digimaster.kotlin2.R
import com.digimaster.kotlin2.view.HomeActivity
import com.digimaster.kotlin2.viewmodel.NewsViewModel

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel
    }
}
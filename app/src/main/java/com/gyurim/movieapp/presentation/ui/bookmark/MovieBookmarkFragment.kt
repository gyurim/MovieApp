package com.gyurim.movieapp.presentation.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gyurim.movieapp.R
import com.gyurim.movieapp.databinding.FragmentMovieBookmarkBinding

class MovieBookmarkFragment : Fragment() {
    lateinit var binding : FragmentMovieBookmarkBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_bookmark, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root // 레이아웃 뷰 반환
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        binding.movieBookmarkToolbar.setNavigationOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                requireActivity().supportFragmentManager.beginTransaction().remove(this@MovieBookmarkFragment).commit()
                requireActivity().supportFragmentManager.popBackStack()
            }
        })
    }
}
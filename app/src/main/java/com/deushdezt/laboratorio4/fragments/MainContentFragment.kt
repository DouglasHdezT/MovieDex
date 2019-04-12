package com.deushdezt.laboratorio4.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.deushdezt.laboratorio4.R
import com.deushdezt.laboratorio4.pojos.Movie
import kotlinx.android.synthetic.main.main_content_fragment_layout.view.*

class MainContentFragment: Fragment() {

    var movie = Movie()

    companion object {
        fun newInstance(movie: Movie): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.movie = movie
            return newFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
         val view = inflater.inflate(R.layout.main_content_fragment_layout, container, false)

        bindData(view)

        return view
    }

    fun bindData(view: View){
        view.movie_title_main_content_fragment.text = movie.Title
        view.movie_rate_main_content_fragment.text = movie.imdbRating
        view.plot_main_content_fragment.text = movie.Plot
        view.released_main_content_fragment.text = movie.Released
        view.genre_main_content_fragment.text = movie.Genre
        view.runtime_main_content_fragment.text = movie.Runtime
        Glide.with(view).load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.image_main_content_fragment)

    }

}
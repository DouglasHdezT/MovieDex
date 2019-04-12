package com.deushdezt.laboratorio4.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.deushdezt.laboratorio4.MyMovieAdapter
import com.deushdezt.laboratorio4.R
import com.deushdezt.laboratorio4.adapters.MovieAdapter
import com.deushdezt.laboratorio4.adapters.MovieSimpleListAdapter
import com.deushdezt.laboratorio4.pojos.Movie
import kotlinx.android.synthetic.main.movies_list_fragment.*
import kotlinx.android.synthetic.main.movies_list_fragment.view.*

class MainListFragment: Fragment(){

    private lateinit var  movies :ArrayList<Movie>
    private lateinit var moviesAdapter : MyMovieAdapter
    private lateinit var searchListener : (String) -> Unit
    private lateinit var portraitListener: (Movie) -> Unit
    private lateinit var landscapeListener : (Movie) -> Unit


    companion object {
        fun newInstance(dataset : ArrayList<Movie>, searchListener: (String) -> Unit, portraitListener: (Movie) -> Unit, landscapeListener: (Movie) -> Unit): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.movies = dataset
            newFragment.searchListener = searchListener
            newFragment.portraitListener = portraitListener
            newFragment.landscapeListener = landscapeListener
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)

        initRecyclerView(resources.configuration.orientation, view)
        initSearchButton(view)

        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        container.movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            moviesAdapter = MovieAdapter(movies, portraitListener)
            container.movie_list_rv.adapter = moviesAdapter as MovieAdapter
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            moviesAdapter = MovieSimpleListAdapter(movies, landscapeListener)
            container.movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter
        }
    }

    fun updateAdapter(movieList: ArrayList<Movie>) { moviesAdapter.changeDataSet(movieList) }

    fun initSearchButton(container:View) = container.add_movie_btn.setOnClickListener { searchListener(movie_name_et.text.toString()) }
}
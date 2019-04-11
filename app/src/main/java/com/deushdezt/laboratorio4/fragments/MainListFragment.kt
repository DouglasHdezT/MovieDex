package com.deushdezt.laboratorio4.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deushdezt.laboratorio4.MyMovieAdapter
import com.deushdezt.laboratorio4.R
import com.deushdezt.laboratorio4.adapters.MovieAdapter
import com.deushdezt.laboratorio4.adapters.MovieSimpleListAdapter
import com.deushdezt.laboratorio4.pojos.Movie
import kotlinx.android.synthetic.main.movies_list_fragment.*

class MainListFragment: Fragment(), MainListFragment.searchNewMovieListener {

    lateinit var  movies :List<Movie>
    lateinit var moviesAdapter : MyMovieAdapter
    lateinit var listenerTool ?: searchNewMovieListener

    companion object {
        fun newInstance(dataset : List<Movie>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.movies = dataset
            return newFragment
        }
    }

    interface searchNewMovieListener{
        fun searchMovie(movieName: String)
        fun updateListToAdapter(adapter : MyMovieAdapter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initRecyclerView(resources.configuration.orientation)


        return container
    }

    fun initRecyclerView(orientation:Int){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            moviesAdapter = MovieAdapter(movies, {})
            movie_list_rv.adapter = moviesAdapter as MovieAdapter
        }else{
            moviesAdapter = MovieSimpleListAdapter(movies, {})
            movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter
        }

        movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is searchNewMovieListener) {
            listener = context
        } else {
            throw RuntimeException("Se necesita una implementación de  OnSelectOption")
        }
    }

    override fun onDetach() {
        super.onDetach()

    }
}
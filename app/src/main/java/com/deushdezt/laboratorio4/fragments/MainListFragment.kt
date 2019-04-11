package com.deushdezt.laboratorio4.fragments

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import com.deushdezt.laboratorio4.adapters.MovieAdapter
import com.deushdezt.laboratorio4.adapters.MovieSimpleListAdapter
import kotlinx.android.synthetic.main.movies_list_fragment.*

class MainListFragment: Fragment(), SearchNewMovieListener {

    lateinit var  movies :List<Movie>
    lateinit var moviesAdapter : MyMovieAdapter
    lateinit var listenerTool ?:  SearchNewMovieListener

    companion object {
        fun newInstance(dataset : List<Movie>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.movies = dataset
            return newFragment
        }
    }

    interface SearchNewMovieListener{
        fun searchMovie(movieName: String)

        fun managePortraitItemClick(movie: Movie)

        fun manageLandscapeClick(movie: Movie)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initRecyclerView(resources.configuration.orientation)


        return container
    }

    fun initRecyclerView(orientation:Int){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            moviesAdapter = MovieAdapter(movies, {movie:Movie->listenerTool.managePortraitClick(movie)})
            movie_list_rv.adapter = moviesAdapter as MovieAdapter
        }else{
            moviesAdapter = MovieSimpleListAdapter(movies, {movie:Movie->listenerTool.manageLandscapeClick(movie)})
            movie_list_rv.adapter = moviesAdapter as MovieSimpleListAdapter
        }

        movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    fun initSearchButton() = add_movie_btn.setOnClickListener {
        listenerTool.searchMovie(movie_name_et.text.toString())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SearchNewMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }
}
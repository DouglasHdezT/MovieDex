package com.deushdezt.laboratorio4.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.deushdezt.laboratorio4.R
import com.deushdezt.laboratorio4.pojos.Movie
import kotlinx.android.synthetic.main.viewer_movie.*

class MovieViewerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_movie)

        setSupportActionBar(toolbarviewer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val reciever: Movie = intent?.extras?.getParcelable("MOVIE")!!
        init(reciever)
    }

    fun init(movie: Movie){
        Glide.with(this)
                .load(movie.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title = movie.Title
        app_bar_rating_viewer.text = movie.imdbRating
        plot_viewer.text = movie.Plot
        director_viewer.text = movie.Director
        actors_viewer.text = movie.Actors
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }
}
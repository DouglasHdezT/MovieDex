package com.deushdezt.laboratorio4.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.deushdezt.laboratorio4.R
import com.deushdezt.laboratorio4.fragments.MainContentFragment
import com.deushdezt.laboratorio4.fragments.MainListFragment
import com.deushdezt.laboratorio4.network.NetworkUtils
import com.deushdezt.laboratorio4.pojos.Movie
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), MainListFragment.SearchNewMovieListener {
    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment: MainContentFragment

    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()
    }

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(movieList)
        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else {
            mainContentFragment = MainContentFragment.newInstance(Movie())
            supportFragmentManager.beginTransaction()
                .replace(R.id.land_main_cont_fragment, mainContentFragment)
                .commit()

            R.id.land_main_fragment
        }

        supportFragmentManager.beginTransaction()
            .replace(resource, mainFragment)
            .commit()
    }

    fun addMovieToList(movie: Movie) {
        movieList.add(movie)
        mainFragment.moviesAdapter.changeDataSet(movieList)
        Log.d("Number", movieList.size.toString())
    }

    override fun searchMovie(movieName: String) {
        FetchMovie().execute(movieName)
    }

    override fun managePortraitItemClick(movie: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("MOVIE", movie)
        startActivity(Intent(this, MovieViewerActivity::class.java).putExtras(movieBundle))
    }

    override fun manageLandscapeItemClick(movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)

        supportFragmentManager.beginTransaction()
            .replace(R.id.land_main_cont_fragment, mainContentFragment)
            .commit()
    }

    private inner class FetchMovie : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String): String {

            if (params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieUrl = NetworkUtils().buildtSearchUrl(movieName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(movieUrl)
            } catch (e: IOException) {
                ""
            }
        }

        override fun onPostExecute(movieInfo: String) {
            super.onPostExecute(movieInfo)
            if (!movieInfo.isEmpty()) {
                val movieJson = JSONObject(movieInfo)
                if (movieJson.getString("Response") == "True") {
                    val movie = Gson().fromJson<Movie>(movieInfo, Movie::class.java)
                    addMovieToList(movie)
                } else {
                    Toast.makeText(this@MainActivity, "No existe en la base de datos,", Toast.LENGTH_LONG).show()
                }
            }else
            {
                Toast.makeText(this@MainActivity, "A ocurrido un error,", Toast.LENGTH_LONG).show()
            }
        }
    }
}


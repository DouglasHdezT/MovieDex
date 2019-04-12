package com.deushdezt.laboratorio4

import com.deushdezt.laboratorio4.pojos.Movie
import java.util.*

object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val dataset_fragment_key = "CLE"
}

interface MyMovieAdapter {
    fun changeDataSet(newDataSet : List<Movie>)
}
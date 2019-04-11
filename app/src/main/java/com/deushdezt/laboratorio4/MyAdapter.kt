package com.deushdezt.laboratorio4

import com.deushdezt.laboratorio4.pojos.Movie
import java.util.*

interface MyMovieAdapter {
    fun changeDataSet(newDataSet : List<Movie>)
}
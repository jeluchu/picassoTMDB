/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jeluchu.picassotmdb.data.api

import com.jeluchu.picassotmdb.BuildConfig
import com.jeluchu.picassotmdb.data.model.MoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

  private val moviesApi: MoviesApi
  private const val API_KEY = BuildConfig.TMDB_API_KEY
  private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
  const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"

  init {
    val builder = OkHttpClient.Builder()
    val okHttpClient = builder.build()
    val retrofit = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    moviesApi = retrofit.create(MoviesApi::class.java)
  }

  fun getPopularMovies(): Call<MoviesResponse> {
    return moviesApi.getPopularMovies(API_KEY)
  }

  fun getSimpsonsMovies(): Call<MoviesResponse> {
    return moviesApi.searchMovie(API_KEY, "simpsons")
  }
}
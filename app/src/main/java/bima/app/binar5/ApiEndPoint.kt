package bima.app.binar5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTg1MjBiNTYxZmRhMDNjYTE2ZGUwNTY5MWE3YWFlZiIsInN1YiI6IjY2NDc3MTI1YTE4ZThhNDBjNjYzMDE1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ZDTvvIio430ekuAZvqDJ4iuxW32RGHrjq287wYguRCY", "accept: application/json")
    @GET("popular")
    fun popular(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTg1MjBiNTYxZmRhMDNjYTE2ZGUwNTY5MWE3YWFlZiIsInN1YiI6IjY2NDc3MTI1YTE4ZThhNDBjNjYzMDE1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ZDTvvIio430ekuAZvqDJ4iuxW32RGHrjq287wYguRCY", "accept: application/json")
    @GET("{id}")
    fun getMovieDetails(
        @Path("id") id: Int,
        @Query("language") language: String = "en-US"
    ): Call<Movie>

}
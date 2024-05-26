package bima.app.binar5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class isi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi)

        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        if (movieId != 0) {
            loadMovieDetails(movieId)
        }
    }

    private fun loadMovieDetails(movieId: Int) {
        ApiService.endpoint.getMovieDetails(movieId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    // Log the successful response
                    Log.d("API Success", "Successfully retrieved movie details: ${response.body()}")

                    val movie = response.body()
                    findViewById<TextView>(R.id.original_title).text = movie?.original_title
                    findViewById<TextView>(R.id.overview).text = movie?.overview
                    findViewById<TextView>(R.id.status).text = "Status: ${movie?.status}"
                    findViewById<TextView>(R.id.revenue).text = "Revenue: ${movie?.revenue}"

                    Glide.with(this@isi)
                        .load("https://image.tmdb.org/t/p/w500" + movie?.backdrop_path)
                        .into(findViewById<ImageView>(R.id.image_back))
                } else {
                    // Log an error response from the server and handle the error message
                    Log.e("API Error", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // Log failure in making the request
                Log.e("API Failure", "Network failure: ${t.message}")
            }
        })
    }
}

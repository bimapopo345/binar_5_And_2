package bima.app.binar5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        setupRecyclerView()
        loadMovies()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = adapter_list() // Create an instance of the adapter
        recyclerView.adapter = adapter // Set the adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var loading = true
            var pastVisibleItems: Int = 0
            var visibleItemCount: Int = 0
            var totalItemCount: Int = 0
            var previousTotal = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { // Check for scroll down
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading && (totalItemCount > previousTotal)) {
                        loading = false
                        previousTotal = totalItemCount
                    }

                    if (!loading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = true
                        loadMovies()
                    }
                }
            }
        })
    }

    private fun loadMovies() {
        ApiService.endpoint.popular().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    val adapter = recyclerView.adapter as? adapter_list
                    adapter?.addData(movies) ?: Log.e("Adapter Error", "Adapter is not of type adapter_list or is null")
                } else {
                    Log.e("API Error", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("API Failure", "Network failure: ${t.message}")
            }
        })
    }
}

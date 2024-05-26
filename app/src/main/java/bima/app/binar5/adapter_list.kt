package bima.app.binar5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class adapter_list(private val movies: MutableList<Movie> = mutableListOf()) : RecyclerView.Adapter<adapter_list.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
    fun addData(newMovies: List<Movie>) {
        movies.addAll(newMovies)
        notifyDataSetChanged() // Notify the adapter that the data set has changed
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val imageView = itemView.findViewById<ImageView>(R.id.gambar_list)
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .into(imageView)

            itemView.findViewById<TextView>(R.id.original_title).text = movie.original_title
            itemView.findViewById<TextView>(R.id.release_date).text = movie.release_date
            itemView.findViewById<TextView>(R.id.popularity).text = movie.popularity.toString()

            imageView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, isi::class.java)
                intent.putExtra("MOVIE_ID", movie.id)
                context.startActivity(intent)
            }
        }
    }
}
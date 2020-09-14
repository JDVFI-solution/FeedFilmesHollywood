package br.com.jdvfi.feedfilmeshollywood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import br.com.jdvfi.feedfilmeshollywood.R
import br.com.jdvfi.feedfilmeshollywood.model.Results
import br.com.jdvfi.feedfilmeshollywood.telas.MoviesActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    val context: Context,
    val movies:List<Results>
) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): MoviesHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent,false)
        return  MoviesHolder(view)

    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(viewHolder: MoviesHolder, position: Int) {
        viewHolder.bindView(movies[position], context)
    }

    class MoviesHolder(view: View) : RecyclerView.ViewHolder(view){

        val title = view.title
        val desc = view.desc
        val author = view.author
        val published_ad = view.publishedAt
        val source = view.source
        val time = view.source
        val imageView = view.img
        val progressBar = view.progress_load_photo

        fun bindView(movies: Results, context: Context){
            title.text = movies.display_title
            desc.text = movies.summary_short
            author.text = movies.byline
            published_ad.text = movies.publication_date
            time.text = movies.date_updated

            val picasso = Picasso.get()
            picasso.load(movies.multimedia.src)
                .into(imageView)
            progressBar.visibility = View.INVISIBLE

            itemView.setOnClickListener{
                MoviesActivity.getStartIntent(context,"","")
            }
        }
    }
}
package br.com.jdvfi.feedfilmeshollywood.telas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jdvfi.feedfilmeshollywood.R
import br.com.jdvfi.feedfilmeshollywood.adapter.MoviesAdapter
import br.com.jdvfi.feedfilmeshollywood.data.retrofit.APIClientNYTimes
import br.com.jdvfi.feedfilmeshollywood.data.retrofit.ApiService
import br.com.jdvfi.feedfilmeshollywood.model.Link
import br.com.jdvfi.feedfilmeshollywood.model.Multimedia
import br.com.jdvfi.feedfilmeshollywood.model.Results
import kotlinx.android.synthetic.main.activity_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setSupportActionBar(movies_toolbar as Toolbar?)

        with(rv_movies) {
            layoutManager = LinearLayoutManager(this@MoviesActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = MoviesAdapter(this@MoviesActivity, getMovies())

        }

    }
//
    fun getMovies(): List<Results> {
        return listOf(
            Results(
                "Square Grouper: The Godfathers of Ganja",
                "R",
                0,
                "RACHEL SALTZ",
                "Adventures in the Drug Trades",
                "“Square Grouper” is a documentary about pot smuggling in South Florida in the 1970s and ’80s.",
                "2011-04-14",
                "2011-04-15",
                "2017-11-02 04:18:13",
                Link(
                    "article",
                    "http://www.nytimes.com/2011/04/15/movies/square-grouper-movie-review.html",
                    "Read the New York Times Review of Square Grouper: The Godfathers of Ganja"
                ),
                Multimedia(
                    "mediumThreeByTwo210",
                    "https://static01.nyt.com/images/2019/06/05/arts/blackgodfather1/blackgodfather1-mediumThreeByTwo210.jpg",
                    210,
                    140
                )
            )
        )
    }

    fun getFilmes(): List<Results>{
        var moviesList: MutableList<Results> = mutableListOf()
        val service = ApiService.initRetrofit().create(APIClientNYTimes::class.java)

        service.getFeedMovies().enqueue(object: Callback<Results>{
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Listagem",t.message)
            }

            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                response.body()?.let{ moviesResponse ->
                    moviesList = getMovies() as MutableList<Results>
                    Log.e("Listagem",moviesResponse.toString())
                }
            }

        } )
         return moviesList
    }

    companion object{
        fun getStartIntent(conext: Context, title: String, description: String): Intent{
            return Intent(conext,MoviesDetailActivity::class.java).apply {
                putExtra("Titulo","Adventures in the Drug Trades")
                putExtra("url","http://www.nytimes.com/2011/04/15/movies/square-grouper-movie-review.html")
            }
        }
    }
}

package br.com.jdvfi.feedfilmeshollywood.data.retrofit

import br.com.jdvfi.feedfilmeshollywood.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClientNYTimes {

    @GET("search.json")
    fun getFeedMovies(
        @Query("query") query: String = "godfather",
        @Query("api-key") apiKey: String ="aNXjU0AckXSSZIX7ReExCmeq5auPoM2B"
    ):  Call<Results>

}
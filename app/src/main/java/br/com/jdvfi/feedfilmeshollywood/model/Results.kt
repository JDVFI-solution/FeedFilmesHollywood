package br.com.jdvfi.feedfilmeshollywood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("display_title")
    @Expose
    val display_title: String,

    @SerializedName("mpaa_rating")
    @Expose
    val mpaa_rating: String,

    @SerializedName("critics_pick")
    @Expose
    val critics_pick: Int,

    @SerializedName("byline")
    @Expose
    val byline: String,

    @SerializedName("headline")
    @Expose
    val headline: String,

    @SerializedName("summary_short")
    @Expose
    val summary_short: String,

    @SerializedName("publication_date")
    @Expose
    val publication_date: String,

    @SerializedName("opening_date")
    @Expose
    val opening_date: String,

    @SerializedName("date_updated")
    @Expose
    val date_updated: String,

    @SerializedName("link")
    @Expose
    val link: Link,

    @SerializedName("multimedia")
    @Expose
    val multimedia: Multimedia
)
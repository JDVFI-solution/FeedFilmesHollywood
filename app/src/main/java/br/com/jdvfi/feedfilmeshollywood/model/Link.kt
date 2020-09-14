package br.com.jdvfi.feedfilmeshollywood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("url")
    @Expose
    val url:String,

    @SerializedName("suggested_link_text")
    @Expose
    val suggested_link_text: String
)
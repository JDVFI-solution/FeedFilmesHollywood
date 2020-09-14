package br.com.jdvfi.feedfilmeshollywood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Multimedia(

    @SerializedName("type")
    @Expose
    val type: String,

    @SerializedName("src")
    @Expose
    val src: String,

    @SerializedName("width")
    @Expose
    val width: Int,

    @SerializedName("height")
    @Expose
    val height: Int
)
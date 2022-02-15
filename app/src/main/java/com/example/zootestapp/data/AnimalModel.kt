package com.example.zootestapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimalModel(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("latin_name")
    @Expose
    val latinName: String,

    @SerializedName("animal_type")
    @Expose
    val type: String,

    @SerializedName("active_time")
    @Expose
    val activeTime: String,

    @SerializedName("length_min")
    @Expose
    val lengthMin: String,

    @SerializedName("length_max")
    @Expose
    val lengthMax: String,

    @SerializedName("weight_min")
    @Expose
    val weightMin: String,

    @SerializedName("weight_max")
    @Expose
    val weightMax: String,

    @SerializedName("lifespan")
    @Expose
    val lifespan: String,

    @SerializedName("habitat")
    @Expose
    val habitat: String,

    @SerializedName("diet")
    @Expose
    val diet: String,

    @SerializedName("geo_range")
    @Expose
    val geoRange: String,

    @SerializedName("image_link")
    @Expose
    val imageLink: String,
): Serializable
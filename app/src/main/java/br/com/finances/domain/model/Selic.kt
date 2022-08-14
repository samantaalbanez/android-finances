package br.com.finances.domain.model

import com.google.gson.annotations.SerializedName

data class Selic(
    @SerializedName("data")
    val date: String,
    @SerializedName("valor")
    val value: String
)
package com.san.archapp.data.entity


import com.san.archapp.data.entity.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    @SerialName("data")
    var `data`: List<Data>?,
    @SerialName("meta")
    var meta: Meta?
)
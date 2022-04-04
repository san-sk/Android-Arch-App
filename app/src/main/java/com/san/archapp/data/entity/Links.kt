package com.san.archapp.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("current")
    var current: String?,
    @SerialName("next")
    var next: String?,
    @SerialName("previous")
    var previous: String?
)
package com.san.archapp.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("pagination")
    var pagination: Pagination?
)
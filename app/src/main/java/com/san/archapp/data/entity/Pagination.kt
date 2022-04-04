package com.san.archapp.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("limit")
    var limit: Int?,
    @SerialName("links")
    var links: Links?,
    @SerialName("page")
    var page: Int?,
    @SerialName("pages")
    var pages: Int?,
    @SerialName("total")
    var total: Int?
)
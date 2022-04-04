package com.san.archapp.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_data")
data class Data(
    @SerialName("id")
    @PrimaryKey var id: Int?,
    @SerialName("email")
    var email: String?,
    @SerialName("gender")
    var gender: String?,
    @SerialName("name")
    var name: String?,
    @SerialName("status")
    var status: String?,
    var comments: String? = ""
)
package com.github.yasukotelin.jetpack_compose_sample.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: String,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    val url: String,
)
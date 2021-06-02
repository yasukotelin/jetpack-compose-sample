package com.github.yasukotelin.jetpack_compose_sample.model

import com.google.gson.annotations.SerializedName

data class User(
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val company: String,
    val blog: String,
    val location: String,
    val bio: String,
    @SerializedName("twitter_user_name")
    val twitterUserName: String,
)
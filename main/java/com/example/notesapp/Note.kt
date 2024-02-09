package com.example.notesapp
import com.google.gson.annotations.SerializedName
data class Note( @SerializedName("id") val id: Int,
                 @SerializedName("title") val title: String,
                 @SerializedName("content") val content: String)

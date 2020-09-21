package com.gmail.part1.satrio.ini_tugas_kotlin.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Berita_Pojo(
        @SerializedName("status") @Expose var status:String,
        @SerializedName("totalResults") @Expose var totalResults:Int,
        @SerializedName("articles") @Expose var articles: List<Article>? = null
){
    data class Article(
        @SerializedName("source") @Expose var source:Source,
        @SerializedName("author") @Expose var author:Any,
        @SerializedName("title") @Expose var title:String,
        @SerializedName("description") @Expose var description:Any,
        @SerializedName("url") @Expose var url:String,
        @SerializedName("urlToImage") @Expose var urlToImage:String,
        @SerializedName("publishedAt") @Expose var publishedAt:String
    ){
        data class Source(
            @SerializedName("id") @Expose var id:Any,
            @SerializedName("name") @Expose var name:String
        )
    }
}

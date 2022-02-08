package br.com.mvclopes.gistcompose.model.repository.api

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService{

    @GET("gists/public")
    suspend fun getPublicGists(): ArrayList<GistResponse?>

    @GET
    suspend fun getBannerImage(@Url url: String): String
}
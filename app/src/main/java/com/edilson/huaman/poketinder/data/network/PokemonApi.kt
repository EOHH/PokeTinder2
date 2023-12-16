package com.edilson.huaman.poketinder.data.network

import com.edilson.huaman.poketinder.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("?limit=20")
    suspend fun getPokemons() : Response<PokemonListResponse>
}

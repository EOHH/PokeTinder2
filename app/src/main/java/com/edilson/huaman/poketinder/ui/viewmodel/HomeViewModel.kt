package com.edilson.huaman.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.edilson.huaman.poketinder.data.database.PokemonDatabase
import com.edilson.huaman.poketinder.data.database.entities.MyPokemonEntity
import com.edilson.huaman.poketinder.data.model.PokemonResponse
import com.edilson.huaman.poketinder.data.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel: ViewModel() {

    val pokemonList = MutableLiveData<List<PokemonResponse>>()
    val isLoading = MutableLiveData<Boolean>()

    private val POKEMON_DATABASE_NAME = "pokemon_database"

    init {
        getAllPokemons()
    }

    private fun getAllPokemons() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
            isLoading.postValue(false)
            if ( call.isSuccessful) {
                call.body()?.let {
                    pokemonList.postValue(it.results)
                }
            }
        }
    }

    // Para guardar los pokemon in Like

    fun savePokemon(pokemonResponse: PokemonResponse, context: Context) {
        val myPokemon = MyPokemonEntity(
            name = pokemonResponse.name,
            image = pokemonResponse.getPokemonImage(),
            idPokemon = pokemonResponse.getPokemonId()
        )

        viewModelScope.launch {
            getRoomDatabase(context).getPokemonDao().insert(myPokemon)
        }
    }

    fun getRoomDatabase(context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            POKEMON_DATABASE_NAME)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
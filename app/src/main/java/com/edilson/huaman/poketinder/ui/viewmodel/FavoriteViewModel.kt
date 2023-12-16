package com.edilson.huaman.poketinder.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.edilson.huaman.poketinder.data.database.PokemonDatabase
import com.edilson.huaman.poketinder.data.database.entities.MyPokemonEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {

    private val POKEMON_DATABASE_NAME = "pokemon_database"

    val myPokemonList = MutableLiveData<List<MyPokemonEntity>>()

    fun getMyPokemons(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val myPokemons = getRoomDatabase(context).getPokemonDao().getAllPokemons()
            val distintoPokemons = myPokemons.distinctBy { it.idPokemon }
            myPokemonList.postValue(distintoPokemons)
        }
    }

    fun deleteAllPokemon(context: Context) {
        viewModelScope.launch {
            getRoomDatabase(context).getPokemonDao().deleteTable()
            myPokemonList.postValue(emptyList())
        }
    }

    fun getRoomDatabase(context: Context) : PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            POKEMON_DATABASE_NAME).build()
    }
}

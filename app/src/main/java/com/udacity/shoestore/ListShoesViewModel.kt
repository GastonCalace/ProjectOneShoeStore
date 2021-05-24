package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListShoesViewModel : ViewModel() {


    private val _listShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    val listShoes: LiveData<MutableList<Shoe>>
        get() = _listShoes

    fun addShoe(name: String, company: String, size: Int, description: String) {
        _listShoes.value?.add(Shoe(name, size.toDouble(), company, description))
    }

}
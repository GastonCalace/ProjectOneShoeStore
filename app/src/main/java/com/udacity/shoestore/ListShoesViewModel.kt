package com.udacity.shoestore

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.databinding.ItemListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.item_list.view.*

class ListShoesViewModel :ViewModel() {

    private val _listShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    val listShoes: LiveData<MutableList<Shoe>>
        get() = _listShoes

    fun addNewShoe(newShoe: Shoe) { _listShoes.value?.add(newShoe) }

    fun listingItems(layout: LinearLayout, list: LiveData<MutableList<Shoe>>) {
        list.value?.forEach{ shoe ->
            layout.addLayout(shoe) }
    }

    private fun LinearLayout.addLayout(shoe: Shoe) {
        val itemList: View = LayoutInflater.from(context).inflate(
                R.layout.item_list,
                this,false)

        itemList.sizeShoeText.text = shoe.size.toString()
        itemList.nameShoeText.text = shoe.name
        itemList.companyShoeText.text = shoe.company
        itemList.descShoeText.text = shoe.description

        this.addView(itemList)
    }

}
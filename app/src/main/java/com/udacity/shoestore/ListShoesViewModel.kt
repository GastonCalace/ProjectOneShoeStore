package com.udacity.shoestore

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListShoesViewModel :ViewModel() {

    private val _listShoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    val listShoes: LiveData<MutableList<Shoe>>
        get() = _listShoes


    fun addNewShoe(name: String, company: String, size: Int, description: String) {
        _listShoes.value?.add(Shoe(name, size.toString().toDouble(),
            company, description)
        )
    }

    fun listingItems(layout: LinearLayout) {
        listShoes.value?.forEach{ shoe ->
            layout.addLayout(shoe.size.toString(), shoe.name, shoe.company, shoe.description) }
    }

    private fun LinearLayout.addLayout(newSizeText: String, newNameText: String, newCompanyText: String, newDescText: String) {
        val itemList: View = LayoutInflater.from(context).inflate(
            R.layout.item_list,
            this,false)
        val sizeText = itemList.findViewById<View>(R.id.sizeShoeText) as TextView
        val nameText = itemList.findViewById<View>(R.id.nameShoeText) as TextView
        val companyText = itemList.findViewById<View>(R.id.companyShoeText) as TextView
        val descText = itemList.findViewById<View>(R.id.descShoeText) as TextView
        sizeText.text = newSizeText.toString()
        nameText.text = newNameText
        companyText.text = newCompanyText
        descText.text = newDescText
        this.addView(itemList)
    }

}
package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.ListShoesFragmentBinding



class ListShoesFragment : Fragment() {

    private val viewModel: ListShoesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: ListShoesFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.list_shoes_fragment, container, false)
        var args = ListShoesFragmentArgs.fromBundle(requireArguments())

        val nameShoe = args.nameShoe
        val companyShoe = args.companyShoe
        val sizeShoe = args.sizeShoe
        val descShoe = args.descShoe



        viewModel.listShoes.observe(this,
                Observer {newList ->
                    if (sizeShoe != -1)
                        viewModel.addShoe(nameShoe.toString(),companyShoe.toString(),sizeShoe, descShoe.toString())
                    if (newList.isNotEmpty()) {
                        viewModel.listShoes.value?.forEach{ shoe ->
                            binding.myLinearLayout.addLayout(shoe.size.toString(), shoe.name, shoe.company, shoe.description) }
                    }

                })

       binding.detailButton.setOnClickListener() {
           view?.findNavController()?.navigate(ListShoesFragmentDirections.actionListShoesFragmentToDetailsFragment())
        }

        return binding.root
    }

    private fun LinearLayout.addLayout(newSizeText: String, newNameText: String, newCompanyText: String, newDescText: String) {
        val itemList: View = LayoutInflater.from(requireContext()).inflate(R.layout.item_list,
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


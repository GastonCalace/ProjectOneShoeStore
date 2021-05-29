package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.DetailsFragmentBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.item_list.view.*

class DetailsFragment: Fragment() {

    private val viewModel: ListShoesViewModel by activityViewModels()
    private val newShoe: Shoe = Shoe("", "", "", "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: DetailsFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.details_fragment, container, false)

        binding.newShoe = newShoe

        var newShoeDetails = object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.addShoeButton.isEnabled = (binding.editCompanyShoe.text.trim().isNotEmpty() && binding.editDescriptionShoe.text.trim().isNotEmpty() &&
                        binding.editNameShoe.text.trim().isNotEmpty()&& binding.editSizeShoe.text.trim().isNotEmpty())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }

        binding.editDescriptionShoe.addTextChangedListener(newShoeDetails)
        binding.editSizeShoe.addTextChangedListener(newShoeDetails)
        binding.editCompanyShoe.addTextChangedListener(newShoeDetails)
        binding.editNameShoe.addTextChangedListener(newShoeDetails)

        binding.addShoeButton.setOnClickListener {
            binding.apply {
                newShoe?.name = editNameShoe.text.toString()
                newShoe?.company = editCompanyShoe.text.toString()
                newShoe?.size = editSizeShoe.text.toString()
                newShoe?.name = editDescriptionShoe.text.toString()
            }
            viewModel.addNewShoe(newShoe)
            view?.findNavController()?.popBackStack()
        }

        binding.cancelButton.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        return binding.root
    }

}
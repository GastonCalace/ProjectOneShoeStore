package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.DetailsFragmentBinding

class DetailsFragment: Fragment() {

    private val viewModel: ListShoesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: DetailsFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.details_fragment, container, false)


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
            viewModel.addNewShoe(binding.editNameShoe.text.toString(), binding.editCompanyShoe.text.toString(),
                binding.editSizeShoe.text.toString().toInt(), binding.editDescriptionShoe.text.toString())
            view?.findNavController()?.navigate(DetailsFragmentDirections.actionDetailsFragmentToListShoesFragment())
        }

        return binding.root
    }

}
package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ListShoesFragmentBinding



class ListShoesFragment : Fragment() {

    private val viewModel: ListShoesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: ListShoesFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.list_shoes_fragment, container, false)

        setHasOptionsMenu(true)

        viewModel.listingItems(binding.myLinearLayout)

       binding.detailButton.setOnClickListener() {
           view?.findNavController()?.navigate(ListShoesFragmentDirections.actionListShoesFragmentToDetailsFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}


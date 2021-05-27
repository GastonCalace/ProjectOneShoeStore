package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.core.app.SharedElementCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.udacity.shoestore.databinding.WelcomeScreenFragmentBinding

class WelcomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: WelcomeScreenFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.welcome_screen_fragment, container, false)
        binding.instructionsButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeScreen_to_instructionsFragment)
        )

        return binding.root
    }

}
package com.riopermana.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.riopermana.repo.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding : FragmentDashboardBinding? = null
    private val binding : FragmentDashboardBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        setupFragmentContainer()
        return binding.root
    }

    private fun setupFragmentContainer(){
        val navHost = childFragmentManager.findFragmentById(R.id.dashboard_fragment_container) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHost.findNavController())
    }
}
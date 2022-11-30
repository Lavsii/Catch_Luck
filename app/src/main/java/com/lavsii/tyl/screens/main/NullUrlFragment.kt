package com.lavsii.tyl.screens.main

import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.lavsii.tyl.R

import com.lavsii.tyl.databinding.FragmentNullUrlBinding
import com.lavsii.tyl.utils.APP_ACTIVITY

class NullUrlFragment : Fragment() {
    private var _binding: FragmentNullUrlBinding? = null
    private val binding get() = _binding!!
    private var homeBtn = R.id.home_btn

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                val homeBtn = menu.findItem(R.id.home_btn)
                homeBtn.isVisible = true
                val exitBtn = menu.findItem(R.id.exit_btn)
                exitBtn.isVisible = true
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.home_btn ->{
                        homeBtn = View.VISIBLE
                        APP_ACTIVITY.navController.navigate(R.id.action_nullUrlFragment_to_startFragment)
                    }
                    R.id.exit_btn -> {
                        activity?.finishAffinity()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentNullUrlBinding.inflate(layoutInflater, container, false)
        return binding.root

    }
}
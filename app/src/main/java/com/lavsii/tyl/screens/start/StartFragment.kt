package com.lavsii.tyl.screens.start

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lavsii.tyl.R
import com.lavsii.tyl.data.Countries
import com.lavsii.tyl.data.getCountries
import com.lavsii.tyl.databinding.FragmentStartBinding
import com.lavsii.tyl.utils.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_start.*


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                val exitBtn = menu.findItem(R.id.exit_btn)
                exitBtn.isVisible = true
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.exit_btn -> {
                        activity?.finishAffinity()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        val countriesList = getCountries()
        val adapter = StartFragmentAdapter(countriesList)
        recycler_view.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun click(country: Countries) {
            if (country.link != null) {
                val bundle = Bundle()
                bundle.putSerializable("country", country)
                APP_ACTIVITY.navController.navigate(
                    R.id.action_startFragment_to_mainFragment,
                    bundle
                )
            } else {
                val bundle = Bundle()
                bundle.putSerializable("country", country)
                APP_ACTIVITY.navController.navigate(
                    R.id.action_startFragment_to_nullUrlFragment, bundle
                )
            }
        }
    }
}


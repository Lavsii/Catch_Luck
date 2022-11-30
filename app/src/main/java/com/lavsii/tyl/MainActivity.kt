package com.lavsii.tyl

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lavsii.tyl.databinding.ActivityMainBinding
import com.lavsii.tyl.utils.APP_ACTIVITY


class MainActivity : AppCompatActivity() {
    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        mToolbar = binding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        mToolbar.title = R.string.app_name.toString()

/*        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.exit_btn -> {
                        finishAffinity()
                    }
                }
                return true
            }
        })*/
    }

    /*    override fun onCreateOptionsMenu(menu: Menu): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
               when (item.itemId) {
                R.id.exit_btn -> {
                    finishAffinity()
                }
                   R.id.home_btn -> {
                       setVisible(false)
                   }
            }
            return super.onOptionsItemSelected(item)
        }*/
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
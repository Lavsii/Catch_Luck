package com.lavsii.tyl.screens.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lavsii.tyl.R
import com.lavsii.tyl.data.Countries
import com.lavsii.tyl.databinding.FragmentMainBinding
import com.lavsii.tyl.utils.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mCurrentCountry: Countries
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

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
                        APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
                    }
                    R.id.exit_btn -> {
                        activity?.finishAffinity()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        mCurrentCountry = arguments?.getSerializable("country") as Countries
        webView = binding.webviewId
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialization() {
        webView.settings.javaScriptEnabled = true
        this.webView.webViewClient = WebViewClient()
        mCurrentCountry.link?.let { webView.loadUrl(it) }
        val progressBar = binding.mainFragmentLoadingLayout

        webView.webViewClient = object : WebViewClient() {

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                progressBar.visibility = View.VISIBLE
                view.loadUrl(url)
                return false
            }
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.san.archapp.ui.examples.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import com.san.archapp.R
import com.san.archapp.databinding.FragmentWebViewBinding

const val LocalContentWebViewURL = "https://appassets.androidplatform.net/assets/index.html"

@Deprecated("Deprecated in Java")
@Suppress("Unused")
class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private lateinit var binding: FragmentWebViewBinding

    private val myWebViewClient by lazy {
        object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
            }


            override fun shouldInterceptRequest(
                view: WebView?,
                url: String?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, url)
            }

        }
    }

    private val myWebChromeClient by lazy {
        object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                binding.pb.visibility = if (newProgress < 100) View.VISIBLE else View.GONE

            }

            override fun onConsoleMessage(message: ConsoleMessage): Boolean {
                Log.d(
                    "MyApplication", "${message.message()} -- From line " +
                            "${message.lineNumber()} of ${message.sourceId()}"
                )
                return true
            }
        }
    }


    //Local content - requires webkit dependency
    /*private val assetLoader = WebViewAssetLoader.Builder()
        .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(requireContext()))
        .addPathHandler("/res/", WebViewAssetLoader.ResourcesPathHandler(requireContext()))
        .build()


    private val localWebViewClient by lazy { LocalContentWebViewClient(assetLoader) }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wvHome.apply {
            webViewClient = myWebViewClient
            webChromeClient = myWebChromeClient
            settings.apply {
                setRenderPriority(WebSettings.RenderPriority.HIGH)
                javaScriptEnabled = true
                loadWithOverviewMode = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                domStorageEnabled = true
            }
        }.loadUrl("https://www.verizon.com" ?: LocalContentWebViewURL)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.wvHome.canGoBack()) binding.wvHome.goBack()
                }
            })
    }


    private inner class MyWebViewClient() : WebViewClient() {

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (Uri.parse(url).host == "www.example.com") {
                // This is my web site, so do not override; let my WebView load the page
                return false
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                startActivity(this@WebViewFragment.requireActivity(), this, null)
            }
            return true
        }
    }

    private inner class LocalContentWebViewClient(private val assetLoader: WebViewAssetLoader) :
        WebViewClientCompat() {
        override fun shouldInterceptRequest(
            view: WebView,
            request: WebResourceRequest
        ): WebResourceResponse? {
            return assetLoader.shouldInterceptRequest(request.url)
        }

        // to support API < 21
        override fun shouldInterceptRequest(
            view: WebView,
            url: String
        ): WebResourceResponse? {
            return assetLoader.shouldInterceptRequest(Uri.parse(url))
        }
    }


}
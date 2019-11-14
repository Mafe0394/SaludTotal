package com.wigilabs.saludtotal.news

import android.os.Bundle
import android.webkit.WebView
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.base.BaseActivity
import load

class NewsActivity : BaseActivity() {


    private var webView: WebView? = null
    private var contentLoadingProgressBar: ContentLoadingProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        if(!load("isNetworkConnected",false)){
           Toast.makeText(this, R.string.error_no_internet,Toast.LENGTH_LONG).show()
        }
        webView = findViewById(R.id.web_view)
        contentLoadingProgressBar =  findViewById(R.id.contentLoadingProgressBar)
        initSettings()
        webView!!.loadUrl("https://codeday.me/es/qa/20190425/563618.html")
    }


    private fun initSettings() {

        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.setSupportZoom(true)
        webView!!.settings.builtInZoomControls = true
        webView!!.settings.allowFileAccess = true
        webView!!.settings.javaScriptCanOpenWindowsAutomatically = true
        webView!!.settings.useWideViewPort = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.settings.domStorageEnabled = true

        webView!!.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                webView!!.visibility = View.INVISIBLE
            }

            override fun onPageCommitVisible(view: WebView, url: String) {
                super.onPageCommitVisible(view, url)
                contentLoadingProgressBar!!.hide()
                webView!!.visibility = View.VISIBLE
            }
        }

    }


    override fun onBackPressed() {
        if (webView!!.canGoBack())
            webView!!.goBack()
        else
            super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        webView!!.clearCache(true)
        webView!!.clearHistory()
    }

}

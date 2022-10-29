package com.example.vix_schoters_taufikdito.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.vix_schoters_taufikdito.R

class DetailNewsActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        val url = intent.data
        webView = findViewById(R.id.web_view)
        webView.loadUrl(url.toString())
        webView.webViewClient = CallBack()
    }

    class CallBack: WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return false
        }
    }
}
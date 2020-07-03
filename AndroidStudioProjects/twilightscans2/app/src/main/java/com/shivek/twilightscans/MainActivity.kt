package com.shivek.twilightscans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        webview.loadUrl("https://twilightscans.com")
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()
    }
}
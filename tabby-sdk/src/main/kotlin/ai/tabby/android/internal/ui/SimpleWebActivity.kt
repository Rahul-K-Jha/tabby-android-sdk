package ai.tabby.android.internal.ui

import ai.tabby.android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.webkit.*
import androidx.activity.ComponentActivity

internal class SimpleWebActivity : ComponentActivity() {

    private val initialUrl: String get() = intent.getStringExtra(EXTRA_URL)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_web_activity)

        findViewById<WebView>(R.id.webView).apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true

            webViewClient = WebViewClient()
//            this.webChromeClient = webChromeClient

            setOnKeyListener { _, _, keyEvent ->
                when (keyEvent.keyCode) {
                    KeyEvent.KEYCODE_BACK -> when {
                        !canGoBack() -> false
                        keyEvent.action == MotionEvent.ACTION_UP -> { goBack(); true }
                        else -> true
                    }
                    else -> true
                }
            }

            loadUrl(initialUrl)

        }
    }

    companion object {
        fun createIntent(context: Context, url: String): Intent =
            Intent(context, SimpleWebActivity::class.java).apply {
                putExtra(EXTRA_URL, url)
            }

        private const val EXTRA_URL = "extra.Url"
    }
}


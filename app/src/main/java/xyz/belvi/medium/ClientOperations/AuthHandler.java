package xyz.belvi.medium.ClientOperations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import xyz.belvi.medium.R;

public class AuthHandler extends AppCompatActivity {
    public static final String URL = "xyz.belvi.medium.ClientOperations.URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_auth_handler);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.activity_auth_handler);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra(URL);

        webView.addJavascriptInterface(new JSInterface(), "appObj");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Intent intent = new Intent(ClientConstant.connectionReceiverAction);
                intent.putExtra(ClientConstant.connectionStatus, false);
                LocalBroadcastManager.getInstance(AuthHandler.this).sendBroadcast(intent);
                finish();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Intent intent = new Intent(ClientConstant.connectionReceiverAction);
                intent.putExtra(ClientConstant.connectionStatus, false);
                LocalBroadcastManager.getInstance(AuthHandler.this).sendBroadcast(intent);
                finish();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                Intent intent = new Intent(ClientConstant.connectionReceiverAction);
                intent.putExtra(ClientConstant.connectionStatus, false);
                LocalBroadcastManager.getInstance(AuthHandler.this).sendBroadcast(intent);
                finish();
            }

        });
        webView.loadUrl(url);

    }

    public class JSInterface {
        @JavascriptInterface
        public void initCredentials(String code, String state) {
            Intent intent = new Intent(ClientConstant.connectionReceiverAction);
            intent.putExtra(ClientConstant.connectionCode, code);
            intent.putExtra(ClientConstant.connectionState, state);
            intent.putExtra(ClientConstant.connectionStatus, true);
            intent.putExtra(ClientConstant.connectionAccessStatus, true);
            LocalBroadcastManager.getInstance(AuthHandler.this).sendBroadcast(intent);
            finish();
        }

        @JavascriptInterface
        public void accessDenied() {
            Intent intent = new Intent(ClientConstant.connectionReceiverAction);
            intent.putExtra(ClientConstant.connectionStatus, true);
            intent.putExtra(ClientConstant.connectionAccessStatus, false);
            LocalBroadcastManager.getInstance(AuthHandler.this).sendBroadcast(intent);
            finish();
        }
    }
}

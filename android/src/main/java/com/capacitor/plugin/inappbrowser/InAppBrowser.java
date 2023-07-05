package com.capacitor.plugin.inappbrowser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


public class InAppBrowser {

    private final Context context;
    private final AppCompatActivity activity;

    private WebView webView;

    public InAppBrowser(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void open(Uri uri) {
        activity.runOnUiThread(() -> {
            webView = new WebView(context);
            webView.loadUrl(uri.toString());
            activity.addContentView(webView, new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));

            webView.getSettings().setSupportMultipleWindows(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
                {
                    String data =  view.getHitTestResult().getExtra();
                    Context context = view.getContext();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
                    context.startActivity(browserIntent);
                    return false;
                }
            });
            webView.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    String url = request.getUrl().toString();
                    if (url != null && (url.startsWith("tel://") || url.startsWith("mail://"))) {
                        view.getContext().startActivity(
                                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        });
    }

    public void close() {
        activity.runOnUiThread(() -> {
            ((ViewGroup) activity.findViewById(android.R.id.content)).removeView(webView);
            webView.setVisibility(View.GONE);
            webView.destroy();
        });
    }
}

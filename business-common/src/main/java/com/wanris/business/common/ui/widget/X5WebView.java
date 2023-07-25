package com.wanris.business.common.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;

import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;



public class X5WebView extends WebView {
    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);

        this.setWebViewClient(client);
        initWebViewSettings();
        this.getView().setClickable(true);
    }

    private WebViewClient client = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            addImageClickListener(webView);
        }

        private void addImageClickListener(WebView webView) {
            //
            // 遍历页面中所有img的节点，因为节点里面的图片的url即objs[i].src，保存所有图片的src.
            // 为每个图片设置点击事件，objs[i].onclick
            //
            webView.loadUrl("javascript:(function(){" +
                    "var objs = document.getElementsByTagName(\"img\"); " +
                    "for(var i=0;i<objs.length;i++) " +
                    "{" +
                    "objs[i].addEventListener('click',function(e){" +   //去掉默认点击事件
                            "e.preventDefault();" +
                    "});" +
                    "window.imageListener.readImageUrl(objs[i].src);  " //添加图片集合
                    + " objs[i].onclick=function() " +
                    " { "
                    + "  window.imageListener.showImage(this.src); " +  //通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接
                    " } " +
                    "}" +
                    "})()");
        }
    };

    private void initWebViewSettings() {
        setDownloadListener(downloadListener);

        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        WebSettings webSetting = this.getSettings();
        webSetting.setTextZoom(100);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(false);
        webSetting.setDisplayZoomControls(false);
        //webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        //webSetting.setLoadWithOverviewMode(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setAppCacheEnabled(false);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setAppCachePath("/data/data/" + getContext().getPackageName() + "/x5cache"); //设置缓存的指定路径

        requestFocus();
    }

    public OnScrollListener listener;

    /**
     * This is called in response to an internal scroll in this view (i.e., the
     * view scrolled its own contents). This is typically as a result of
     * {@link #scrollBy(int, int)} or {@link #scrollTo(int, int)} having been
     * called.
     *
     * @param l    Current horizontal scroll origin.
     * @param t    Current vertical scroll origin.
     * @param oldl Previous horizontal scroll origin.
     * @param oldt Previous vertical scroll origin.
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.scrollHeight(t);
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }

    public interface OnScrollListener {
        void scrollHeight(int h);
    }

    DownloadListener downloadListener = (url, userAgent, contentDisposition, mimetype, contentLength) -> {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getContext().startActivity(intent);
    };
}

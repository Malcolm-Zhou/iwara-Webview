package com.example.mzhou086.androidtest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.mzhou086.androidtest.bean.VideoInfo;
import com.example.mzhou086.androidtest.util.CrawlTool;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private static Gson gson = new Gson();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.wv_index);
        webView.getSettings().setJavaScriptEnabled(true); // 开启javascript支持
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setAllowFileAccess(true);

        webView.addJavascriptInterface(new IndexController(), "android");
        webView.loadUrl("file:///android_asset/index.html");


    }

    public class IndexController {
        @JavascriptInterface
        public void getData(String pageNum) {
            ArrayList<VideoInfo> crawlData = CrawlTool.getCrawlData(pageNum);
            String json = gson.toJson(crawlData);
            webView.loadUrl("javascript:jsonToPage_a2j('" + json + "')");
        }
        @JavascriptInterface
        public void playVideo(String url, String resolution) {
            onPlayVideo(url, resolution);
        }
    }


    public void onPlayVideo(final String address, final String resolution) {

        final String[] url = {""};

        //handler与线程之间的通信及数据处理
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 11) {
                    //msg.obj是获取handler发送信息传来的数据
                    @SuppressWarnings("unchecked")
                    String url = (String) msg.obj;
                    play(url);

                }
            }
        };

        Runnable runnable = new Runnable() {
            public void run() {

                url[0] = CrawlTool.GetVideoAddress(address, resolution);
                if (!url[0].contains("file.php")) {
                    //视频来自外站
                    url[0] = "TempAddressForOuterVideo";
                }
                handler.sendMessage(handler.obtainMessage(11, url[0]));
            }
        };

        new Thread(runnable).start();
    }

    private void play(String url) {
        Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
        mediaIntent.setDataAndType(Uri.parse(url), "video/mp4");
        startActivity(mediaIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}

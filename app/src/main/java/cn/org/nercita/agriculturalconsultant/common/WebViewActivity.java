package cn.org.nercita.agriculturalconsultant.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.org.nercita.agriculturalconsultant.R;
import cn.org.nercita.agriculturalconsultant.utils.LogUtil;
import cn.org.nercita.agriculturalconsultant.view.TitleBar;



/**
 * Created by 梁兴胜 on 2017/4/13.
 * 详情网页
 */

public class WebViewActivity extends AppCompatActivity {
    @Bind(R.id.tb_activity_webview)
    TitleBar mTitleBar;
    @Bind(R.id.tv_activity_webview)
    TextView tvNotive;
    @Bind(R.id.wv_activity_webview)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 获取链接地址并加载
     * @author: liangxingsheng
     * @date: 2017/4/13 上午11:14
     */
    private void init() {
        final String url = getIntent().getStringExtra("url");
        LogUtil.e(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                tvNotive.setVisibility(View.VISIBLE);
            }
        });
        mWebView.loadUrl(url);
        mTitleBar.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * webView可返回
     * @author: liangxingsheng
     * @date: 2017/4/13 上午11:14
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {

            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.tv_activity_webview)
    public void onClick() {

        mWebView.reload();
    }
}

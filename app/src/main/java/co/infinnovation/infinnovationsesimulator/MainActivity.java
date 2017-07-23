package co.infinnovation.infinnovationsesimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar spinner;
    protected final String homepageUrl = "https://infinnovationses-vistas.rhcloud.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar) findViewById(R.id.activity_main_spinner);
        spinner.setVisibility(View.VISIBLE);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        WebSettings ws = mWebView.getSettings();
        ws.setJavaScriptEnabled(true);


        mWebView.loadUrl(homepageUrl);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient(spinner));

    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.home:
                mWebView.loadUrl(homepageUrl);
                break;
            case R.id.refresh:
                String currentUrl = mWebView.getUrl();
                if(currentUrl.startsWith("file:///")) {
                    mWebView.loadUrl(homepageUrl);
                }
                else {
                    mWebView.reload();
                }
                break;
            case R.id.exit:
                finish();
                break;
        }

        return true;
    }
}

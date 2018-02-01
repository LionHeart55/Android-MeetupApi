package cs413.project_2_api_lionheart55.Views;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.R;

/**
 * @author shawn Shocron
 * Created by shawn Shocron on 7/22/17.
 */

public class LinkActivity extends BaseActivity {

    private final static String TAG = "LinkActivity";

    private ProgressDialog progressBar;
    private AlertDialog alertDialog;
    private WebView webViewLink;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_layout); //activity_main);
        initAnimation();

        String linkUrl = (String) getIntent().getSerializableExtra(Constants.EXTRA_URL);

        webViewLink = (WebView) findViewById(R.id.webViewLink);
        webViewLink.getSettings().setJavaScriptEnabled(true);
        webViewLink.loadUrl(linkUrl);
        webViewLink.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                final String url = request.getUrl().toString();
                webViewLink.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if(progressBar==null) {
                    progressBar = new ProgressDialog(LinkActivity.this);
                    progressBar.setTitle("Please wait ...");
                    progressBar.setMessage("Loading Page \n"+url);
                }
                progressBar.show();
            }


            @Override
            public void onPageFinished(WebView view, String url){
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                Log.e(TAG, "Error: " + error.getDescription().toString());
                Toast.makeText(LinkActivity.this, "Oh no! Failed to load page\n"+error.getDescription().toString(), Toast.LENGTH_SHORT).show();
                if(alertDialog==null) alertDialog = new AlertDialog.Builder(LinkActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Failed to load page\n"+ request.getUrl().toString());
                alertDialog.setButton(0, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
    }
}

package cs413.project_2_api_lionheart55.Views;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import cs413.project_2_api_lionheart55.App;
import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.Model.Group;
import cs413.project_2_api_lionheart55.R;
import cs413.project_2_api_lionheart55.RestControllers.VolleySingleton;

/**
 * @author shawn Shocron
 * Created by shawn Shocron on 7/3/17.
 */

public class GroupActivity extends BaseActivity {

    private final static String TAG = "GroupActivity";

    private Group group;

    public NetworkImageView ivGroupPhoto;
    public TextView tvName;
    public WebView webViewDesc;
    public TextView tvJoinMode;
    public TextView tvRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_layout); //activity_main);

        initAnimation();

        group = (Group) getIntent().getSerializableExtra(Constants.EXTRA_GROUP);

        ivGroupPhoto = (NetworkImageView) findViewById(R.id.ivGroupPhoto);
        ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
        ivGroupPhoto.setImageUrl(group.getGroupPhoto(), imageLoader);

        tvJoinMode = (TextView) findViewById(R.id.tvJoinMode);
        tvJoinMode.setText(group.getJoin_mode());

        tvRegion = (TextView) findViewById(R.id.tvRegion);
        tvRegion.setText(group.getRegion());


        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(group.getName());

        webViewDesc = (WebView) findViewById(R.id.webViewDesc);
        webViewDesc.loadData(group.getDescription(), "text/html; charset=UTF-8;", null);
        webViewDesc.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                final String url = request.getUrl().toString();
                slideTransition(url, TransitionType.SlideJava);
                return true;
            }
        });
    }

    public void slideTransition(String url, BaseActivity.TransitionType type) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(getApplicationContext(), LinkActivity.class);

        intent.putExtra(Constants.KEY_ANIM_TYPE, type);
        intent.putExtra(Constants.EXTRA_URL, url);
        startActivity(intent, options.toBundle());
    }
}

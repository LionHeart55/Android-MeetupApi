package cs413.project_2_api_lionheart55.Views;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.Date;
import java.util.List;

import cs413.project_2_api_lionheart55.App;
import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.Model.Event;
import cs413.project_2_api_lionheart55.Model.Group;
import cs413.project_2_api_lionheart55.Model.Venue;
import cs413.project_2_api_lionheart55.R;
import cs413.project_2_api_lionheart55.RestControllers.EventsJsonController;
import cs413.project_2_api_lionheart55.RestControllers.GroupJsonController;
import cs413.project_2_api_lionheart55.RestControllers.VolleySingleton;

import static cs413.project_2_api_lionheart55.R.id.url;

/**
 * @author shawn Shocron
 * Created by shawn Shocron on 7/3/17.
 */

public class EventActivity extends BaseActivity {

    private final static String TAG = "EventActivity";

    private GroupJsonController controller;
    private Event event;

    private NetworkImageView ivEventPhoto;
    private ImageView locIcon;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvAddress;
    private TextView tvYesRsvp;
    private TextView tvLink;
    private TextView tvGroup;
    private WebView webViewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);

        initAnimation();

        event = (Event) getIntent().getSerializableExtra(Constants.EXTRA_EVENT);

        Log.i(TAG, "Event Data:\n\n"+event.toString());

        ivEventPhoto = (NetworkImageView) findViewById(R.id.ivEventPhoto);
        ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
        ivEventPhoto.setImageUrl(event.getGroup().getGroupPhoto(), imageLoader);

        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(event.getName());

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.setText("Time: "+event.getTimeFormated());

        tvYesRsvp = (TextView) findViewById(R.id.tvYesRsvp);
        tvYesRsvp.setText("Attending: " + event.getYesRsvpCount());

        if(event.hasLocation()) {
            tvAddress = (TextView) findViewById(R.id.tvAddress);
            tvAddress.setVisibility(View.VISIBLE);
            tvAddress.setText(event.getVenue().getFullAddress());
            tvAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String url = tvLink.getText().toString();
                    slideLocationTransition(event.getVenue(), TransitionType.SlideJava);
                }
            });
        }

        tvGroup = (TextView) findViewById(R.id.tvGroup);
        tvGroup.setText(event.getGroup().getName());
        tvGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"gonna look for a group: " + event.getGroup().getUrlname());
                gotoGroupPage(event.getGroup().getUrlname());
            }
        });

        tvLink = (TextView) findViewById(R.id.tvLink);
        tvLink.setText(event.getLink());
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = tvLink.getText().toString();
                slideLinkTransition(url, TransitionType.SlideJava);
            }
        });

        webViewDesc = (WebView) findViewById(R.id.webViewDesc);
        webViewDesc.loadData(event.getDescription(), "text/html; charset=UTF-8;", null);
        webViewDesc.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                final String url = request.getUrl().toString();
                slideLinkTransition(url, TransitionType.SlideJava);
                return true;
            }
        });
    }

    private void slideLinkTransition(String url, TransitionType type) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(getApplicationContext(), LinkActivity.class);

        intent.putExtra(Constants.KEY_ANIM_TYPE, type);
        intent.putExtra(Constants.EXTRA_URL, url);
        startActivity(intent, options.toBundle());
    }

    private void slideGroupTransition(Group group, TransitionType type) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(getApplicationContext(), GroupActivity.class);

        intent.putExtra(Constants.KEY_ANIM_TYPE, type);
        intent.putExtra(Constants.EXTRA_GROUP, group);
        startActivity(intent, options.toBundle());
    }

    private void slideLocationTransition(Venue venue, TransitionType type) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);

        intent.putExtra(Constants.KEY_ANIM_TYPE, type);
        intent.putExtra(Constants.EXTRA_LOCATION, venue);
        startActivity(intent, options.toBundle());
    }

    private void gotoGroupPage(String urlname) {
        if(controller==null)
            initGroupController();

        controller.cancelAllRequests();
        controller.sendRequest(urlname);
    }

    private void initGroupController() {
        Log.i(TAG,"gonna look for a group: " + event.getGroup().getUrlname());

        controller = new GroupJsonController(
            new GroupJsonController.OnResponseListener() {
                    @Override
                    public void onSuccess(Group group) {
                        if(group!=null) {
                            //Log.i(TAG,"item returned: "+group.getUrlname());
                            //Toast.makeText(EventActivity.this, "Got response id: "+group.getId(), Toast.LENGTH_SHORT).show();
                            slideGroupTransition(group, TransitionType.SlideJava);
                        } else {
                            Log.i(TAG,"No item");
                            Toast.makeText(EventActivity.this, "Got no item back in response.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(EventActivity.this, "Failed: "+errorMessage, Toast.LENGTH_SHORT).show();
                    }
            });
    }
}

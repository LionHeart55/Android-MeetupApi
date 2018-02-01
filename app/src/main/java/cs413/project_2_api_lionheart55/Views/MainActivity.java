package cs413.project_2_api_lionheart55.Views;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.Model.Event;
import cs413.project_2_api_lionheart55.R;
import cs413.project_2_api_lionheart55.RestControllers.EventsJsonController;

public class MainActivity extends LocationableActivity
                        implements SearchView.OnQueryTextListener,
                                    RecyclerViewAdapter.OnClickListener
{
    private final static String TAG = "MainActivity";

    private EventsJsonController controller;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetups_layout); //activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.tvEmptyRecyclerView);
        textView.setText("Search for events using SearchView in toolbar");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(new ArrayList<Event>());
        adapter.setListener(this);

        initMeetupController();

        controller.cancelAllRequests();
        controller.sendRequest(null, mLongitude, mLatitude);
    }

    private void initMeetupController() {
        controller = new EventsJsonController(
            new EventsJsonController.OnResponseListener() {
                @Override
                public void onSuccess(List<Event> events) {
                    if(events.size() > 0) {
                        Log.i(TAG,"item returned: "+events.size());

                        Toast.makeText(MainActivity.this, "Got response, "+events.size()+" items.", Toast.LENGTH_SHORT).show();
                        textView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.invalidate();
                        adapter.updateDataSet(events);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.i(TAG,"No item");
                        recyclerView.invalidate();
                        recyclerView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Got no items back in response.");
                        //Toast.makeText(MainActivity.this, "Got no items back in response.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(String errorMessage) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Failed to retrieve enents data:\n\n"+errorMessage);
                    //Toast.makeText(MainActivity.this, "Failed: "+errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
    }

    /*public void onLocationChangedCallBack() {
        controller.cancelAllRequests();
        controller.sendRequest(null, mLongitude, mLatitude);
    }*/

    /**
     * create options from menu/menu_activity_main.xml where we have searchView as one of the menu items
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search");
        searchView.setSubmitButtonEnabled(true);
        return true;
    }

    /**
     * this method is invoked when user presses search button in soft keyboard
     * @param query query text in search view
     * @return  boolean
     *                 <p> - true  - query handled </p>
     *                 <p> - false - query not handled (returning false will collapse soft keyboard)</p>
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query.length() > 1) {
            controller.cancelAllRequests();
            controller.sendRequest(query,mLongitude,mLatitude);
            return false;
        } else {
            Toast.makeText(MainActivity.this, "Must provide more than one character", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("Must provide more than one character to search");
            return true;
        }
    }

    /**
     * this method is invoked on every key press of soft keyboard while user is typing
     * @param newText newText is updated query text on every input of user from soft keyboard
     * @return boolean
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() > 1) {
            controller.cancelAllRequests();
            controller.sendRequest(newText,mLongitude,mLatitude);
        } else if(newText.equals("")) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        return true;
    }

    /**
     * Interface Implementation
     * <p>This method will be invoked when user press anywhere on cardview</p>
     */
    @Override
    public void onCardClick(View view, Event event) {
        // If transitioning fails test card click with the Snackbar message
        //showClick(view);
        fadeTransition(view, event, BaseActivity.TransitionType.FadeJava);  //Transition Types:ExplodeJava, ExplodeXML, SlideJava, SlideXML, FadeJava, FadeXML
    }

    /**
     * Interface Implementation
     * <p>This method will be invoked when user press on poster of the event</p>
     */
    @Override
    public void onPosterClick(View view, Event event) {
        //Toast.makeText(this, event.getName() + " poster clicked", Toast.LENGTH_SHORT).show();

        // If transitioning fails test card click with the Snackbar message
        //showClick(view);
        fadeTransition(view, event, BaseActivity.TransitionType.FadeJava);
    }

    //==============================================
    private void showClick(View view, int position) {
        //int position = getAdapterPosition();
        Snackbar.make(view, "Click detected on item " + position + "\n ",   ///+ event.toString(),
                Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public void fadeTransition(View view, Event event, BaseActivity.TransitionType type) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        //Intent intent = EventActivity.newIntent(this, event);
        Intent intent = new Intent(this, EventActivity.class);

        intent.putExtra(Constants.KEY_ANIM_TYPE, type);
        intent.putExtra(Constants.EXTRA_EVENT, event);
        startActivity(intent, options.toBundle());
    }
    //===============================================
}

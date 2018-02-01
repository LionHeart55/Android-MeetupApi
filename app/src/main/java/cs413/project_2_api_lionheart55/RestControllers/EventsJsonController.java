package cs413.project_2_api_lionheart55.RestControllers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;

import java.util.List;

import cs413.project_2_api_lionheart55.App;
import cs413.project_2_api_lionheart55.Model.Event;

/*
 * Created by abhijit on 12/2/16.
 */

/**
 * <p> Provides interface between {@link android.app.Activity} and {@link com.android.volley.toolbox.Volley} </p>
 */
public class EventsJsonController {

    private final static String TAG = "MeetupJsonController";

    private OnResponseListener responseListener;

    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public EventsJsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param query query term for search
     */
    public void sendRequest(String query, String lon, String lat) {

        // Request Method
        int method = Request.Method.GET;

        //String url = "http://www.omdbapi.com/?s=" + Uri.encode(query) + "&t=movie&apikey=3a186791";
        String url = "https://api.meetup.com/find/events?key=5ea413640657b666b6f7a466e361f60&sign=true&photo-host=public&page=20&fields=group_photo";

        if(query != null) url += "&text=" + query;
        if(lon != null)  url += "&lon=" + lon;
        if(lat != null)  url += "&lat=" + lat;

        // Create new request using JsonRequest
        EventsJsonRequest request
            = new EventsJsonRequest(
                method,
                url,
                new Response.Listener<List<Event>>() {
                    @Override
                    public void onResponse(List<Event> events) {
                        Log.i(TAG,"sendRequest-success: "+events.size());
                        responseListener.onSuccess(events);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG,"sendRequest-error: "+error.getMessage());
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        Log.i(TAG,"request sent");
        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        Log.i(TAG,"at controller cancelAllRequests");
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    /**
     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
     *  <p>Object available in {@link JsonRequest} and implemented in {@link com.example.csc413_volley_template.MainActivity}</p>
     */
    public abstract static class OnResponseListener {
        public abstract void onSuccess(List<Event> events);
        public abstract void onFailure(String errorMessage);
    }

}
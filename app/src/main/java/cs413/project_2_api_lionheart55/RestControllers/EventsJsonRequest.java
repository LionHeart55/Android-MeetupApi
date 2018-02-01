package cs413.project_2_api_lionheart55.RestControllers;

/*
 * Created by abhijit on 12/2/16.
 */

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import cs413.project_2_api_lionheart55.Model.Event;

/**
 * Volley request to receive JSON as response and parse it to create list of events
 */
public class EventsJsonRequest extends Request<List<Event>> {

    private final static String TAG = "EventsJsonRequest";
    // Success listener implemented in controller
    private Response.Listener<List<Event>> successListener;

    /**
     * Class constructor
     * @param method            Request method
     * @param url               url to API
     * @param successListener   success listener
     * @param errorListener     failure listener
     */
    public EventsJsonRequest(int method,
                             String url,
                             Response.Listener<List<Event>> successListener,
                             Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<Event>> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        Log.i(TAG,jsonString);
        List<Event> events=null;
        JSONArray array;
        // Try to convert JsonString to list of events
        try {
            array = new JSONArray(jsonString);   // Convert JsonString to JSONArray
            events = Event.parseJsonArray(array);
        } catch (JSONException e) {       // in case of exception, return volley error
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to process the request"));
        }
        // return list of events
        return Response.success(events, getCacheEntry());
    }

    @Override
    protected void deliverResponse(List<Event> events) {
        successListener.onResponse(events);
    }
}

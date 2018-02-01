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
import org.json.JSONObject;

import java.util.List;

import cs413.project_2_api_lionheart55.Model.Group;
import cs413.project_2_api_lionheart55.Model.Group;

/**
 * Volley request to receive JSON as response and parse it to create list of groups
 */
public class GroupJsonRequest extends Request<Group> {

    private final static String TAG = "GroupJsonRequest";
    // Success listener implemented in controller
    private Response.Listener<Group> successListener;

    /**
     * Class constructor
     * @param method            Request method
     * @param url               url to API
     * @param successListener   success listener
     * @param errorListener     failure listener
     */
    public GroupJsonRequest(int method,
                             String url,
                             Response.Listener<Group> successListener,
                             Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<Group> parseNetworkResponse(NetworkResponse response) {
        // Convert byte[] data received in the response to String
        String jsonString = new String(response.data);
        Log.i(TAG,jsonString);
        Group group=null;
        JSONObject obj;
        // Try to convert JsonString to list of groups
        try {
            obj = new JSONObject(jsonString);   // Convert JsonString to JSONArray
            group = new Group(obj);
        } catch (JSONException e) {       // in case of exception, return volley error
            e.printStackTrace();
            // return new volley error with message
            return Response.error(new VolleyError("Failed to process the request"));
        }
        // return list of groups
        return Response.success(group, getCacheEntry());
    }

    @Override
    protected void deliverResponse(Group group) {
        successListener.onResponse(group);
    }
}

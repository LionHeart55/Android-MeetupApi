package cs413.project_2_api_lionheart55.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cs413.project_2_api_lionheart55.Constants;

/**
 * Created by shawncron on 7/17/17.
 *
 */

public class Event implements Serializable {
    String name;
    String id;
    String link;
    String visibility;
    String status;
    String description;
    int waitlist_count;
    int yes_rsvp_count;
    long duration;
    long time;
    long updated;
    long utc_offset;
    Group group;
    Venue venue;

    /**
     *
     * @return  list of movies
     * @throws JSONException
     */
    public static List<Event> parseJsonArray(JSONArray array) throws JSONException {
        List<Event> events = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"
        if(array!=null && array.length()>0){
            // Get JSONArray from JSONObject
            for(int i = 0; i < array.length(); i++){
                // Create new Movie object from each JSONObject in the JSONArray
                events.add(new Event(array.getJSONObject(i)));
            }
        }

        return events;
    }

    /**
     * <p>Class constructor</p>
     * <p>Sample Movie JSONObject</p>
     * <pre>
     * {
     *  "Title": "Batman Begins",
     *  "Year": "2005",
     *  "imdbID": "tt0372784",
     *  "Type": "movie",
     *  "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"
     * }
     * </pre>
     * @param jsonObject    {@link JSONObject} from each item in the search result
     * @throws JSONException     when parser fails to parse the given JSON
     */
    public Event(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("id")) this.setId(jsonObject.getString("id"));
        if(jsonObject.has("status")) this.setStatus(jsonObject.getString("status"));
        if(jsonObject.has("yes_rsvp_count")) this.setYesRsvpCount(jsonObject.getInt("yes_rsvp_count"));
        if(jsonObject.has("link")) this.setLink(jsonObject.getString("link"));
        if(jsonObject.has("time")) this.setTime(jsonObject.getLong("time"));
        if(jsonObject.has("description")) this.setDescription(jsonObject.getString("description"));

        if(jsonObject.has("group")) {
            JSONObject groupJSON = jsonObject.getJSONObject("group");
            this.group = new Group(groupJSON);
        }

        if(jsonObject.has("venue")) {
            JSONObject groupJSON = jsonObject.getJSONObject("venue");
            this.venue = new Venue(groupJSON);
        }
    }

    public String getDescription() {
        return (description!=null) ? description : "";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWaitlist_count() {
        return waitlist_count;
    }

    public void setWaitlist_count(int waitlist_count) {
        this.waitlist_count = waitlist_count;
    }

    public int getYesRsvpCount() {
        return yes_rsvp_count;
    }

    public void setYesRsvpCount(int yes_rsvp_count) {
        this.yes_rsvp_count = yes_rsvp_count;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getTime() {
        return time;
    }

    public String getTimeFormated() {
        Date dtTime =  new Date(this.time);
        String timeStr = Constants.sdfDate.format(dtTime);
        return timeStr;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(long utc_offset) {
        this.utc_offset = utc_offset;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Venue getVenue() { return venue; }

    public void setVenue(Venue venue) { this.venue = venue; }

    public boolean hasLocation() { return (venue!=null); }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", visibility='" + visibility + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", waitlist_count=" + waitlist_count +
                ", yes_rsvp_count=" + yes_rsvp_count +
                ", duration=" + duration +
                ", time=" + time +
                ", updated=" + updated +
                ", utc_offset=" + utc_offset +
                ", group=" + group +
                '}';
    }
}

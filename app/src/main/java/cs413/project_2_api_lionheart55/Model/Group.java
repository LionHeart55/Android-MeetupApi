package cs413.project_2_api_lionheart55.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {

    long  id;
    long created;
    String name;
    String join_mode;
    double lat;
    double lon;
    String urlname;
    String description;
    String who;
    String localized_location;
    String region;
    String groupPhoto;

    /**
     *
     * @param jsonObject    {@link JSONObject} response, received in Volley success listener
     * @return  list of movies
     * @throws JSONException
     */
    public static List<Group> parseJsonArray(JSONArray array) throws JSONException{
        List<Group> groups = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"
        if(array!=null && array.length()>0){
            // Get JSONArray from JSONObject
            for(int i = 0; i < array.length(); i++){
                // Create new Movie object from each JSONObject in the JSONArray
                groups.add(new Group(array.getJSONObject(i)));
            }
        }

        return groups;
    }

    /**
     * <p>Class constructor</p>
     * <p>Sample Group JSONObject</p>
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
    public Group(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("id")) this.setId(jsonObject.getLong("id"));
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("urlname")) this.setUrlname(jsonObject.getString("urlname"));
        if(jsonObject.has("description")) this.setDescription(jsonObject.getString("description"));

        if(jsonObject.has("join_mode")) this.setJoin_mode(jsonObject.getString("join_mode"));
        if(jsonObject.has("who")) this.setWho(jsonObject.getString("who"));
        if(jsonObject.has("region")) this.setRegion(jsonObject.getString("region"));
        if(jsonObject.has("localized_location")) this.setLocalized_location(jsonObject.getString("localized_location"));
        if(jsonObject.has("lat")) this.setLat(jsonObject.getDouble("lat"));
        if(jsonObject.has("lon")) this.setLon(jsonObject.getDouble("lon"));

        if(jsonObject.has("group_photo")) {
            JSONObject group_photo = jsonObject.getJSONObject("group_photo");
            if(group_photo.has("photo_link")) this.setGroupPhoto( group_photo.getString("photo_link") );
        } else if(jsonObject.has("photo")) {
            JSONObject group_photo = jsonObject.getJSONObject("photo");
            if(group_photo.has("photo_link")) this.setGroupPhoto( group_photo.getString("photo_link") );
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoin_mode() {
        return join_mode;
    }

    public void setJoin_mode(String join_mode) {
        this.join_mode = join_mode;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUrlname() {
        return urlname;
    }

    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getLocalized_location() {
        return localized_location;
    }

    public void setLocalized_location(String localized_location) {
        this.localized_location = localized_location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return (description.length()<30) ? description : description.substring(0,28)+"...";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(String group_photo) {
        this.groupPhoto = group_photo;
    }
}

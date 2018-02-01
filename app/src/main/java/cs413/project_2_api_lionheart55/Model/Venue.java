package cs413.project_2_api_lionheart55.Model;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

/*
    "venue": {
        "id": 3826512,
        "name": "The Crepe House",
        "lat": 37.75469207763672,
        "lon": -122.42092895507812,
        "repinned": false,
        "address_1": "1132 Valencia Street",
        "city": "San Francisco",
        "country": "us",
        "localized_country_name": "USA",
        "zip": "94110",
        "state": "CA"
      }
* */

public class Venue implements Serializable {

    long  id;
    String name;
    double lat;
    double lon;
    String address_1;
    String city;
    String country;
    String localized_country_name;
    String zip;
    String state;

    /**
     * <p>Class constructor</p>
     * <p>Sample Venue JSONObject</p>
     * @param jsonObject    {@link JSONObject} from each item in the search result
     * @throws JSONException     when parser fails to parse the given JSON
     */
    public Venue(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("id")) this.setId(jsonObject.getLong("id"));
        if(jsonObject.has("lat")) this.setLat(jsonObject.getDouble("lat"));
        if(jsonObject.has("lon")) this.setLon(jsonObject.getDouble("lon"));
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));

        if(jsonObject.has("address_1")) this.setAddress1(jsonObject.getString("address_1"));
        if(jsonObject.has("city")) this.setCity(jsonObject.getString("city"));
        if(jsonObject.has("country")) this.setCountry(jsonObject.getString("country"));
        if(jsonObject.has("zip")) this.setZip(jsonObject.getString("zip"));
        if(jsonObject.has("state")) this.setState(jsonObject.getString("state"));
        if(jsonObject.has("localized_country_name")) this.setLocalizedCountryName(jsonObject.getString("localized_country_name"));
    }

    public String getFullAddress() {
        return address_1 + "\n" + city +", " + state + ", " + zip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress1() {
        return address_1;
    }

    public void setAddress1(String address_1) {
        this.address_1 = address_1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocalizedCountryName() {
        return localized_country_name;
    }

    public void setLocalizedCountryName(String localized_country_name) {
        this.localized_country_name = localized_country_name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

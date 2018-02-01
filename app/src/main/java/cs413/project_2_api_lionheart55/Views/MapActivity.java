package cs413.project_2_api_lionheart55.Views;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.Model.Venue;
import cs413.project_2_api_lionheart55.R;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapActivity extends BaseActivity
        implements OnMapReadyCallback {

    private Venue mVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        initAnimation();

        mVenue = (Venue) getIntent().getSerializableExtra(Constants.EXTRA_LOCATION);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker of the MeetupVenue,
        // and move the map's camera to the same location.
        LatLng venueLonLat = new LatLng(mVenue.getLat(), mVenue.getLon());

        Marker marker = googleMap.addMarker(
                new MarkerOptions()
                .position(venueLonLat)
                .title(mVenue.getName())
                .snippet(mVenue.getFullAddress()));
        marker.showInfoWindow();

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(venueLonLat));

        //CameraUpdate center = CameraUpdateFactory.newLatLng(sydney);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        //googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }
}

package cs413.project_2_api_lionheart55;

import java.text.SimpleDateFormat;

/**
 * @author shawn Shocron
 * Created by shawn Shocron on 7/3/17.
 */

public class Constants {

    public static SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public static final String KEY_ANIM_TYPE = "anim_type";
    public static final String KEY_TITLE    = "anim_title";
    public static final String EXTRA_EVENT = "event_id";
    public static final String EXTRA_GROUP = "group_id";
    public static final String EXTRA_LOCATION = "location_id";
    public static final String EXTRA_URL = "link_url";

    public static final int SPLASH_TIME_OUT = 3000;   // Splash screen timer

    public static final int MY_PERMISSION_REQUEST_FINE_LOCATION = 101;
    public static final int MY_PERMISSION_REQUEST_COARSE_LOCATION = 102;

}

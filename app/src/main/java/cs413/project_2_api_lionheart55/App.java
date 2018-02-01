package cs413.project_2_api_lionheart55;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 *  Created by shocron 07/17/2017
 *  Check AndroidManifest.xml under application
 *  android:name=".app.App"
 *  will setup this class as Singleton and will be instantiated when application starts
 */
public class App extends Application {

    private static App instance;

    /**
     * Return application context anywhere in the application
     * @return  Application context
     */
    public static Context getContext(){
         return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        Log.i("APP","Created App");
    }
}

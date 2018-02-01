package cs413.project_2_api_lionheart55.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;

import java.text.SimpleDateFormat;

import cs413.project_2_api_lionheart55.Constants;
import cs413.project_2_api_lionheart55.R;

/**
 * @author shawn Shocron
 * Created by shawn Shocron on 7/3/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected enum TransitionType {
        ExplodeJava, ExplodeXML, SlideJava, SlideXML, FadeJava, FadeXML
    }

    private TransitionType mTransitionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        // For AppCompat getWindow must be called before calling super.OnCreate
        // Must be called before setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // For overlap between Exiting  MainActivity.java and Entering TransitionActivity.java
        getWindow().setAllowEnterTransitionOverlap(false);

    }

    public static Intent newIntent(Context packageContext, Class clazz, Object ... params) {
        Intent intent = new Intent(packageContext, clazz);
        //intent.putExtra(Constants.EXTRA_GROUP, group);
        return intent;
    }

    protected void initAnimation() {
        mTransitionType = (TransitionType) getIntent().getSerializableExtra(Constants.KEY_ANIM_TYPE);

        switch (mTransitionType) {
            case ExplodeJava: { // For Explode By Code
                Explode enterTransition = new Explode();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }
            case ExplodeXML: { // For Explode By XML
                Transition enterTansition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                getWindow().setEnterTransition(enterTansition);
                break;
            }
            case SlideJava: { // For Slide By Code
                Slide enterTransition = new Slide();
                enterTransition.setSlideEdge(Gravity.TOP);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                getWindow().setEnterTransition(enterTransition);
                break;
            }
            case SlideXML: { // For Slide by XML
                Transition enterTansition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                getWindow().setEnterTransition(enterTansition);
                break;
            }
            case FadeJava: { // For Fade By Code
                Fade enterTransition = new Fade();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }
            case FadeXML: { // For Fade by XML
                Transition enterTansition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
                getWindow().setEnterTransition(enterTansition);
                break;
            }
        }



    }
}

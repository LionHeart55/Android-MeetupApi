package cs413.project_2_api_lionheart55.Views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import cs413.project_2_api_lionheart55.App;
import cs413.project_2_api_lionheart55.Model.Event;
import cs413.project_2_api_lionheart55.Model.Venue;
import cs413.project_2_api_lionheart55.R;
import cs413.project_2_api_lionheart55.RestControllers.VolleySingleton;


/*
 * Created by abhijit on 11/20/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static String TAG = "RecyclerViewAdapter";

    private List<Event> eventList;
    private OnClickListener listener;

    public RecyclerViewAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card_layout, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Event event = eventList.get(position);

        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        cardViewHolder.setName(event.getName());
        cardViewHolder.setRsvpAndTime(event.getYesRsvpCount(), event.getTimeFormated());
        cardViewHolder.setGroupName(event.getGroup().getName());
        cardViewHolder.setPictureUrl(event.getGroup().getGroupPhoto());
        if(event.hasLocation())
            cardViewHolder.setAddress(event.getVenue());

        if(listener!=null) {
            cardViewHolder.bindClickListener(listener, event);
        }
    }

    /*private int calculateExtraHeight(Event event, CardViewHolder cardView) {
        int eventLines = event.getName().length()/35;
        int groupLines = event.getGroup().getName().length()/35;
        Log.i(TAG,eventLines + " - " + event.getName());
        Log.i(TAG,groupLines + " - " + event.getGroup().getName());
        int extraLines = eventLines + groupLines;
        if(extraLines>0 && event.getVenue()==null) extraLines--;
        if(extraLines>0) {

        }
    }*/

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    /**
     * Removes older data from eventList and update it.
     * Once the data is updated, notifies RecyclerViewAdapter.
     * @param modelList list of events
     */
    public void updateDataSet(List<Event> modelList) {
        this.eventList.clear();
        this.eventList.addAll(modelList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onCardClick(View view, Event event);
        void onPosterClick(View view, Event event);
    }

    /**
     *  CardViewHolder will hold the layout of the each item in the RecyclerView.
     */
    private class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView name;
        private TextView rsvp;
        private TextView groupName;
        private NetworkImageView picture;
        private ImageView ivLoc;
        private TextView tvAddress;

        /**
         * Class constructor.
         * @param view  layout of each item int the RecyclerView
         */
        CardViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.card_view);
            this.name = (TextView) view.findViewById(R.id.tvName);
            this.rsvp = (TextView) view.findViewById(R.id.tvYes);
            this.groupName = (TextView) view.findViewById(R.id.tvGroupName);
            this.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            this.picture = (NetworkImageView) view.findViewById(R.id.nivPicture);
            this.ivLoc = (ImageView) view.findViewById(R.id.ivLoc);
        }

        void setNoVenue() {
            ivLoc.setVisibility(View.GONE);
            tvAddress.setVisibility(View.GONE);
        }
        /**
         * append title text to Title:
         * @param name String of Title of event
         */
        void setName(String name) {
            this.name.setText(name);
        }

        /**
         * append year text to Release Year:
         * @param description String of year of release
         */
        void setRsvpAndTime(int rsvp, String time) {
            String rsvpYes = "Attending: " + rsvp +  "      " + time;
            this.rsvp.setText(rsvpYes);
        }

        /**
         * append year text to Release Year:
         * @param description String of year of release
         */
        void setAddress(Venue venue) {
            ivLoc.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.VISIBLE);
            this.tvAddress.setText(venue.getAddress1()+", "+venue.getCity());
        }

        /**
         * append year text to Release Year:
         * @param description String of year of release
         */
        void setGroupName(String groupName) {
            this.groupName.setText(groupName);
        }

        /**
         * Sends ImageRequest using volley using imageLoader and Cache.
         * This is pre-implemented feature of Volley to cache images for faster responses.
         * Check VolleySingleton class for more details.
         * @param imageUrl URL to poster of the Event
         */
        void setPictureUrl(String imageUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.picture.setImageUrl(imageUrl, imageLoader);
        }

        /**
         *
         * @param listener {@link OnClickListener}
         * @param event
         */
        void bindClickListener(final OnClickListener listener, final Event event){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClick(view, event);
                }
            });

            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPosterClick(view, event);
                }
            });
        }
    }
}

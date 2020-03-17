package sinhalacoder.com.wedagedara.places;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Place;
import sinhalacoder.com.wedagedara.utils.WedaGedaraAdapterNotifier;

public class PlaceFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Place, PlaceViewHolder> {

    private static final String TAG = "DoctorFbRecyclerAdapter";
    private Context mContext;
    private WedaGedaraAdapterNotifier<Place> mPlaceAdapterNotifier;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     * {@link WedaGedaraAdapterNotifier} for notifying the map markers.
     *
     * @param options FirebaseRecyclerOptions
     * @param placeAdapterNotifier WedaGedaraAdapterNotifier<Place>
     */
    PlaceFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Place> options, WedaGedaraAdapterNotifier<Place> placeAdapterNotifier) {
        super(options);
        this.mContext = context;
        mPlaceAdapterNotifier = placeAdapterNotifier;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_item, viewGroup, false);

        return new PlaceViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull PlaceViewHolder holder, int position, @NonNull Place model) {
        Log.d(TAG, "firebaseSearch: PlaceViewHolder result:" + model.getName());
        holder.setDetails(mContext, model);
        mPlaceAdapterNotifier.addMarker(model);
    }
}

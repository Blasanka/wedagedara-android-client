package sinhalacoder.com.wedagedara.places;
/*---------------------o----------o----------------------
 * Created by Blasanka on 12,January,2020
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

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
import sinhalacoder.com.wedagedara.models.Doctor;
import sinhalacoder.com.wedagedara.models.Place;

public class PlaceFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Place, PlaceViewHolder> {

    private static final String TAG = "DoctorFbRecyclerAdapter";
    private Context mContext;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options FirebaseRecyclerOptions
     */
    PlaceFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Place> options) {
        super(options);
        this.mContext = context;
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
        Log.d(TAG, "firebaseSearch: PlaceViewHolder result:" + model.getPlace_name());
        holder.setDetails(mContext, model);
    }
}

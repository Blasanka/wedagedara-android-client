package sinhalacoder.com.wedagedara.doctors;

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
import sinhalacoder.com.wedagedara.utils.WedaGedaraAdapterNotifier;

public class DoctorFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Doctor, DoctorViewHolder> {

    private static final String TAG = "DoctorFbRecyclerAdapter";
    private Context mContext;
    private WedaGedaraAdapterNotifier<Doctor> mDoctorAdapterNotifier;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options FirebaseRecyclerOptions
     * @param doctorAdapterNotifier WedaGedaraAdapterNotifier<Place>
     */
    DoctorFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Doctor> options, WedaGedaraAdapterNotifier<Doctor> doctorAdapterNotifier) {
        super(options);
        this.mContext = context;
        mDoctorAdapterNotifier = doctorAdapterNotifier;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_item, viewGroup, false);

        return new DoctorViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorViewHolder holder, int position, @NonNull Doctor model) {
        Log.d(TAG, "firebaseSearch: PlaceViewHolder result:" + model.getName());
        holder.setDetails(mContext, model);
        mDoctorAdapterNotifier.addMarker(model);
    }
}

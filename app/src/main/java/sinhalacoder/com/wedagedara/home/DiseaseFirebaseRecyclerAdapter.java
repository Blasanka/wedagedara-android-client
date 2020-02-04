package sinhalacoder.com.wedagedara.home;

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
import sinhalacoder.com.wedagedara.models.Disease;

public class DiseaseFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Disease, DiseaseViewHolder> {

    private static final String TAG = "DoctorFbRecyclerAdapter";
    private Context mContext;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options FirebaseRecyclerOptions
     */
    public DiseaseFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Disease> options) {
        super(options);
        this.mContext = context;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_disease_card, viewGroup, false);

        return new DiseaseViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position, @NonNull Disease model) {
        Log.d(TAG, "firebaseSearch: PlaceViewHolder result:" + model.getName());
        holder.setDetails(model);
    }
}

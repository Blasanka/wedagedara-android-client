package sinhalacoder.com.wedagedara.doctors;
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

public class DoctorFirebaseRecyclerAdapter extends FirebaseRecyclerAdapter<Doctor, DoctorViewHolder> {

    private static final String TAG = "DoctorFbRecyclerAdapter";
    private Context mContext;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options FirebaseRecyclerOptions
     */
    public DoctorFirebaseRecyclerAdapter(Context context, @NonNull FirebaseRecyclerOptions<Doctor> options) {
        super(options);
        this.mContext = context;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_search_list_item, viewGroup, false);

        return new DoctorViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorViewHolder holder, int position, @NonNull Doctor model) {
        Log.d(TAG, "firebaseSearch: DoctorViewHolder result:" + model.getFull_name());
        holder.setDetails(mContext, model);
    }
}

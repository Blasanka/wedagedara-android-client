package sinhalacoder.com.wedagedara.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Doctor;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;

    final Context mContext = HomeActivity.this;

    private EditText mSearchField;
    private ImageButton mSearchButton;
    private RecyclerView mResultList;
    private DatabaseReference mDoctorDatabase;

    FirebaseRecyclerAdapter<Doctor, DoctorViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initWidgets();

        initImageLoader();
        setupBottomNavigationView();
    }

    private void initWidgets() {
        mDoctorDatabase = FirebaseDatabase.getInstance().getReference("doctors");
        mSearchField = findViewById(R.id.search_field);
        mSearchButton = findViewById(R.id.search_btn);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString().toLowerCase();
                firebaseSearch(searchText);
            }
        });

        Query allQuery = mDoctorDatabase.orderByChild("full_name");
        setDataToAdapter(allQuery);
    }

    /**
     * Load images and cache them for latter use
     */
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    /*
     * Setting up custom package bottom nv
     * */
    public void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: creating nv object ...");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void firebaseSearch(String searchText) {
        Log.d(TAG, "firebaseSearch: search text:" + searchText);
        Toast.makeText(HomeActivity.this, "Searching...", Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery = mDoctorDatabase.orderByChild("search_full_name").startAt(searchText).endAt(searchText + "\uf8ff");
        setDataToAdapter(firebaseSearchQuery);
    }// View Holder Class

    private void setDataToAdapter(Query firebaseSearchQuery) {
        FirebaseRecyclerOptions<Doctor> options =
                new FirebaseRecyclerOptions.Builder<Doctor>()
                        .setQuery(firebaseSearchQuery, Doctor.class)
                        .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Doctor, DoctorViewHolder>(options) {
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
                holder.setDetails(getApplicationContext(), model.getFull_name(), model.getLocation(), model.getPhone_number(), model.getImage_url());
            }
        };
        firebaseRecyclerAdapter.startListening();
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder {
        View mView;

        DoctorViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        void setDetails(Context ctx, String doctorName, String location, String phoneNumber, String doctorImage) {
            TextView doctorNameTv = mView.findViewById(R.id.name_text);
            TextView doctorLocationTv = mView.findViewById(R.id.location_text);
            TextView phoneNumberTv = mView.findViewById(R.id.phone_number_text);
            ImageView doctorImageIv = mView.findViewById(R.id.item_photo);
            doctorNameTv.setText(doctorName);
            doctorLocationTv.setText(location);
            phoneNumberTv.setText(phoneNumber);
            UniversalImageLoader.setImage(doctorImage, doctorImageIv, null, "");
//            Glide.with(ctx).load(doctorImage).into(user_image);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.stopListening();
        }
    }
}

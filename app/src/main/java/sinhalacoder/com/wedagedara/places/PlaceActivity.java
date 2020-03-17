package sinhalacoder.com.wedagedara.places;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.doctors.GoogleMapHelper;
import sinhalacoder.com.wedagedara.models.Place;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.WedaGedaraAdapterNotifier;

import static sinhalacoder.com.wedagedara.utils.Constants.MAPVIEW_BUNDLE_KEY;

public class PlaceActivity extends AppCompatActivity {

    private static final String TAG = "PlaceActivity";
    private static final int ACTIVITY_NUM = 3;

    final Context mContext = PlaceActivity.this;

    private RecyclerView mResultList;

    FirebaseRecyclerAdapter<Place, PlaceViewHolder> firebaseRecyclerAdapter;

    private MapView mMapView;

    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private boolean mLocationPermissionGranted = false;
    GoogleMapHelper<Place> mGoogleMapHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        setupToolbar();
        setupActivityWidgets();
        mMapView = findViewById(R.id.map_view);
        getLocationPermission();
        mGoogleMapHelper = new GoogleMapHelper<>(this, mMapView, mLocationPermissionGranted);
        mGoogleMapHelper.initGoogleMap(savedInstanceState);
        initItemWidgets();

        setupBottomNavigationView();
    }

    private void setupActivityWidgets () {
        ProgressBar mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initItemWidgets() {
        DatabaseReference mPlaceDatabase = FirebaseDatabase.getInstance().getReference("places");

//        firebasePickUp();

        Query allQuery = mPlaceDatabase.orderByChild("place_name");
        setDataToAdapter(allQuery);
    }

    private void setDataToAdapter(Query firebaseSearchQuery) {
        FirebaseRecyclerOptions<Place> options =
                new FirebaseRecyclerOptions.Builder<Place>()
                        .setQuery(firebaseSearchQuery, Place.class)
                        .build();

        firebaseRecyclerAdapter = new PlaceFirebaseRecyclerAdapter(mContext, options, mGoogleMapHelper);
        firebaseRecyclerAdapter.startListening();
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    private void setupToolbar () {
        Toolbar toolbar = findViewById(R.id.basicToolbar);
        TextView title = findViewById(R.id.toolBarTitle);
        title.setText(getString(R.string.places_app_bar_title));
        setSupportActionBar(toolbar);
    }

    /*
     * Setting up custom package bottom nv
     * */
    public void setupBottomNavigationView () {
        Log.d(TAG, "setupBottomNavigationView: creating nv object ...");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: trying location permission");
        String[] permissions = {FINE_LOCATION, COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "onRequestPermissionsResult: permission denied");
                            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionGranted = true;
                    // initialize map
                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter != null){
            firebaseRecyclerAdapter.startListening();
        }
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseRecyclerAdapter != null){
            firebaseRecyclerAdapter.stopListening();
        }
        mMapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

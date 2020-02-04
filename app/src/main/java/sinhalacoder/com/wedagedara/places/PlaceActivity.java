package sinhalacoder.com.wedagedara.places;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Place;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;

import static sinhalacoder.com.wedagedara.utils.Constants.MAPVIEW_BUNDLE_KEY;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "PlaceActivity";
    private static final int ACTIVITY_NUM = 3;

    final Context mContext = PlaceActivity.this;
    private ProgressBar mProgressBar;

    private RecyclerView mResultList;
    private DatabaseReference mPlaceDatabase;

    FirebaseRecyclerAdapter<Place, PlaceViewHolder> firebaseRecyclerAdapter;

    private MapView mMapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        setupToolbar();
        setupActivityWidgets();
        initItemWidgets();
        mMapView = findViewById(R.id.map_view);
        initGoogleMap(savedInstanceState);

        setupBottomNavigationView();
    }

    private void setupActivityWidgets () {
        mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initItemWidgets() {
        mPlaceDatabase = FirebaseDatabase.getInstance().getReference("places");

//        firebasePickUp();

        Query allQuery = mPlaceDatabase.orderByChild("place_name");
        setDataToAdapter(allQuery);
    }

    private void initGoogleMap(Bundle savedInstanceState) {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
    }

    private void setDataToAdapter(Query firebaseSearchQuery) {
        FirebaseRecyclerOptions<Place> options =
                new FirebaseRecyclerOptions.Builder<Place>()
                        .setQuery(firebaseSearchQuery, Place.class)
                        .build();

        firebaseRecyclerAdapter = new PlaceFirebaseRecyclerAdapter(mContext, options);
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

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.startListening();
        }
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseRecyclerAdapter !=null){
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
    public void onMapReady(GoogleMap map) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
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

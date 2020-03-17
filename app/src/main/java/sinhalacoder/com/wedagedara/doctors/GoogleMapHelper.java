package sinhalacoder.com.wedagedara.doctors;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

import sinhalacoder.com.wedagedara.models.WedaGedaraModel;
import sinhalacoder.com.wedagedara.utils.WedaGedaraAdapterNotifier;

import static sinhalacoder.com.wedagedara.utils.Constants.MAPVIEW_BUNDLE_KEY;

public class GoogleMapHelper<T extends WedaGedaraModel> implements OnMapReadyCallback, WedaGedaraAdapterNotifier<T> {
    private GoogleMap mMap;
    private MapView mMapView;
    private Context mContext;
    private static final String TAG = "GoogleMapHelper";
    private static final float DEFAULT_ZOOM = 15f;
    private boolean mLocationPermissionGranted = false;

    public GoogleMapHelper(Context context, MapView mapView, boolean locationPermission) {
        this.mMapView = mapView;
        mContext = context;
        mLocationPermissionGranted = locationPermission;
    }

    public void addMarker(T model) {
        Log.d(TAG, "addMarkers: marker adding...");
        double latitude = model.getLatitude();
        double longitude = model.getLongitude();
        LatLng location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(location));
    }

    private void moveCamera(LatLng latLng) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + " lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the device current location");
        FusedLocationProviderClient mFussedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);

        try {
            if (mLocationPermissionGranted) {
                final Task location = mFussedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task) {
                        Log.d(TAG, "onComplete: found location!");
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()));
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
            Toast.makeText(mContext, "Current location not found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void initGoogleMap(Bundle savedInstanceState) {
        Log.d(TAG, "initGoogleMap: initializing google map");
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

    @Override
    public void onMapReady(GoogleMap map) {
        Log.d(TAG, "onMapReady: map is ready!");

        mMap = map;
        if (mLocationPermissionGranted) {
            map.setMyLocationEnabled(true);
            getDeviceLocation();
        }

    }

}

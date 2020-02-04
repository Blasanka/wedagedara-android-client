package sinhalacoder.com.wedagedara.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.doctors.DoctorActivity;
import sinhalacoder.com.wedagedara.home.DiseaseActivity;
import sinhalacoder.com.wedagedara.meds.MediActivity;
import sinhalacoder.com.wedagedara.places.PlaceActivity;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHlp";

    public static void setupBottomNavigationView (BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: setting up..");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation (final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.ic_doc:
                        Intent docIntent = new Intent(context, DoctorActivity.class); // ACTIVITY NUM 1
                        context.startActivity(docIntent);
                        break;
                    case R.id.ic_drugstore:
                        Intent mediIntent = new Intent(context, MediActivity.class); // ACTIVITY NUM 2
                        context.startActivity(mediIntent);
                        break;
                    case R.id.ic_place:
                        Intent placesIntent = new Intent(context, PlaceActivity.class); // ACTIVITY NUM 3
                        context.startActivity(placesIntent);
                        break;
                    default:
                        Intent homeIntent = new Intent(context, DiseaseActivity.class); // ACTIVITY NUM 0
                        context.startActivity(homeIntent);
                        break;
                }
                return false;
            }
        });
    }
}

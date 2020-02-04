package sinhalacoder.com.wedagedara.meds;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Medication;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;

public class MediActivity extends AppCompatActivity {

    private static final String TAG = "MediActivity";
    private static final int ACTIVITY_NUM = 2;
    private static final int NUM_GRID_COLUMN = 2;

    final Context mContext = MediActivity.this;

    DatabaseReference myRef;
    private ArrayList<Medication> mMediList;
    MediGridAdapter mMediGridAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi);
        setupActivityWidgets();
        setupToolbar();

        FirebaseDatabase mDoctorDatabase = FirebaseDatabase.getInstance();
        myRef = mDoctorDatabase.getReference("medication");
        mMediList = new ArrayList<>();

        firebaseFetchMeds();
        setupBottomNavigationView();
    }

    private void setupActivityWidgets () {
        ProgressBar mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
    }


    private void firebaseFetchMeds() {
        Log.d(TAG, "firebaseFetchMeds: started...");
        Query firebaseQuery = myRef.orderByChild("medication");
        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: snapshot: " + dataSnapshot);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    mMediList.add(snapshot.getValue(Medication.class));
                }
                setupImageGrid(mMediList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: database error" + databaseError.getMessage());
            }
        });
    }// View Holder Class

    private void setupImageGrid (ArrayList<Medication> meds) {
        GridView gridView = findViewById(R.id.gridView);

//        int gridWidth = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth / NUM_GRID_COLUMN;

        mMediGridAdapter = new MediGridAdapter(mContext, R.layout.layout_grid_imageview, "", meds);
        gridView.setAdapter(mMediGridAdapter);
    }

    private void setupToolbar () {
        Toolbar toolbar = findViewById(R.id.basicToolbar);
        TextView title = findViewById(R.id.toolBarTitle);
        title.setText(getString(R.string.medi_app_bar_title));
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
    protected void onDestroy() {
        super.onDestroy();
        if (mMediGridAdapter !=null){
            mMediGridAdapter.clear();
        }
    }
}

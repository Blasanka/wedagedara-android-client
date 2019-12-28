package sinhalacoder.com.wedagedara.places;
/*---------------------o----------o----------------------
 * Created by Blasanka on 06,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.SectionsStatePagerAdapter;

public class ViewPlaceActivity extends AppCompatActivity {

    private static final String TAG = "ViewPlaceActivity";
    private static final int ACTIVITY_NUM = 4;
    private Context mContext;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_location_activity);
        mContext = ViewPlaceActivity.this;
        Log.d(TAG, "onCreate: Started!");

        mViewPager = findViewById(R.id.container);
        mRelativeLayout = findViewById(R.id.parentRelLayout);

        // setup account options
        setupSettingsList();

        // handle navigation on back arrow press on top appbar
        navigateBack();

        // setting up bottom navigation view
        setubBottomNavigationView();
    }

    public void setViewPager (Integer fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #" + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void navigateBack() {
        ImageView backArrowBt = findViewById(R.id.accountSettingBackBt);
        backArrowBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'ViewPlaceActivity'");
                finish();
            }
        });
    }

    private void setupSettingsList () {
        Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list");
        ListView listView = findViewById(R.id.accSettingsListView);

        ArrayList<String> settingsOptions = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_expandable_list_item_1, settingsOptions);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: clicked on position #" + position);
                setViewPager(position);
            }
        });
    }
    /*
     * Setting up custom package bottom nv
     * */
    public void setubBottomNavigationView () {
        Log.d(TAG, "setupBottomNavigationView: creating nv object ...");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}

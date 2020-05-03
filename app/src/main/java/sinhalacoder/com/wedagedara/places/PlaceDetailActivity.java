package sinhalacoder.com.wedagedara.places;
/*---------------------o----------o----------------------
 * Created by Blasanka on 06,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.Objects;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.meds.MediDetailActivity;
import sinhalacoder.com.wedagedara.models.Medication;
import sinhalacoder.com.wedagedara.models.Place;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.SectionsStatePagerAdapter;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class PlaceDetailActivity extends AppCompatActivity {

    private static final String TAG = "PlaceDetailActivity";
    Context mContext = PlaceDetailActivity.this;

    ImageView backBt, itemImage;
    TextView title, duration, description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detailview);
        mContext = PlaceDetailActivity.this;
        Log.d(TAG, "onCreate: Started!");

        // initiate widgets
        init();

        setDataToWidgets();
    }

    private void setTextViewValue(TextView textView, String value) {
        Log.d(TAG, "getIncomingIntent: received incoming activity: " + value);
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        }
    }

    private void setDataToWidgets() {
        Intent intent = getIntent();
        Place place = Objects.requireNonNull(intent.getExtras()).getParcelable(getString(R.string.place));
        if (place != null) {
            setTextViewValue(title, place.getName());
            setTextViewValue(duration, place.getDuration());
            setTextViewValue(description, place.getDescription());

            UniversalImageLoader.setImage(place.getImage_url(), itemImage, null, "");
        }
    }

    private void init() {
        Log.d(TAG, "init: initializing");
        itemImage = findViewById(R.id.image);
        title = findViewById(R.id.title);
        duration = findViewById(R.id.duration);
        description = findViewById(R.id.description);
        backBt = findViewById(R.id.searchDetailBackBt);
        // handle navigation on back arrow press on top appbar
        backBtOnClickAction();
    }

    private void backBtOnClickAction() {
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

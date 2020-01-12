package sinhalacoder.com.wedagedara.home;
/*---------------------o----------o----------------------
 * Created by Blasanka on 04,January,2020
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Doctor;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class SearchDetailActivity extends AppCompatActivity {
    private static final String TAG = "SearchDetailActivity";
    Context mContext = SearchDetailActivity.this;

    ImageView backBt, searchItemImage;
    TextView title, subtitle, phoneNumber, location, description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: setting layout");
        setContentView(R.layout.activity_search_detailview);

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
        Doctor doctor = Objects.requireNonNull(intent.getExtras()).getParcelable(getString(R.string.doctor));
        if (doctor != null) {
            setTextViewValue(title, doctor.getFull_name());
            setTextViewValue(phoneNumber, doctor.getPhone_number());
            setTextViewValue(location, doctor.getLocation());
            setTextViewValue(description, doctor.getDescription());
            setTextViewValue(subtitle, doctor.getType());

            UniversalImageLoader.setImage(doctor.getImage_url(), searchItemImage, null, "");
        }
    }

    private void init() {
        Log.d(TAG, "init: initializing");
        searchItemImage = findViewById(R.id.image);
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        phoneNumber = findViewById(R.id.phone_number);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        backBt = findViewById(R.id.searchDetailBackBt);
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

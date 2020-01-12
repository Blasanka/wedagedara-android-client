package sinhalacoder.com.wedagedara.meds;
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
import sinhalacoder.com.wedagedara.models.Medication;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class MediDetailActivity extends AppCompatActivity {
    private static final String TAG = "SearchDetailActivity";
    Context mContext = MediDetailActivity.this;

    ImageView backBt, itemImage;
    TextView title, location, description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: setting layout");
        setContentView(R.layout.activity_medi_detailview);

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
        Medication medication = Objects.requireNonNull(intent.getExtras()).getParcelable(getString(R.string.medication));
        if (medication != null) {
            setTextViewValue(title, medication.getName());
            setTextViewValue(location, medication.getLocations());
            setTextViewValue(description, medication.getDescription());

            UniversalImageLoader.setImage(medication.getImage_url(), itemImage, null, "https://");
        }
    }

    private void init() {
        Log.d(TAG, "init: initializing");
        itemImage = findViewById(R.id.image);
        title = findViewById(R.id.title);
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

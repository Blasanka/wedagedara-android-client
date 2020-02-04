package sinhalacoder.com.wedagedara.home;


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
import sinhalacoder.com.wedagedara.models.Disease;

public class DiseaseDetailActivity extends AppCompatActivity {
    private static final String TAG = "DoctorDetailActivity";
    Context mContext = DiseaseDetailActivity.this;

    ImageView backBt;
    TextView name, description, cause, solution, medicationGoods, prepareMethod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: setting layout");
        setContentView(R.layout.activity_disease_detailview);

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
        Disease disease = Objects.requireNonNull(intent.getExtras()).getParcelable(getString(R.string.disease));
        if (disease != null) {
            setTextViewValue(name, disease.getName());
            setTextViewValue(cause, disease.getCause());
            setTextViewValue(solution, disease.getSolution());
            setTextViewValue(description, disease.getDescription());
            setTextViewValue(medicationGoods, disease.getMedication_goods());
            setTextViewValue(prepareMethod, disease.getPrepare_method());
        }
    }

    private void init() {
        Log.d(TAG, "init: initializing");
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        cause = findViewById(R.id.cause);
        solution = findViewById(R.id.solution);
        medicationGoods = findViewById(R.id.medication_goods);
        prepareMethod = findViewById(R.id.prepare_method);
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

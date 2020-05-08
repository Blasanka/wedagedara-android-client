package sinhalacoder.com.wedagedara;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sinhalacoder.com.wedagedara.home.DiseaseActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, DiseaseActivity.class);
        startActivity(intent);
        finish();
    }
}

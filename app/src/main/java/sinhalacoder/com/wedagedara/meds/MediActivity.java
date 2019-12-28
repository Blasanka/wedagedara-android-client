package sinhalacoder.com.wedagedara.meds;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.places.PlaceActivity;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.GridImageAdapter;

public class MediActivity extends AppCompatActivity {

    private static final String TAG = "MediActivity";
    private static final int ACTIVITY_NUM = 2;
    private static final int NUM_GRID_COLUMN = 2;

    final Context mContext = MediActivity.this;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi);
        setupActivityWidgets();
        setupToolbar();
        setupBottomNavigationView();

        tempGridSetup();
        setupBottomNavigationView();
    }

    private void setupActivityWidgets () {
        mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    private void tempGridSetup () {
        ArrayList<String> imageList = new ArrayList<>();

        imageList.add("lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg");
        imageList.add("lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg");
        imageList.add("lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s1024/Bee%252520and%252520Flower.jpg");
        imageList.add("lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg");
        imageList.add("lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg");
        imageList.add("lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg");
        imageList.add("lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg");
        imageList.add("lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg");
        imageList.add("lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg");

        ArrayList<String> mediList = new ArrayList<>();
        mediList.add("නෙල්ලි");
        mediList.add("පන්පාඩගම්");
        mediList.add("නෙල්ලි");
        mediList.add("පන්පාඩගම්");
        mediList.add("නෙල්ලි");
        mediList.add("පන්පාඩගම්");
        mediList.add("නෙල්ලි");
        mediList.add("පන්පාඩගම්");
        mediList.add("පන්පාඩගම්");
        setupImageGrid(imageList, mediList);
    }

    private void setupImageGrid (ArrayList<String> imgUrls, ArrayList<String> mediTexts) {
        GridView gridView = findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth / NUM_GRID_COLUMN;

        GridImageAdapter gridImageAdapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "https://", imgUrls, mediTexts);
        gridView.setAdapter(gridImageAdapter);
    }

    private void setupToolbar () {
        Toolbar toolbar = findViewById(R.id.profileToolbar);
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

}

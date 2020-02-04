package sinhalacoder.com.wedagedara.home;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Disease;
import sinhalacoder.com.wedagedara.utils.BottomNavigationViewHelper;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class DiseaseActivity extends AppCompatActivity {

    private static final String TAG = "DiseaseActivity";
    private static final int ACTIVITY_NUM = 0;

    final Context mContext = DiseaseActivity.this;

    private EditText mSearchField;
    private RecyclerView mResultList;
    private DatabaseReference mDiseaseDatabase;

    FirebaseRecyclerAdapter<Disease, DiseaseViewHolder> firebaseRecyclerAdapter;

    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        setupToolbar();
        initItemWidgets();

        bottomSheetDialog = new BottomSheetDialog(mContext);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.layout_keyboard, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        initImageLoader();
        setupBottomNavigationView();
    }

    /**
     * Load images and cache them for latter use
     */
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    public void onKeyPressed(View v) {
        final TextView button = (TextView) v;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String previousText = mSearchField.getText().toString();
                String typedText = previousText + button.getText().toString();
                mSearchField.setText(typedText);
                mSearchField.setSelection(typedText.length());
                Log.d(TAG, "onKeyPressed: " + typedText);
            }
        });
    }

    /** Method invoke from xml layout when delete key layout onTap
     * @param v view is coming from xml layout
     */
    public void onDeleteKeyPressed(View v) {
        final ImageView button = (ImageView) v;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String previousText = mSearchField.getText().toString();
                if (previousText.length() > 0)
                    previousText = previousText.substring(0, previousText.length()-1);
                mSearchField.setText(previousText);
                mSearchField.setSelection(previousText.length());
                Log.d(TAG, "onDeleteKeyPressed: " + previousText);
            }
        });
    }

    private void setupToolbar () {
        Toolbar toolbar = findViewById(R.id.basicToolbar);
        TextView title = findViewById(R.id.toolBarTitle);
        title.setText(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    private void initItemWidgets() {
        mDiseaseDatabase = FirebaseDatabase.getInstance().getReference("diseases");
        mSearchField = findViewById(R.id.search_field);

        mSearchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                assert im != null;
                im.hideSoftInputFromWindow(mSearchField.getWindowToken(), 0);

                if (hasFocus) bottomSheetDialog.show();
                else bottomSheetDialog.dismiss();
            }
        });

        mSearchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                assert im != null;
                im.hideSoftInputFromWindow(mSearchField.getWindowToken(), 0);
                bottomSheetDialog.show();
            }
        });

        ImageButton mSearchButton = findViewById(R.id.search_btn);
        mResultList = findViewById(R.id.result_list);

        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString().toLowerCase();
                firebaseSearch(searchText);
            }
        });

        Query allQuery = mDiseaseDatabase.orderByChild("name");
        setDataToAdapter(allQuery);
    }

    /*
     * Setting up custom package bottom nv
     * */
    public void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: creating nv object ...");
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void firebaseSearch(String searchText) {
        Log.d(TAG, "firebaseSearch: search text:" + searchText);
        Toast.makeText(DiseaseActivity.this, "සොයමින්...", Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery = mDiseaseDatabase.orderByChild("search_name").startAt(searchText).endAt(searchText + "\uf8ff");
        setDataToAdapter(firebaseSearchQuery);
    }// View Holder Class

    private void setDataToAdapter(Query firebaseSearchQuery) {
        FirebaseRecyclerOptions<Disease> options =
                new FirebaseRecyclerOptions.Builder<Disease>()
                        .setQuery(firebaseSearchQuery, Disease.class)
                        .build();

        firebaseRecyclerAdapter = new DiseaseFirebaseRecyclerAdapter(mContext, options);
        firebaseRecyclerAdapter.startListening();
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.stopListening();
        }
    }
}

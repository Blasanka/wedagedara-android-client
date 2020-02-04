package sinhalacoder.com.wedagedara.meds;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Medication;
import sinhalacoder.com.wedagedara.utils.SquareImageView;

public class MediGridAdapter extends ArrayAdapter<Medication> {

    private static final String TAG = "MediGridAdapter";
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<Medication> meds;

    MediGridAdapter(Context mContext, int layoutResource, String mAppend, ArrayList<Medication> meds) {
        super(mContext, layoutResource, meds);
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.meds = meds;
        Log.d(TAG, "MediGridAdapter: meds list length: " + meds.size());
    }

    private static class ViewHolder {
        SquareImageView image;
        ProgressBar mProgressBar;
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*
         * ViewHolder build pattern, similar to recycler view
         */
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.mProgressBar = convertView.findViewById(R.id.gridImageProgressBar);
            viewHolder.image = convertView.findViewById(R.id.gridImageView);
            viewHolder.textView = convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);

            viewHolder.textView.setText(meds.get(position).getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MediDetailActivity.class);
                    intent.putExtra(context.getString(R.string.medication), meds.get(position));
                    context.startActivity(intent);
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imgUrl = meds.get(position).getImage_url();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imgUrl, viewHolder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (viewHolder.mProgressBar != null) {
                    viewHolder.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return meds.size();
    }
}

package sinhalacoder.com.wedagedara.utils;
/*---------------------o----------o----------------------
 * Created by Blasanka on 08,December,2019
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import sinhalacoder.com.wedagedara.R;

public class GridImageAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<String> imgUrls;
    private ArrayList<String> mediTexts;

    public GridImageAdapter(Context mContext, int layoutResource, String mAppend, ArrayList<String> imgUrls, ArrayList<String> mediTexts) {
        super(mContext, layoutResource, imgUrls);
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.imgUrls = imgUrls;
        this.mediTexts = mediTexts;
    }

    private static class ViewHolder {
        SquareImageView image;
        ProgressBar mProgressBar;
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /**
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

            viewHolder.textView.setText(mediTexts.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getContext(), "You Clicked "+mediTexts.get(position), Toast.LENGTH_LONG).show();

                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imgUrl = getItem(position);
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
}

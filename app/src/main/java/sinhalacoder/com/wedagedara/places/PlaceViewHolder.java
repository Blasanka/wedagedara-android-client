package sinhalacoder.com.wedagedara.places;
/*---------------------o----------o----------------------
 * Created by Blasanka on 12,January,2020
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Place;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

class PlaceViewHolder extends RecyclerView.ViewHolder {
    private View mView;

    PlaceViewHolder(final View itemView) {
        super(itemView);
        mView = itemView;
    }

    void setDetails(Context ctx, final Place place) {
        TextView placeNameTv = mView.findViewById(R.id.name_text);
        TextView placeLocationTv = mView.findViewById(R.id.location_text);
        TextView contactTv = mView.findViewById(R.id.phone_number_text);
        ImageView placeImageIv = mView.findViewById(R.id.item_photo);
        placeNameTv.setText(place.getPlace_name());
        placeLocationTv.setText(place.getDuration());
        contactTv.setText(place.getContact());
        UniversalImageLoader.setImage(place.getImage_url(), placeImageIv, null, "");
//            Glide.with(ctx).load(doctorImage).into(user_image);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = v.getContext();
                Intent intent = new Intent(mView.getContext(), PlaceDetailActivity.class);
                intent.putExtra(ctx.getString(R.string.place), place);
                mView.getContext().startActivity(intent);
            }
        });
    }
}

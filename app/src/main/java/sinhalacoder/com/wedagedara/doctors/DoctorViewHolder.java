package sinhalacoder.com.wedagedara.doctors;
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
import sinhalacoder.com.wedagedara.home.SearchDetailActivity;
import sinhalacoder.com.wedagedara.models.Doctor;
import sinhalacoder.com.wedagedara.utils.UniversalImageLoader;

public class DoctorViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public DoctorViewHolder(final View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDetails(Context ctx, final Doctor doctor) {
        TextView doctorNameTv = mView.findViewById(R.id.name_text);
        TextView doctorLocationTv = mView.findViewById(R.id.location_text);
        TextView phoneNumberTv = mView.findViewById(R.id.phone_number_text);
        ImageView doctorImageIv = mView.findViewById(R.id.item_photo);
        doctorNameTv.setText(doctor.getFull_name());
        doctorLocationTv.setText(doctor.getLocation());
        phoneNumberTv.setText(doctor.getPhone_number());
        UniversalImageLoader.setImage(doctor.getImage_url(), doctorImageIv, null, "");
//            Glide.with(ctx).load(doctorImage).into(user_image);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = v.getContext();
                Intent intent = new Intent(mView.getContext(), SearchDetailActivity.class);
                intent.putExtra(ctx.getString(R.string.doctor), doctor);
                mView.getContext().startActivity(intent);
            }
        });
    }
}

package sinhalacoder.com.wedagedara.home;
/*---------------------o----------o----------------------
 * Created by Blasanka on 12,January,2020
 * Contact: blasanka95@gmail.com
 *-------------------------<>----------------------------*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sinhalacoder.com.wedagedara.R;
import sinhalacoder.com.wedagedara.models.Disease;

class DiseaseViewHolder extends RecyclerView.ViewHolder {
    private View mView;

    DiseaseViewHolder(final View itemView) {
        super(itemView);
        mView = itemView;
    }

    void setDetails(final Disease disease) {
        TextView diseaseNameTv = mView.findViewById(R.id.name);
        TextView diseaseDescriptionTv = mView.findViewById(R.id.description);
        TextView causeTv = mView.findViewById(R.id.cause);
//        TextView solutionTv = mView.findViewById(R.id.solution);

        diseaseNameTv.setText(disease.getName());
        if (disease.getDescription().length() > 50)
            diseaseDescriptionTv.setText(String.format("%s...", disease.getDescription().substring(0, 50)));
        else diseaseDescriptionTv.setText(disease.getDescription());
        if (disease.getCause().length() > 50)
            causeTv.setText(String.format("%s...", disease.getCause().substring(0, 50)));
        else causeTv.setText(String.format("%s...", disease.getCause()));
//        if (disease.getSolution().length() > 40)
//            solutionTv.setText(String.format("%s...", disease.getSolution().substring(0, 40)));
//        else solutionTv.setText(disease.getSolution());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = v.getContext();
                Intent intent = new Intent(mView.getContext(), DiseaseDetailActivity.class);
                intent.putExtra(ctx.getString(R.string.disease), disease);
                mView.getContext().startActivity(intent);
            }
        });
    }
}

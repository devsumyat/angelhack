package mm.com.sumyat.nurse.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mm.com.sumyat.nurse.R;
import mm.com.sumyat.nurse.helper.LocaleHelper;
import mm.com.sumyat.nurse.ui.model.Patient;

/**
 * Created by sumyathtun on 6/30/18.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private ArrayList<Patient> patientArrayList;
    private Context mContext;
    private boolean newPatient;
    private onCheckInbuttonClick onCheckInbuttonClick;

    public PatientAdapter(Context context, boolean newPatient,ArrayList<Patient> patientArrayList) {
        this.mContext = context;
        this.newPatient = newPatient;
        this.patientArrayList = patientArrayList;
    }

    public void setOnCheckInbuttonClick(PatientAdapter.onCheckInbuttonClick onCheckInbuttonClick) {
        this.onCheckInbuttonClick = onCheckInbuttonClick;
    }

    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PatientAdapter.ViewHolder holder, int position) {
        bindData(patientArrayList.get(position), holder);
    }

    private void bindData(Patient patient, ViewHolder holder) {
        holder.txtName.setText(patient.getName());
        holder.txtAge.setText(patient.getAge());
        holder.txtDisease.setText(patient.getDisease());
        holder.layout.setOnClickListener(view -> {
            onCheckInbuttonClick.onClick();
        });
    }

    @Override
    public int getItemCount() {
        return patientArrayList.size();
    }

    public void addAll(ArrayList<Patient> patients) {
        this.patientArrayList.addAll(patients);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout layout;
        private CircleImageView imgProfile;
        private TextView txtName, txtAge, txtDisease;
        private ImageView imgNew;

        Typeface zwType = Typeface.createFromAsset(mContext.getApplicationContext().getAssets(), "fonts/zawgyi.ttf");

        Typeface myType = Typeface.createFromAsset(mContext.getApplicationContext().getAssets(), "fonts/myanmar.ttf");

        public ViewHolder(View itemView) {
            super(itemView);
            this.setIsRecyclable(false);

            String languageCode = LocaleHelper.getLanguage(mContext);

            layout = itemView.findViewById(R.id.layout);
            imgProfile = itemView.findViewById(R.id.img_profile);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAge = itemView.findViewById(R.id.txt_age);
            txtDisease = itemView.findViewById(R.id.txt_disease);
            imgNew = itemView.findViewById(R.id.img_new);

            if (languageCode.equals("MY")) {
                txtName.setTypeface(myType);
                txtAge.setTypeface(myType);
                txtDisease.setTypeface(myType);
            } else {
                txtName.setTypeface(zwType);
                txtAge.setTypeface(zwType);
                txtDisease.setTypeface(zwType);
            }

            if(newPatient)
                imgNew.setVisibility(View.VISIBLE);
            else
                imgNew.setVisibility(View.GONE);
        }
    }

    public interface onCheckInbuttonClick {
        void onClick();
    }
}

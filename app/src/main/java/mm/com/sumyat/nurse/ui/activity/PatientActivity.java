package mm.com.sumyat.nurse.ui.activity;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.myatminsoe.mdetect.MDetect;
import mm.com.sumyat.nurse.R;
import mm.com.sumyat.nurse.helper.LocaleHelper;
import mm.com.sumyat.nurse.ui.adapter.PatientAdapter;
import mm.com.sumyat.nurse.ui.model.Patient;
import mm.com.sumyat.nurse.ui.presenterImpl.PatientPresenterImpl;
import mm.com.sumyat.nurse.ui.view.PatientView;

public class PatientActivity extends AppCompatActivity implements PatientView, PatientAdapter.onCheckInbuttonClick {

    private RecyclerView newPatientRView, oldPatientRView;
    private PatientAdapter historyAdapter;
    private PatientAdapter newAdapter;
    private PatientPresenterImpl presenter;
    private Button changeTimeBtn;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        initLanguage();
        initReference();
        initPresenter();
    }

    void initLanguage() {
        Log.w("Home", "uni:" + MDetect.INSTANCE.isUnicode());
        if (MDetect.INSTANCE.isUnicode()) {
            LocaleHelper.setLocale(this, "my", "MY");
        } else {
            LocaleHelper.setLocale(this, "my", "ZG");
        }
    }

    void initReference() {
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>NUX</font>"));

        newPatientRView = findViewById(R.id.rview_new_list);
        oldPatientRView = findViewById(R.id.rview_old_list);
        changeTimeBtn = findViewById(R.id.btn_change_time);

        newPatientRView.setLayoutManager(new LinearLayoutManager(this));
        newAdapter = new PatientAdapter(this, true, new ArrayList<>());
        newPatientRView.setAdapter(newAdapter);
        newAdapter.setOnCheckInbuttonClick(this);

        oldPatientRView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new PatientAdapter(this, false, new ArrayList<>());
        oldPatientRView.setAdapter(historyAdapter);
        historyAdapter.setOnCheckInbuttonClick(this);

        changeTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeChage();
            }
        });
    }

    private void initPresenter() {
        presenter = new PatientPresenterImpl(this, this);
        presenter.init();
    }

    @Override
    public void showPatientHistory(ArrayList<Patient> patients) {
        historyAdapter.addAll(patients);
    }

    @Override
    public void showNewPatient(ArrayList<Patient> patients) {
        newAdapter.addAll(patients);
    }

    @Override
    public void showTimeChage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.time_change_dialog, null);

        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();

        TextView btnCancel = dialogView.findViewById(R.id.txt_cancel);
        TextView btnYes = dialogView.findViewById(R.id.txt_update);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    @Override
    public void showDescription() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.patient_desc_dialog, null);

        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.show();

        TextView btnCancel = dialogView.findViewById(R.id.txt_disagree);
        TextView btnYes = dialogView.findViewById(R.id.txt_agree);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    @Override
    public void onClick() {
        showDescription();
    }
}

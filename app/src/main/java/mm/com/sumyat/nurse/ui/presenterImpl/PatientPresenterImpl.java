package mm.com.sumyat.nurse.ui.presenterImpl;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mm.com.sumyat.nurse.BuildConfig;
import mm.com.sumyat.nurse.helper.LocaleHelper;
import mm.com.sumyat.nurse.ui.model.Patient;
import mm.com.sumyat.nurse.ui.presenter.PatientPresenter;
import mm.com.sumyat.nurse.ui.view.PatientView;

/**
 * Created by sumyathtun on 6/30/18.
 */

public class PatientPresenterImpl implements PatientPresenter {

    private Context context;
    private final PatientView view;

    public PatientPresenterImpl(Context context, PatientView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void init() {
        view.showNewPatient(getNewPatientList());
        view.showPatientHistory(getHistoryPatientList());
    }

    @Override
    public ArrayList<Patient> getHistoryPatientList() {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(LoadHistoryListFromJSON());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Patient patient = new Patient(obj.getString("id"),
                        obj.getString("name"),
                        obj.getString("age"),
                        obj.getString("disease"));
                list.add(patient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String LoadHistoryListFromJSON() {
        String json = null;
        try {
            String languageCode = LocaleHelper.getLanguage(context);
            InputStream is;
            if (languageCode.equals("MY"))
                is = context.getAssets().open("old_patient_mm.json");
            else
                is = context.getAssets().open("old_patient_zw.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public ArrayList<Patient> getNewPatientList() {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(loadNewListFromJSON());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Patient patient = new Patient(obj.getString("id"),
                        obj.getString("name"),
                        obj.getString("age"),
                        obj.getString("disease"));
                list.add(patient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String loadNewListFromJSON() {
        String json = null;
        try {
            String languageCode = LocaleHelper.getLanguage(context);
            InputStream is;
            if (languageCode.equals("MY"))
                is = context.getAssets().open("new_patient_mm.json");
            else
                is = context.getAssets().open("new_patient_zw.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

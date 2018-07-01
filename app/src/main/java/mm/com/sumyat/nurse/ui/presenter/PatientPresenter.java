package mm.com.sumyat.nurse.ui.presenter;

import java.util.ArrayList;

import mm.com.sumyat.nurse.ui.model.Patient;

/**
 * Created by sumyathtun on 6/30/18.
 */

public interface PatientPresenter {

    void init();

    ArrayList<Patient> getHistoryPatientList();

    String LoadHistoryListFromJSON();

    ArrayList<Patient> getNewPatientList();

    String loadNewListFromJSON();

}

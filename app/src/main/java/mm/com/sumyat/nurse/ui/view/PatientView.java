package mm.com.sumyat.nurse.ui.view;

import java.util.ArrayList;

import mm.com.sumyat.nurse.ui.model.Patient;

/**
 * Created by sumyathtun on 6/30/18.
 */

public interface PatientView {

    void showPatientHistory(ArrayList<Patient> patients);

    void showNewPatient(ArrayList<Patient> patients);

    void showTimeChage();

    void showDescription();
}

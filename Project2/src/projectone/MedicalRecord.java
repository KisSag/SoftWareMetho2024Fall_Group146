package projectone;
import projecttwo.*;

/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 */
public class MedicalRecord {
    public static final int LENGTH_INITIALLIST = 4;
    public static final int LENGTH_INCREASECAPACITY = 4;

    private Patient[] patients;
    private int size; //number of patient objects in the array

    /**
     * Default constructor initializing the patient array and size
     */
    public MedicalRecord() {
        patients = new Patient[LENGTH_INITIALLIST];
        size = 0;
    }

    /**
     * Add an appointment with its patient into the record
     * @param appointment The appointment to be added
     */
    public void add(Appointment appointment) {
        //check if patient is exist
        int TargetIndex = getIndexPatient(appointment.getPatient().getProfile());
        if (TargetIndex == -1) {
            generateNewPatient(appointment);
        } else {
            patients[TargetIndex].addFinishedAppointment(appointment);
        }
    }

    /**
     * Add a whole appointment list
     * @param appointmentList The list of appointments to be added
     */
    public void add(List<Appointment> appointmentList) {
        for (Appointment app : appointmentList) {
            add(app);
        }
    }

    /**
     * Read a valid appointment and extract patient info into the record
     * @param appointment The appointment to be processed
     */
    private void generateNewPatient(Appointment appointment) {
        for (int i = 0; i < patients.length; i += 1) {
            if (patients[i] == null) {
                patients[i] = new Patient(appointment);
                size++; //Increment the size when a new patient is added
                return;
            }
        }
        grow();
        patients[patients.length - LENGTH_INCREASECAPACITY] = new Patient(appointment);
        size++; //Increment the size when the array grows and a new patient is added
    }

    /**
     * Get the number of patients in the record
     * @return The number of patients
     */
    public int countPatientNumber() {
        return size;
    }

    /**
     * Check if a patient is in the record
     * @param pa The profile of the patient to check
     * @return The index of the target patient if exists, -1 otherwise
     */
    public int getIndexPatient(Profile pa) {
        for (int i = 0; i < patients.length; i += 1) {
            if (patients[i] != null && patients[i].getProfile().equals(pa)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return a patient object by index
     * @param index The index of the patient
     * @return The patient object at the given index
     */
    public Patient getPatient_byIndex(int index) {
        return patients[index];
    }

    /**
     * Get the list of patients in the record
     * @return The array of patients
     */
    public Patient[] getPatientsList() {
        return patients;
    }

    /**
     * Helper method to increase the capacity of the patient array by 4
     */
    private void grow() {
        Patient[] new_patients = new Patient[patients.length + LENGTH_INCREASECAPACITY];
        for (int i = 0; i < patients.length; i += 1) {
            new_patients[i] = patients[i];
        }
        patients = new_patients;
    }
}


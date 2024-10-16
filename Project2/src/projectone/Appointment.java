package projectone;
import projecttwo.*;

/**
 * {@code @author:} Tianxiang Huang
 */
public class Appointment implements Comparable<Appointment>{
    private Date date; 
    private Timeslot timeslot; 
    private Person patient; 
    private Person provider;

    /**
     * Default Constructor
     */
    public Appointment() {
        date = null;
        timeslot = null;
        patient = null;
        provider = null;
    }

    /**
     * Full constructor
     * @param da Date of the appointment
     * @param ti Timeslot of the appointment
     * @param pa Patient for the appointment
     * @param pr Provider for the appointment
     */
    public Appointment(Date da, Timeslot ti, Person pa, Person pr) {
        date = new Date(da);
        timeslot = ti;
        patient = pa;
        provider = pr;
    }

    /**
     * Deep copy of an appointment
     * @param TargetAppontment The appointment to copy
     */
    public Appointment(Appointment TargetAppontment) {
        date = new Date(TargetAppontment.getDate());
        timeslot = new Timeslot(TargetAppontment.getTimeslot());
        patient = new Patient((Patient) TargetAppontment.getPatient());
        provider = TargetAppontment.getProvider();
    }

    /**
     * Get date
     * @return Date of the appointment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get timeslot
     * @return Timeslot of the appointment
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Get patient info
     * @return Patient of the appointment
     */
    public Person getPatient() {
        return patient;
    }

    /**
     * Get provider info
     * @return Provider of the appointment
     */
    public Person getProvider() {
        return provider;
    }

    /**
     * Given an appointment, check if it conflicts with the current appointment by provider
     * @param appointment The appointment to check for conflict
     * @return true if conflict, false otherwise
     */
    public boolean checkAppointmentConflict_Provider(Appointment appointment) {
        if (getProvider().equals(appointment.getProvider()) &&
                getDate().equals(appointment.getDate()) &&
                getTimeslot().equals(appointment.getTimeslot())) {
            return true;
        }
        return false;
    }

    /**
     * Given an appointment, check if it conflicts with the current appointment by patient
     * @param appointment The appointment to check for conflict
     * @return true if conflict, false otherwise
     */
    public boolean checkAppointmentConflict_Patient(Appointment appointment) {
        if (getPatient().equals(appointment.getPatient()) &&
                getDate().equals(appointment.getDate()) &&
                getTimeslot().equals(appointment.getTimeslot())) {
            return true;
        }
        return false;
    }

    /**
     * Check if the given condition satisfies the current appointment
     * @param patient The patient to check
     * @param date The date to check
     * @param timeslot The timeslot to check
     * @return true if all conditions satisfy, false otherwise
     */
    public boolean findAppointment(Patient patient, Date date, Timeslot timeslot) {
        if (getPatient().equals(patient) &&
                getDate().equals(date) &&
                getTimeslot().equals(timeslot)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        result += date.toString() + " ";
        result.toLowerCase();
        result += timeslot.toString() + " ";
        result += patient.toString();
        result += provider.toString();
        return result;
    }

    @Override
    public int compareTo(Appointment TargetAppontment) {
        return this.toString().compareTo(TargetAppontment.toString());
    }

    @Override
    public boolean equals(Object other) {
        Appointment TargetAppontment = (Appointment) other;
        if (this.date.equals(TargetAppontment.getDate()) &&
                this.timeslot.equals(TargetAppontment.getTimeslot()) &&
                this.patient.equals(TargetAppontment.getPatient()) &&
                this.provider.equals(TargetAppontment.getProvider())) {
            return true;
        }
        return false;
    }

}

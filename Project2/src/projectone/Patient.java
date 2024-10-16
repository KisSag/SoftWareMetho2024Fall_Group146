package projectone;
import projecttwo.Person;
/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 */
public class Patient extends Person {
    private Visit visits;

    /**
     * Default constructor initializing profile and visits to null.
     */
    public Patient() {
        profile = null;
        visits = null;
    }

    /**
     * Constructor initializing a Patient from an Appointment.
     * @param appointment The appointment to initialize the patient with
     */
    public Patient(Appointment appointment) {
        profile = appointment.getPatient().getProfile();
        visits = new Visit(appointment);
    }

    /**
     * Constructor initializing a Patient from a Profile.
     * @param pro The profile to initialize the patient with
     */
    public Patient(Profile pro) {
        profile = pro;
        visits = null;
    }

    /**
     * Deep copy constructor for a Patient.
     * @param pa The patient to be copied
     */
    public Patient(Patient pa) {
        this.profile = new Profile(pa.getProfile());
        visits = pa.visits;
    }

    /**
     * Iterator through the visit linked list and get the total charge.
     * @return int, as sum of all charges
     */
    public int charge() {
        Visit VisitRunner = visits;
        int FinialCharge = 0;
        while (VisitRunner != null) {
            Provider provider = (Provider) VisitRunner.getCurrentAppointment().getProvider();
            FinialCharge += provider.getPrice();
            VisitRunner = VisitRunner.getNextFinishedVisit();
        }
        return FinialCharge;
    }

    /**
     * Input an appointment, and add the appointment as a finished visit.
     * @param appointment The appointment to be added as finished
     */
    public void addFinishedAppointment(Appointment appointment) {
        Visit NewFinishedVisit = new Visit(appointment);
        NewFinishedVisit.assignNextFinishedVisit(visits);
        visits = NewFinishedVisit;
    }

    /**
     * Get the head node of visits linked list.
     * @return the head node of visits
     */
    public Visit getFirstFinihedVisit() {
        return visits;
    }

    /**
     * Get profile of the Patient.
     * @return the profile class
     */
    @Override
    public Profile getProfile() {
        return profile;
    }

    /**
     * Compare the current Patient with another Person.
     * @param TargetPerson The person to compare to
     * @return result of the comparison
     */
    @Override
    public int compareTo(Person TargetPerson) {
        return this.profile.compareTo(TargetPerson.getProfile());
    }

    /**
     * Check if the current Patient is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        //if not even a same class
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Patient TargetPatient = (Patient) other;
        return this.profile.equals(TargetPatient.getProfile());
    }

    /**
     * Returns a string representation of the Patient.
     * @return string representation of the Patient
     */
    @Override
    public String toString() {
        String result = "";
        result += profile.getFirstName() + " " + profile.getLastName() + " " + profile.getBirthDay().toString() + " ";
        //result += "[Amount due: $" + Float.toString(charge()) + "]";
        return result;
    }
}


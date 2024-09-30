package projectone;
import projecttwo.Person;
/**
 * Last Modified: 9/30/2024
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 * Test: not yet
 * 
 */
public class Patient extends Person { 
    private Visit visits;

    //constructor
    public Patient(){
        profile = null;
        visits = null;
    }
    public Patient(Appointment appointment){
        profile = appointment.getPatient().getProfile();
        visits = new Visit(appointment);
    }
    public Patient(Profile pro){
        profile = pro;
        visits = null;
    }
    /**
     * deep cp of patient
     * @param pa
     */
    public Patient(Patient pa){
        this.profile = new Profile(pa.getProfile());
        visits = pa.visits;
    }
    
    /** 
     * iterator through the visit linklist and get all the charge
     * @return int, as sum of all charge
     */
    public int charge() {
        Visit VisitRunner = visits;
        int FinialCharge = 0;

        while(VisitRunner != null){
            Provider provider = (Provider)VisitRunner.getCurrentAppointment().getProvider();
            FinialCharge += provider.rate();
            VisitRunner = VisitRunner.getNextFinishedVisit();
        }

        return FinialCharge;
    } //traverse the linked list to compute the charge

    /**
     * input a appointment, and add the appointment as visited
     * @param appointment
     */
    public void addFinishedAppointment(Appointment appointment){
        Visit NewFinishedVisit = new Visit(appointment);
        NewFinishedVisit.assignNextFinishedVisit(visits);
        visits = NewFinishedVisit;
    }

    /**
     * get the head of node of visits linklist
     * @return the head of node of visit
     */
    public Visit getFirstFinihedVisit(){
        return visits;
    }

    /**
     * get profile of Patient
     * @return return the profile class
     */
    @Override public Profile getProfile(){
        return profile;
    }
    
    @Override public int compareTo(Person TargetPerson){
        return this.profile.compareTo(TargetPerson.getProfile());
    }

    @Override public boolean equals(Object other){

        //if not even a same class
        if(other.getClass() != this.getClass()){
            return false;
        }

        Patient TargetPatient = (Patient)other;
        return this.profile.equals(TargetPatient.getProfile());
    }

    @Override public String toString(){
        String result = "";
        result += profile.getFirstName() + " " + profile.getLastName() + " " + profile.getBirthDay().toString() + " ";
        result += "[Amount due: $" + Float.toString(charge()) + "]";
        return result;
    }

    /* this is test for project 1
    public static void main(String[] args) {
        Profile profile1 = new Profile("Jayden", "Hsu", new Date(1, 1, 1990));
        Appointment appointment1 = new Appointment(new Date(11, 21, 2024), Timeslot.SLOT1, profile1, Provider.PATEL);
        Patient patient = new Patient(appointment1);

        //Test case 1: Get profile of patient
        System.out.println("Test case 1: " + patient.getProfile().getFirstName()); //Expected: Jayden

        //Test case 2: Add finished appointment
        patient.addFinishedAppointment(appointment1);
        System.out.println("Test case 2: First finished visit: " + patient.getFirstFinihedVisit().getCurrentAppointment().getDate()); //Expected: 11/21/2024
    }
    */
    
}
